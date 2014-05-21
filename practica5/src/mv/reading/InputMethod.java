package mv.reading;

public interface InputMethod {
	
	/**
	 * Lee un caracter.
	 * @return int
	 */
	public int readChar();
	
	/**
	 * Cierra el archivo abierto.
	 */
	public void close();
	
	public void reset();
	
	public void open();
}
