package io.aipim.vcppostgrestoaas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.eclipse.digitaltwin.aas4j.v3.model.AASSubmodelElements;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultProperty;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSubmodelElementList;

public class Smc {

	private HashMap<String, Object> props;
	private ArrayList<Smc> children = new ArrayList<>();

	public void put(String key, Object value) {
		props.put(key, value);
	}

	public void insert(Smc smc) {
		children.add(smc);
	}

	public SubmodelElement toAas() {
		return new DefaultSubmodelElementList.Builder()
			.orderRelevant(false)
			.typeValueListElement(
				AASSubmodelElements.PROPERTY
			)
			.value(
				children
					.stream()
					.map(c -> c.toAas())
					.collect(Collectors.toList())
			)
			.value(
				props
					.entrySet()
					.stream()
					.map(ntry ->
						new DefaultProperty.Builder()
							.idShort(ntry.getKey())
							.value(
								ntry.getValue().toString()
							)
							.build()
					)
					.collect(Collectors.toList())
			)
			.build();
	}
}
