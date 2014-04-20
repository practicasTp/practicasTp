package mv.reading;

public interface InputMethod {
	
	/**
	 * Lee un caracter.
	 * @return int
	 */
	abstract public int readChar();
	
	/**
	 * Cierra el archivo abierto.
	 */
	abstract public void close();
}
