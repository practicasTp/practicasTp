package mv.writing;

public interface OutputMethod {
	
	/**
	 * Método para escribir un carácter.
	 * @param char c.
	 */
	public void writeChar(char c);
	
	/**
	 * Cierra el archivo abierto.
	 */
	public void close();
	
	/**
	 * Abre el archivo
	 */
	public void open();
	
	/**
	 * Resetea el archivo
	 */
	public void reset();
}
