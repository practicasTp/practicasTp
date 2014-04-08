package mv.instructions;

import mv.ExecutionMode;
import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectProgramCounterException;
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
	public void executeAux (Cpu cpu) throws InsufficientOperandsException {
		try {
			if(cpu.getSizeStack() >= 1) {	
				int cima = cpu.pop();
				if (this.execute(cima)) {
					if (!this.relative)
						try {
							cpu.jumpProgramCounter(this.operando);
						}
						catch(IncorrectProgramCounterException e) {
							System.err.println(e.getMessage());
							if(cpu.mode == ExecutionMode.BACH){
								System.err.println("Sayonara Baby.");
								System.exit(1);
							}
						}
					else 
						cpu.increaseProgramCounter(this.operando);
				} else {
					cpu.increaseProgramCounter ();
				}
			} else 
				throw new InsufficientOperandsException("Error: no hay operandos suficientes para realizar la operación.\n");
		} catch(EmptyStackException e) {
			System.err.println(e.getMessage());
		}
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
