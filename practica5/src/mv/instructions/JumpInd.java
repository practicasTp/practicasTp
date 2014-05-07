package mv.instructions;

import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectProgramCounterException;

public class JumpInd extends Jumps{
	
	/**
	 * Modifica el contador de programa de la cpu cambiando el valor por el indicado en la cima de la pila
	 * @param cpu
	 * @return boolean
	 * @throws EmptyStackException 
	 * @throws IncorrectProgramCounterException 
	 */
	public void executeAux (Cpu cpu) throws IncorrectProgramCounterException, EmptyStackException {
		cpu.jumpProgramCounter(cpu.pop());
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción jump
	 * @param s
	 * @return new InconditionalJump o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("JUMPIND")) {
			return new JumpInd ();
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción jump
	 * @return "JUMP"
	 */
	public String toString () {
		return "JUMPIND ";
	}
}
