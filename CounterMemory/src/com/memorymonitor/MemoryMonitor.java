package com.memorymonitor;

/**
 * @author xblia
 * Jun 2, 2015 - 9:02:25 PM
 */
public class MemoryMonitor extends Thread 
{
	public static String line = "<br/>";
	private IMonitorMemory monitorMemory;
	private Runtime rt;
	private boolean isRunning = true;
	
	public MemoryMonitor(IMonitorMemory monitorMemory) {
		super();
		this.monitorMemory = monitorMemory;
		rt = Runtime.getRuntime();
	}
	
	

	@Override
	public void run() 
	{
		while(isRunning)
		{
			long freeMemory = rt.freeMemory();
			long maxMemory = rt.totalMemory();
			long usedMemory = maxMemory - freeMemory;
			String strInfo =convertFileSize(usedMemory)  + " / " + convertFileSize(maxMemory);
			monitorMemory.onMemoryChange(strInfo, maxMemory, usedMemory);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stopMonitor()
	{
		this.isRunning = false;
	}
	
	public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
 
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }

}
