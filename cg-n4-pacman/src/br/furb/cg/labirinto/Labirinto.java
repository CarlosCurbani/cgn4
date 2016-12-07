package br.furb.cg.labirinto;

import java.util.ArrayList;
import java.util.Random;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.swing.JOptionPane;

import br.furb.cg.objetoGL.ObjetoGrafico;
import br.furb.cg.pacman.Adversario;
import br.furb.cg.pacman.PacMan;

public class Labirinto {
	private int[][] terreno;
	private PacMan pacMan;
	private int length;
	private ObjetoGrafico objetoGrafico;
	private ArrayList<Adversario> adversarios;
	private static Labirinto instance;
	private GL gl;
	private GLU glu;
	private int pontuacao = 0;
	private float corRed[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	private float corGreen[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	private float corBlue[] = { 0.0f, 0.0f, 1.0f, 1.0f };
	private float corYellow[] = {20f, 20f, 0f, 1f};
	private float corWhite[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	
	private Labirinto(){
		//geraTerreno(10);
		geraTerrenoFixo();
		pacMan = new PacMan(5, 4);
		adversarios = createAdversarios();
		imprimeLabirinto();
		objetoGrafico = new ObjetoGrafico();
	}
	
	public static Labirinto getInstance(){
		if (instance == null){
			instance = new Labirinto();
		}
		return instance;
	}
	
	
	private void geraTerrenoFixo(){
		terreno = new int[][]{
			 {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			 {1, 5, 5, 5, 5, 1, 5, 5, 5, 1},
			 {1, 5, 1, 1, 5, 5, 5, 1, 5, 1},
			 {1, 5, 5, 5, 5, 1, 1, 1, 5, 1},
			 {1, 5, 1, 1, 5, 1, 5, 5, 5, 1},
			 {1, 5, 5, 1, 2, 5, 5, 1, 5, 1},
			 {1, 1, 5, 5, 5, 1, 5, 1, 5, 1},
			 {1, 1, 5, 1, 5, 1, 5, 1, 5, 1},
			 {1, 5, 5, 5, 5, 5, 5, 5, 5, 1},
			 {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
};
		
	}
	


	public ArrayList<Adversario> createAdversarios() {
		Random rn = new Random();
		ArrayList<Adversario> adversarios = new ArrayList<>();
		do {
			int x = rn.nextInt(10);
			int y = rn.nextInt(10);
			if (terreno[x][y] == 0  || terreno[x][y] == 5) {
				terreno[x][y] = 3;
				Adversario adv = new Adversario(x, y, length, terreno);
				adversarios.add(adv);
				new Thread(adv).start();
				
			}
		} while (adversarios.size() < 2);
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
		if (terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY() + 1] != 1) {
			if(terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY() + 1] == 2){
				fimJogo();
			}else if(terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY() + 1] == 0){
				terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY()] = 0;
			}else if(terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY() + 1] == 5){
				pontuacao += 100;
				terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY()] = 0;				
			}	
			terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY() + 1] = 2;
			pacMan.moveToRight();
		}
		imprimeLabirinto();
	}
	
	public void moveToLeft() {
		if (pacMan.getPosicaoY() == 0) 
			return;		
		if (terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY() - 1] != 1) {
			if(terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY() - 1] == 2){
				fimJogo();
			}else if(terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY() - 1] == 0){
				terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY()] = 0;
			}else if(terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY() - 1] == 5){
				pontuacao += 100;
				terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY()] = 0;				
			}	
			terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY() - 1] = 2;
			pacMan.moveToLeft();
		}
		imprimeLabirinto();
	}
	
	public void moveUp() {
		if (pacMan.getPosicaoX() == 0) 
			return;
		if (terreno[pacMan.getPosicaoX() - 1][pacMan.getPosicaoY() ] != 1) {
			if(terreno[pacMan.getPosicaoX() - 1][pacMan.getPosicaoY()] == 2){
				fimJogo();
			}else if(terreno[pacMan.getPosicaoX() - 1][pacMan.getPosicaoY() ] == 0){
				terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY()] = 0;
			}else if(terreno[pacMan.getPosicaoX() - 1][pacMan.getPosicaoY()] == 5){
				pontuacao += 100;
				terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY()] = 0;				
			}	
			terreno[pacMan.getPosicaoX() - 1][pacMan.getPosicaoY()] = 2;
			pacMan.moveUp();
		}
		imprimeLabirinto();
	}
	
	public void moveDown() {
		if (pacMan.getPosicaoX() == length) 
			return;
		if (terreno[pacMan.getPosicaoX() + 1][pacMan.getPosicaoY() ] != 1) {
			if(terreno[pacMan.getPosicaoX() + 1][pacMan.getPosicaoY()] == 2){
				fimJogo();
			}else if(terreno[pacMan.getPosicaoX() + 1][pacMan.getPosicaoY() ] == 0){
				terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY()] = 0;
			}else if(terreno[pacMan.getPosicaoX() + 1][pacMan.getPosicaoY()] == 5){
				pontuacao += 100;
				terreno[pacMan.getPosicaoX()][pacMan.getPosicaoY()] = 0;				
			}	
			terreno[pacMan.getPosicaoX() + 1][pacMan.getPosicaoY()] = 2;
			pacMan.moveDown();
		}
		imprimeLabirinto();
	}
	
	public synchronized void desenhaLabirinto(){
		boolean fgGanhou = true;
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno.length; j++) {
				if (terreno[i][j] == 1){
					objetoGrafico.drawCube(2, 8, 2, corBlue, gl);					
					gl.glTranslated(2, 0f, 0f);
				}else{
					if (terreno[i][j] == 2){
						gl.glTranslated(0, 2f, 0f);
						objetoGrafico.drawPacMan(2, 8, 2, corYellow, gl);
						gl.glTranslated(0, -2f, 0f);
					}
					if (terreno[i][j] == 3){
						gl.glTranslated(0, 2f, 0f);
						objetoGrafico.drawPacMan(2, 6, 2, corRed, gl);
						gl.glTranslated(0, -2f, 0f);
					}
					if (terreno[i][j] == 5){
						fgGanhou = false;
						gl.glTranslated(0, 2f, 0f);
						objetoGrafico.drawFruit(2, 6, 2, corWhite, gl);
						gl.glTranslated(0, -2f, 0f);
					}
					objetoGrafico.drawCube(2, 2, 2, corGreen, gl);					
					gl.glTranslated(2, 0f,0f);					
				}
			}
			gl.glTranslated(-1 *(2 * terreno.length ), 0f, 2f);			
		}if(fgGanhou){
			ganhouJogo();
		}
	}
	
	public void fimJogo(){
		JOptionPane.showMessageDialog(null, "Voce perdeu! Sua pontuação foi:"+pontuacao, "Fim de jogo",  JOptionPane.ERROR_MESSAGE);
		
	}
	
	
	public void ganhouJogo(){
		JOptionPane.showMessageDialog(null, "Voce ganhou! Sua pontuação foi:"+pontuacao, "GANHOUUU",  JOptionPane.INFORMATION_MESSAGE);
	}
	
	public synchronized void alteraCoordenada(int x, int y, int estado){
		terreno[x][y] = estado; 
		
		
	}

	public GL getGl() {
		return gl;
	}

	public void setGl(GL gl) {
		this.gl = gl;
	}

	public GLU getGlu() {
		return glu;
	}

	public void setGlu(GLU glu) {
		this.glu = glu;
	}
	
	
	
}
