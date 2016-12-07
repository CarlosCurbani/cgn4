package br.furb.cg.main;

import javax.swing.*;
import java.awt.*; 

import javax.media.opengl.*;

public class Frame
{
	private Main renderer;

	/**
	 * Construtor da classe ExemploJava que não recebe parâmetros. Cria uma janela e insere  
	 * um componente canvas OpenGL.
	 */
	public Frame()
	{
		// Cria janela
		JFrame janela = new JFrame("CG-PAC_MAN");   
		janela.setBounds(50,100,500,500); 
		janela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		BorderLayout layout = new BorderLayout(); 
		Container caixa=janela.getContentPane();
		caixa.setLayout(layout); 

		// Cria um objeto GLCapabilities para especificar o número de bits 
		// por pixel para RGBA
		GLCapabilities c = new GLCapabilities();
		c.setRedBits(8);
		c.setBlueBits(8);
		c.setGreenBits(8);
		c.setAlphaBits(8); 

		// Cria o objeto que irá gerenciar os eventos
		renderer = new Main();
		
		// Cria um canvas, adiciona na janela, e especifica o objeto "ouvinte" 
		// para os eventos Gl, de mouse e teclado
		// GLCanvas canvas = GLDrawableFactory.getFactory().createGLCanvas(c);
		GLCanvas canvas = new GLCanvas(c);
		janela.add(canvas,BorderLayout.CENTER);
		canvas.addGLEventListener(renderer);        
		canvas.addMouseListener(renderer);
		canvas.addKeyListener(renderer);
		janela.setVisible(true);
		canvas.requestFocus();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame ej = new Frame();
	}	
}
