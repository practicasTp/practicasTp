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
			System.out.print("\nIntroduzca caracter de entrada:");
			in = System.in.read();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		return in;
	}
}
