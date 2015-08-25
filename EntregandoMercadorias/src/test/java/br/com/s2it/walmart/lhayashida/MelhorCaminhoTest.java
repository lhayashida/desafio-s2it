package br.com.s2it.walmart.lhayashida;

import java.math.BigDecimal;

import org.junit.Test;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

public class MelhorCaminhoTest {

	private MelhorCaminho melhorCaminho = new MelhorCaminho();
	
	@Test
	public void calculaMelhorCaminhoOrigemParameterTest() {				
		Response response = melhorCaminho.calculaMelhorCaminho(null, "B", BigDecimal.TEN, new BigDecimal(2.5));
		Assert.assertEquals(400, response.getStatus());	
	}
	
	@Test
	public void calculaMelhorCaminhoDestinoParameterTest() {				
		Response response = melhorCaminho.calculaMelhorCaminho("A", "", BigDecimal.TEN, new BigDecimal(2.5));
		Assert.assertEquals(400, response.getStatus());	
	}
	
	@Test
	public void calculaMelhorCaminhoAutonomiaParameterTest() {				
		Response response = melhorCaminho.calculaMelhorCaminho("A", "B", null, new BigDecimal(2.5));
		Assert.assertEquals(400, response.getStatus());	
	}
	
	@Test
	public void calculaMelhorCaminhoCustoParameterTest() {				
		Response response = melhorCaminho.calculaMelhorCaminho("A", "B", BigDecimal.TEN, null);
		Assert.assertEquals(400, response.getStatus());	
	}
	
	@Test
	public void calculaMelhorCaminhoAutonomiaZeroParameterTest() {				
		Response response = melhorCaminho.calculaMelhorCaminho("A", "B", BigDecimal.ZERO, new BigDecimal(2.5));
		Assert.assertEquals(400, response.getStatus());	
	}
	
	@Test
	public void calculaMelhorCaminhoCustoZeroParameterTest() {				
		Response response = melhorCaminho.calculaMelhorCaminho("A", "B", BigDecimal.TEN, BigDecimal.ZERO);
		Assert.assertEquals(400, response.getStatus());	
	}
}