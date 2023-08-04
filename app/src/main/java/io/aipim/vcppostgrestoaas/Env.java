package io.aipim.vcppostgrestoaas;

import java.util.List;
import org.eclipse.digitaltwin.aas4j.v3.model.AssetKind;
import org.eclipse.digitaltwin.aas4j.v3.model.Environment;
import org.eclipse.digitaltwin.aas4j.v3.model.KeyTypes;
import org.eclipse.digitaltwin.aas4j.v3.model.ReferenceTypes;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultAssetAdministrationShell;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultAssetInformation;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultEnvironment;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultKey;
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
					.id("https://aipim.io/DataCatalog/1")
					.idShort("DataCatalog1")
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
											"specificAssetId"
										)
										.value("id1")
										.build()
								)
							)
							.build()
					)
					.submodels(
						new DefaultReference.Builder()
							.type(
								ReferenceTypes.MODEL_REFERENCE
							)
							.keys(
								List.of(
									new DefaultKey.Builder()
										.type(
											KeyTypes.SUBMODEL
										)
										.value(
											"https://aipim.io/DataCatalog/1/ModelEntry"
										)
										.build()
								)
							)
							.build()
					)
					.build()
			)
			.submodels(
				new DefaultSubmodel.Builder()
					.id(
						"https://aipim.io/DataCatalog/1/ModelEntry"
					)
					.idShort("ModelEntry")
					.submodelElements(subel)
					.build()
			)
			.build();
	}
}
