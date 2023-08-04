package io.aipim.vcppostgrestoaas;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResponseParser<T> {
	void consumeRecord(ResultSet rs) throws SQLException;

	T digest();
}
