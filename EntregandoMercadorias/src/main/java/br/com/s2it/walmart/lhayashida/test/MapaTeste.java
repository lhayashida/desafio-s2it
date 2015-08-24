package br.com.s2it.walmart.lhayashida.test;

import java.util.ArrayList;
import java.util.List;

import br.com.s2it.walmart.lhayashida.entity.Rota;;

public class MapaTeste {	
	
	/**
	 * Carrega um mapa teste (sem persistencia)
	 * 
	 * @return List<Rota>
	 */	
	public static List<Rota> inicializa() {
		
		List<Rota> mapa = new ArrayList<Rota>();
		
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
		rota = new Rota("D", "E",30L);		
		mapa.add(rota);
		
		return mapa;
	}
}
