package mv.instructions;

public class RelativeBf extends ConditionalJump {
	
	public RelativeBf (int operando) {
		super (operando);
		this.operando = operando;
	}
	
	/**
	 * Si la cima es igual a 0 hace un salto relativo
	 * @param cima
	 * @return boolean
	 */
	public boolean execute (int cima) {
		if (cima == 0) {
			this.relative = true;
			return true;
		}
		else return false;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción rbf
	 * @param s
	 * @return new RelativeBf o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("RBF")){
			int operando = Integer.parseInt(s[1]);
			return new RelativeBf (operando);
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción rbf
	 * @return "RBF"
	 */
	public String toString () {
		return "RBF " + this.operando;
	}
}
