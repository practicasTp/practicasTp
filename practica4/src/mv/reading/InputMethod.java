package mv.reading;

public interface InputMethod {
	
	/**
	 * Lee un caracter.
	 * @return int
	 */
	abstract public int readChar();
	
	abstract public void close();
}
