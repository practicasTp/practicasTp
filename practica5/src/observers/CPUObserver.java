package observers;

import mv.instructions.Instruction;
import mv.program.ProgramMv;

public interface CPUObserver {
	public void onStartInstrExecution(Instruction instr); 
	public void onEndInstrExecution(int pc /*aqui tengo que ver que necesito*/);
	public void onStartRun();
	public void onEndRun();
	public void onError(String msg);
	public void onHalt();
	public void onReset(ProgramMv program);
}
