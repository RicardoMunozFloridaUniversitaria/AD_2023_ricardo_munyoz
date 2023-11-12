package es.florida.ad_aev2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * clase Controlador (MVC)
 * 
 * @author Ricardo Munyoz Pastor
 */
public class Controlador {
	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListenerLogin;

	/**
	 * metodo que permite la interaccion con modelo y vista
	 * 
	 * @param modelo
	 * @param vista
	 */
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control();
	}

	/**
	 * metodo que configura los controladores de eventos para la interfaz de usuario
	 */
	public void control() {
		actionListenerLogin = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// System.out.println("Boton login apretado");
				String textoUsuario = vista.getTextFieldUsuario().getText();
				String textoPass = vista.getTextFieldContrasenya().getText();
				try {
					boolean res = modelo.comprobarCredenciales(textoUsuario, textoPass);
					// System.out.println(res);
					if (res == false) {
						// System.out.println("Credenciales inválidas.");
						JOptionPane.showMessageDialog(null, "Credenciales inválidas.", "Credenciales inválidas.",
								JOptionPane.ERROR_MESSAGE);
					} else {
						System.out.println("Credenciales válidas.");
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		vista.getBtnLogin().addActionListener(actionListenerLogin);
	}
}
