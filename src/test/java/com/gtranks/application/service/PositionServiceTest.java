package com.gtranks.application.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gtranks.application.domain.Driver;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:DispatcherServlet-context.xml", "classpath:application-context.xml" })
public class PositionServiceTest {
	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private PositionService positionService;

	@Before
	public void Setup() {
		MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void serviceTest() {
		List<Map<Driver, Integer>> results = positionService.getChampResults(1);
		System.out.println(results);
		assertNotNull(results);
	}
}
