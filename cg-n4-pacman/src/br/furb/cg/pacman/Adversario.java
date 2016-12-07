package br.furb.cg.pacman;

import java.util.Random;

public class Adversario extends Personagem implements Runnable {

	private int length;
	private int[][] terreno;
	
	public Adversario(int x, int y, int length, int[][] terreno) {
		super(x, y);
		this.length = length;
		this.terreno = terreno;
	}

	@Override
	public void run() {
		Random rn = new Random();
		while (true) {
			int key = rn.nextInt(4);
			switch (key) {
			case 0:
				moveToRight();
				break;
			case 1: 
				moveToLeft();
				break;
			case 2: 
				moveUp();
				break;
			case 3:
				moveDown();
				break;
			default:
				break;
			}
		}
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
		if (this.getPosicaoY() == length) 
			return;
		if (terreno[this.getPosicaoX()][this.getPosicaoY() + 1] == 0) {
			terreno[this.getPosicaoX()][this.getPosicaoY()] = 0;
			terreno[this.getPosicaoX()][this.getPosicaoY() + 1] = 3;
		}
//		imprimeLabirinto();
	}
	
	public void moveToLeft() {
		if (this.getPosicaoY() == 0) 
			return;
		if (terreno[this.getPosicaoX()][this.getPosicaoY() - 1] == 0) {
			terreno[this.getPosicaoX()][this.getPosicaoY()] = 0;
			terreno[this.getPosicaoX()][this.getPosicaoY() - 1] = 3;
		}
//		imprimeLabirinto();
	}
	
	public void moveUp() {
		if (this.getPosicaoX() == 0) 
			return;
		if (terreno[this.getPosicaoX() - 1][this.getPosicaoY()] == 0) {
			terreno[this.getPosicaoX()][this.getPosicaoY()] = 0;
			terreno[this.getPosicaoX() - 1][this.getPosicaoY()] = 3;
		}
//		imprimeLabirinto();
	}
	
	public void moveDown() {
		if (this.getPosicaoX() == length) 
			return;
		if (terreno[this.getPosicaoX() + 1][this.getPosicaoY()] == 0) {
			terreno[this.getPosicaoX()][this.getPosicaoY()] = 0;
			terreno[this.getPosicaoX() + 1][this.getPosicaoY()] = 3;
		}
//		imprimeLabirinto();
	}
	
}
