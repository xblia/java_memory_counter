package com.memorymonitor;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 * 
 * @author xblia
 * Jun 2, 2015 - 8:41:21 PM
 */
public class JMemoryMonitorBar extends JProgressBar implements IMonitorMemory
{

	private static final long serialVersionUID = -7845453017454470230L;
	private MemoryMonitor memoryMonitor;

	public JMemoryMonitorBar()
	{
		this.setStringPainted(true);
		this.setMaximum(100);
		this.setString("memory monitor");
		Timer timer = new Timer();
		timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				JMemoryMonitorBar.this.startMonitor();
			}
		}, 3000);
	}
	
	public void startMonitor()
	{
		if(null != memoryMonitor)
		{
			memoryMonitor.stopMonitor();
		}
		memoryMonitor = new MemoryMonitor(this);
		memoryMonitor.start();
	}
	
	public void stopMonitor()
	{
		if(null != memoryMonitor)
		{
			memoryMonitor.stopMonitor();
		}
	}

	@Override
	public void onMemoryChange(String info, long total, long used)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				JMemoryMonitorBar.this.setString(info);
				int val = (int)((used*1.0/total) * 100);
				JMemoryMonitorBar.this.setValue(val);
				JMemoryMonitorBar.this.updateUI();
			}
		});
	}
}
