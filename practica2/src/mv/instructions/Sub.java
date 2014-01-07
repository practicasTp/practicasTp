package mv.instructions;

public class Sub extends Arithmetics {
	
	public Sub () {
		super(TipoInstruction.SUB);
	}
	
	/**
	 * Ejecuta una resta.
	 * @param n1
	 * @param n2
	 */
	public boolean execute (int n1, int n2) {
		this.result = n1 - n2;
		return true;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción sub
	 * @return new Sub o null
	 */
	public Instruction parse (String[] s) {
		if ((s.length == 1) && s[0].equalsIgnoreCase("SUB")) 
			return new Sub();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción sub
	 * @return "SUB"
	 */
	public String toString () {
		return "SUB";
	}
}
