package mv.writing;

public class StdOut implements OutputMethod{
	
	/**
	 * Escribe un caracter por consola.
	 * @param char c.
	 */
	public void writeChar(char c) {
		System.out.print(c);
	}
	
	/**
	 * No hace nada porque no tiene ningún archivo que cerrar.
	 */
	public void close() {}

	@Override
	public void open() {}

	@Override
	public void reset() {}
}
