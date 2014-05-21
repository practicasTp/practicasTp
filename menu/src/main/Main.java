package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class Main implements ActionListener {
	private JFrame frame;
	private JMenuBar menu;
	private JMenu opciones, importar;
	private JMenuItem programa, entrada;
	private JFileChooser fc;
	private String newProgram, newEntrada;
	
	public Main () {
		this.frame  = new JFrame("IMPORTANDO ARCHIVOS DESDE MENU");
		
		this.menu = new JMenuBar();
		this.frame.setJMenuBar(menu);
		this.opciones = new JMenu("File");
		this.menu.add(this.opciones);
		this.importar = new JMenu("Importar");
		
		this.programa = new JMenuItem("Programa");
		this.programa.addActionListener(this);
		this.importar.add(this.programa);
		
		this.entrada = new JMenuItem("Entrada");
		this.entrada.addActionListener(this);
		this.importar.add(this.entrada);

		this.opciones.add(this.importar);
		this.fc = new JFileChooser();
		
		this.defineFrame();
	}
	
	public void defineFrame () {
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(800, 600);
		this.frame.setVisible(true);
	}
	
	public void actionPerformed (ActionEvent e) {
		/*if (e.getSource() == this.importar) {
			this.createWindow();
		}*/
		if (e.getSource() == this.programa) {
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				 File file = fc.getSelectedFile();
				 System.out.println("Loading: " + file.getName());
				 System.out.println("Directorio: " + file.getParent());
				 System.out.println("Path: " + file.getPath());
				 this.newProgram = file.getName();
				 JOptionPane.showMessageDialog(this.frame, "Se ha importado correctamente el programa: " + this.newProgram);
			} else {
			 System.out.println("Load command cancelled by user.");
			}
		} else if (e.getSource() == this.entrada) {
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				 File file = fc.getSelectedFile();
				 System.out.println("Loading: " + file.getName());
				 System.out.println("Directorio: " + file.getParent());
				 System.out.println("Path: " + file.getPath());
				 this.newEntrada = file.getName();
				 JOptionPane.showMessageDialog(this.frame, "Se ha importado correctamente la entrada: " + this.newEntrada);
			} else {
			 System.out.println("Load command cancelled by user.");
			}
		}
	}
	
	public static void main(String[] ar) {
		Main main = new Main();
    }   
}
