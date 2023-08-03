package io.aipim.vcppostgrestoaas;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResponseParser<T> {
	T parseResponse(ResultSet rs) throws SQLException;
}
