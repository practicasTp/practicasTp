package mv.instructions;

public class Mult extends Arithmetics {
	
	
	/**
	 * Ejecuta una multiplicación
	 * @param n1
	 * @param n2
	 * @return boolean
	 */
	public boolean execute (int n1, int n2) {
		this.result = n1 * n2;
		return true;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción mult
	 * @param s
	 * @return new Mult o null
	 */
	public Instruction parse (String[] s) {
		if ((s.length == 1) && (s[0].equalsIgnoreCase("MULT") || s[0].equalsIgnoreCase("MUL"))) 
			return new Mult();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción mult
	 * @return "MULT"
	 */
	public String toString () {
		return "MULT";
	}
}
