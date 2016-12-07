package br.furb.cg.objetoGL;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

public class ObjetoGrafico {
	private GLUT glut;
	private float luzAmbiente[]={0.2f, 0.2f, 0.2f, 1.0f};
	
	
	public void drawCube(float xS, float yS, float zS, float[] corObjeto, GL gl) {
	    gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, corObjeto, 0);
	    gl.glEnable(GL.GL_LIGHTING);
	    
		gl.glPushMatrix();
			gl.glScalef(xS,yS,zS);
			gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, luzAmbiente, 0);
			glut.glutSolidCube(1.0f);
		gl.glPopMatrix();
		
		gl.glDisable(GL.GL_LIGHTING);
	}
	
	public void drawPacMan(float xS, float yS, float zS, float[] corObjeto, GL gl) {
	    gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, corObjeto, 0);
	    gl.glEnable(GL.GL_LIGHTING);

		gl.glPushMatrix();
			glut.glutSolidSphere(1, 30, 30);
		gl.glPopMatrix();
		
		gl.glDisable(GL.GL_LIGHTING);
	}
	
	public void drawFruit(float xS, float yS, float zS, float[] corObjeto, GL gl) {
	    gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, corObjeto, 0);
	    gl.glEnable(GL.GL_LIGHTING);

		gl.glPushMatrix();
			glut.glutSolidSphere(0.5, 30, 30);
		gl.glPopMatrix();
		
		gl.glDisable(GL.GL_LIGHTING);
	}
	

	public ObjetoGrafico(){
		glut = new GLUT();
		
	}
}
