package io.aipim.vcppostgrestoaas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.eclipse.digitaltwin.aas4j.v3.model.Environment;

@RequiredArgsConstructor
public class Fetcher {

	// JDBC URL, username, and password of PostgreSQL server
	@NonNull
	String url;

	@NonNull
	String user;

	@NonNull
	String password;

	private HashMap<String, Smc> pool = new HashMap<>();

	public Environment fetch()
		throws SQLException, IOException {
		// Query to be executed
		var query = new QueryBuilder(
			new QueryBuilderConfig.QueryBuilderConfigBuilder()
				.template("fetch.sql")
				.group_id(3)
				.limit(Optional.of(30))
				.build()
		)
			.build();

		var lll = new ArrayList<Smc>();

		try (
			Connection connection = DriverManager.getConnection(
				url,
				user,
				password
			);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query)
		) {
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
						smc.put(
							ent.getKey(),
							ent.getValue()
						)
					);

				smc.put("leaf", leaf);

				pool.put(path, smc);

				System.out.println(path);

				var prt = PathParser.parent(path);
				if (prt.isPresent()) {
					pool.get(prt.get()).insert(smc);
				} else {
					lll.add(smc);
				}
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
