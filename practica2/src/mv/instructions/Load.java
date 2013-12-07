package mv.instructions;

import mv.cpu.Cpu;

public class Load extends SystemMv {

	public Load (int operando) {
		super (TipoInstruction.LOAD);
		this.operando = operando;
	}
	
	public boolean executeAux (Cpu cpu) {
		int value = cpu.getMemoryValue(this.operando);
		cpu.push(value);
		return true;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("LOAD")) {
			int operando = Integer.parseInt(s[1]);
			return new Load (operando);
		} else return null;
	}
	
	public String toString () {
		return "LOAD";
	}
}
