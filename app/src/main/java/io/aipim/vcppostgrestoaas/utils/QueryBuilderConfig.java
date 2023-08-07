package io.aipim.vcppostgrestoaas.utils;

import java.util.Optional;
import lombok.Builder;

@Builder
public class QueryBuilderConfig {

	public String template;
	public int group_id;

	@Builder.Default
	public Optional<Integer> limit = Optional.empty();
}
