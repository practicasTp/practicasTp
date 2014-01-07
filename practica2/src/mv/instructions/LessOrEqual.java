package mv.instructions;

public class LessOrEqual extends Compare {

	public LessOrEqual () {
		super (TipoInstruction.LE);
	}
	
	/**
	 * Comprueba si la cima es menor o igual que la subcima.
	 * @param cima
	 * @param subcima
	 */
	public boolean compare (int cima, int subcima) {
		if (cima <= subcima) return true;
		else return false;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción le
	 * @return new LessOrEqual o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("LE"))
			return new LessOrEqual ();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción le
	 * @return "LE"
	 */
	public String toString () {
		return "LE";
	}
}
