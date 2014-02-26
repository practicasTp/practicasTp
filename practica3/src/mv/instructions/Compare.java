package mv.instructions;

import mv.cpu.Cpu;

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
	 */
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
