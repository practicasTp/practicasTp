package mv.instructions;

import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;
import mv.exceptions.NegativeNumberIntoMemoryException;

public class Store extends SystemMv{

	public Store (int operando) {
		this.operando = operando;
	}
	
	/**
	 * Metodo que almacena en una posicion de la memoria, un dato.
	 * @param cpu
	 * @return boolean
	 * @throws NegativeNumberIntoMemoryException 
	 * @throws EmptyStackException 
	 */
	public boolean executeAux (Cpu cpu) throws NegativeNumberIntoMemoryException {
		boolean resultado = false;
		try {
			if (cpu.getSizeStack() >= 1) {
				if(this.operando>=0){
					int cima = cpu.pop();
					cpu.store(this.operando, cima);
					resultado = true;
				}else{
					throw new NegativeNumberIntoMemoryException("Error: no existen posiciones negativas en la memoria.\n");
				}
			}
		} catch(EmptyStackException e) {
			System.err.println(e.getMessage());
		}
		
		return resultado;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción store
	 * @param s
	 * @return new Store o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("STORE")) {
			int operando = Integer.parseInt(s[1]);
			return new Store (operando);
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción store
	 * @return "STORE"
	 */
	public String toString () {
		return "STORE " + this.operando;
	}
}
