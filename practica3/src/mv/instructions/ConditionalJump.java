package mv.instructions;

import mv.cpu.Cpu;
import mv.exceptions.InsufficientOperandsException;

abstract public class ConditionalJump extends Jumps{
	protected boolean relative;

	public ConditionalJump (int operando) {
		this.relative = false;
	}
	
	/**
	 * Método que ejecuta la instrucción
	 * @param cima
	 * @return boolean
	 */
	abstract protected boolean execute (int cima);
	
	/**
	 * Método que ejecuta las acciones comunes a todas las intrucciones condicionales
	 * y ejecuta el método que ejecuta la instrucción.
	 * @param cpu
	 * @return boolean
	 * @throws InsufficientOperandsException 
	 */
	public boolean executeAux (Cpu cpu) throws InsufficientOperandsException {
		boolean execute = false;
		
		if(cpu.getSizeStack() >= 1) {	
			int cima = cpu.pop();
			if (this.execute(cima)) {
				if (!this.relative)
					cpu.jumpProgramCounter(this.operando);
				else 
					cpu.increaseProgramCounter(this.operando);
				execute = true;
			} else {
				cpu.increaseProgramCounter ();
				execute = true;
			}
		} else 
			throw new InsufficientOperandsException("Error: no hay operandos suficientes para realizar la operación.");
		
		return execute;
	}
	
	/**
	 * Parsea un string para identificar la instrucción.
	 * @param s
	 * @return instruction.
	 */
	abstract public Instruction parse (String[] s);
	
	/**
	 * Pasa a string la instrucción.
	 * @return string
	 */
	abstract public String toString ();
}
