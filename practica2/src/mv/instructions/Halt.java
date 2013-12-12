package mv.instructions;

public class Halt extends SystemMv {

	public Halt () {
		super (TipoInstruction.HALT);
	}
	
	public boolean executeAux (Cpu cpu) {
		return true;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("HALT"))
			return new Halt ();
		else return null;
	}
	
	public String toString () {
		return "HALT";
	}
}
