package mv.instructions;

import mv.cpu.Cpu;

public class Halt extends SystemMv {

	public Halt () {
		super (TipoInstruction.HALT);
	}
	
	public boolean executeAux (Cpu cpu) {
		cpu.exit();
		
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
