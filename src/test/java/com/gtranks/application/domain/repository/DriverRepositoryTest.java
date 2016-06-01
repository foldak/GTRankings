package com.gtranks.application.domain.repository;

import static org.junit.Assert.assertEquals;

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
@ContextConfiguration(
		{
		"classpath:DispatcherServlet-context.xml",
		"classpath:application-context.xml"
})
public class DriverRepositoryTest {
	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private DriverRepository driverRepository;
	@Before
	public void Setup(){
		MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void repoTest(){
		driverRepository.getOne(1L);
		assertEquals(Driver.class, driverRepository.getOne(1L).getClass());
	}
}
