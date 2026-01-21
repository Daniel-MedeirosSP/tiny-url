package com.tyni.url;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UrlApplication.class)
class UrlApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void mainMethodTest() {
		UrlApplication.main(new String[] {});
	}

}
