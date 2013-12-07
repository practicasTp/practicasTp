package mv.instructions;

public class BranchIfTrue extends ConditionalJump {

	public BranchIfTrue (int operando) {
		super (TipoInstruction.BT, operando);
		this.operando = operando;
	}
	
	public boolean execute (int cima) {
		if (cima == 1) return true;
		else return false;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("BT")){
			int operando = Integer.parseInt(s[1]);
			return new BranchIfTrue (operando);
		} else return null;
	}
	
	public String toString () {
		return "BT";
	}
}
