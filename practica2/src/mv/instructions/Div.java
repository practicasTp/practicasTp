package mv.instructions;

public class Div extends Arithmetics {
	
	public Div () {
		super(TipoInstruction.DIV);
	}
	
	/**
	 * Ejecuta una división.
	 */
	public boolean execute (int n1, int n2) {
		if (n2 != 0) {
			this.result = n1 / n2;
			return true;
		} else return false;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción div
	 * @return new div o null
	 */
	public Instruction parse (String[] s) {
		if ((s.length == 1) && s[0].equalsIgnoreCase("DIV")) 
			return new Div();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción div
	 * @return "DIV"
	 */
	public String toString () {
		return "DIV";
	}
}
