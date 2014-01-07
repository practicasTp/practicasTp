package mv.instructions;

public class BranchIfTrue extends ConditionalJump {

	public BranchIfTrue (int operando) {
		super (TipoInstruction.BT, operando);
		this.operando = operando;
	}
	
	/**
	 * Comprueba si la cima esta a 1 y devuelve un booleano para indicarlo.
	 */
	public boolean execute (int cima) {
		if (cima == 1) return true;
		else return false;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción bt.
	 * @return new bt o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("BT")){
			int operando = Integer.parseInt(s[1]);
			return new BranchIfTrue (operando);
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción bt.
	 * @return "BT"
	 */
	public String toString () {
		return "BT " + this.operando;
	}
}
