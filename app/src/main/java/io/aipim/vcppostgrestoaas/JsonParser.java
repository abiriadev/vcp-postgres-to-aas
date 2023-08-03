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

	JsonParser(String schema) throws JSONException {
		if (schema != null) this.schema =
			Optional.of(new JSONObject(schema));
	}

	HashMap<String, AasPropValue> parse(String rawJson)
		throws JSONException {
		return rawJson == null
			? new HashMap<>()
			: new JSONObject(rawJson)
				.keySet()
				.stream()
				.collect(
					Collectors.toMap(
						k -> k,
						v -> new AasPropValue(v),
						(p, n) -> n,
						HashMap::new
					)
				);
	}
}
