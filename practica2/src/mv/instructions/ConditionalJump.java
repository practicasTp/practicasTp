package mv.instructions;

import mv.cpu.Cpu;

abstract public class ConditionalJump extends Jumps{

	public ConditionalJump (TipoInstruction tipo, int operando) {
		super (tipo, operando);
	}
	
	abstract protected boolean execute (int cima);
	
	public boolean executeAux (Cpu cpu) {
		int cima = cpu.pop();
		if (this.execute(cima)) {
			cpu.jumpProgramCounter(this.operando);
			return true;
		} else return false;
	}
	
	abstract public Instruction parse (String[] s);
	
	abstract public String toString ();
}
