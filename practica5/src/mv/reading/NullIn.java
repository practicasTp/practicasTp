package mv.reading;

public class NullIn implements InputMethod {
	
	/**
	 * Siempre devuelve -1 ya que no existe lectura.
	 * @return int.
	 */
	public int readChar() {
		return -1;
	}
	
	/**
	 * No hace nada porque no tiene ningún archivo que cerrar.
	 */
	public void close() {}

	public void reset() {}

	public void open() {}
}
