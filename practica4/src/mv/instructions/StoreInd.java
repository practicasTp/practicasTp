package mv.instructions;

import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;

public class StoreInd extends SystemMv {

	
	/**
	 * Guardo en la posicióin de memoria que me da la cima de la pila, el dato que está en la subcima de la pila
	 * @param cpu
	 * @return boolean
	 * @throws EmptyStackException 
	 */
	public void executeAux (Cpu cpu) throws EmptyStackException {
		int valueToStore	= cpu.pop();
		int positionToStore = cpu.pop();
		cpu.store(positionToStore, valueToStore);
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción jump
	 * @param s
	 * @return new InconditionalJump o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("STOREIND")) {
			return new StoreInd ();
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción jump
	 * @return "JUMP"
	 */
	public String toString () {
		return "STOREIND ";
	}
}
