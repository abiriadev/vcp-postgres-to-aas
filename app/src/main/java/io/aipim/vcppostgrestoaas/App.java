package io.aipim.vcppostgrestoaas;

import io.github.cdimascio.dotenv.Dotenv;
import org.eclipse.digitaltwin.aas4j.v3.dataformat.json.JsonSerializer;

public class App {

	public static void main(String[] args) {
		var dotenv = Dotenv.load();

		try {
			System.out.println(
				new JsonSerializer()
					.write(
						new Fetcher<>(
							ConnectionUrlBuilder.build(
								dotenv
							),
							new TreeBuilder()
						)
							.fetch()
					)
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
