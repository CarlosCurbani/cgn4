package br.furb.cg.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.SwingUtilities;

import br.furb.cg.labirinto.Labirinto;
import br.furb.cg.objetoGL.Camera;
import br.furb.cg.objetoGL.ObjetoGrafico;

public class Main implements GLEventListener, KeyListener{
	public static void main(String[] args) {
		System.out.println("CG-N4-PACMAN");
		Labirinto labirinto = new Labirinto();
		
	}		
	private GL gl;
	private GLU glu;
	private GLAutoDrawable glDrawable;
	private double mouseX;
	private double mouseY;
	ObjetoGrafico objetoGrafico;
	ObjetoGrafico objetoGraficoFilho;
	boolean filhoAtivo = false;
	boolean desenhando = false;
	boolean selecionar = false;
	boolean editando = false;
	Camera camera= new Camera();
	private float sruX = 200;
	private float sruY = 200;
	private List<ObjetoGrafico>  objetos = new ArrayList<>(); 
			
	public void init(GLAutoDrawable drawable)
	{
		glDrawable = drawable;
		gl = drawable.getGL();
		glu = new GLU();
		glDrawable.setGL(new DebugGL(gl));
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
	}

	public void display(GLAutoDrawable arg0)
	{
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		camera.posicionar(gl, glu);		
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();		
		desenhaSRU();

		
		////for (ObjetoGrafico unicoObjeto :  objetos) {
		//	unicoObjeto.desenha(gl);
//		}
//		if(objetoGrafico != null){
//			objetoGrafico.desenha(gl);			
//		}
//
//		gl.glFlush();
	}
	
	


	public void desenhaSRU() {
		gl.glLineWidth(1.0f);

		// Eixo X
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);
		{
			gl.glVertex2d(-sruX, 0.0);
			gl.glVertex2d(+sruX, 0.0);
		}
		gl.glEnd();

		// Eixo Y
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glBegin(GL.GL_LINES);
		{
			gl.glVertex2d(0.0, -sruY);
			gl.glVertex2d(0.0, +sruY);
		}
		gl.glEnd();

	}
	/**
	 * Eventos do teclado
	 */
	public void keyPressed(KeyEvent e) {
		
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_I:
			System.out.println("inicio");			
			break;
		case KeyEvent.VK_F:
			System.out.println("fim");			
			break;
		}

		glDrawable.display();
	}

	// metodo definido na interface GLEventListener.
	// "render" feito depois que a janela foi redimensionada.
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	    gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
		// System.out.println(" --- reshape ---");
	}

	// metodo definido na interface GLEventListener.
	// "render" feito quando o modo ou dispositivo de exibicao associado foi
	// alterado.
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		// System.out.println(" --- displayChanged ---");
	}

	public void keyReleased(KeyEvent arg0) {
		// System.out.println(" --- keyReleased ---");
	}

	public void keyTyped(KeyEvent arg0) {
		// System.out.println(" --- keyTyped ---");
	}

	public double getMouseX() {
		return mouseX;
	}

	public void setMouseX(double mouseX) {
		this.mouseX = mouseX;
	}

	public double getMouseY() {
		return mouseY;
	}

	public void setMouseY(double mouseY) {
		this.mouseY = mouseY;
	}
	
	

}
