package mv.instructions;

import mv.ExecutionMode;
import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;
import mv.exceptions.InsufficientOperandsException;

abstract public class Compare implements Instruction {
	
	/**
	 * Método que ejecuta la instrucción.
	 * @param cima
	 * @param subcima
	 * @return booleano indicando el resultado de la ejecución.
	 */
	abstract protected boolean compare (int cima, int subcima);
	
	/**
	 * Método que se encarga de realizar las acciones comunes a todas las instrucciones de
	 * comparación y de ejecutar la instrucción.
	 * @param cpu
	 * @return boolean.
	 * @throws InsufficientOperandsException 
	 */
	public boolean execute (Cpu cpu) throws InsufficientOperandsException {
		boolean execute = false;
		
		try {
			if (cpu.getSizeStack() >= 2) {
				//obtener cima y subcima.
				int cima = cpu.pop();
				int subcima = cpu.pop();
				if (compare (cima, subcima)) cpu.push(1);
				else cpu.push(0);
				cpu.increaseProgramCounter();
				execute = true;
			}
			else 
				throw new InsufficientOperandsException("Error: no hay operandos suficientes para realizar la operación.\n");
		} catch(EmptyStackException e) {
			System.err.println(e.getMessage());
			if(cpu.mode == ExecutionMode.BACH){
				System.exit(1);
			}
		}
			
		return execute;
	}
	
	/**
	 * Método que parsea un string para identificarse como una instrucción booleana.
	 * @param s
	 * @return instruction
	 */
	abstract public Instruction parse (String[] s);
	
	/**
	 * Método que pasa a string la instrucción booleana
	 */
	abstract public String toString ();
}
