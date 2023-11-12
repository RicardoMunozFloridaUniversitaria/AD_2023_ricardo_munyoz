package es.florida.ad_aev2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * clase Vista (MVC)
 * 
 * @author Ricardo Munyoz Pastor
 */
public class Vista {

	private JFrame frame;
	private JTextField textFieldUsuario;
	private JTextField textFieldContrasenya;
	private JButton btnLogin;

	/**
	 * Create the application.
	 */
	public Vista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblInicioSesion = new JLabel("Inicio de sesión.");
		lblInicioSesion.setFont(new Font("Arial", Font.BOLD, 16));
		lblInicioSesion.setBounds(149, 11, 134, 52);
		frame.getContentPane().add(lblInicioSesion);

		JLabel lblUsuario = new JLabel("Nombre de usuario:");
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 12));
		lblUsuario.setBounds(10, 82, 120, 14);
		frame.getContentPane().add(lblUsuario);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(126, 79, 254, 20);
		frame.getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		JLabel lblContrasenya = new JLabel("Contraseña:");
		lblContrasenya.setFont(new Font("Arial", Font.BOLD, 12));
		lblContrasenya.setBounds(37, 140, 79, 14);
		frame.getContentPane().add(lblContrasenya);

		textFieldContrasenya = new JTextField();
		textFieldContrasenya.setBounds(126, 137, 254, 20);
		frame.getContentPane().add(textFieldContrasenya);
		textFieldContrasenya.setColumns(10);

		btnLogin = new JButton("Entrar.");
		btnLogin.setFont(new Font("Arial", Font.BOLD, 13));
		btnLogin.setBounds(158, 193, 105, 35);
		frame.getContentPane().add(btnLogin);

		this.frame.setVisible(true);
	}

	/**
	 * metodo para recoger lo que el usuario introduce en el TextFieldUsuario
	 * 
	 * @return texto en el campo de usuario
	 */
	public JTextField getTextFieldUsuario() {
		return textFieldUsuario;
	}

	/**
	 * metodo para recoger lo que el usuario introduce en el TextFieldContrasenya
	 * 
	 * @return texto en el campo de contrasenya
	 */
	public JTextField getTextFieldContrasenya() {
		return textFieldContrasenya;
	}

	/**
	 * metodo para recoger la pulsacion del boton de login
	 * 
	 * @return pulsa boton
	 */
	public JButton getBtnLogin() {
		return btnLogin;
	}

	/**
	 * getter del frame
	 * 
	 * @return frame
	 */
	public JFrame getFrame() {
		return frame;
	}
}
