package br.com.s2it.walmart.entity;

import java.util.ArrayList;
import java.util.List;

public class CaminhoCompleto implements Comparable<CaminhoCompleto> {
	
	/**
	 * Distancia total
	 */
	private Long distanciaTotal;
	
	/**
	 * Lista com as arestas a serem percorridas
	 */
	private List<Rota> rota;
	
	/**
	 * Construtor
	 */
	public CaminhoCompleto() {
		this.distanciaTotal = 0L;
		this.rota =  new ArrayList<Rota>();			
	}
	
	/**
	 * Inclui uma novo caminho ao existente
	 * 
	 * @param caminho caminho a ser incluido
	 */
	public void inclui(CaminhoCompleto caminho) {
		this.distanciaTotal += caminho.distanciaTotal;
		this.rota.addAll(caminho.rota);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder resultado = new StringBuilder();
		for (Rota aresta : rota) {
			resultado.append(aresta);
			resultado.append(';');						
		}
		return resultado.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(CaminhoCompleto caminho) {
		if (this.distanciaTotal > caminho.distanciaTotal) {
			return 1;
		} else if (this.distanciaTotal < caminho.distanciaTotal) {
			return -1;
		} else {
			return 0;	
		}		
	}
	
	/* Getters & Setters */
	
	public Long getDistanciaTotal() {
		return distanciaTotal;
	}

	public void setDistanciaTotal(Long distanciaTotal) {
		this.distanciaTotal = distanciaTotal;
	}

	public List<Rota> getRota() {
		return rota;
	}

	public void setRota(List<Rota> rota) {
		this.rota = rota;
	}
			
}
