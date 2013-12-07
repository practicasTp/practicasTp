package mv.instructions;

public class BranchIfFalse extends ConditionalJump {

	public BranchIfFalse (int operando) {
		super (TipoInstruction.BF, operando);
		this.operando = operando;
	}
	
	public boolean execute (int cima) {
		if (cima == 0) return true;
		else return false;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("BF")){
			int operando = Integer.parseInt(s[1]);
			return new BranchIfFalse (operando);
		} else return null;
	}
	
	public String toString () {
		return "BF";
	}
}
