package mv.instructions;

public class BranchIfFalse extends ConditionalJump {

	public BranchIfFalse (int operando) {
		super (TipoInstruction.BF, operando);
		this.operando = operando;
	}
	
	/**
	 * Comprueba si la cima esta a 0 devolviendo un booleano.
	 * @param cima
	 * @return boolean
	 */
	public boolean execute (int cima) {
		if (cima == 0) return true;
		else return false;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción bf.
	 * param s
	 * @return new bf o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("BF")){
			int operando = Integer.parseInt(s[1]);
			return new BranchIfFalse (operando);
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción bf.
	 * @return "BF"
	 */
	public String toString () {
		return "BF " + this.operando;
	}
}
