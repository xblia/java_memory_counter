package com;

public class MemoryMonitor extends Thread 
{
	public static String line = "<br/>";
	private IMonitorMemory monitorMemory;
	private Runtime rt;
	
	public MemoryMonitor(IMonitorMemory monitorMemory) {
		super();
		this.monitorMemory = monitorMemory;
		rt = Runtime.getRuntime();
	}

	@Override
	public void run() 
	{
		while(true)
		{
			long freeMemory = rt.freeMemory();
			long maxMemory = rt.totalMemory();
			long usedMemory = rt.totalMemory() - rt.freeMemory();
			StringBuilder strInfo = new StringBuilder();
			strInfo.append("<html>");
			strInfo.append(" max:" + maxMemory + "(" +convertFileSize(maxMemory)+")" +line);
			strInfo.append("free:" + freeMemory + "(" +convertFileSize(freeMemory)+")" +line);
			strInfo.append(" use:" + usedMemory + "(" +convertFileSize(usedMemory)+")" +line);
			strInfo.append("<html/>");
			monitorMemory.onMemoryChange(strInfo.toString());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
