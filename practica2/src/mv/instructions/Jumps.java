package mv.instructions;

import mv.cpu.Cpu;

abstract public class Jumps extends Instruction {
	protected int operando;
	
	public Jumps (TipoInstruction tipo, int operando) {
		super(tipo, operando);
	}
	
	abstract protected boolean executeAux (Cpu cpu);
	
	public boolean execute (Cpu cpu) {
		if(cpu.getSizeStack() >= 2) {
			if(executeAux(cpu)) return true;
			else return false;
		} else return false;
	}
	
	abstract public Instruction parse (String[] s);
	
	abstract public String toString ();
}
