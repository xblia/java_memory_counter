package com.memorymonitor.test;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import com.memorymonitor.JMemoryMonitorBar;

/**
 * @author xblia
 * Jun 2, 2015 - 9:02:36 PM
 */
public class MainFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1770906206829710968L;
	private JMemoryMonitorBar memoryBar = new JMemoryMonitorBar();
	private JButton startBtn = new JButton("Start");
	private int width = 300;
	private int height = 300;
	
	public MainFrame() {
		this.setSize(width, height);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getRootPane().setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		
		initView();
		this.setVisible(true);
	}
	
	private void initView()
	{
		memoryBar = new JMemoryMonitorBar();
		startBtn = new JButton("Start");
		
		this.add(memoryBar, BorderLayout.NORTH);
		this.add(startBtn, BorderLayout.SOUTH);
		
		startBtn.addActionListener(this);
	}

	public static void main(String[] args) {
		new MainFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		new FileWriteTask();
	}
}
