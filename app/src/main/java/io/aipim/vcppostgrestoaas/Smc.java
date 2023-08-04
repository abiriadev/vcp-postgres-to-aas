package io.aipim.vcppostgrestoaas;

import com.google.common.collect.Streams;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.eclipse.digitaltwin.aas4j.v3.model.AASSubmodelElements;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultProperty;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSubmodelElementList;

@RequiredArgsConstructor
public class Smc implements Serializable {

	private final String name;
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
		return new DefaultSubmodelElementList.Builder()
			.idShort(name)
			.orderRelevant(false)
			.typeValueListElement(
				AASSubmodelElements.PROPERTY
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
