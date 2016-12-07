package br.furb.cg.labirinto;

import java.util.ArrayList;
import java.util.Random;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import br.furb.cg.objetoGL.ObjetoGrafico;
import br.furb.cg.pacman.Adversario;
import br.furb.cg.pacman.PacMan;

public class Labirinto {
	private int[][] terreno;
	private PacMan pacMan;
	private int length;
	private ObjetoGrafico objetoGrafico;
	private ArrayList<Adversario> adversarios;
	
	public Labirinto(){
		geraTerreno(10);
		pacMan = new PacMan(5, 4);
		adversarios = createAdversarios();
		imprimeLabirinto();
		objetoGrafico = new ObjetoGrafico();
	}
	
	private void geraTerreno(int x){
		int maxParede = 0;
		length = x - 1;
		terreno = new int [x][x];
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno.length; j++) {
				int value = (int) Math.round(Math.random() * 1);
				if (value == 1 && maxParede < (x * 3)) {
					terreno[i][j] = 1;		
					maxParede++;
				} else {
					terreno[i][j] = 0;
				}
						
			}
			System.out.println("");			
		}
		terreno[5][4] = 2;
	}

	public ArrayList<Adversario> createAdversarios() {
		Random rn = new Random();
		ArrayList<Adversario> adversarios = new ArrayList<>();
		do {
			int x = rn.nextInt(10);
			int y = rn.nextInt(10);
			if (terreno[x][y] == 0) {
				Adversario adv = new Adversario(x, y, length, terreno);
				adversarios.add(adv);
				new Thread(adv).start();
				terreno[x][y] = 3;
			}
		} while (adversarios.size() < 3);
		return adversarios;
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
	
	public void desenhaLabirinto(GL gl, GLU glu){
	    float corRed[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	    float corGreen[] = { 0.0f, 1.0f, 0.0f, 1.0f };
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno.length; j++) {
				if (terreno[i][j] == 1){
					gl.glColor3f(20.0f, 20.0f, 0.0f);
					objetoGrafico.drawCube(2, 8, 2, corRed, gl);					
					gl.glTranslated(2, 0f, 0f);
				}else{
					if (terreno[i][j] == 2){
						gl.glTranslated(0, 2f, 0f);
						gl.glColor3f(20.0f, 0f, 0.0f);
						objetoGrafico.drawPacMan(2, 8, 2, corRed, gl);
						gl.glTranslated(0, -2f, 0f);
					}
					gl.glColor3f(0.0f, 20.0f, 20.0f);
					objetoGrafico.drawCube(2, 2, 2, corGreen, gl);					
					gl.glTranslated(2, 0f,0f);
					
				}
			}
			gl.glTranslated(-1 *(2 * terreno.length ), 0f, 2f);
			
		}
	}
	
}
