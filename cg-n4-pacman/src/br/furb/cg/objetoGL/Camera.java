package br.furb.cg.objetoGL;


import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Camera
{	
	private float ortho2D_minX = -400.0f;
	private float ortho2D_maxX = +400.0f;
	private float ortho2D_minY = -400.0f;
	private float ortho2D_maxY = +400.0f;
	
	/**
	 * Atribui os valores máximos e minimos dos eixos X e Y 
	 * ao "ortho". 
	 * 
	 * @param gl
	 * @param glu
	 * 
	 * @see javax.media.opengl.glu.GLU#gluOrtho2D(double, double, double, double)
	 */
	public void posicionar(GL gl, GLU glu)
	{			
		glu.gluOrtho2D(ortho2D_minX, ortho2D_maxX, ortho2D_minY, ortho2D_maxY);
	}
	
	/**
	 * Converte a coordenado do mouse em ponto da tela gl
	 * @param xPonto
	 * @param yPonto
	 * @param xTela
	 * @param yTela
	 * @return
	 */
//	public Ponto4D convertePontoTela(double xPonto, double yPonto, double xTela, double yTela)
//	{
//		double xTotal = ortho2D_maxX - ortho2D_minX;
//		double yTotal = ortho2D_maxY - ortho2D_minY;
//		
//		double escalaX = xTotal / xTela;
//		double escalaY = yTotal / yTela;
//
//		double x = ((xPonto * escalaX) + ortho2D_minX);
//		double y = ((yPonto * escalaY) + ortho2D_minY) * -1;
//		
//		return new Ponto4D(x, y);
//	}
}
