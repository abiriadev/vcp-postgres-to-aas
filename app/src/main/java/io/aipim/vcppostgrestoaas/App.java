package io.aipim.vcppostgrestoaas;

import io.aipim.vcppostgrestoaas.utils.ConnectionUrlBuilder;
import io.aipim.vcppostgrestoaas.utils.QueryBuilderConfig;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.Optional;
import org.eclipse.digitaltwin.aas4j.v3.dataformat.json.JsonSerializer;

public class App {

	public static void main(String[] args) {
		var dotenv = Dotenv
			.configure()
			.ignoreIfMissing()
			.load();

		try {
			System.out.println(
				new JsonSerializer()
					.write(
						new Fetcher<>(
							ConnectionUrlBuilder.build(
								dotenv
							),
							QueryBuilderConfig
								.builder()
								.template("fetch.sql")
								.group_id(3)
								.limit(Optional.of(100))
								.build(),
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
