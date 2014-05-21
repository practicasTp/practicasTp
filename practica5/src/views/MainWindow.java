package views;


import gui.swing.InputPanel;
import gui.swing.MemoryPanel;
import gui.swing.OutputPanel;
import gui.swing.ProgramPanel;
import gui.swing.StackPanel;
import gui.swing.StatePanel;
import gui.swing.ToolBarPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.instructions.Instruction;
import mv.program.ProgramMv;
import observers.CPUObserver;
import observers.MemoryObserver;
import observers.Observable;
import observers.StackObserver;
import controllers.GUIControler;

public class MainWindow  extends JFrame implements CPUObserver, ActionListener{

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
	private StatePanel statePanel;
	
	//menu
	private JMenuBar menu;
	private JMenu opciones, importar;
	private JMenuItem programa, entrada, exit;
	private JFileChooser fc;
	
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
	 * al tipo. Adem√°s crea las ventanas necesarias para mostrar toda la informaci√≥n
	 * en la interfaz.
	 */
	private void initGUI() {
		this.toolBar 	= new ToolBarPanel(ctrl, cpu,memory,stack);
		this.program	= new ProgramPanel(ctrl,cpu);
		this.stackPanel = new StackPanel(ctrl,stack,cpu);
		this.memoryPanel= new MemoryPanel(ctrl,memory,cpu);
		this.input		= new InputPanel(ctrl,cpu);
		this.output		= new OutputPanel(ctrl,cpu);
		this.statePanel = new StatePanel(ctrl,stack,memory,cpu);
		//this.outputView = new StatusBar(cpu, stack, memory);
		

		//Definimos el tipo de estructura general de nuestra ventana
		setLayout(new BorderLayout());
		//Dividimos la ventana en secciones
		add(program, BorderLayout.WEST); 
		add(toolBar, BorderLayout.NORTH);
		add(statePanel,BorderLayout.SOUTH);
		
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
		
		//menu
		this.menu = new JMenuBar();
		this.setJMenuBar(menu);
		this.opciones = new JMenu("Archivo");
		this.opciones.setMnemonic('A');
		this.opciones.setPreferredSize(new Dimension(150, 20));
		this.menu.add(this.opciones);
		this.importar = new JMenu("Importar ...");
		this.importar.setMnemonic('I');
		this.importar.setIcon(new ImageIcon(this.getClass().getResource("folder.png")));
		this.importar.setPreferredSize(new Dimension(150, 20));
		this.programa = new JMenuItem("Programa", new ImageIcon(this.getClass().getResource("program.png")));
		this.programa.setMnemonic('P');
		this.programa.setPreferredSize(new Dimension(150, 20));
		this.programa.addActionListener(this);
		this.importar.add(this.programa);
		this.importar.addSeparator();
		this.entrada = new JMenuItem("Entrada", new ImageIcon(this.getClass().getResource("entrada.png")));
		this.entrada.addActionListener(this);
		this.entrada.setMnemonic('E');
		this.importar.add(this.entrada);
		this.opciones.add(this.importar);
		this.fc = new JFileChooser();
		this.opciones.addSeparator();
		this.exit = new JMenuItem("Salir ...", new ImageIcon(this.getClass().getResource("exit.png"))); 
		this.exit.setMnemonic('S');
	    this.opciones.add(this.exit);
	    this.exit.addActionListener(this);
	

		
		this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we){ 
				ctrl.quit();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.programa) {
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				this.ctrl.loadNewProgram(file.getPath());
				JOptionPane.showMessageDialog(this, "Se ha importado correctamente el programa: " + file.getName());
			}
		} else if (e.getSource() == this.entrada) {
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
			
				this.ctrl.loadNewInput(file.getPath());
				JOptionPane.showMessageDialog(this,"Se ha importado correctamente la entrada: "+ file.getName());
			}
		}else if (e.getSource() == this.exit) {
			this.ctrl.quit();
		}
	}
	
	//métodos inservibles en esta parte
	public void onStartInstrExecution(Instruction instr) {}
	public void onEndInstrExecution(int pc, Memory<Integer> memory, OperandStack<Integer> stack, ProgramMv program) {}
	public void onStartRun() {}
	public void onEndRun() {}
	public void onError(String msg) {}
	public void onHalt() {}
	public void onReset(ProgramMv program) {}

}
