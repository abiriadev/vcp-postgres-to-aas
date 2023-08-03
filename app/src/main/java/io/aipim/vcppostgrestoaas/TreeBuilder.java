package io.aipim.vcppostgrestoaas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.digitaltwin.aas4j.v3.model.Environment;

@Slf4j
public class TreeBuilder
	implements ResponseParser<Environment> {

	private List<Smc> lll = new ArrayList<Smc>();
	private HashMap<String, Smc> pool = new HashMap<>();

	@Override
	public Environment parseResponse(ResultSet rs)
		throws SQLException {
		while (rs.next()) {
			var path = rs.getString("path"); // @varchar(100)
			var name = rs.getString("name"); // @varchar(50)
			var attribute = rs.getString("attribute"); // @json?
			var attributeSchema = rs.getString(
				"attribute_schema"
			); // @json?
			var leaf = rs.getBoolean("leaf"); // @boolean

			var smc = new Smc(name);

			new JsonParser(attributeSchema)
				.parse(attribute)
				.entrySet()
				.stream()
				.forEach(ent ->
					smc.put(ent.getKey(), ent.getValue())
				);

			smc.put("leaf", leaf);

			pool.put(path, smc);

			log.info(path);

			var prt = PathParser.parent(path);
			if (
				prt.isPresent() &&
				PathParser.depth(prt.get()) > 0
			) {
				pool.get(prt.get()).insert(smc);
			} else {
				lll.add(smc);
			}
		}

		return Env.build(
			lll
				.stream()
				.map(l -> l.toAas())
				.collect(Collectors.toList())
		);
	}
}
