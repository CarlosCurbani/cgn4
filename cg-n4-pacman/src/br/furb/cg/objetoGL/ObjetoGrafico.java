package br.furb.cg.objetoGL;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

public class ObjetoGrafico {
	private GLUT glut;
	
	
	
	public void drawCube(float xS, float yS, float zS, float[] corObjeto, GL gl) {
	    gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, corObjeto, 0);
	    gl.glEnable(GL.GL_LIGHTING);

		gl.glPushMatrix();
			gl.glScalef(xS,yS,zS);
			glut.glutSolidCube(1.0f);
		gl.glPopMatrix();
		
		gl.glDisable(GL.GL_LIGHTING);
	}
	

	public ObjetoGrafico(){
		glut = new GLUT();
		
	}
}
