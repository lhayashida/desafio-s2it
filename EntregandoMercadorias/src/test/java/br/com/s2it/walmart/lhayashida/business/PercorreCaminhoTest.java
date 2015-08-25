package br.com.s2it.walmart.lhayashida.business;

import org.junit.Test;

import br.com.s2it.walmart.lhayashida.exception.EntregandoMercadoriasException;

public class PercorreCaminhoTest {
	
	PercorreCaminho percorreCaminho =  new PercorreCaminho();
	
	@Test(expected=EntregandoMercadoriasException.class)
	public void pesquisaTodosCaminhosMapaVazio() throws EntregandoMercadoriasException {
		percorreCaminho.pesquisaTodosCaminhos("A", "B", null);		
	}

}
