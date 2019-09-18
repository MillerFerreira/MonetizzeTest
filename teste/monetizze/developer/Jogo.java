package teste.monetizze.developer;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;


public class Jogo {
	
	private int totalDezenas;
	private int totalJogos;
	private List<ArrayList<Integer>> jogos;
	private List<Integer> resultado;
	
	public Jogo(int totalDezenas, int totalJogos) {
		boolean dezenasValidas = (totalDezenas > 5 && totalDezenas < 11);
		boolean jogosValidos = (totalJogos > 0);
		
		if(!dezenasValidas || !jogosValidos) {
			throw new IllegalArgumentException("Numero de dezenas ou numero de jogos invalidos!");
		}		
		this.totalDezenas = totalDezenas;
		this.totalJogos = totalJogos;
		jogos = new ArrayList<ArrayList<Integer>>();
	}
	
	public int getDezenas() {
		return totalDezenas;
	}
	
	public void setDezenas(int dezenas) {
		this.totalDezenas = dezenas;
	}
	
	public int getTotalJogos() {
		return totalJogos;
	}
	
	public void setTotalJogos(int totalJogos) {
		this.totalJogos = totalJogos;
	}
	
	public List<ArrayList<Integer>> getJogos() {
		return jogos;
	}
	
	public void setJogos(List<ArrayList<Integer>> jogos) {
		this.jogos = jogos;
	}
	
	public List<Integer> getResultado() {
		return resultado;
	}
	
	public void setResultado(List<Integer> resultado) {
		this.resultado = resultado;
	}

	private ArrayList<Integer> jogoAleatorio(int nDezenas) {
		ArrayList<Integer> jogoAleatorio = new ArrayList<Integer>();
		Random randomClass = new Random();
		Set<Integer> dezenas = new HashSet<Integer>();
		
		for(int i = 0; i < nDezenas; i++) {
			int sorteado = -1;
			
			do {
				sorteado = randomClass.nextInt(60) + 1;
			} while(dezenas.contains(sorteado));
			
			dezenas.add(sorteado);
			jogoAleatorio.add(sorteado);
		}
		Collections.sort(jogoAleatorio);
		
		return jogoAleatorio;
	}
	
	public void gerarJogos() {		
		for(int j = 0; j < totalJogos; j++) {
			ArrayList<Integer> novoJogo = new ArrayList<Integer>();
			novoJogo = jogoAleatorio(totalDezenas);			
			jogos.add(novoJogo);			
		}
	}
	
	public void realizarSorteio() {		
		resultado = jogoAleatorio(6);
	}
}
