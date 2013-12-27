package mv.commands;

public class Steps extends Step {
	private int steps;
	
	public Steps (int steps) {
		this.steps = steps;
	}
	
	/**
	 * Metodo que se encarga de ejecurar N instrucciones
	 * @return resultado
	 */
	public boolean executeCommand() {
		//inicializo el contador de instrucciones restantes al numero de instruciones que quiero ejecutar
		int contadorInstruccionesRestantes = this.steps;
		boolean resultado = false;
		
		do{
			//si la instrucción se ejecuta correctamente
			if (CommandInterpreter.cpu.step()){
				resultado = true;
				//muestro estado de la máquina
				CommandInterpreter.printStateMachine();
				//quito una instrucción a ejecutar
				contadorInstruccionesRestantes --;
				//si era la última paro ejecución
				if(contadorInstruccionesRestantes == 0){
					resultado = false;
				}
			//si no ha ido bien, paro la ejecución y ¿reinicio la máquina?
			}else{
				//CommandInterpreter.cpu.resetCpu();
				resultado = false;
			}
		//repito hasta parar la ejecución	
		}while(resultado);
		
		//si la cpu me dice que no hay más instrucciones entonces paro la máquina
		if(CommandInterpreter.cpu.finished()){
			this.isFinished = true;
		}
		
		return resultado;
	}
	
	/**
	 * Método que pasa a string el comando ejecutado
	 * @return step + numero de instrucciones a ejecutar
	 */
	public String toString () {
		return "STEP "+this.steps;
	}
}
