package mv.instructions;

import mv.cpu.Cpu;

abstract public class Boolean extends Instruction {

	public Boolean(TipoInstruction tipo) {
		super(tipo);
	}
	
	abstract protected boolean executeAux(Cpu cpu);
	
	public boolean execute(Cpu cpu) {
		TipoInstruction instruccionBooleana = getTipoInstruccion();
		
		if (cpu.getSizeStack() >= 2 || (instruccionBooleana.equals(TipoInstruction.NOT) && cpu.getSizeStack() >= 1)) {
			if (this.executeAux(cpu)) cpu.push(1);
			else cpu.push(0);
			cpu.increaseProgramCounter();
			return true;
		}
		else return false;
	}
	
	abstract public Instruction parse (String[] s);
	
	abstract public String toString ();
}
