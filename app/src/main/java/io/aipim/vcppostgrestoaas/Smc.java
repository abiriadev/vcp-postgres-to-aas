package io.aipim.vcppostgrestoaas;

import com.google.common.collect.Streams;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.eclipse.digitaltwin.aas4j.v3.model.KeyTypes;
import org.eclipse.digitaltwin.aas4j.v3.model.ReferenceTypes;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultKey;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultLangStringNameType;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultProperty;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultReference;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSubmodelElementCollection;

@RequiredArgsConstructor
public class Smc implements Serializable {

	private final String name;
	private final String category;
	private HashMap<String, AasPropValue> props =
		new HashMap<>();
	private ArrayList<Smc> children = new ArrayList<>();

	public void put(String key, AasPropValue value) {
		props.put(key, value);
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
				Streams
					.concat(
						props
							.entrySet()
							.stream()
							.map(ent ->
								new DefaultProperty.Builder()
									.idShort(ent.getKey())
									.valueType(
										ent
											.getValue()
											.getType()
									)
									.value(
										ent
											.getValue()
											.getValue()
											.orElse("null")
									)
									.build()
							),
						children
							.stream()
							.map(c -> c.toAas())
					)
					.collect(Collectors.toList())
			)
			.build();
	}
}
