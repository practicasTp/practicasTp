package mv.instructions;

public class Add extends Arithmetics{
	
	public Add () {
		super(TipoInstruction.ADD);
	}
	
	/**
	 * Método que ejecuta una suma de 2 operandos
	 * @return true
	 * @param n1
	 * @param n2
	 */
	public boolean execute (int n1, int n2) {
		this.result = n1 + n2;
		return true;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción add
	 * @return new add o null
	 * @param s
	 */
	public Instruction parse (String[] s) {
		if ((s.length == 1) && s[0].equalsIgnoreCase("ADD")) 
			return new Add();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción add
	 * @return "ADD"
	 */
	public String toString () {
		return "ADD";
	}
}
