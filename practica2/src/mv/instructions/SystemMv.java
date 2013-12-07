package mv.instructions;

import mv.cpu.Cpu;

abstract public class SystemMv extends Instruction {
	protected int operando;
	
	public SystemMv (TipoInstruction tipo) {
		super(tipo);
	}
	
	abstract protected boolean executeAux(Cpu cpu);
	
	public boolean execute(Cpu cpu) {
		if(this.executeAux(cpu)) {
			cpu.increaseProgramCounter();
			return true;
		} else return false;
	}
	
	abstract public Instruction parse (String[] s);
	
	abstract public String toString ();
}
