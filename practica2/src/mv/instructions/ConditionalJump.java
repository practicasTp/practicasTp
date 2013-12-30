package mv.instructions;

import mv.cpu.Cpu;

abstract public class ConditionalJump extends Jumps{
	protected boolean relative;

	public ConditionalJump (TipoInstruction tipo, int operando) {
		super (tipo, operando);
		this.relative = false;
	}
	
	abstract protected boolean execute (int cima);
	
	public boolean executeAux (Cpu cpu) {
		int cima = cpu.pop();
		if (this.execute(cima)) {
			if (!this.relative)
				cpu.jumpProgramCounter(this.operando);
			else 
				cpu.increaseProgramCounter(this.operando);
			return true;
		} else {
			cpu.increaseProgramCounter ();
			return true;
		}
	}
	
	abstract public Instruction parse (String[] s);
	
	abstract public String toString ();
}
