package mv.instructions;

public class GreaterThan extends Compare {
	
	/**
	 * Compara la cima y la subcima indicando si es mayor.
	 * @param cima
	 * @param subcima
	 * @return boolean
	 */
	public boolean compare (int cima, int subcima) {
		if (subcima > cima) return true;
		else return false;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción gt
	 * @param s
	 * @return new GreaterThan o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("GT"))
			return new GreaterThan ();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción gt
	 * @return "GT"
	 */
	public String toString () {
		return "GT";
	}
}
