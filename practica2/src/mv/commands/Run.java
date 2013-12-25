package mv.commands;

public class Run extends Step {
	public Run(){
		super();
	}
	
	public boolean executeCommand(){
		CommandInterpreter.cpu.resetCpu();
		boolean resultado = false;
		
		do{
			
			if (CommandInterpreter.cpu.step()){
				resultado = true;
				CommandInterpreter.printStateMachine();
			}else{
				resultado = false;
			}
			
		}while(resultado!=false);
			
		
		return resultado;
	}
	
	public String toString () {
		return "RUN ";
	}
}
