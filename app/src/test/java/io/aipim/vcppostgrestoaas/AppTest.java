/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.aipim.vcppostgrestoaas;

import static org.junit.Assert.*;

import io.aipim.vcppostgrestoaas.utils.PathParser;
import java.util.Optional;
import org.junit.Test;

public class AppTest {

	static char sep = '／';

	@Test
	public void parentOfShouldBeNull() {
		assertEquals(
			PathParser.parent("／"),
			Optional.empty()
		);
	}

	@Test
	public void passingStringWithoutSeparatorShouldPanic() {
		assertThrows(
			IllegalArgumentException.class,
			() -> PathParser.parent("/")
		);
	}

	@Test
	public void shouldGetParentPath() {
		assertEquals(
			PathParser.parent("／abc"),
			Optional.of("／")
		);

		assertEquals(
			PathParser.parent("／abc／def"),
			Optional.of("／abc")
		);
	}

	@Test
	public void depthOfRootShouldBeZero() {
		assertEquals(PathParser.depth("／"), 0);
	}

	@Test
	public void depthOfPathWithoutSeparatorShouldNotBeDefined() {
		assertThrows(
			IllegalArgumentException.class,
			() -> PathParser.depth("/")
		);
	}

	@Test
	public void getDepth() {
		assertEquals(PathParser.depth("／abc"), 1);
		assertEquals(PathParser.depth("／abc／def"), 2);
		assertEquals(
			PathParser.depth("／abc／def／ghi"),
			3
		);
	}
}
