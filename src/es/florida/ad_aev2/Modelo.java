package es.florida.ad_aev2;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * clase Modelo (MVC)
 * 
 * @author Ricardo Munyoz Pastor
 */
public class Modelo {

	String ficheroAdmin = "admin.xml";
	String ficheroClient = "client.xml";
	Connection con;
	Statement stmt;

	/**
	 * metodo para leer la informacion de conexion desde client.xml y establecer una
	 * conexion
	 */
	public Modelo() {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File(ficheroClient));
			String usuario = document.getElementsByTagName("usuario").item(0).getTextContent();
			String contrasenya = document.getElementsByTagName("contrasena").item(0).getTextContent();
			String url = document.getElementsByTagName("url").item(0).getTextContent();
			// System.out.println(usuario);
			// System.out.println(contrasenya);
			// System.out.println(url);
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, usuario, contrasenya);
			stmt = con.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * metodo para verificar las credenciales
	 * 
	 * @param name     nombre de usuario
	 * @param password contrasenya
	 * @return bool res
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public boolean comprobarCredenciales(String name, String password)
			throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException {
		boolean res = false;
		try {
			// System.out.println("Comprobar credenciales");
			ResultSet rs = stmt.executeQuery("SELECT * FROM users");
			// System.out.println(rs);
			while (rs.next()) {
				String user = rs.getString(2);
				// System.out.println(user);
				String pass = rs.getString(3);
				// System.out.println(pass);
				String tipo = rs.getString(4);
				// System.out.println(tipo);
				if (user.equals(name) && pass.equals(password)) {
					if (tipo.equals("admin")) {
						stmt.close();
						con.close();

						DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
						Document document = dBuilder.parse(new File(ficheroAdmin));
						String usuario = document.getElementsByTagName("usuario").item(0).getTextContent();
						String contrasenya = document.getElementsByTagName("contrasena").item(0).getTextContent();
						String url = document.getElementsByTagName("url").item(0).getTextContent();

						Class.forName("com.mysql.cj.jdbc.Driver");
						con = DriverManager.getConnection(url, usuario, contrasenya);
						stmt = con.createStatement();
					}
					res = true;
					break;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return res;
		}
		return res;
	}
}