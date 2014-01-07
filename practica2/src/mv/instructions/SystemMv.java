package mv.instructions;

import mv.cpu.Cpu;

abstract public class SystemMv extends Instruction {
	protected int operando;
	
	public SystemMv (TipoInstruction tipo) {
		super(tipo);
	}
	
	/**
	 * Método que ejecuta la instrucción
	 * @param cpu
	 * @return boolean
	 */
	abstract protected boolean executeAux(Cpu cpu);
	
	/**
	 * Se encarga de ejecutar las instrucciones propias del sistema, incrementando el 
	 * contador de programa en caso de que se ejecuten correctamente.
	 */
	public boolean execute(Cpu cpu) {
		if(this.executeAux(cpu)) {
			cpu.increaseProgramCounter();
			return true;
		} else return false;
	}
	
	/**
	 * Parsea un string para identificar la instrucción.
	 * @return instruction.
	 */
	abstract public Instruction parse (String[] s);
	
	/**
	 * Pasa a string la instrucción.
	 * @return string
	 */
	abstract public String toString ();
}
