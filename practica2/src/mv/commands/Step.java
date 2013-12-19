package mv.commands;

public class Step extends CommandInterpreter {
	
	public Step () {
		super();
	}
	
	public boolean executeCommand() {
		boolean resultado = false;
		if (CommandInterpreter.cpu.step()) resultado = true;
		
		if(CommandInterpreter.cpu.finished()){
			this.isFinished = true;
		}
		
		return resultado;
	}
	
	public String toString () {
		return "STEP";
	}
}
