package mv.instructions;

import mv.cpu.Cpu;

public class Pop extends SystemMv {

	public Pop () {
		super (TipoInstruction.POP);
	}
	
	public boolean executeAux (Cpu cpu) {
		if (cpu.getSizeStack() >= 1) {
			cpu.pop();
			return true;
		} else return false;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("POP"))
			return new Pop ();
		else return null;
	}
	
	public String toString () {
		return "POP";
	}
}
