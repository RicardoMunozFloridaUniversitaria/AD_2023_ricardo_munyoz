package es.florida.ad_aev1;
import java.io.File;
import java.io.FilenameFilter;

/**
 * clase para realizar el filtrado de extension
 */
public class FiltroExtension implements FilenameFilter {
	
	String extension;
	
	FiltroExtension(String extension){
		this.extension = extension;
	}
	 @Override
	 public boolean accept(File dir, String name) {
		 return name.endsWith(extension);
	 }
}
