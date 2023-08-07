package io.aipim.vcppostgrestoaas.utils;

import java.util.Optional;

public class PathParser {

	static char sep = '／';

	public static long depth(String path) {
		var c = path
			.chars()
			.filter(ch -> ch == sep)
			.count();
		if (c == 0) throw new IllegalArgumentException();
		return c == 1 && path.equals("" + sep) ? 0 : c;
	}

	public static Optional<String> parent(String path) {
		var depth = depth(path);

		return depth == 0
			? Optional.empty()
			: depth == 1
				? Optional.of("" + sep)
				: Optional.of(
					path.substring(0, path.lastIndexOf(sep))
				);
	}
}
