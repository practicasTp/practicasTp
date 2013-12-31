package mv.commands;

public class Write extends CommandInterpreter {
	private int pos;
	private int dato;
	
	public Write (int pos, int dato) {
		this.pos 	= pos;
		this.dato 	= dato;
	}
	
	/**
	 * Metodo que se encarga de apilar un número en la pila
	 * @return resultado
	 */
	public boolean executeCommand() {
		boolean resultado = false;
		
		if(this.pos >= 0){
			CommandInterpreter.cpu.store(this.pos, this.dato);
			//muestro estado de la máquina
			CommandInterpreter.printStateMachine();
			resultado = true;
		}else{
			System.err.println("No puedes almacenar datos en posiciones negativas");
		}
		
		return resultado;
	}
	
	/**
	 * Método que pasa a string el comando ejecutado
	 * @return push + numero que se apila en la pila de cpu
	 */
	public String toString () {
		return "WRITE "+this.pos+" "+this.dato;
	}
}