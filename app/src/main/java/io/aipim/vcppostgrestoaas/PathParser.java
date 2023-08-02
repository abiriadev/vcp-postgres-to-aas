package io.aipim.vcppostgrestoaas;

import java.util.Optional;

public class PathParser {

	static String sep = "／";

	public static Optional<String> parent(String path) {
		var lio = path.lastIndexOf(sep);

		return lio == 0
			? Optional.empty()
			: Optional.of(path.substring(0, lio));
	}
}
