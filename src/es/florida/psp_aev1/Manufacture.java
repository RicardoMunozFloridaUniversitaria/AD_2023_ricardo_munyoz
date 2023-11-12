package es.florida.psp_aev1;

import java.awt.EventQueue;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
/**
 * clase Manufacture donde esta casi todo el codigo excepto la GUI aunque la GUI se inicia desde aqui
 * @author Ricardo Munyoz Pastor
 */
public class Manufacture {
	private static final ExecutorService executor = Executors.newFixedThreadPool(8);
	private static List<String> piezasCompletadas = new ArrayList<>();
	private static BlockingQueue<String> colaFabricacion = new LinkedBlockingQueue<>();
	
	/**
	 * metodo principal que inicia la interfaz grafica, entra en el bucle hasta que executor termine
	 * durante el bucle crea una pieza de la cola y al finalizar el bucle escribe el fichero
	 * @param args 
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		iniciarGUI();
		while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
			try {
				String pieza = colaFabricacion.take();
				empezarFabricar(pieza);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		escribirFichero();
	}

	/**
	 * metodo para iniciar la interfaz gráfica  
	 */
	private static void iniciarGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Order orderWindow = new Order();
				orderWindow.getFrame().setVisible(true);
			}
		});
	}

	/**
	 * metodo que empieza la fabricacion de una pieza   
	 * @param pieza que se tiene que empezar a crear
	 */
	private static void empezarFabricar(String pieza) {
		executor.execute(() -> {
			crearPieza(pieza);
		});
	}

	/**
	 * metodo que crea la pieza tomando en cuenta el tiempo de fabricacion, cuando crea una pieza
	 * la agrega a piezasCompletadas e imprime el momento en el que ha sido creada 
	 * @param pieza que se va a crear
	 */
	private static synchronized void crearPieza(String pieza) {
		int tiempoFabricacion = getTiempoFabricacion(pieza);

		synchronized (Manufacture.class) {
			procesoFabricacion(tiempoFabricacion);

			String guardaHora = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String resultado = pieza + "_" + guardaHora;
			piezasCompletadas.add(resultado);
			System.out.println(resultado);
		}
	}

	/**
	 * metodo que añade tiempo a la fabricacion de cada pieza
	 * @param tiempoFabricacion tiempo de fabricacion que tiene cada pieza
	 */
	private static void procesoFabricacion(int tiempoFabricacion) {
		long tiempoInicio = System.currentTimeMillis();
		long tiempoFin = tiempoInicio + tiempoFabricacion;
		int iteracciones = 0;

		while (System.currentTimeMillis() < tiempoFin) {
			for (int i = 0; i < 1000; i++) {
				iteracciones++;
			}
		}
	}

	/**
	 * crea un fichero de nombre LOG_horaDeCreacion.txt en el que escribe todas 
	 * las piezas completadas de piezasCompletadas
	 */
	private static void escribirFichero() {
		String guardaHora = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String nombreArchivo = "LOG_" + guardaHora + ".txt";

		try (FileWriter writer = new FileWriter(nombreArchivo)) {
			for (String pieza : piezasCompletadas) {
				writer.write(pieza + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * metodo para devolver el tiempo de fabriciacion segun la pieza
	 * @param pieza tipo de pieza
	 * @return devuelve el tiempo de fabricacion segun la pieza
	 */
	private static int getTiempoFabricacion(String pieza) {
		switch (pieza) {
		case "I":
			return 1000;
		case "O":
			return 2000;
		case "T":
			return 3000;
		case "J":
		case "L":
			return 4000;
		case "S":
		case "Z":
			return 5000;
		default:
			return 0;
		}
	}

	/**
	 * agrega una cantidad de un tipo de piezas concreta a la cola
	 * @param pieza tipo de pieza
	 * @param cantidad de pieza a anyadir
	 */
	public static void anyadirCola(String pieza, int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			try {
				colaFabricacion.put(pieza);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}