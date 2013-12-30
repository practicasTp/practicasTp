package mv.instructions;

import mv.cpu.Cpu;

public class RelativeJump extends Jumps {
	
	public RelativeJump (int operando) {
		super (TipoInstruction.RJUMP, operando);
		this.operando = operando;
	}
	
	public boolean executeAux (Cpu cpu) {
		cpu.increaseProgramCounter(this.operando);
		return true;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("RJUMP")) {
			int operando = Integer.parseInt(s[1]);
			return new RelativeJump (operando);
		} else return null;
	}
	
	public String toString () {
		return "RJUMP " + this.operando;
	}
}
