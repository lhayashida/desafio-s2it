package br.com.s2it.walmart.lhayashida.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rota {

	/**
	 * origem
	 */
	private String origem;
	
	/**
	 * destino
	 */
	private String destino;
	
	/**
	 * distancia (em quilometros)
	 */
	private Long distancia;

	/**
	 * construtor default (vazio)
	 */
	public Rota() { }
	
	/**
	 * Construtor com todos os campos
	 * 
	 * @param startPoint origem
	 * @param endPoint destino
	 * @param distance distancia
	 */
	public Rota(String origem, String destino, Long distancia) {
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
	}
		
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return origem + "->" + destino + '(' + distancia+ ')'; 
	}

	/* Getter & Setters */
	
	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Long getDistancia() {
		return distancia;
	}

	public void setDistancia(Long distancia) {
		this.distancia = distancia;
	}
		
}
