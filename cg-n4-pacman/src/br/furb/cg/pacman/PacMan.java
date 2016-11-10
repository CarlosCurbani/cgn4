package br.furb.cg.pacman;

public class PacMan {
	private int posicaoX;
	private int posicaoY;
	
	public PacMan(){
		setPosicao(1, 1);
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
	
}
