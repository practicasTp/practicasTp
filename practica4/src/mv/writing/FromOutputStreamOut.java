package mv.writing;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FromOutputStreamOut implements OutputMethod{
	private OutputStream out;
	
	public FromOutputStreamOut(String archivo) {
		//Recibo la ruta del archivo y la añado al objeto out.
		try {
			this.out = new FileOutputStream(archivo);
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Escribe un caracter en el archivo indicado
	 * @param char c.
	 */
	public void writeChar(char c) {
		try {
			out.write(c);
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
