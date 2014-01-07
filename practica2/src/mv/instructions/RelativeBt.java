package mv.instructions;

public class RelativeBt extends ConditionalJump {
	
	public RelativeBt (int operando) {
		super (TipoInstruction.RBT, operando);
		this.operando = operando;
	}
	
	/**
	 * Si la cima es igual a 1 hace un salto relativo en función del atributo operando.
	 * @param cima
	 */
	public boolean execute (int cima) {
		if (cima == 1) {
			this.relative = true;
			return true;
		}
		else return false;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción rbt
	 * @return new RelativeBt o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("RBT")){
			int operando = Integer.parseInt(s[1]);
			return new RelativeBt (operando);
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción rbt
	 * @return "RBT"
	 */
	public String toString () {
		return "RBT " + this.operando;
	}
}
