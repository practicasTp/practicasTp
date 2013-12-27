package mv.commands;

public class Run extends Step {
	public Run(){
		super();
	}
	
	/**
	 * Metodo que se encarga de ejecurar todas las instrucciones
	 * @return resultado
	 */
	public boolean executeCommand(){
		//cuando hago un run, reseteo la máquina
		CommandInterpreter.cpu.resetCpu();
		boolean resultado = false;
		
		do{
			//si la instrucción se ejecuta correctamente
			if (CommandInterpreter.cpu.step()){
				resultado = true;
				//muestro el estado de la máquina
				CommandInterpreter.printStateMachine();
			}else{
				//si no, paro ejecución y ¿reinicio la máquina?
				resultado = false;
				//CommandInterpreter.cpu.resetCpu();
			}
		//repito hasta que la cpu me diga que no hay más instrucciones a ejecutar	
		}while(resultado!=false);
			
		//si la cpu me dice que ha terminado, paro la máquina (del bucle se puede salir por fallo de instrucción)
		if(CommandInterpreter.cpu.finished()){
			this.isFinished = true;
		}
		
		return resultado;
	}
	
	/**
	 * Método que pasa a string el comando ejecutado
	 * @return RUN
	 */
	public String toString () {
		return "RUN ";
	}
}
