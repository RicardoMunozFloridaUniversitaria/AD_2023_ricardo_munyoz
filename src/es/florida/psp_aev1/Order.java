package es.florida.psp_aev1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
/**
 * clase Order clase creada con el windowbuilder con el codigo de la interfaz 
 * @author Ricardo Munyoz Pastor
 */
public class Order {

	private JFrame frame;
	private JTextField textFieldI;
	private JTextField textFieldO;
	private JTextField textFieldT;
	private JTextField textFieldJ;
	private JTextField textFieldL;
	private JTextField textFieldS;
	private JTextField textFieldZ;

	/**
	 * Create the application.
	 */
	public Order() {
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

		JLabel lblCantidadPiezas = new JLabel("Selecciona una cantidad de piezas para cada tipo.");
		lblCantidadPiezas.setFont(new Font("Arial", Font.BOLD, 14));
		lblCantidadPiezas.setBounds(54, 0, 418, 53);
		frame.getContentPane().add(lblCantidadPiezas);

		JLabel lblI = new JLabel("I:");
		lblI.setFont(new Font("Arial", Font.BOLD, 12));
		lblI.setBounds(54, 79, 27, 14);
		frame.getContentPane().add(lblI);

		textFieldI = new JTextField();
		textFieldI.setBounds(64, 76, 40, 20);
		frame.getContentPane().add(textFieldI);
		textFieldI.setColumns(10);

		JLabel lblO = new JLabel("O:");
		lblO.setFont(new Font("Arial", Font.BOLD, 12));
		lblO.setBounds(126, 79, 18, 14);
		frame.getContentPane().add(lblO);

		textFieldO = new JTextField();
		textFieldO.setColumns(10);
		textFieldO.setBounds(147, 76, 40, 20);
		frame.getContentPane().add(textFieldO);

		JLabel lblT = new JLabel("T:");
		lblT.setFont(new Font("Arial", Font.BOLD, 12));
		lblT.setBounds(230, 79, 18, 14);
		frame.getContentPane().add(lblT);

		textFieldT = new JTextField();
		textFieldT.setColumns(10);
		textFieldT.setBounds(247, 76, 40, 20);
		frame.getContentPane().add(textFieldT);

		JLabel lblJ = new JLabel("J:");
		lblJ.setFont(new Font("Arial", Font.BOLD, 12));
		lblJ.setBounds(320, 79, 18, 14);
		frame.getContentPane().add(lblJ);

		textFieldJ = new JTextField();
		textFieldJ.setColumns(10);
		textFieldJ.setBounds(338, 76, 40, 20);
		frame.getContentPane().add(textFieldJ);

		JLabel lblL = new JLabel("L:");
		lblL.setFont(new Font("Arial", Font.BOLD, 12));
		lblL.setBounds(86, 123, 18, 14);
		frame.getContentPane().add(lblL);

		textFieldL = new JTextField();
		textFieldL.setColumns(10);
		textFieldL.setBounds(104, 120, 40, 20);
		frame.getContentPane().add(textFieldL);

		JLabel lblS = new JLabel("S:");
		lblS.setFont(new Font("Arial", Font.BOLD, 12));
		lblS.setBounds(179, 123, 18, 14);
		frame.getContentPane().add(lblS);

		textFieldS = new JTextField();
		textFieldS.setColumns(10);
		textFieldS.setBounds(197, 120, 40, 20);
		frame.getContentPane().add(textFieldS);

		JLabel lblZ = new JLabel("Z:");
		lblZ.setFont(new Font("Arial", Font.BOLD, 12));
		lblZ.setBounds(277, 123, 18, 14);
		frame.getContentPane().add(lblZ);

		textFieldZ = new JTextField();
		textFieldZ.setColumns(10);
		textFieldZ.setBounds(298, 120, 40, 20);
		frame.getContentPane().add(textFieldZ);

		JButton btnIniciarFabricacion = new JButton("Iniciar Fabricación");
		btnIniciarFabricacion.setFont(new Font("Arial", Font.PLAIN, 11));
		btnIniciarFabricacion.setBounds(138, 176, 122, 23);
		frame.getContentPane().add(btnIniciarFabricacion);
		btnIniciarFabricacion.addActionListener(e -> {
			iniciarFabricacion();
		});

	}

	/**
	 *obtiene la cantidad de piezas por tipo que se quieren crear y las agrega a la cola con Manufacture.anyadirCola 
	 */
	private void iniciarFabricacion() {
		int cantidadI = Integer.parseInt(textFieldI.getText());
		int cantidadO = Integer.parseInt(textFieldO.getText());
		int cantidadT = Integer.parseInt(textFieldT.getText());
		int cantidadJ = Integer.parseInt(textFieldJ.getText());
		int cantidadL = Integer.parseInt(textFieldL.getText());
		int cantidadS = Integer.parseInt(textFieldS.getText());
		int cantidadZ = Integer.parseInt(textFieldZ.getText());

		Manufacture.anyadirCola("I", cantidadI);
		Manufacture.anyadirCola("O", cantidadO);
		Manufacture.anyadirCola("T", cantidadT);
		Manufacture.anyadirCola("J", cantidadJ);
		Manufacture.anyadirCola("L", cantidadL);
		Manufacture.anyadirCola("S", cantidadS);
		Manufacture.anyadirCola("Z", cantidadZ);
	}

	/**
	 * metodo para poder manipular la interfaz desde la clase Manufacture
	 * @return devuelve el JFrame creado en esta clase
	 */
	public JFrame getFrame() {
		return frame;
	}
}
