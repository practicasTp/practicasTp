package mv.instructions;

public class Equals extends Compare {
	
	/**
	 * Compara la cima y la subcima indicando si son iguales
	 * @param cima
	 * @param subcima
	 * @return boolean
	 */
	public boolean compare (int cima, int subcima) {
		if (cima == subcima) return true;
		else return false;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción eq
	 * @param s
	 * @return new Equals o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("EQ"))
			return new Equals ();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción eq
	 * @return "EQ"
	 */
	public String toString () {
		return "EQ";
	}
}
