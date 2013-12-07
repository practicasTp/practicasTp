package mv.instructions;

import mv.cpu.Cpu;

public class Neg extends SystemMv {
	
	public Neg () {
		super (TipoInstruction.NEG);
	}
	
	public boolean executeAux (Cpu cpu) {
		if (cpu.getSizeStack() >= 1) {
			int cima = cpu.pop();
			if (cima == 0) cpu.push(0);
			else {
				cima = cima * -1;
				cpu.push(cima);
			}
			
			return true;
		} else return false;
	}
	
	public Instruction parse (String[] s) {
		if (s.length >= 1 && s[0].equalsIgnoreCase("NEG"))
			return new Neg ();
		else return null;
	}
	
	public String toString () {
		return "NEG";
	}
}
