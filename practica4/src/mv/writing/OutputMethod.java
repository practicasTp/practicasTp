package mv.writing;

public interface OutputMethod {
	
	/**
	 * Método para escribir un carácter.
	 * @param char c.
	 */
	abstract public void writeChar(char c);
	
	abstract public void close();
}
