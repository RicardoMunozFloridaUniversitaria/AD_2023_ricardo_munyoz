package es.florida.ad_aev1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import java.awt.Label;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

/**clase por defecto WindowBuilder
 * @author Ricardo Munyoz Pastor
 */
public class Aev1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldRuta;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldBuscarString;
	private JTextField textFieldNombreFusion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aev1 frame = new Aev1();
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
	public Aev1() {
		List<Fichero> listaFicheros = new ArrayList<>();
		List<File> archivosSeleccionados = new ArrayList<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 625);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelRutaDirectorio = new JPanel();
		panelRutaDirectorio.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelRutaDirectorio.setBounds(10, 11, 593, 579);
		contentPane.add(panelRutaDirectorio);
		panelRutaDirectorio.setLayout(null);

		textFieldRuta = new JTextField();
		textFieldRuta.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldRuta.setBounds(139, 25, 263, 30);
		panelRutaDirectorio.add(textFieldRuta);
		textFieldRuta.setColumns(10);

		JLabel lblRuta = new JLabel("Ruta Directorio:");
		lblRuta.setFont(new Font("Arial", Font.BOLD, 14));
		lblRuta.setBounds(10, 24, 119, 30);
		panelRutaDirectorio.add(lblRuta);

		JButton btnRuta = new JButton("Buscar");
		btnRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaFicheros.clear();
				String textRuta = textFieldRuta.getText();
				File file = new File(textRuta);
				FiltroExtension filtro = new FiltroExtension(".txt");
				String[] lista = file.list(filtro);
				File[] archivos = file.listFiles(filtro);
				int contador = 0;
				String nombre = null;
				String extension = null;
				long tamanyo;
				String fecha;
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				int j = 0;
				for (int i = 0; i < lista.length; i++) {
					long fechaMod = archivos[j].lastModified();
					fecha = formato.format(fechaMod);
					tamanyo = archivos[j].length() / 1024;
					String str = lista[i];
					int punto = str.indexOf('.');
					if (punto != -1) {
						nombre = str.substring(0, punto);
						extension = str.substring(punto);
					}
					contador++;
					j++;
					Fichero fichero = new Fichero(tamanyo, nombre, fechaMod);
					listaFicheros.add(fichero);
					JOptionPane.showMessageDialog(null,
							"Fichero " + contador + "\n" + "Nombre: " + nombre + "\n" + "Extensión: " + extension + "\n"
									+ "Tamaño: " + tamanyo + " KB" + "\n" + "Última modificación: " + fecha,
							"Ficheros con extensión .txt en el directorio.", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnRuta.setFont(new Font("Arial", Font.BOLD, 11));
		btnRuta.setBounds(441, 24, 89, 30);
		panelRutaDirectorio.add(btnRuta);

		JRadioButton rdbtnNombreAscendente = new JRadioButton("Nombre y Ascendente.");
		rdbtnNombreAscendente.setSelected(true);
		buttonGroup.add(rdbtnNombreAscendente);
		rdbtnNombreAscendente.setFont(new Font("Arial", Font.PLAIN, 11));
		rdbtnNombreAscendente.setBounds(20, 94, 146, 23);
		panelRutaDirectorio.add(rdbtnNombreAscendente);

		JRadioButton rdbtnNombreDescendente = new JRadioButton("Nombre y Descendente.");
		buttonGroup.add(rdbtnNombreDescendente);
		rdbtnNombreDescendente.setFont(new Font("Arial", Font.PLAIN, 11));
		rdbtnNombreDescendente.setBounds(20, 123, 146, 23);
		panelRutaDirectorio.add(rdbtnNombreDescendente);

		JRadioButton rdbtnTamanyoAscendente = new JRadioButton("Tamaño y Ascendente.");
		buttonGroup.add(rdbtnTamanyoAscendente);
		rdbtnTamanyoAscendente.setFont(new Font("Arial", Font.PLAIN, 11));
		rdbtnTamanyoAscendente.setBounds(168, 94, 146, 23);
		panelRutaDirectorio.add(rdbtnTamanyoAscendente);

		JRadioButton rdbtnTamanyoDescendente = new JRadioButton("Tamaño y Descendente.");
		buttonGroup.add(rdbtnTamanyoDescendente);
		rdbtnTamanyoDescendente.setFont(new Font("Arial", Font.PLAIN, 11));
		rdbtnTamanyoDescendente.setBounds(168, 123, 146, 23);
		panelRutaDirectorio.add(rdbtnTamanyoDescendente);

		JRadioButton rdbtnFechaAscendente = new JRadioButton("Última modificación y Ascendente.");
		buttonGroup.add(rdbtnFechaAscendente);
		rdbtnFechaAscendente.setFont(new Font("Arial", Font.PLAIN, 11));
		rdbtnFechaAscendente.setBounds(316, 94, 191, 23);
		panelRutaDirectorio.add(rdbtnFechaAscendente);

		JRadioButton rdbtnFechaDescendiente = new JRadioButton("Última modificación y Descendente. ");
		buttonGroup.add(rdbtnFechaDescendiente);
		rdbtnFechaDescendiente.setFont(new Font("Arial", Font.PLAIN, 11));
		rdbtnFechaDescendiente.setBounds(316, 123, 214, 23);
		panelRutaDirectorio.add(rdbtnFechaDescendiente);

		Label lblFiltrado = new Label("Elige como filtrar la búsqueda:");
		lblFiltrado.setFont(new Font("Arial", Font.BOLD, 12));
		lblFiltrado.setBounds(20, 67, 360, 21);
		panelRutaDirectorio.add(lblFiltrado);

		JLabel lblBuscarString = new JLabel("Introduce la palabra que quieres buscar:");
		lblBuscarString.setFont(new Font("Arial", Font.BOLD, 12));
		lblBuscarString.setBounds(21, 188, 243, 14);
		panelRutaDirectorio.add(lblBuscarString);

		textFieldBuscarString = new JTextField();
		textFieldBuscarString.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldBuscarString.setBounds(262, 180, 203, 30);
		panelRutaDirectorio.add(textFieldBuscarString);
		textFieldBuscarString.setColumns(10);

		JButton btnBuscarString = new JButton("Buscar");
		btnBuscarString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textRuta = textFieldRuta.getText();
				String textBuscarString = textFieldBuscarString.getText();
				File file = new File(textRuta);
				FiltroExtension filtro = new FiltroExtension(".txt");
				String[] lista = file.list(filtro);
				File[] archivos = file.listFiles(filtro);
				int contador = 0;
				String nombre = null;
				int contadorTotal = 0;
				for (File fichero : archivos) {
					if (fichero.isFile()) {
						int contadorArchivo = contarCadenaEnArchivo(fichero, textBuscarString);
						contadorTotal += contadorArchivo;
						JOptionPane.showMessageDialog(null,
								"Encontrado " + contadorArchivo + " veces en " + fichero.getName(),
								"Coincidencias de la string introducida en los archivos .txt del directorio.",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				for (int i = 0; i < lista.length; i++) {
					String str = lista[i];
					int punto = str.indexOf('.');
					if (punto != -1) {
						nombre = str.substring(0, punto);
					}

					contador++;
				}
			}
		});
		btnBuscarString.setFont(new Font("Arial", Font.BOLD, 11));
		btnBuscarString.setBounds(475, 180, 89, 27);
		panelRutaDirectorio.add(btnBuscarString);
		JLabel lblInfoBuscar = new JLabel(
				"(Primero introduzca la ruta en el primer campo luego la palabra a buscar en el segundo y presione el botón de Buscar.)  ");
		lblInfoBuscar.setFont(new Font("Arial", Font.ITALIC, 10));
		lblInfoBuscar.setBounds(10, 213, 573, 14);
		panelRutaDirectorio.add(lblInfoBuscar);

		JButton btnFusionar = new JButton("Fusionar Archivos");
		btnFusionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setMultiSelectionEnabled(true);
				int resultado = fileChooser.showOpenDialog(contentPane);
				if (resultado == JFileChooser.APPROVE_OPTION) {
					archivosSeleccionados.clear();
					File[] archivos = fileChooser.getSelectedFiles();
					File copia = null;
					if (archivos.length > 1) {
						String rutaAbsoluta = archivos[0].getAbsolutePath();
						Path rutaDirectorio = Paths.get(rutaAbsoluta);
						Path ruta = rutaDirectorio.getParent();
						if (textFieldNombreFusion.getText().compareTo("") == 0) {
							String temporal = ruta.toString();
							copia = new File(temporal, "archivoCopia");
						} else {
							String temporal = ruta.toString();
							copia = new File(temporal, textFieldNombreFusion.getText());
						}
						if (copia.exists()) {
							Object[] options = { "Si", "No" };
							JOptionPane.showOptionDialog(null, "¿Quieres sobreescribir el archivo?",
									"Error de sobreescritura", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
									null, options, options[0]);
						}
						for (File nombreArchivo : archivos) {
							FileReader reader;
							try {
								FileWriter writer = new FileWriter(copia, true);
								reader = new FileReader(nombreArchivo);
								BufferedReader bufferedReader = new BufferedReader(reader);
								String linea;
								while ((linea = bufferedReader.readLine()) != null) {
									writer.write(linea + "\n");
								}
								bufferedReader.close();
								writer.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Por favor introduce dos o mas archivos.",
								"Fusionar dos o mas archivos.", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnFusionar.setFont(new Font("Arial", Font.BOLD, 12));
		btnFusionar.setBounds(195, 421, 146, 30);
		panelRutaDirectorio.add(btnFusionar);

		JLabel lblInfoFusiion = new JLabel(
				"(En caso de no rellenar el campo de nuevo nombre el archivo se llamará archivoCopia.) ");
		lblInfoFusiion.setFont(new Font("Arial", Font.ITALIC, 10));
		lblInfoFusiion.setBounds(107, 373, 415, 14);
		panelRutaDirectorio.add(lblInfoFusiion);

		textFieldNombreFusion = new JTextField();
		textFieldNombreFusion.setBounds(262, 329, 203, 33);
		panelRutaDirectorio.add(textFieldNombreFusion);
		textFieldNombreFusion.setColumns(10);

		JLabel lblNewLabel = new JLabel("Introduce el nombre archivo resultante:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(20, 329, 234, 33);
		panelRutaDirectorio.add(lblNewLabel);
	}

	/**
	 *  metodo para contar las ocurrencias de una string dada en un fichero concreto
	 * @param cadenaBuscada es la palabra introducida que queremos buscar  
	 * @param archivo es el archivo donde se busca
	 * @return nos devuelve el valor de las coincidencias con contador
	 */
	public static int contarCadenaEnArchivo(File archivo, String cadenaBuscada) {
		int contador = 0;
		try {
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				contador += contarCadenaEnLinea(linea, cadenaBuscada);
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contador;
	}

	/**
	 *  metodo para contar las ocurrencias de una string en una linea (metodo para usar en contarCadenaArchivo)
	 * @param cadenaBuscada es la palabra a buscar 
	 * @param linea es la linea en la que esta buscando  
	 * @return devuelve el numero contador que guarda el numero de coincidencias 
	 */
	public static int contarCadenaEnLinea(String linea, String cadenaBuscada) {
		int contador = 0;
		int index = 0;
		while ((index = linea.indexOf(cadenaBuscada, index)) != -1) {
			contador++;
			index += cadenaBuscada.length();
		}
		return contador;
	}
}
