package gui.swing;

import mv.cpu.Cpu;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.instructions.Out;
import mv.program.ProgramMv;
import mv.reading.InputMethod;
import mv.writing.OutputMethod;

public class GUIControler {
	private Cpu cpu;
	private MainWindow gui;

	GUIControler(Cpu cpu, MainWindow gui) {
		this.cpu = cpu;
		this.gui = gui;
	}

	private void reportError(String msg, String title) { 
		//... } // Usa JDialog
	}

	void step() { 
		//... }
	}

	void run() { 
		//... }
	}

	void pop() { 
		//... }
	}

	void push(String s) { 
		//... }
	}

	void quit() { 
		//... } // tiene que cerrar los InStream y OutStream
	}

	void memorySet(String n1, String n2) { 
		//... }
	}

	int getPC() {
		return cpu.getPC();
	}

	void setInStream(InputMethod in) { 
		//... }
	}

	void setOutStream(OutputMethod out) { 
		//... }
	}

	InputMethod getInStream() {
		return null; 
		//... 
		
	}

	OutputMethod getOutStream() { 
		//... }
		return null;
	}

	ProgramMv getProgram() { 
		return this.cpu.getProgram();
	}

	OperandStack<Integer> getOperandStack() { 
		//... }
		return null;
	}

	Memory<Integer> getMemory() { 
		//... }
		return null;
	}
}
