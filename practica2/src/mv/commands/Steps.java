package mv.commands;

public class Steps extends Step {
	private int steps;
	
	public Steps (int steps) {
		this.steps = steps;
	}
	
	public boolean executeCommand() {		
		int contador = this.steps;
		boolean resultado = false;
		
		do{
			
			if (CommandInterpreter.cpu.step()){
				resultado = true;
				CommandInterpreter.printStateMachine();
			}else{
				resultado = false;
				contador = 0;
			}
			
			contador--;
		}while(contador!=0);
		
		return resultado;
	}
	
	public String toString () {
		return "STEP "+this.steps;
	}
}
