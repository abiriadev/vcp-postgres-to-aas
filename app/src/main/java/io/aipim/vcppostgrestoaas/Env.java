package io.aipim.vcppostgrestoaas;

import java.util.List;
import org.eclipse.digitaltwin.aas4j.v3.model.Environment;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultEnvironment;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSubmodel;

public class Env {

	public static Environment build(
		List<SubmodelElement> subel
	) {
		return new DefaultEnvironment.Builder()
			.submodels(
				new DefaultSubmodel.Builder()
					.submodelElements(subel)
					.build()
			)
			.build();
	}
}
