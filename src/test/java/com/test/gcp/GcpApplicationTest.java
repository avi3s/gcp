package com.test.gcp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootTest
@TestConfiguration
@Import(GcpApplication.class)
class GcpApplicationTest {

	@Test
	void testMain() {
		GcpApplication.main(new String[] {});
	}
}