package mv.instructions;

public class RelativeBf extends ConditionalJump {
	
	public RelativeBf (int operando) {
		super (TipoInstruction.RBF, operando);
		this.operando = operando;
	}
	
	public boolean execute (int cima) {
		if (cima == 0) {
			this.relative = true;
			return true;
		}
		else return false;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("RBF")){
			int operando = Integer.parseInt(s[1]);
			return new RelativeBf (operando);
		} else return null;
	}
	
	public String toString () {
		return "RBF " + this.operando;
	}
}
