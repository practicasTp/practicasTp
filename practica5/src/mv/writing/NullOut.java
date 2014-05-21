package mv.writing;

public class NullOut implements OutputMethod {
	
	/**
	 * No hace nada, ya que no hay salida.
	 */
	public void writeChar(char c) {}
	
	/**
	 * No hace nada porque no tiene ningún archivo que cerrar.
	 */
	public void close() {}

	@Override
	public void open() {}

	@Override
	public void reset() {}
}
