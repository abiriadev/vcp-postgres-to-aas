package io.aipim.vcppostgrestoaas;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.eclipse.digitaltwin.aas4j.v3.model.DataTypeDefXSD;

@AllArgsConstructor
@Getter
public class AasPropValue {

	private DataTypeDefXSD type;
	private Optional<String> value;

	static DataTypeDefXSD jsonTypeToXsd(Object value) {
		if (
			value instanceof String
		) return DataTypeDefXSD.STRING; else if (
			value instanceof Integer
		) return DataTypeDefXSD.INTEGER; else if (
			value instanceof Float
		) return DataTypeDefXSD.FLOAT; else if (
			value instanceof Double
		) return DataTypeDefXSD.DOUBLE; else if (
			value instanceof BigDecimal
		) return DataTypeDefXSD.DECIMAL; else if (
			value instanceof Boolean
		) return DataTypeDefXSD.BOOLEAN; else if (
			value.equals(null)
		) return null; else throw new IllegalArgumentException(); // probably array or nested object
	}

	AasPropValue(Object value) {
		this(
			jsonTypeToXsd(value),
			Optional.ofNullable(
				value == null ? null : value.toString()
			)
		);
	}

	AasPropValue(DataTypeDefXSD type) {
		this(type, Optional.empty());
	}
}
