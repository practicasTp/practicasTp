package mv.instructions;

import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectMemoryPositionException;

public class LoadInd extends SystemMv {

	
	/**
	 * Cargo de memoria una posición que está almacenada en la pila y apilo el valor extraido
	 * @param cpu
	 * @return boolean
	 */
	public void executeAux (Cpu cpu) {
		try {
			int positionToLoad = cpu.pop();
			int MemoryValue = cpu.getMemoryValue(positionToLoad);
			cpu.push(MemoryValue);
		} catch (EmptyStackException e) {
			System.err.println(e.getMessage());
		} catch (IncorrectMemoryPositionException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción jump
	 * @param s
	 * @return new InconditionalJump o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("LOADIND")) {
			return new LoadInd ();
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción jump
	 * @return "JUMP"
	 */
	public String toString () {
		return "LOADIND ";
	}
}
