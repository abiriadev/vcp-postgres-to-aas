package io.aipim.vcppostgrestoaas;

import io.aipim.vcppostgrestoaas.utils.ToXsd;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.eclipse.digitaltwin.aas4j.v3.model.DataTypeDefXSD;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

	Optional<HashMap<String, DataTypeDefXSD>> schema =
		Optional.empty();

	// NOTE: schema is possibly null
	JsonParser(String schema) throws JSONException {
		this.schema =
			Optional
				.ofNullable(schema)
				.map(s -> new JSONObject(s)) // NOTE: all schema is JSONObject
				.map(j ->
					j
						.keySet()
						.stream()
						.collect(
							Collectors.toMap(
								k -> k,
								k ->
									ToXsd.jsonSchemaToXsd( // NOTE: data_type is guaranteed to be valid JSON schema type
										j
											.getJSONObject(
												k
											)
											.getString(
												"data_type"
											) // NOTE: these path are guaranteed to be exist
									),
								(p, n) -> n,
								HashMap::new
							)
						)
				);
	}

	List<XsdProp> parse(String rawJson)
		throws JSONException {
		if (
			rawJson == null ||
			rawJson.equals("null") ||
			schema.isEmpty() // NOTE: it is guaranteed for attributes to be null when their schema is null
		) return List.of();

		// NOTE: rawJson is now guaranteed not to be null
		var rawAttr = new JSONObject(rawJson);

		// NOTE: schema is now guaranteed not to be null
		return schema
			.get()
			.entrySet()
			.stream()
			.map(ent ->
				new XsdProp(
					ent.getKey(),
					ent.getValue(),
					rawAttr.get(ent.getKey())
				)
			)
			.collect(Collectors.toList());
	}
}
