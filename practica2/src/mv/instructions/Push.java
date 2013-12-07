package mv.instructions;

import mv.cpu.Cpu;

public class Push extends SystemMv {

	public Push (int operando) {
		super (TipoInstruction.PUSH);
		this.operando = operando;
	}
	
	public boolean executeAux (Cpu cpu) {
		cpu.push(this.operando);
		return true;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("PUSH")) {
			int operando = Integer.parseInt(s[1]);
			return new Push (operando);
		} else return null;
	}
	
	public String toString () {
		return "PUSH";
	}
}
