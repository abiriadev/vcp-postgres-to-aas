package io.aipim.vcppostgrestoaas.utils;

import org.eclipse.digitaltwin.aas4j.v3.model.DataTypeDefXSD;

public class ToXsd {

	public static DataTypeDefXSD jsonSchemaToXsd(
		String type
	) {
		if (
			type.equals("CODE")
		) return DataTypeDefXSD.SHORT; else if (
			type.equals("DOUBLE")
		) return DataTypeDefXSD.DOUBLE; else if (
			type.equals("LONG")
		) return DataTypeDefXSD.LONG; else if (
			type.equals("STRING")
		) return DataTypeDefXSD.STRING; else throw new IllegalArgumentException(
			String.format(
				"Unknown type from json schema: %s",
				type
			)
		);
	}
}
