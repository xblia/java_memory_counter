package com;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame implements IMonitorMemory
{
	private static final long serialVersionUID = 1770906206829710968L;
	private JLabel label = new JLabel("Memory...");
	private int width = 300;
	private int height = 300;
	
	public MainFrame() {
		this.setSize(width, height);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(label);
		this.getRootPane().setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		this.setVisible(true);
		
		new MemoryMonitor(this).start();
		
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}

	@Override
	public void onMemoryChange(String info) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				label.setText(info);
				label.updateUI();
			}
		});
	}
}
