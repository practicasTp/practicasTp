package mv.writing;

public class StdOut implements OutputMethod{
	
	/**
	 * Escribe un caracter por consola.
	 * @param char c.
	 */
	public void writeChar(char c) {
		System.out.print(c);
	}
}
