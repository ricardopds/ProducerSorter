package br.com.movie.services;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import br.com.movie.controller.ProducerController;
import br.com.movie.model.Producer;


@TestMethodOrder(OrderAnnotation.class)
class ProducerServicesTest {
	
	ProducerController producerController;
	private static ProducerServices producerServices = new ProducerServices();
	
	private static final String _USER_1 = "JUnit";
	private static final Long _YEAR_P1 = 2017L;
	private static final Long _YEAR_P2 = 2018L;
	private static final Long _YEAR_F1 = 2019L;
	private static final Long _INTERVAL_1 = 2L;
	private static final Long _INTERVAL_2 = 1L;
	private static final String _MAX = "max";
	private static final String _MIN = "min";
	
	@BeforeEach
	void SetUp() throws Exception{
	}
	

	@Test
	@Order(10)
	void testListProducerWithMinimumAndMaximumTwoPrizesInterval() throws Exception {
		Map<String, List<Producer>> toTestMap = producerServices.listProducerWithMinimumAndMaximumTwoPrizesInterval();
		assertNotNull(toTestMap);
		assertTrue(toTestMap.get(_MIN) != null);
		assertTrue(toTestMap.get(_MAX) != null);
	}
	
	@Test
	@Order(20)
	void testCreateProducer() {
		Producer producerTest = new Producer();
		producerTest.setProducer(_USER_1);
		producerTest.setPreviousWin(_YEAR_P1);
		producerTest.setFollowingWin(_YEAR_F1);
		producerTest.setInterval(_INTERVAL_1);
		producerTest.setMultipleWins(true);
		Response testResponse = producerServices.createProducer(producerTest);
		assertTrue(testResponse.getStatusInfo() == Status.CREATED);
	}

	@Test
	@Order(30)
	void testGetProducer() {
		List<Producer> producersTest = producerServices.getProducer(_USER_1);
		assertNotNull(producersTest);
		assertTrue(!producersTest.isEmpty());
	}

	@Test
	@Order(40)
	void testUpdateProducer() {
		Producer producerTest = new Producer();
		producerTest.setProducer(_USER_1);
		producerTest.setPreviousWin(_YEAR_P2);
		producerTest.setFollowingWin(_YEAR_F1);
		producerTest.setInterval(_INTERVAL_2);
		producerTest.setMultipleWins(true);
		producerServices.updateProducer(_USER_1, producerTest);
		List <Producer> producersTestUpdated = producerServices.getProducer(_USER_1);
		
		for(Producer toTestUpdateProducer: producersTestUpdated) {
			assertTrue(producerTest.getProducer() == toTestUpdateProducer.getProducer());
			assertTrue(producerTest.getPreviousWin()== toTestUpdateProducer.getPreviousWin());
			assertTrue(producerTest.getFollowingWin()== toTestUpdateProducer.getFollowingWin());
			assertTrue(producerTest.getInterval()== toTestUpdateProducer.getInterval());
			assertTrue(producerTest.isMultipleWins()== toTestUpdateProducer.isMultipleWins());
		}
		
		assertTrue(true);
	}

	@Test
	@Order(50)
	void testDeleteProducer() {
		producerServices.deleteProducer(_USER_1);
		try {
			@SuppressWarnings("unused")
			List<Producer> toTestDeleteProducer = producerServices.getProducer(_USER_1);
			assertTrue(false);
		} catch (WebApplicationException e) {
			assertTrue(true);
		} 
	}

}
