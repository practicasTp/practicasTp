package mv.reading;

import java.io.IOException;

public class StdIn implements InputMethod {
	
	/**
	 * Lee un caracter de consola y los transforma en entero en formato ASCII.
	 * @return int in.
	 */
	public int readChar() {
		int in = -1;
		try {
			System.out.print("Introduzca caracter de entrada:");
			do{
				in = System.in.read();
			}while(in==10 || in==13);
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		return in;
	}
	
	/**
	 * No hace nada porque no tiene ningún archivo que cerrar.
	 */
	public void close() {}

	@Override
	public void reset() {		
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}
}
