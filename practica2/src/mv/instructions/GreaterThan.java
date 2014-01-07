package mv.instructions;

public class GreaterThan extends Compare {

	public GreaterThan () {
		super (TipoInstruction.GT);
	}
	
	/**
	 * Compara la cima y la subcima indicando si es mayor.
	 */
	public boolean compare (int cima, int subcima) {
		if (cima > subcima) return true;
		else return false;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción gt
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
