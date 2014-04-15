package gui.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

import mv.cpu.Cpu;
import mv.writing.OutputMethod;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private Cpu cpu;
	private ToolBarPanel toolBar;
	private ProgramPanel program;
	private StackPanel stack;
	private MemoryPanel memory;
	private InputPanel input;
	private OutputPanel output;

	public MainWindow(Cpu cpu) {
		//Titulo del la ventana
		super("Maquina Virtual");
		this.cpu = cpu;
		initGUI();
		updateView();
	}
	private void initGUI() {
		GUIControler guiCtrl = new GUIControler(cpu, this);
		this.toolBar 	= new ToolBarPanel(guiCtrl);
		this.program	= new ProgramPanel(guiCtrl);
		this.stack 		= new StackPanel(guiCtrl);
		this.memory 	= new MemoryPanel(guiCtrl);
		this.input		= new InputPanel(guiCtrl);
		this.output		= new OutputPanel(guiCtrl);
		

		//Definimos el tipo de estructura general de nuestra ventana
		setLayout(new BorderLayout());
		//Dividimos la ventana en secciones
		add(program, BorderLayout.WEST); 
		add(toolBar, BorderLayout.NORTH);
		
		//Panel para todo lo que va en el centro, Memoria, Pila, Entrada y Salida.
		JPanel centerPanel = new JPanel(new GridLayout(2,0));
		
		add(centerPanel,BorderLayout.CENTER);
		
		//Dividimos el panel central en dos paneles uno para la pila y la memoria y otro para la entrada y salida.
		JPanel centerPanelNorth = new JPanel(new GridLayout(1,0));
		JPanel centerPanelSouth = new JPanel(new GridLayout(2,0));
		
		//Paneles para pila y memoria
		JPanel stackPanel = new JPanel(new BorderLayout());
		JPanel memoryPanel = new JPanel(new BorderLayout());
		
		//Al norte pila y memoria
		centerPanel.add(centerPanelNorth);
		
		centerPanelNorth.add(stackPanel);
		centerPanelNorth.add(memoryPanel);
		
		stackPanel.add(stack);
		memoryPanel.add(memory);
		
		//Al sur entrada y salida
		centerPanel.add(centerPanelSouth);
		
		centerPanelSouth.add(input);
		centerPanelSouth.add(output);
		
		this.setSize(1000,1000);
        this.setLocationRelativeTo(null);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	void updateView() {
		program.updateView();
		stack.updateView();
		memory.updateView();
	}
}