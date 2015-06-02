package com.memorymonitor.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;

/**
 * @author xblia Jun 2, 2015 - 9:04:19 PM
 */
public class FileWriteTask extends Thread
{

	File file = new File(System.getProperty("user.dir") + File.separator
			+ System.currentTimeMillis() + ".txt");
	boolean isRunning = true;
	long beginTime = System.currentTimeMillis();
	public FileWriteTask()
	{
		this.start();
	}

	@Override
	public void run()
	{
		FileOutputStream fileWriter = null;
		try
		{
			fileWriter = new FileOutputStream(file);
			byte b = 1;
			while (isRunning)
			{
				byte[] buff = new byte[1024*5];
				for(int i = 0; i < 1024 * 5; i++)
				{
					buff[i] = b;
				}
				fileWriter.write(buff);
				fileWriter.flush();
				if((System.currentTimeMillis() - beginTime) > (1000 * 30))
				{
					break;
				}
			}
			fileWriter.flush();
			System.out.println("----------------");
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (null != fileWriter)
			{
				try
				{
					fileWriter.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public void stopTask()
	{
		this.isRunning = false;
	}
}
