package es.florida.ad_aev2;

/**
 * clase principal de la app
 * 
 * @author Ricardo Munyoz Pastor
 */
public class Principal {

	/**
	 * metodo principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(modelo, vista);
	}
}
