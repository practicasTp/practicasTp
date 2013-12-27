package mv.commands;

public class Step extends CommandInterpreter {
	
	public Step () {
		super();
	}
	
	/**
	 * Método que ejecuta una sola instrucción
	 * @return resultado
	 */
	public boolean executeCommand() {
		boolean resultado = false;
		
		//si la ejecución ha ido correctamente, muestro el estado de la máquina
		if (CommandInterpreter.cpu.step()){
			resultado = true;
			CommandInterpreter.printStateMachine();
			//si hay fallo de ejecución ¿reinicio la máquina?
		}else{
			//CommandInterpreter.cpu.resetCpu();
		}
		
		//si la cpu me dice que no hay más instrucciones entonces paro la máquina
		if(CommandInterpreter.cpu.finished()){
			this.isFinished = true;
		}
		
		return resultado;
	}
	
	/**
	 * Método que pasa a string el comando ejecutado
	 * @return step
	 */
	public String toString () {
		return "STEP";
	}
}
