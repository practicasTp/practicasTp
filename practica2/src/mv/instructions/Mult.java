package mv.instructions;

public class Mult extends Arithmetics {
	
	public Mult () {
		super(TipoInstruction.MUL);
	}
	
	/**
	 * Ejecuta una multiplicación
	 * @param n1
	 * @param n2
	 */
	public boolean execute (int n1, int n2) {
		this.result = n1 * n2;
		return true;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción mult
	 * @return new Mult o null
	 */
	public Instruction parse (String[] s) {
		if ((s.length == 1) && s[0].equalsIgnoreCase("MULT")) 
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
