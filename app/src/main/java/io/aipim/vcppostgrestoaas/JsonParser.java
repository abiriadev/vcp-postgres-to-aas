package io.aipim.vcppostgrestoaas;

import io.aipim.vcppostgrestoaas.AasPropValue;
import java.util.HashMap;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;

@AllArgsConstructor
public class JsonParser {

	JSONObject schema;

	HashMap<String, AasPropValue> parse(String rawJson)
		throws JSONException {
		return new JSONObject(rawJson)
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
