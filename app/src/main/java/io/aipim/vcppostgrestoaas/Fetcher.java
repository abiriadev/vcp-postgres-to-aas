package io.aipim.vcppostgrestoaas;

import io.aipim.vcppostgrestoaas.utils.QueryBuilder;
import io.aipim.vcppostgrestoaas.utils.QueryBuilderConfig;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Fetcher<T> {

	// JDBC URL, username, and password of PostgreSQL server
	@NonNull
	String url;

	@NonNull
	QueryBuilderConfig queryBuilderConfig;

	@NonNull
	private ResponseParser<T> responseParser;

	public T fetch() throws SQLException, IOException {
		// Query to be executed
		var query = new QueryBuilder(queryBuilderConfig)
			.build();

		try (
			Connection connection = DriverManager.getConnection(
				url
			);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query)
		) {
			while (rs.next()) responseParser.consumeRecord(
				rs
			);
			return responseParser.digest();
		}
	}
}
