package io.aipim.vcppostgrestoaas;

import io.aipim.vcppostgrestoaas.utils.ToXsd;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import org.eclipse.digitaltwin.aas4j.v3.model.DataTypeDefXSD;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

	Optional<HashMap<String, DataTypeDefXSD>> schema =
		Optional.empty();

	private static HashMap<String, Object> jsonObjectToHashMap(
		JSONObject jsonObject
	) {
		return jsonObject
			.keySet()
			.stream()
			.collect(
				Collectors.toMap(
					k -> k,
					v -> jsonObject.get(v),
					(p, n) -> n,
					HashMap::new
				)
			);
	}

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

	HashMap<String, AasPropValue> parse(String rawJson)
		throws JSONException {
		return rawJson == null || rawJson.equals("null")
			? new HashMap<>()
			: jsonObjectToHashMap(new JSONObject(rawJson))
				.entrySet()
				.stream()
				.collect(
					Collectors.toMap(
						k -> k.getKey(),
						v -> new AasPropValue(v.getValue()),
						(p, n) -> n,
						HashMap::new
					)
				);
	}
}
