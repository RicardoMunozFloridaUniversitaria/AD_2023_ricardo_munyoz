package es.florida.ad_aev1;

/**
 * clase donde construimos el tipo Fichero con los atributos y metodos que necesitamos en Aev1
 * @author Ricardo Munyoz Pastor
 */
public class Fichero {

	public long peso;
	public String nombre;
	public long ultimaMod;
	
	public Fichero(long peso, String nombre, long ultimaMod) {
		this.peso = peso;
		this.nombre = nombre;
		this.ultimaMod = ultimaMod;
	}
}
