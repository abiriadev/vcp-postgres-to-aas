package io.aipim.vcppostgrestoaas;

import io.aipim.vcppostgrestoaas.utils.ToXsd;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.digitaltwin.aas4j.v3.model.DataTypeDefXSD;
import org.eclipse.digitaltwin.aas4j.v3.model.EmbeddedDataSpecification;
import org.eclipse.digitaltwin.aas4j.v3.model.Extension;
import org.eclipse.digitaltwin.aas4j.v3.model.LangStringNameType;
import org.eclipse.digitaltwin.aas4j.v3.model.LangStringTextType;
import org.eclipse.digitaltwin.aas4j.v3.model.Property;
import org.eclipse.digitaltwin.aas4j.v3.model.Qualifier;
import org.eclipse.digitaltwin.aas4j.v3.model.Reference;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultLangStringNameType;

@Getter
@Setter
public class XsdProp implements Property {

	private List<EmbeddedDataSpecification> embeddedDataSpecifications;
	private Reference semanticID;
	private List<Reference> supplementalSemanticIds;
	private String category;
	private List<LangStringTextType> description;
	private List<LangStringNameType> displayName;
	private String idShort;
	private List<Extension> extensions;
	private List<Qualifier> qualifiers;
	private Reference valueID;
	private DataTypeDefXSD valueType;

	private Optional<String> value = Optional.empty();

	public String getValue() {
		return value.orElse(null);
	}

	public void setValue(String value) {
		this.value = Optional.ofNullable(value);
	}

	XsdProp(String name, String type, Object value) {
		this(name, ToXsd.jsonSchemaToXsd(type), value);
	}

	// NOTE: value is possibly null
	XsdProp(
		String name,
		DataTypeDefXSD type,
		Object value
	) {
		idShort = name;
		displayName =
			List.of(
				new DefaultLangStringNameType.Builder()
					.language("ko")
					.text(name)
					.build()
			);
		valueType = type;
		this.value =
			Optional
				.ofNullable(value)
				.map(s -> s.toString());
	}
}
