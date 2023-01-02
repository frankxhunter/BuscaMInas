package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GameOver extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Juego juego;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblGameOver;
	private JButton btnNuevo;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOver frame = new GameOver(null,0);
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
	public GameOver(Juego juego,int condicion) {
		setResizable(false);
		setTitle("Game Over");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel());
		this.juego=juego;
		if(condicion==1)
			victoria();
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 0, 444, 152);
			panel.setLayout(null);
			panel.add(getLblGameOver());
			panel.add(getBtnNuevo());
			panel.add(getBtnCancelar());
		}
		return panel;
	}
	private JLabel getLblGameOver() {
		if (lblGameOver == null) {
			lblGameOver = new JLabel("You lost :(");
			lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
			lblGameOver.setFont(new Font("Tahoma", Font.PLAIN, 37));
			lblGameOver.setBounds(10, 11, 424, 81);
			
		}
		return lblGameOver;
	}
	public void victoria(){
		lblGameOver.setText("Congratulations :)");
		lblGameOver.setForeground(Color.GREEN);
		setTitle("You won");
	}
	private JButton getBtnNuevo() {
		if (btnNuevo == null) {
			btnNuevo = new JButton("New");
			btnNuevo.setFocusable(false);
			btnNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					juego.nuevoJuego();
					dispose();
				}
			});
			btnNuevo.setBounds(246, 118, 89, 23);
		}
		return btnNuevo;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancel");
			btnCancelar.setFocusable(false);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setBounds(345, 118, 89, 23);
		}
		return btnCancelar;
	}
}
