package br.furb.cg.pacman;

public class PacMan {
	private int posicaoX;
	private int posicaoY;
	
	public PacMan(){
		setPosicao(5, 4);
	}
	
	public void setPosicao(int x, int y){
		posicaoX = x;
		posicaoY = y;
	}
	
	public int getPosicaoX() {
		return posicaoX;
	}
	
	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}
	
	public int getPosicaoY() {
		return posicaoY;
	}
	
	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}	
	
	public void moveToRight() {
		posicaoY++;
	}
	
	public void moveToLeft() {
		posicaoY--;
	}
	
	public void moveUp() {
		posicaoX--;
	}
	
	public void moveDown() {
		posicaoX++;
	}
}
