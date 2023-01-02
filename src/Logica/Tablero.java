package Logica;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Tablero {
	private Casilla [][] casillas;
	private int numFilas;
	private int numColumnas;
	private int numMinas;
	private int numCasillasAbiertas;
	private int numMinasAbiertas;
	private Consumer<ArrayList<Casilla>> eventoPartidaPerdida;
	private Consumer<ArrayList<Casilla>> eventoPartidaGanada;
	private Consumer<Casilla> eventoCasillaAbierta;
	private Consumer<Casilla> eventoMinaAbierta;





	public Consumer<Casilla> getEventoMinaAbierta() {
		return eventoMinaAbierta;
	}

	public void setEventoMinaAbierta(Consumer<Casilla> eventoMinaAbierta) {
		this.eventoMinaAbierta = eventoMinaAbierta;
	}

	public Consumer<ArrayList<Casilla>> getEventoPartidaGanada() {
		return eventoPartidaGanada;
	}

	public void setEventoPartidaGanada(
			Consumer<ArrayList<Casilla>> eventoPartidaGanada) {
		this.eventoPartidaGanada = eventoPartidaGanada;
	}

	public Consumer<Casilla> getEventoCasillaAbierta() {
		return eventoCasillaAbierta;
	}

	public void setEventoCasillaAbierta(Consumer<Casilla> eventoCasillaAbierta) {
		this.eventoCasillaAbierta = eventoCasillaAbierta;
	}

	public Consumer<ArrayList<Casilla>> getEventoPartidaPerdida() {
		return eventoPartidaPerdida;
	}

	public void setEventoPartidaPerdida(
			Consumer<ArrayList<Casilla>> eventoPartidaPerdida) {
		this.eventoPartidaPerdida = eventoPartidaPerdida;
	}

	public Tablero(int numFilas, int numCoumas, int numMinas) {
		this.numFilas = numFilas;
		this.numColumnas = numCoumas;
		this.numMinas=numMinas;
		inicializarCasillas();
		generarMinas();
		actualizarCasillas();
	}

	public void inicializarCasillas(){
		casillas= new Casilla [numFilas][numColumnas];
		for(int i=0;i<casillas.length;i++)
			for(int j=0;j<casillas[i].length;j++)
				casillas[i][j]=new Casilla(i,j);
	}
	public void generarMinas(){
		int cant=0;
		while(cant!=numMinas){
			int fila=(int)(Math.random()*numFilas);
			int columna=(int)(Math.random()*numColumnas);
			if(!casillas[fila][columna].isMina()){
				casillas[fila][columna].setMina(true);
				cant++;
			}
		}
	}
	public void imprimirTablero(){
		for(int i=0;i<casillas.length;i++){
			for(int j=0;j<casillas[i].length;j++)
				System.out.print(casillas[i][j].isMina()? "*": casillas[i][j].getNumMinasAlrededor());
			System.out.println("");
		}
	}
	public void actualizarCasillas(){
		for(int i=0;i<casillas.length;i++){
			for(int j=0;j<casillas[i].length;j++)
				if(casillas[i][j].isMina()){
					ArrayList<Casilla> casillasAlrededor=obtenerAlrededor(i, j);
					for(Casilla c: casillasAlrededor)c.incrementar();

				}
		}
	}
	public ArrayList<Casilla> obtenerAlrededor(int posFila,int posColumna){
		ArrayList<Casilla> salida=new ArrayList<Casilla>();
		for(int i=0;i<8;i++){
			int fila=posFila;
			int columna=posColumna;
			switch (i) {
			case 0:fila--;break;
			case 1:fila--;columna++;break;
			case 2:fila--;columna--;break;
			case 3:columna--;break;
			case 4:columna++;break;
			case 5:fila++;break;
			case 6:fila++;columna--;break;
			case 7:fila++;columna++;break;
			}
			if(fila>=0 && fila<this.numFilas && columna>=0 && columna<this.numColumnas )
				salida.add(casillas[fila][columna]);
		}
		return salida;
	}
	public ArrayList<Casilla> obtenerLIstaMinas(){
		ArrayList<Casilla> casillasConMina=new ArrayList<Casilla>();
		for(int i=0;i<casillas.length;i++)
			for(int j=0;j<casillas[i].length;j++)
				if(casillas[i][j].isMina())
					casillasConMina.add(casillas[i][j]);
		return casillasConMina;
	}

	public void seleccionarCasilla(int fila, int columna){
		if(!partidaGanada()){
			eventoCasillaAbierta.accept(casillas[fila][columna]);
			if(casillas[fila][columna].isMina())
				eventoPartidaPerdida.accept(obtenerLIstaMinas());

			else{ 
				if(casillas[fila][columna].getNumMinasAlrededor()==0){
					marcarCasillaAbierta(fila, columna);
					ArrayList<Casilla> casillasAlrededor=obtenerAlrededor(fila, columna);
					for(Casilla x: casillasAlrededor){
						if(!x.isAbierta()){
							seleccionarCasilla(x.getPosFila(), x.getPosColumna());

						}
					}
				}
				else
					marcarCasillaAbierta(fila, columna);
				if(partidaGanada()){
					eventoPartidaGanada.accept(obtenerLIstaMinas());
				}
			}
		}
	}
	public boolean partidaGanada(){
		if((numCasillasAbiertas>=(numFilas*numColumnas)-numMinas) || numMinasAbiertas>=numMinas)
			return true;
		else
			return false;
	}
	public void marcarCasillaAbierta(int fila,int columna){
		if(!casillas[fila][columna].isAbierta()){
			numCasillasAbiertas++;
			casillas[fila][columna].setAbierta(true);
		}
	}


	public static void main(String args[]){
		Tablero tabla=new Tablero(5,15,8);
		tabla.imprimirTablero();
	}

	public void seleccionarMinar(int fila, int columna) {
		if(casillas[fila][columna].isMina()){
			eventoMinaAbierta.accept(casillas[fila][columna]);
			numMinasAbiertas++;
			if(partidaGanada())
				eventoPartidaGanada.accept(obtenerLIstaMinas());
		}
		else
			eventoPartidaPerdida.accept(obtenerLIstaMinas());	
	}

}
