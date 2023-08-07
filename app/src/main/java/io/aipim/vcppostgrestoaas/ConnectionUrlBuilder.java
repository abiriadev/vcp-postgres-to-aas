package io.aipim.vcppostgrestoaas;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUrlBuilder {

	static String build(Dotenv dotenv) throws Exception {
		var database = dotenv.get("PGDATABASE");
		if (database == null) throw new Exception(
			"PGDATABASE environment variable not set"
		);

		var host = dotenv.get("PGHOST");
		if (host == null) throw new Exception(
			"PGHOST environment variable not set"
		);

		var port = dotenv.get("PGPORT", "5432");

		var user = dotenv.get("PGUSER");
		if (user == null) throw new Exception(
			"PGUSER environment variable not set"
		);

		var password = dotenv.get("PGPASSWORD");
		if (password == null) throw new Exception(
			"PGPASSWORD environment variable not set"
		);

		return String.format(
			"jdbc:postgresql://%s:%s/%s?user=%s&password=%s",
			host,
			port,
			database,
			user,
			password
		);
	}
}
