package br.com.viniciusmendonca.cm.visao;

import java.util.Scanner;

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
				System.out.println("Outra partida? (S/n)");
				String resposta = entrada.nextLine();
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}
			}
		}catch (SairException e){
			System.out.println("Tchau!!");
		}finally {
			entrada.close();
		}
	}
}
