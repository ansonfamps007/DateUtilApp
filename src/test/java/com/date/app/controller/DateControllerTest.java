package com.date.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.date.app.constants.ApiConstants;
import com.date.app.service.DateService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

/**
 * Tests Date controller.
 */
class DateControllerTest {

	@MockBean
	private DateService dateService;

	@Autowired
	private MockMvc mockMvc;

	private final String actualDate = "31/01/2016";
	private final String expectedDate = "01/02/2016";
	private final String days = "1";
	private final String inValidDays = "-1";

	/**
     * Test add date api.
     *
     * @throws Exception the exception.
     */
	@Test
	void addDateTest() throws Exception {

		when(dateService.addDate(actualDate, 1)).thenReturn(expectedDate);
		MvcResult result = mockMvc.perform(get("/v1/api/date/add")
				            .param("date", actualDate)
				            .param("days", days))
				            .andExpect(status()
				            .isOk())
				            .andReturn();
		assertEquals("New Date: " + expectedDate, result
			                .getResponse()
			                .getContentAsString());
	}

	/**
     * Test add date api day with -ve value.
     *
     * @throws Exception the exception.
     */
	@Test
	void addDateInvalidDaysExceptionTest() throws Exception {

		MvcResult result = mockMvc.perform(get("/v1/api/date/add")
				           .param("date", actualDate)
				           .param("days", inValidDays))
				           .andExpect(status()
				           .isBadRequest())
				           .andReturn();
		assertEquals(ApiConstants.INVALID_DAYS, result
				           .getResponse()
				           .getContentAsString());
	}

	/**
     * Test add date api null days.
     *
     * @throws Exception the exception.
     */
	@Test
	void addDateNullDaysParamExceptionTest() throws Exception {

		              mockMvc
		               .perform(get("/v1/api/date/add")
		               .param("date", actualDate)
		               .param("days", ""))
				       .andExpect(status()
				       .isBadRequest())
				       .andReturn();
		assertTrue(true);
	}
	
	/**
     * Test add date api null date.
     *
     * @throws Exception the exception.
     */
	@Test
	void addDateNullDateParamExceptionTest() throws Exception {

		              mockMvc
		               .perform(get("/v1/api/date/add")
		               .param("date", "")
		               .param("days", "1"))
				       .andExpect(status()
				       .isBadRequest())
				       .andReturn();
		assertTrue(true);
	}
	
	/**
     * Test add date api missing parameters.
     *
     * @throws Exception the exception.
     */
	@Test
	void addDateMissingParamExceptionTest() throws Exception {

		              mockMvc
		               .perform(get("/v1/api/date/add")
		               .param("date", actualDate))
				       .andExpect(status()
				       .isBadRequest())
				       .andReturn();
		assertTrue(true);
	}
}
