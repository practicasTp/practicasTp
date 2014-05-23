package gui.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class ToolBarPanel extends JPanel implements CPUObserver, MemoryObserver<Integer>, StackObserver<Integer>{
	private GUIControler guiCtrl;
	private JButton stepButton;
	private JButton runButton;
	private JButton pauseButton;
	private JButton resetButton;
	
	public ToolBarPanel(GUIControler guiCtrl, Observable<CPUObserver> cpu, Observable<MemoryObserver<Integer>> memory, Observable<StackObserver<Integer>> stack) {
		memory.addObserver(this);
		stack.addObserver(this);
		cpu.addObserver(this);
		this.guiCtrl = guiCtrl;
		initGUI();
	}
	
	/**
	 * Inicializa el ToolBarPanel creando los botones correspondientes de acción para
	 * interactuar con el programa.
	 */
	private void initGUI() {
		
		//Dividimos el panel central en dos paneles
		JPanel centerPanelWest = new JPanel(new GridLayout(1,0));
		JPanel centerPanelEast = new JPanel(new GridLayout(1,0));
		
		centerPanelWest.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 0));
		centerPanelEast.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 15));
		
		//Definimos el tipo de estructura general de nuestra ventana
		setLayout(new BorderLayout());
		//Dividimos la ventana en secciones
		add(centerPanelWest, BorderLayout.WEST); 
		add(centerPanelEast, BorderLayout.EAST);
		
		
		stepButton = new JButton();
		stepButton.setIcon(createImageIcon("step.png"));
		stepButton.setToolTipText("Step");
		centerPanelWest.add(stepButton);
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.step();
			}
		});
		runButton = new JButton();
		runButton.setIcon(createImageIcon("run.png"));
		runButton.setToolTipText("Run");
		centerPanelWest.add(runButton);
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						guiCtrl.run();
					}	
				}.start();
				
			}
		});
		pauseButton = new JButton();
		pauseButton.setIcon(createImageIcon("pause.png"));
		pauseButton.setToolTipText("Pause");
		centerPanelWest.add(pauseButton);
		pauseButton.setVisible(false);
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.stop();
			}
		});
		resetButton = new JButton();
		resetButton.setIcon(createImageIcon("reset.png"));
		resetButton.setToolTipText("Reset");
		resetButton.setVisible(false);
		centerPanelEast.add(resetButton);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.reset();
			}
		});
		JButton quitButton = new JButton();
		quitButton.setIcon(createImageIcon("exit.png"));
		quitButton.setToolTipText("Exit");
		centerPanelEast.add(quitButton);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.quit();
			}
		});
		
	}
	
	/**
	 * Genera una imagen de icono y lo devuelve.
	 * @param path
	 * @return ImageIcon
	 */
	private static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MainWindow.class.getResource(path);
		if (imgURL != null)
			return new ImageIcon(imgURL);
		return null;
	}

	/**
	 * No hace nada en este elemento
	 */
	public void onStartInstrExecution(Instruction instr) {}

	/**
	 * No hace nada en este elemento
	 */
	public void onEndInstrExecution(int pc, Memory<Integer> memory, OperandStack<Integer> stack, ProgramMv program) {
		resetButton.setVisible(true);
	}

	@Override
	public void onStartRun() {
		stepButton.setEnabled(false);
		runButton.setEnabled(false);
		pauseButton.setVisible(true);
		resetButton.setEnabled(false);
	}

	@Override
	public void onEndRun() {
		stepButton.setEnabled(true);
		runButton.setEnabled(true);
		pauseButton.setVisible(false);
		resetButton.setEnabled(true);
	}

	/**
	 * No hace nada en este elemento
	 */
	public void onError(String msg) {}

	@Override
	public void onHalt() {
		stepButton.setEnabled(false);
		runButton.setEnabled(false);
	}

	@Override
	public void onReset(ProgramMv program) {
		pauseButton.setVisible(false);
		resetButton.setVisible(false);		
		stepButton.setEnabled(true);
		runButton.setEnabled(true);
		
	}

	@Override
	public void onWrite(int index, Integer value) {
		resetButton.setVisible(true);
	}

	@Override
	public void onMemReset() {}

	@Override
	public void onPush(Integer value) {
		resetButton.setVisible(true);
	}

	@Override
	public void onPop(Integer value) {
		resetButton.setVisible(true);
	}

	@Override
	public void onStackReset() {}

	@Override
	public void onNewIn() {
		// TODO Auto-generated method stub
		
	}
	
}
