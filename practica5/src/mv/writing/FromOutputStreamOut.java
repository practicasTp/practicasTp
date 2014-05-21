package mv.writing;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FromOutputStreamOut implements OutputMethod{
	private OutputStream out;
	private String file;
	
	public FromOutputStreamOut(String archivo) {
		this.file = archivo;
		this.open();
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
	
	/**
	 * Cierra el archivo de salida abierto capturando las excepciones que se puedan
	 * producir.
	 */
	public void close() {
		try {
			this.out.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void open() {
		//Recibo la ruta del archivo y la añado al objeto out.
		try {
			this.out = new FileOutputStream(this.file);
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void reset() {
		this.close();
		this.open();
	}
}
