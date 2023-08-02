package io.aipim.vcppostgrestoaas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class App {

	public static void main(String[] args) {
		try {
			// JDBC URL, username, and password of PostgreSQL server
			String url =
				"jdbc:postgresql://192.168.0.33:5437/mod";
			String user = "sa";
			String password = "1";

			// Query to be executed
			var query = new QueryBuilder(
				new QueryBuilderConfig.QueryBuilderConfigBuilder()
					.template("fetch.sql")
					.group_id(3)
					.limit(Optional.of(5))
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
				while (rs.next()) {
					var treeId = rs.getString("tree_id"); // @uuid
					var dit = rs.getString("dit"); // @tsvector
					var path = rs.getString("path"); // @varchar(100)
					var treeName = rs.getString(
						"tree_name"
					); // @varchar(50)
					var treeDescription = rs.getString(
						"tree_description"
					); // @varchar(100)?
					var alias = rs.getString("alias"); // @varchar(50)?
					var active = rs.getBoolean("active"); // @boolean
					var category = rs.getString("category"); // @verchar(50)
					var attribute = rs.getString(
						"attribute"
					); // @json?
					var treeCreatedBy = rs.getString(
						"tree_created_by"
					); // @varchar?
					var treeCreatedAt = rs.getTimestamp(
						"tree_created_at"
					); // @timestamp?
					var treeModifiedBy = rs.getString(
						"tree_modified_by"
					); // @varchar?
					var treeModifiedAt = rs.getTimestamp(
						"tree_modified_at"
					); // @timestamp?
					var groupId = rs.getLong("group_id"); // @bigint?
					var level = rs.getInt("level"); // @integer
					var categoryName = rs.getString(
						"category_name"
					); // @varchar(50)
					var categoryDescription = rs.getString(
						"category_description"
					); // @varchar(100)?
					var attributeSchema = rs.getString(
						"attribute_schema"
					); // @json?
					var categoryCreatedBy = rs.getString(
						"category_created_by"
					); // @varchar?
					var categoryCreatedAt = rs.getTimestamp(
						"category_created_at"
					); // @timestamp?
					var categoryModifiedBy = rs.getString(
						"category_modified_by"
					); // @varchar?
					var categoryModifiedAt =
						rs.getTimestamp(
							"category_modified_at"
						); // @timestamp?
					var leaf = rs.getBoolean("leaf"); // @boolean
					var categoryId = rs.getString(
						"category_id"
					); // @uuid

					System.out.println(
						treeId +
						dit +
						path +
						treeName +
						treeDescription +
						alias +
						active +
						category +
						attribute +
						treeCreatedBy +
						treeCreatedAt +
						treeModifiedBy +
						treeModifiedAt +
						groupId +
						level +
						categoryName +
						categoryDescription +
						attributeSchema +
						categoryCreatedBy +
						categoryCreatedAt +
						categoryModifiedBy +
						categoryModifiedAt +
						leaf +
						categoryId
					);
				}
			} catch (SQLException e) {
				System.out.println(
					"Error connecting to the database"
				);
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
