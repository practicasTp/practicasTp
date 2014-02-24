package mv.reading;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FromInputStreamIn implements InputMethod {
	private InputStream in = null;
	
	public FromInputStreamIn(String archivo) {
		try {
			this.in = new FileInputStream (archivo);
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Lee un caracter del archivo indicado y lo trasnforma a entero en formato ASCII.
	 * @return int entrada
	 */
	public int readChar() {
		int entrada = -1;
		try {
			entrada = in.read();
		}
		catch (EOFException e) {
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return entrada;
	}
}
