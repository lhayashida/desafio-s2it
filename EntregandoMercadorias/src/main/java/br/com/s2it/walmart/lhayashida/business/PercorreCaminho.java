package br.com.s2it.walmart.lhayashida.business;

import java.util.ArrayList;
import java.util.List;

import br.com.s2it.walmart.lhayashida.entity.Rota;
import br.com.s2it.walmart.lhayashida.entity.CaminhoCompleto;

public class PercorreCaminho {
	
	/**
	 * Retorna todos os caminhos possiveis, dado um mapa
	 * 
	 * @param origem origem
	 * @param destino destino
	 * @param mapa mapa
	 * @return List<CaminhoCompleto> 
	 */
	public List<CaminhoCompleto> pesquisaTodosCaminhos(String origem,
			String destino, List<Rota> mapa) {

		List<CaminhoCompleto> caminhos = new ArrayList<CaminhoCompleto>();

		List<CaminhoCompleto> rotas = calculaCaminhos(origem, destino, mapa);

		if (rotas != null && !rotas.isEmpty()) {
			caminhos.addAll(rotas);
		}

		return caminhos;
	}

	/**
	 * Devolve os caminhos possiveis, dado um ponto de origem e destino 
	 * @param origem
	 * @param destino
	 * @param mapa
	 * @return List<CaminhoCompleto>
	 */
	private List<CaminhoCompleto> calculaCaminhos(String origem, String destino, List<Rota> mapa) {
		List<CaminhoCompleto> resultado = new ArrayList<CaminhoCompleto>();
		
		// monta uma lista com todas as rotas com a origem mapeada
		List<Rota> candidatas = new ArrayList<Rota>();
		for (Rota rota : mapa) {	
			if (origem.equals(rota.getOrigem())) {
				candidatas.add(rota);
			}
		}
						
		for (Rota rota : candidatas) {
						
			if (destino.equals(rota.getDestino())) {
				
				CaminhoCompleto caminho = new CaminhoCompleto();
				caminho.getRota().add(rota);
				caminho.setDistanciaTotal(caminho.getDistanciaTotal() + rota.getDistancia());
				resultado.add(caminho);

			} else {

				List<CaminhoCompleto> destinos = this.calculaCaminhos(rota.getDestino(), destino, mapa);

				if (!destinos.isEmpty()) {

					for (CaminhoCompleto continuacao : destinos) {
						CaminhoCompleto caminho = new CaminhoCompleto();
						caminho.getRota().add(rota);
						caminho.setDistanciaTotal(caminho.getDistanciaTotal() + rota.getDistancia());

						caminho.inclui(continuacao);
						resultado.add(caminho);
					}
				}											
			}
		}

		return resultado;
	}		
	
}
