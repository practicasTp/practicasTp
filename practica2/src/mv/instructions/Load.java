package mv.instructions;

import mv.cpu.Cpu;

public class Load extends SystemMv {

	public Load (int operando) {
		super (TipoInstruction.LOAD);
		this.operando = operando;
	}
	
	/**
	 * Captura el valor de la memoria en la posición indicada por el atributo operando y lo 
	 * introduce en la pila.
	 * @param cpu
	 */
	public boolean executeAux (Cpu cpu) {
		if(this.operando>=0){
			int value = cpu.getMemoryValue(this.operando);
			cpu.push(value);
			return true;
		}else{
			System.out.println("La posición a cargar no puede ser negativa.");
			return false;
		}
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción load
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
