package io.aipim.vcppostgrestoaas;

import org.eclipse.digitaltwin.aas4j.v3.model.Environment;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultEnvironment;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSubmodel;

public class Env {

	public Environment toAas(SubmodelElement subel) {
		return new DefaultEnvironment.Builder()
			.submodels(
				new DefaultSubmodel.Builder()
					.submodelElements(subel)
					.build()
			)
			.build();
	}
}
