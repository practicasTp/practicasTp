package mv.instructions;

public class LessThan extends Compare{
	
	/**
	 * Comprueba si la cima es menor que la subcima.
	 * @param cima
	 * @param subcima
	 * @return boolean
	 */
	public boolean compare (int cima, int subcima) {
		if (subcima < cima) return true;
		else return false;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción lt
	 * @param s
	 * @return new LessThan o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("LT"))
			return new LessThan ();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción lt
	 * @return "LT"
	 */
	public String toString () {
		return "LT";
	}
}
