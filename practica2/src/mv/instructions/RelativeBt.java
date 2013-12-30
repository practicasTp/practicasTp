package mv.instructions;

public class RelativeBt extends ConditionalJump {
	
	public RelativeBt (int operando) {
		super (TipoInstruction.RBT, operando);
		this.operando = operando;
	}
	
	public boolean execute (int cima) {
		if (cima == 1) {
			this.relative = true;
			return true;
		}
		else return false;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("RBT")){
			int operando = Integer.parseInt(s[1]);
			return new RelativeBt (operando);
		} else return null;
	}
	
	public String toString () {
		return "RBT " + this.operando;
	}
}
