package mv.commands;

public class Pop extends CommandInterpreter {
	
	public Pop () {
		super();
	}
	
	/**
	 * Metodo que se encarga de apilar un número en la pila
	 * @return resultado
	 */
	public boolean executeCommand() {
		boolean resultado = false;
		
		if(CommandInterpreter.cpu.getSizeStack()>0){
			CommandInterpreter.cpu.pop();
			//muestro estado de la máquina
			CommandInterpreter.printStateMachine();
			resultado = true;
		}else{
			System.err.println("No hay elementos en la pila para eliminar.");
		}
		
		return resultado;
	}
	
	/**
	 * Método que pasa a string el comando ejecutado
	 * @return push + numero que se apila en la pila de cpu
	 */
	public String toString () {
		return "POP ";
	}
}