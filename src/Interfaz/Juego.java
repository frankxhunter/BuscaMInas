package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.Casilla;
import Logica.Tablero;

public class Juego extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int numFilas=10;
	private int numColumnas=10;
	private int numMinas=10;
	private JButton[][]botonesTablero;
	private Tablero tablero;
	private JMenuBar menuBar;
	private JMenu Men;
	private JMenuItem mntmNuevoJuego;
	private int estado;
	private JMenu mnMore;
	private JMenuItem mntmHelp;
	private boolean victoria=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juego frame = new Juego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Juego() {
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Juego.class.getResource("/Iconos/mine.png")));
		setResizable(false);
		setTitle("Busca Minas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		nuevoJuego();

	}

	public int getNumFilas() {
		return numFilas;
	}

	public void setNumFilas(int numFilas) {
		this.numFilas = numFilas;
	}

	public int getNumColumnas() {
		return numColumnas;
	}

	public void setNumColumnas(int numColumnas) {
		this.numColumnas = numColumnas;
	}

	public int getNumMinas() {
		return numMinas;
	}

	public void setNumMinas(int numMinas) {
		this.numMinas = numMinas;
	}

	private void crearTablero(){
		tablero=new Tablero(numFilas, numColumnas,numMinas);
		
		tablero.setEventoPartidaPerdida(new Consumer<ArrayList<Casilla>>(){
			public void accept(ArrayList<Casilla> t){
				for(Casilla x:t){
					botonesTablero[x.getPosFila()][x.getPosColumna()].setEnabled(false);
					botonesTablero[x.getPosFila()][x.getPosColumna()].
					setIcon(new ImageIcon(Juego.class.getResource("/Iconos/mine.png"))); 
					botonesTablero[x.getPosFila()][x.getPosColumna()].
					setDisabledIcon(new ImageIcon(Juego.class.getResource("/Iconos/mine.png"))); 
					botonesTablero[x.getPosFila()][x.getPosColumna()].setBackground(null);
				}
				GameOver x=new GameOver(getThis(),0);
				estado=-1;
				x.setLocationRelativeTo(getThis());
				x.setModal(true);
				x.setVisible(true);
			}
		});
		
		tablero.setEventoPartidaGanada(new Consumer<ArrayList<Casilla>>(){
			public void accept(ArrayList<Casilla> t){
				if(tablero.partidaGanada() && !victoria){
				for(Casilla x:t){
					botonesTablero[x.getPosFila()][x.getPosColumna()].setEnabled(false);
					botonesTablero[x.getPosFila()][x.getPosColumna()].
					setIcon(new ImageIcon(Juego.class.getResource("/Iconos/flag.png")));
					botonesTablero[x.getPosFila()][x.getPosColumna()].
					setDisabledIcon(new ImageIcon(Juego.class.getResource("/Iconos/flag.png"))); 
					botonesTablero[x.getPosFila()][x.getPosColumna()].setBackground(null);
				}
				GameOver x=new GameOver(getThis(),1);
				victoria=true;
				x.setLocationRelativeTo(getThis());
				estado=1;
				x.setModal(true);
				x.setVisible(true);
				}
			}
		});
		tablero.setEventoMinaAbierta(new Consumer<Casilla>(){
			@Override
			public void accept(Casilla t) {
				botonesTablero[t.getPosFila()][t.getPosColumna()].setBackground(null);
				botonesTablero[t.getPosFila()][t.getPosColumna()].setEnabled(false);
				botonesTablero[t.getPosFila()][t.getPosColumna()].
				setIcon(new ImageIcon(Juego.class.getResource("/Iconos/flag.png")));
				botonesTablero[t.getPosFila()][t.getPosColumna()].
				setDisabledIcon(new ImageIcon(Juego.class.getResource("/Iconos/flag.png")));
				
				
			}
		});
		tablero.setEventoCasillaAbierta(new Consumer<Casilla>(){
			public void accept(Casilla x){
				botonesTablero[x.getPosFila()][x.getPosColumna()].setEnabled(false);
				int cant=x.getNumMinasAlrededor();
				if(cant!=0){
					botonesTablero[x.getPosFila()][x.getPosColumna()].
					setIcon(new ImageIcon(Juego.class.getResource("/Iconos/"+cant+".png")));
					botonesTablero[x.getPosFila()][x.getPosColumna()].
					setDisabledIcon(new ImageIcon(Juego.class.getResource("/Iconos/"+cant+".png")));
				}else
					botonesTablero[x.getPosFila()][x.getPosColumna()].setText("");
				botonesTablero[x.getPosFila()][x.getPosColumna()].setBackground(null);
			}
		});
		tablero.imprimirTablero();
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMen());
			menuBar.add(getMnMore());
		}
		return menuBar;
	}
	private JMenu getMen() {
		if (Men == null) {
			Men = new JMenu("Menu");
			Men.add(getMntmNuevoJuego());
		}
		return Men;
	}
	private JMenuItem getMntmNuevoJuego() {
		if (mntmNuevoJuego == null) {
			mntmNuevoJuego = new JMenuItem("Options");
			mntmNuevoJuego.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Opciones x=new Opciones(getThis());
					x.setLocationRelativeTo(getThis());
					x.setModal(true);
					x.setVisible(true);
				}
			});
		}
		return mntmNuevoJuego;
	}
	private void btnClic(MouseEvent e) {
		if(estado==0){
			JButton btn=(JButton)e.getSource();
			String[] coordenada=btn.getName().split(",");
			int posFila=Integer.parseInt(coordenada[0]);
			int posColumna=Integer.parseInt(coordenada[1]);
			if(e.getButton()==MouseEvent.BUTTON1)
			tablero.seleccionarCasilla(posFila, posColumna);
			else if(e.getButton()==MouseEvent.BUTTON3)
				if(btn.isEnabled())
				tablero.seleccionarMinar(posFila, posColumna);
		}
	}

	public void cargarControles(){
		int ancho=45;
		int largo=45;
		int x=20;
		int y=20;
		int separacion=5;
		botonesTablero=new JButton[numFilas][numColumnas];
		for(int i=0;i<numFilas;i++){
			int auxY=y;
			for(int j=0; j<numColumnas;j++){
				botonesTablero[i][j]=new JButton();
				botonesTablero[i][j].setName(i+","+j);
				botonesTablero[i][j].setFocusable(false);
				botonesTablero[i][j].setBackground(new Color(240, 230, 140));
				botonesTablero[i][j].setBounds(auxY, x, ancho, largo);
				auxY+=ancho+separacion;
				botonesTablero[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
							btnClic(e);
					}
				});
			
				contentPane.add(botonesTablero[i][j]);
			}
			x+=largo+separacion;
		}
	}

	public void nuevoJuego(){
		removerControles();
		cargarControles();
		crearTablero();
		repaint();
		estado=0;
		setSize(botonesTablero[numFilas-1][numColumnas-1].getX()+80,
				botonesTablero[numFilas-1][numColumnas-1].getY()+125);
		setLocationRelativeTo(null);
		victoria=false;
	}

	public void removerControles(){
		if(botonesTablero!=null)
			for(int i=0;i<botonesTablero.length;i++)
				for(int j=0;j<botonesTablero[i].length;j++)
					getContentPane().remove(botonesTablero[i][j]);
	}

	public Juego getThis(){
		return this;
	}
	private JMenu getMnMore() {
		if (mnMore == null) {
			mnMore = new JMenu("More");
			mnMore.add(getMntmHelp());
		}
		return mnMore;
	}
	private JMenuItem getMntmHelp() {
		if (mntmHelp == null) {
			mntmHelp = new JMenuItem("Help");
			mntmHelp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String message="Hello\n El objetivo de esta juego es encontrar todas las minas"
							+ "cuando selecionas una \ncasilla se mostrara su contenido, si es una mina pierdes "
							+ "o sino, se \nmostrara un numero indicando el numero de mina alrededdror que hay a una casilla \n"
							+ "de distancia, tambien con clic derecho se puede seleccionar una casilla q sea \n"
							+ "una mina, pero si no es una minas habras perdido, Suerte :)";
					JOptionPane.showMessageDialog(getThis(), message);
				}
			});
		}
		return mntmHelp;
	}
}

