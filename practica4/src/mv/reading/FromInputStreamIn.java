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
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Devuelve el objeto de entrada.
	 * @return InputStream
	 */
	public InputStream getInputStream(){
		return this.in;
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
			System.err.println(e.getMessage());
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		return entrada;
	}
	
	/**
	 * Cierra el archivo de entrada abierto y captura las excepciones que se puedan
	 * producir.
	 */
	public void close() {
		try {
			this.in.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
