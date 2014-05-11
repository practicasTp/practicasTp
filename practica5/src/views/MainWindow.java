package views;


import gui.swing.InputPanel;
import gui.swing.MemoryPanel;
import gui.swing.OutputPanel;
import gui.swing.ProgramPanel;
import gui.swing.StackPanel;
import gui.swing.ToolBarPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mv.instructions.Instruction;
import mv.program.ProgramMv;
import observers.CPUObserver;
import observers.MemoryObserver;
import observers.Observable;
import observers.StackObserver;
import controllers.Controller;
import controllers.GUIControler;

public class MainWindow  extends JFrame implements CPUObserver{

	private GUIControler ctrl;
	private Observable<CPUObserver> cpu;
	private Observable<StackObserver<Integer>> stack;
	private Observable<MemoryObserver<Integer>> memory;
	private ToolBarPanel toolBar;
	private ProgramPanel program;
	private StackPanel stackPanel;
	private MemoryPanel memoryPanel;
	private InputPanel input;
	private OutputPanel output;
	
	public MainWindow(GUIControler ctrl, Observable<CPUObserver> cpu, Observable<StackObserver<Integer>> stack, Observable<MemoryObserver<Integer>> memory) {
		super("Virtual Machine");
		this.cpu = cpu;
		this.stack = stack;
		this.memory = memory;
		this.ctrl = ctrl;
		initGUI();
		cpu.addObserver(this);
	}
	
	/**
	 * Inicializa el MainWindow rellenando los atributos que objetos correspondientes
	 * al tipo. Además crea las ventanas necesarias para mostrar toda la información
	 * en la interfaz.
	 */
	private void initGUI() {
		this.toolBar 	= new ToolBarPanel(ctrl, cpu);
		this.program	= new ProgramPanel(ctrl,cpu);
		this.stackPanel = new StackPanel(ctrl,stack,cpu);
		this.memoryPanel= new MemoryPanel(ctrl,memory,cpu);
		this.input		= new InputPanel(ctrl);
		this.output		= new OutputPanel(ctrl);
		//this.outputView = new StatusBar(cpu, stack, memory);
		

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
		
		stackPanel.add(this.stackPanel);
		memoryPanel.add(this.memoryPanel);
		
		//Al sur entrada y salida
		centerPanel.add(centerPanelSouth);
		
		centerPanelSouth.add(input);
		centerPanelSouth.add(output);
		
		this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we){ 
				//guiCtrl.quit();
			}
		});
	}
	
	@Override
	public void onStartInstrExecution(Instruction instr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndInstrExecution(int pc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHalt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(ProgramMv program) {
		// TODO Auto-generated method stub
		
	}

}
