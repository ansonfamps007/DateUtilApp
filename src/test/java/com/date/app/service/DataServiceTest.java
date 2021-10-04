package com.date.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.date.app.exception.ValidationException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
/**
 * Tests Date service.
 */
class DataServiceTest {

	@Autowired
	private DateService dateService;

	private final String actualDate = "31/01/2016";
	private final String expectedDate = "01/02/2016";
	private final String invalidDate = "01/50/2016";
	private final int days = 1;

	/**
     * Test add date.
     *
     * @throws Exception the exception.
     */
	@Test
	void addDateTest() throws Exception {

		String result = dateService
				         .addDate(actualDate, days);
		assertEquals(expectedDate, result);
	}
	
	/**
     * Test add date parse exception.
     *
     * @throws Exception the exception.
     */
	@Test
	void addDateParseExceptionTest() throws Exception {
		Assertions.assertThrows(ValidationException.class, 
				() -> {
            	   dateService
            	   .addDate(invalidDate, days);
		  });
	}

}
