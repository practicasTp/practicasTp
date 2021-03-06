package mv.instructions;

import mv.ExecutionMode;
import mv.cpu.Cpu;
import mv.exceptions.IncorrectMemoryPositionException;
import mv.exceptions.NegativeNumberIntoMemoryException;

public class Load extends SystemMv {

	public Load (int operando) {
		this.operando = operando;
	}
	
	/**
	 * Captura el valor de la memoria en la posición indicada por el atributo operando y lo 
	 * introduce en la pila.
	 * @param cpu
	 * @return boolean
	 * @throws NegativeNumberIntoMemoryException 
	 */
	public boolean executeAux (Cpu cpu) throws NegativeNumberIntoMemoryException {
		boolean resultado = false;
		if(this.operando>=0){
			try {
				int value = cpu.getMemoryValue(this.operando);
				cpu.push(value);
				resultado = true;
			}
			catch (IncorrectMemoryPositionException e) {
				System.err.println(e.getMessage());
			}
		}else{
			throw new NegativeNumberIntoMemoryException("Error: no existen posiciones negativas en la memoria.\n");
			
		}
		
		return resultado;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción load
	 * @param s
	 * @return new Load o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("LOAD")) {
			int operando = Integer.parseInt(s[1]);
			return new Load (operando);
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción load
	 * @return "LOAD"
	 */
	public String toString () {
		return "LOAD " + this.operando;
	}
}
