package io.aipim.vcppostgrestoaas;

public class App {

	public static void main(String[] args) {
		try {
			new Fetcher(
				"jdbc:postgresql://192.168.0.33:5437/mod",
				"sa",
				"1"
			)
				.fetch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
