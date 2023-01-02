package Logica;

public class Casilla {
	private int posFila;
	private int posColumna;
	private boolean mina;
	private int numMinasAlrededor;
	private boolean abierta;
	
	
	
	public Casilla(int posFila, int posColumna) {
		this.posFila = posFila;
		this.posColumna = posColumna;
		abierta=false;
	}
	
	public boolean isAbierta() {
		return abierta;
	}

	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}

	public int getPosFila() {
		return posFila;
	}
	public void setPosFila(int posFila) {
		this.posFila = posFila;
	}
	public int getPosColumna() {
		return posColumna;
	}
	public void setPosColumna(int posColumna) {
		this.posColumna = posColumna;
	}
	public boolean isMina() {
		return mina;
	}
	public void setMina(boolean mina) {
		this.mina = mina;
	}
	public int getNumMinasAlrededor() {
		return numMinasAlrededor;
	}
	public void setNumMinasAlrededor(int numMinasAlrededor) {
		this.numMinasAlrededor = numMinasAlrededor;
	}
	public void incrementar(){
		numMinasAlrededor++;
	}
	
	
}
