package br.com.viniciusmendonca.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.viniciusmendonca.cm.excecao.ExplosaoException;
import br.com.viniciusmendonca.cm.excecao.SairException;
import br.com.viniciusmendonca.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar == true) {
				cicloDoJogo();
				
				System.out.println("Outra partida? (S/n)");
				String resposta = entrada.nextLine();
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}else {
					tabuleiro.reiniciar();
				}
			}
		}catch (SairException e){
			System.out.println("Jogo Fechado!!");
		}finally {
			entrada.close();
		}
	}
	
	private void cicloDoJogo() {
		try {
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro.toString());
				String digitado = capturarValorDigitado("Digite (x, y):");;
				Iterator<Integer> xy =Arrays.stream(digitado.split(","))
					.map(e -> Integer.parseInt(e.trim()))
					.iterator();
				digitado = capturarValorDigitado("1 - Abrir | 2- Marcar/Desmarcar:");
				if("1".equalsIgnoreCase(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				}else if("2".equalsIgnoreCase(digitado)){
					tabuleiro.alterarMarcacao(xy.next(), xy.next());
				}
			}			
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!!!");
		}catch(ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!");
		}
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		if("sair".equalsIgnoreCase(digitado)){
			throw new SairException();
		}
		return digitado;
	}
}
