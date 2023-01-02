package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SwingConstants;

public class Opciones extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JButton btnCancelar;
	private JButton btnAceptar;
	@SuppressWarnings("rawtypes")
	private static JComboBox comboBox;
	private JLabel lblDificultad;
	private static JCheckBox checkOptions;
	private JPanel panel_1;
	private JLabel lblMinas;
	private JLabel lblFila;
	private static JSpinner spFilas;
	private static JSpinner spMinas;
	private  int mines=10;
	private int fila=10;
	private int columna=10;
	private Juego juego;
	private JLabel lblColumna;
	private static JSpinner spColumnas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Opciones dialog = new Opciones(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Opciones(Juego juego) {
		this.juego=juego;
		setResizable(false);
		setTitle("New games options");
		setType(Type.UTILITY);
		setBounds(100, 100, 391, 270);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getPanel());
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 0, 385, 241);
			panel.setLayout(null);
			panel.add(getBtnCancelar());
			panel.add(getBtnAceptar());
			panel.add(getComboBox());
			panel.add(getLblDificultad());
			panel.add(getCheckOptions());
			panel.add(getPanel_1());
		}
		return panel;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancel");
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnCancelar.setFocusable(false);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setBounds(286, 207, 89, 23);
		}
		return btnCancelar;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Accept");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(checkOptions.isSelected()){
						mines=Integer.parseInt(spMinas.getValue().toString());
						fila=Integer.parseInt(spFilas.getValue().toString());
						columna=Integer.parseInt(spColumnas.getValue().toString());
					}
					else
						seleccionDeComboBox();
					juego.setNumMinas(mines);
					juego.setNumColumnas(columna);
					juego.setNumFilas(fila);
					juego.nuevoJuego();
					dispose();
				}
			});
			btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnAceptar.setFocusable(false);
			btnAceptar.setBounds(187, 207, 89, 23);
		}
		return btnAceptar;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Easy", "Normal", "Hard", "Very Hard", "Legendary"}));
			comboBox.setBounds(115, 22, 143, 23);
		}
		return comboBox;
	}
	private JLabel getLblDificultad() {
		if (lblDificultad == null) {
			lblDificultad = new JLabel("Dificulty");
			lblDificultad.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblDificultad.setBounds(29, 11, 89, 42);
		}
		return lblDificultad;
	}
	private JCheckBox getCheckOptions() {
		if (checkOptions == null) {
			checkOptions = new JCheckBox("Avanced options\r\n");
			checkOptions.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(checkOptions.isSelected()){
						comboBox.setEnabled(false);
						panel_1.setEnabled(true);
						spFilas.setEnabled(true);
						spMinas.setEnabled(true);
						lblFila.setEnabled(true);
						lblMinas.setEnabled(true);
						lblColumna.setEnabled(true);
						spColumnas.setEnabled(true);
					}
					else{
						comboBox.setEnabled(true);
						panel_1.setEnabled(false);
						spFilas.setEnabled(false);
						spMinas.setEnabled(false);
						lblFila.setEnabled(false);
						lblMinas.setEnabled(false);
						lblColumna.setEnabled(false);
						spColumnas.setEnabled(false);
					}
				}
			});
			checkOptions.setBounds(10, 61, 120, 23);
		}
		return checkOptions;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setEnabled(false);
			panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Avanced Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(10, 91, 355, 105);
			panel_1.setLayout(null);
			panel_1.add(getLblMinas());
			panel_1.add(getLblFila());
			panel_1.add(getSpFilas());
			panel_1.add(getSpMinas());
			panel_1.add(getLblColumna());
			panel_1.add(getSpColumnas());
		}
		return panel_1;
	}
	private JLabel getLblMinas() {
		if (lblMinas == null) {
			lblMinas = new JLabel("Mines");
			lblMinas.setHorizontalAlignment(SwingConstants.RIGHT);
			lblMinas.setEnabled(false);
			lblMinas.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblMinas.setBounds(171, 33, 60, 14);
		}
		return lblMinas;
	}
	private JLabel getLblFila() {
		if (lblFila == null) {
			lblFila = new JLabel("Filas");
			lblFila.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFila.setEnabled(false);
			lblFila.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblFila.setBounds(24, 33, 46, 14);
		}
		return lblFila;
	}
	private JSpinner getSpFilas() {
		if (spFilas == null) {
			spFilas = new JSpinner();
			spFilas.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
				calibrarMinas();
				}
			});
			spFilas.setModel(new SpinnerNumberModel(10, 2, 15, 1));
			spFilas.setEnabled(false);
			spFilas.setBounds(91, 31, 60, 20);
		}
		return spFilas;
	}
	private JSpinner getSpMinas() {
		if (spMinas == null) {
			spMinas = new JSpinner();
			spMinas.setModel(new SpinnerNumberModel(8, 1, 99, 1));
			spMinas.setEnabled(false);
			spMinas.setBounds(248, 31, 60, 20);
		}
		return spMinas;
	}
	public void seleccionDeComboBox(){
		String dificult=comboBox.getSelectedItem().toString();
		switch (dificult) {
		case "Easy":mines=5;fila=10;columna=10;break;
		case "Normal":mines=15;fila=10;columna=10;break;
		case "Hard":mines=30;fila=15;columna=20;break;
		case "Very Hard":mines=40;fila=15;columna=30;break;
		case "Legendary":mines=200;fila=15;columna=30;break;
		}
	}
	private JLabel getLblColumna() {
		if (lblColumna == null) {
			lblColumna = new JLabel("Columnas");
			lblColumna.setHorizontalAlignment(SwingConstants.RIGHT);
			lblColumna.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblColumna.setEnabled(false);
			lblColumna.setBounds(10, 69, 60, 14);
		}
		return lblColumna;
	}
	private JSpinner getSpColumnas() {
		if (spColumnas == null) {
			spColumnas = new JSpinner();
			spColumnas.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calibrarMinas();
				}
			});
			spColumnas.setModel(new SpinnerNumberModel(10, 2, 30, 1));
			spColumnas.setEnabled(false);
			spColumnas.setBounds(91, 67, 60, 20);
		}
		return spColumnas;
	}
	public void calibrarMinas(){
		int fila=Integer.parseInt(spFilas.getValue().toString());
		int columna=Integer.parseInt(spColumnas.getValue().toString());
		int mina=Integer.parseInt(spMinas.getValue().toString());
		int valor=(fila*columna)-1;
		if(mina>valor)
			spMinas.setModel(new SpinnerNumberModel(valor, 1,valor, 1));
		else
			spMinas.setModel(new SpinnerNumberModel(mina, 1,valor, 1));
	}
}
