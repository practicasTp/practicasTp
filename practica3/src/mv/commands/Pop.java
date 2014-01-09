package mv.commands;

public class Pop extends CommandInterpreter {
	
	public Pop () {
		super();
	}
	
	/**
	 * Metodo que se encarga de desapilar un número en la pila
	 * @return resultado
	 */
	public boolean executeCommand() {
		boolean resultado = false;
		
		//si el contenido de la pila es mayor que 0
		if(CommandInterpreter.cpu.getSizeStack()>0){
			//saco de la pila
			CommandInterpreter.cpu.pop();
			//muestro estado de la máquina
			CommandInterpreter.printStateMachine();
			resultado = true;
		//si no, aviso
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