package br.furb.cg.labirinto;

import br.furb.cg.pacman.PacMan;

public class Labirinto {
	private int[][] terreno;
	private PacMan pacMan;
	private int length;
	
	public Labirinto(){
		geraTerreno(10);
		imprimeLabirinto();
		pacMan = new PacMan();
	}
	
	private void geraTerreno(int x){
		length = x - 1;
		terreno = new int [x][x];
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno.length; j++) {
					terreno[i][j] = (int) Math.round(Math.random() * 1);			
			}
			System.out.println("");			
		}
		terreno[5][4] = 2;
	}

	public void imprimeLabirinto(){
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno.length; j++) {
				System.out.print(" "+terreno[i][j]);				
			}
			System.out.println("");			
		}
		System.out.println("----------------------------------");
	}
	
	public void moveToRight() {
		if (pacMan.getPosicaoY() == length) 
			return;
		if (terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY() + 1] == 0) {
			terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY()] = 0;
			terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY() + 1] = 2;
			pacMan.moveToRight();
		}
		imprimeLabirinto();
	}
	
	public void moveToLeft() {
		if (pacMan.getPosicaoY() == 0) 
			return;
		if (terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY() - 1] == 0) {
			terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY()] = 0;
			terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY() - 1] = 2;
			pacMan.moveToLeft();
		}
		imprimeLabirinto();
	}
	
	public void moveUp() {
		if (pacMan.getPosicaoX() == 0) 
			return;
		if (terreno[pacMan.getPosicaoX() - 1][pacMan.getPosicaoY()] == 0) {
			terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY()] = 0;
			terreno[pacMan.getPosicaoX() - 1][pacMan.getPosicaoY()] = 2;
			pacMan.moveUp();
		}
		imprimeLabirinto();
	}
	
	public void moveDown() {
		if (pacMan.getPosicaoX() == length) 
			return;
		if (terreno[pacMan.getPosicaoX() + 1][pacMan.getPosicaoY()] == 0) {
			terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY()] = 0;
			terreno[pacMan.getPosicaoX() + 1][pacMan.getPosicaoY()] = 2;
			pacMan.moveDown();
		}
		imprimeLabirinto();
	}
	
}
