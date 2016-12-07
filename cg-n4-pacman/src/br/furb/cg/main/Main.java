package br.furb.cg.main;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.awt.event.*; 
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import javax.swing.JOptionPane;

import com.sun.opengl.util.*;
import com.sun.opengl.util.texture.TextureData;

import br.furb.cg.labirinto.Labirinto;

public class Main extends MouseAdapter implements GLEventListener, KeyListener
{
	// Atributos
	private GL gl;
	private GLU glu;
	private GLUT glut;
	private GLAutoDrawable glDrawable;
	private double angle, fAspect;
	private float rotX, rotY, obsZ; 
	private boolean luz;
	private int idTexture[];
	private int width, height;
	private BufferedImage image;
	private TextureData td;
	private ByteBuffer buffer;
	private Labirinto labirinto;
	
	private float corRed[] = { 1.0f, 0.0f, 0.0f, 1.0f };
  private float corGreen[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	private float corBlue[] = { 0.0f, 0.0f, 1.0f, 1.0f };
	private float corYellow[] = {20f, 20f, 0f, 1f};
  private float corWhite[] = { 1.0f, 1.0f, 1.0f, 1.0f };
  private float corBlack[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	
	/**
	 * Construtor da classe Renderer que n�o recebe par�metros.
	 */
	public Main()
	{
		// Especifica o �ngulo da proje��o perspectiva  
		angle=20;   
		// Inicializa o valor para corre��o de aspecto   
		fAspect = 10; 
		
		// Inicializa os atributos usados para alterar a posi��o do 
		// observador virtual (=c�mera)
		rotX = 70;
		rotY = 0;
		obsZ = 200; 
	
		luz = true;
		labirinto =  Labirinto.getInstance();
	}
	
	/**
	 * M�todo definido na interface GLEventListener e chamado pelo objeto no qual ser� feito o desenho
	 * logo ap�s a inicializa��o do contexto OpenGL. 
	 */    
	public void init(GLAutoDrawable drawable)
	{
		glDrawable = drawable;
		gl = drawable.getGL();      
		glu = new GLU();
		glut = new GLUT();

		drawable.setGL(new DebugGL(gl));        


		
	    float posLight[] = { 5.0f, 5.0f, 10.0f, 0.0f };
	    gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, posLight, 0);
	    gl.glEnable(GL.GL_LIGHT0);

	    gl.glEnable(GL.GL_CULL_FACE);
	    gl.glEnable(GL.GL_DEPTH_TEST);
			
	}
	
	public void loadImage(String fileName)
	{
		// Tenta carregar o arquivo		
		image = null;
		try {
			image = ImageIO.read(new File(fileName));
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Erro na leitura do arquivo "+fileName);
		}

		// Obt�m largura e altura
		width  = image.getWidth();
		height = image.getHeight();
		// Gera uma nova TextureData...
		td = new TextureData(0,0,false,image);
		// ...e obt�m um ByteBuffer a partir dela
		buffer = (ByteBuffer) td.getBuffer();
	}

	/**
	 * M�todo definido na interface GLEventListener e chamado pelo objeto no qual ser� feito o desenho
	 * para come�ar a fazer o desenho OpenGL pelo cliente.
	 */  
	public void display(GLAutoDrawable drawable)
	{
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );
		gl.glLoadIdentity();
		
		if (luz)
			gl.glEnable(GL.GL_LIGHTING);
		else
			gl.glDisable(GL.GL_LIGHTING);
		
		especificaParametrosVisualizacao(); 
		defineIluminacao();
		labirinto = Labirinto.getInstance();
		labirinto.setGl(gl);
		labirinto.setGlu(glu);
		labirinto.desenhaLabirinto();

	}
	
