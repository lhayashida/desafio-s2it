package br.com.s2it.walmart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.s2it.walmart.entity.Rota;
import br.com.s2it.walmart.entity.CaminhoCompleto;

public class MenorCaminho {
	
	private static List<Rota> mapa;
	
	public static void main(String[] args) {
		
		mapa = new ArrayList<Rota>();
		Rota rota = new Rota("A", "B", 10L);		
		mapa.add(rota);
		rota = new Rota("B", "D", 15L);
		mapa.add(rota);
		rota = new Rota("A", "C", 20L);
		mapa.add(rota);
		rota = new Rota("C", "D", 30L);
		mapa.add(rota);
		rota = new Rota("B", "E", 50L);
		mapa.add(rota);
		rota = new Rota("D", "E", 30L);
		mapa.add(rota);
		
		List<CaminhoCompleto> todosCaminhos = pesquisaTodosCaminhos("C","E");
		Collections.sort(todosCaminhos);
			
		for (Rota caminho : todosCaminhos.get(0).getRota()) {
			System.out.println(caminho);
		}
	}

	private static List<CaminhoCompleto> pesquisaTodosCaminhos(String origem,
			String destino) {

		List<CaminhoCompleto> caminhos = new ArrayList<CaminhoCompleto>();

		List<CaminhoCompleto> rotas = calculaCaminhos(origem, destino);

		if (rotas != null && !rotas.isEmpty()) {
			caminhos.addAll(rotas);
		}

		return caminhos;
	}
	
	private static List<CaminhoCompleto> calculaCaminhos(String origem, String destino) {
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

				List<CaminhoCompleto> destinos = calculaCaminhos(rota.getDestino(),	destino);

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
