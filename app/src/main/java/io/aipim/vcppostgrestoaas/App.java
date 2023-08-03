package io.aipim.vcppostgrestoaas;

import org.eclipse.digitaltwin.aas4j.v3.dataformat.json.JsonSerializer;

public class App {

	public static void main(String[] args) {
		try {
			System.out.println(
				new JsonSerializer()
					.write(
						new Fetcher(
							"jdbc:postgresql://192.168.0.33:5437/mod",
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