	/**
	 * M�todo definido na interface GLEventListener e chamado pelo objeto no qual ser� feito o desenho
	 * depois que a janela foi redimensionada.
	 */  
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
		gl.glViewport(0, 0, width, height);
		fAspect = (float)width/(float)height;      
	}

	/**
	 * M�todo definido na interface GLEventListener e chamado pelo objeto no qual ser� feito o desenho
	 * quando o modo de exibi��o ou o dispositivo de exibi��o associado foi alterado.
	 */  
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) { }

	/**
	 * M�todo usado para especificar a posi��o do observador virtual (=c�mera).
	 */    
	public void posicionaObservador()
	{
		// Especifica sistema de coordenadas do modelo
		gl.glMatrixMode(GL.GL_MODELVIEW);
		// Inicializa sistema de coordenadas do modelo
		gl.glLoadIdentity();
		// Especifica posi��o do observador e do alvo
		gl.glTranslatef(0,0,-obsZ);
		gl.glRotatef(rotX,1,0,0);
		gl.glRotatef(rotY,0,1,0);
	}
	
	/**
	 * M�todo usado para especificar o volume de visualiza��o.
	 */    
	public void especificaParametrosVisualizacao()
	{
		// Especifica sistema de coordenadas de proje��o
		gl.glMatrixMode(GL.GL_PROJECTION);
		// Inicializa sistema de coordenadas de proje��o
		gl.glLoadIdentity();

		// Especifica a proje��o perspectiva(angulo,aspecto,zMin,zMax)
		glu.gluPerspective(angle, fAspect, 0.2, 500);

		posicionaObservador();
	}
	
	/**
	 * M�todo usado para especificar os par�metros de ilumina��o.
	 */    	
	public void defineIluminacao()
	{
		//Define os par�metros atrav�s de vetores RGBA - o �ltimo valor deve ser sempre 1.0f
		float luzAmbiente[]={0.2f, 0.2f, 0.2f, 1.0f}; 
		float luzDifusa[]={1.0f, 1.0f, 1.0f, 1.0f};  
		float luzEspecular[]={1.0f, 1.0f, 1.0f, 1.0f};
		float posicaoLuz[]={40.0f, 60.0f, 0.0f, 1.0f}; // �ltimo par�metro: 0-direcional, 1-pontual/posicional 

		float posicaoLuz2[]={-40.0f, 60.0f, 0.0f, 1.0f};
		float luzEspecular2[]={1.0f, 1.0f, 1.0f, 0.0f};
		float luzDifusa2[]={1.0f, 1.0f, 1.0f, 1.0f};
		
		//Ativa o uso da luz ambiente 
		gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, luzAmbiente, 0);

		//Define os par�metros da luz de n�mero 0
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, luzAmbiente, 0); 
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, luzDifusa, 0 );
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, luzEspecular, 0);
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, posicaoLuz, 0 ); 	
		
		//Define os par�metros da luz de n�mero 1
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, luzAmbiente, 0); 
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_DIFFUSE, luzDifusa2, 0 );
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPECULAR, luzEspecular2, 0);
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, posicaoLuz2, 0 ); 
		
		// Brilho do material
		float especularidade[]={1.0f, 1.0f, 1.0f, 1.0f};
		int especMaterial = 60;

		// Define a reflect�ncia do material 
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, especularidade, 0);
		// Define a concentra��o do brilho
		gl.glMateriali(GL.GL_FRONT, GL.GL_SHININESS, especMaterial);		
	}

	/**
	 * M�todo da classe MouseAdapter que est� sendo sobrescrito para gerenciar os 
	 * eventos de clique de mouse, de maneira que sej� feito zoom in e zoom out.
	 */  
	public void mouseClicked(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1) // Zoom in
		if (angle >= 4) angle -= 4;
		if (e.getButton() == MouseEvent.BUTTON3) // Zoom out
		if (angle <= 72) angle += 4;
		glDrawable.display();
	}

	/**
	 * M�todo definido na interface KeyListener que est� sendo implementado para, 
	 * de acordo com as teclas pressionadas, permitir mover a posi��o do observador
	 * virtual.
	 */        
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{ 
			case KeyEvent.VK_RIGHT: 
				labirinto.moveToRight();
				break;
			case KeyEvent.VK_LEFT:
				labirinto.moveToLeft();
				break;
			case KeyEvent.VK_UP:
				labirinto.moveUp();
				break;
			case KeyEvent.VK_DOWN:
				labirinto.moveDown();
				break;
			case KeyEvent.VK_A:
				rotY--;
				break;
			case KeyEvent.VK_D:
				rotY++;
				break;
			case KeyEvent.VK_W:
				rotX++;
				break;
			case KeyEvent.VK_S:
				rotX--;
				break;
			case KeyEvent.VK_HOME:
				obsZ++;
				break;
			case KeyEvent.VK_END:
				obsZ--;
				break;
			case KeyEvent.VK_F1:
				luz = !luz;
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
		}  
		glDrawable.display();
	}	

	/**
	 * M�todo definido na interface KeyListener.
	 */      
	public void keyTyped(KeyEvent e) { }

	/**
	 * M�todo definido na interface KeyListener.
	 */       
	public void keyReleased(KeyEvent e) { }
	
}


