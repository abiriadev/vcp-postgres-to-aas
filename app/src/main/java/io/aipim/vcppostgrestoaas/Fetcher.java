package io.aipim.vcppostgrestoaas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.digitaltwin.aas4j.v3.model.Environment;

@RequiredArgsConstructor
public class Fetcher {

	// JDBC URL, username, and password of PostgreSQL server
	@NonNull
	String url;

	@NonNull
	String user;

	@NonNull
	String password;

	@NonNull
	private TreeBuilder treeBuilder;

	public Environment fetch()
		throws SQLException, IOException {
		// Query to be executed
		var query = new QueryBuilder(
			new QueryBuilderConfig.QueryBuilderConfigBuilder()
				.template("fetch.sql")
				.group_id(3)
				.limit(Optional.of(30))
				.build()
		)
			.build();

		try (
			Connection connection = DriverManager.getConnection(
				url,
				user,
				password
			);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query)
		) {
			return treeBuilder.buildTree(rs);
		}
	}
}
