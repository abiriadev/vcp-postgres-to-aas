package io.aipim.vcppostgrestoaas;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.digitaltwin.aas4j.v3.dataformat.json.JsonSerializer;

public class App {

	public static void main(String[] args) {
		try {
			System.out.println(
				new JsonSerializer()
					.write(
						new Fetcher(
							"jdbc:postgresql://localhost:5432/mod",
							"sa",
							"1"
						)
							.fetch()
					)
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
