package com.memorymonitor;

/**
 * @author xblia
 * Jun 2, 2015 - 9:02:16 PM
 */
public interface IMonitorMemory 
{
	void onMemoryChange(String info, long total, long used);
}
