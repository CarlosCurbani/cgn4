package br.furb.cg.labirinto;

public class Labirinto {
	private int[][] terreno;
	
	public Labirinto(){
		geraTerreno10x10();
		imprimeLabirinto();
	}
	
	private void geraTerreno10x10(){
		terreno = new int [10][10];
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno.length; j++) {
					terreno[i][j] = (int) Math.round(Math.random() * 1);			
			}
			System.out.println("");			
		}
		
	}

	public void imprimeLabirinto(){
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno.length; j++) {
				System.out.print(" "+terreno[i][j]);				
			}
			System.out.println("");			
		}
	
	}
	
}
