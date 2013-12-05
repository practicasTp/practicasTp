package mv.instructions;

import mv.cpu.Cpu;

abstract public class Compare extends Instruction {
	
	
	public Compare (TipoInstruction tipo) {
		super(tipo);
	}
	
	protected abstract boolean compare (int cima, int subcima);
	
	public boolean execute (Cpu cpu) {
		if (cpu.getSizeStack() >= 2) {
			//obtener cima y subcima.
			int cima = cpu.pop();
			int subcima = cpu.pop();
			if (compare (cima, subcima)) cpu.push(1);
			else cpu.push(0);
			cpu.increaseProgramCounter();
			return true;
		}
		else return false;
	}
}
