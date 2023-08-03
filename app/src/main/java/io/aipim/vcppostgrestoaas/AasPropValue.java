package io.aipim.vcppostgrestoaas;

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
		if (value instanceof String) {
			return DataTypeDefXSD.STRING;
		} else if (value instanceof Integer) {
			return DataTypeDefXSD.INTEGER;
		} else if (value instanceof Boolean) {
			return DataTypeDefXSD.BOOLEAN;
		} else if (value.equals(null)) {
			return null;
		} else { // probably array or nested object
			throw new IllegalArgumentException();
		}
	}

	AasPropValue(Object value) {
		this(
			jsonTypeToXsd(value),
			Optional.of(value.toString())
		);
	}

	AasPropValue(DataTypeDefXSD type) {
		this(type, Optional.empty());
	}
}
