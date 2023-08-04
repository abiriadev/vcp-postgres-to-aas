package io.aipim.vcppostgrestoaas;

import java.util.List;
import org.eclipse.digitaltwin.aas4j.v3.model.AssetKind;
import org.eclipse.digitaltwin.aas4j.v3.model.Environment;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultAssetAdministrationShell;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultAssetInformation;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultEnvironment;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultReference;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSpecificAssetID;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSubmodel;

public class Env {

	public static Environment build(
		List<SubmodelElement> subel
	) {
		return new DefaultEnvironment.Builder()
			.assetAdministrationShells(
				new DefaultAssetAdministrationShell.Builder()
					.id("https://aipim.io/DataCatalog")
					.assetInformation(
						new DefaultAssetInformation.Builder()
							.assetKind(AssetKind.INSTANCE)
							.globalAssetID(
								"https://kogas.or.kr"
							)
							.specificAssetIds(
								List.of(
									new DefaultSpecificAssetID.Builder()
										.name(
											"spec astid 1"
										)
										.value("v1")
										.build()
								)
							)
							.build()
					)
					.submodels(
						new DefaultReference.Builder()
							.keys(List.of())
							.build()
					)
					.build()
			)
			.submodels(
				new DefaultSubmodel.Builder()
					.submodelElements(subel)
					.build()
			)
			.build();
	}
}
