package io.aipim.vcppostgrestoaas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.eclipse.digitaltwin.aas4j.v3.model.KeyTypes;
import org.eclipse.digitaltwin.aas4j.v3.model.Property;
import org.eclipse.digitaltwin.aas4j.v3.model.ReferenceTypes;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultKey;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultLangStringNameType;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultReference;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSubmodelElementCollection;

@RequiredArgsConstructor
public class Smc implements Serializable {

	private final String name;
	private final String category;
	private List<Property> props = new ArrayList<>();

	private ArrayList<Smc> children = new ArrayList<>();

	public void put(Property prop) {
		props.add(prop);
	}

	public void insert(Smc smc) {
		children.add(smc);
	}

	public SubmodelElement toAas() {
		return new DefaultSubmodelElementCollection.Builder()
			.idShort(name)
			.displayName(
				new DefaultLangStringNameType.Builder()
					.language("ko")
					.text(name)
					.build()
			)
			.semanticID(
				new DefaultReference.Builder()
					.type(ReferenceTypes.EXTERNAL_REFERENCE)
					.keys(
						new DefaultKey.Builder()
							.type(KeyTypes.GLOBAL_REFERENCE)
							.value(
								String.format(
									"https://aipim.io/spec/category/%s",
									category
								)
							)
							.build()
					)
					.build()
			)
			.value(
				Stream
					.concat(
						props.stream(),
						children
							.stream()
							.map(c -> c.toAas())
					)
					.toList()
			)
			.build();
	}
}
