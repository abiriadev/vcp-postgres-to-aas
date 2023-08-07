package io.aipim.vcppostgrestoaas.utils;

import io.aipim.vcppostgrestoaas.App;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class QueryBuilder {

	String template;
	QueryBuilderConfig qbc;

	public QueryBuilder(QueryBuilderConfig qbc)
		throws IOException {
		this.qbc = qbc;
		template =
			IOUtils.toString(
				App.class.getClassLoader()
					.getResourceAsStream(qbc.template),
				StandardCharsets.UTF_8
			);
	}

	public String build() {
		if (qbc.limit == null) System.out.println("nul");

		return String.format(
			template,
			qbc.group_id,
			qbc.limit.orElse(10000000)
		);
	}
}
