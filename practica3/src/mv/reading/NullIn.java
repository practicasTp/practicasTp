package mv.reading;

public class NullIn implements InputMethod {
	
	/**
	 * Siempre devuelve -1 ya que no existe lectura.
	 * @return int.
	 */
	public int readChar() {
		return -1;
	}
}
