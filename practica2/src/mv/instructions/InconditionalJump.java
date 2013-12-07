package mv.instructions;

import mv.cpu.Cpu;

public class InconditionalJump extends Jumps{

	public InconditionalJump (int operando) {
		super (TipoInstruction.JUMP, operando);
		this.operando = operando;
	}
	
	public boolean executeAux (Cpu cpu) {
		cpu.jumpProgramCounter(this.operando);
		return true;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("JUMP")) {
			int operando = Integer.parseInt(s[1]);
			return new InconditionalJump (operando);
		} else return null;
	}
	
	public String toString () {
		return "JUMP";
	}
}
