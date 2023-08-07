package io.aipim.vcppostgrestoaas;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;

@NoArgsConstructor
public class JsonParser {

	Optional<JSONObject> schema = Optional.empty();

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

	JsonParser(String schema) throws JSONException {
		if (schema != null) this.schema =
			Optional.of(new JSONObject(schema));
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
