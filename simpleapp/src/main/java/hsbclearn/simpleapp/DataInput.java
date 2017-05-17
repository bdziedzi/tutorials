package hsbclearn.simpleapp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

public class DataInput implements IDataInput
{

	private List <IIntegerWrapper> dataList;
	
	//  constructor with no arguments will be enable when Insert method implemented
	//DataInput()
	//{
		//dataList = new ArrayList <IIntegerWrapper> ();
	//}
	
	DataInput(List <IIntegerWrapper> injIWList)
	{
		if (injIWList != null)
			dataList = injIWList;
		else
			dataList = new ArrayList <IIntegerWrapper> ();
	}
	
	DataInput(int[] injIntList)
	{
		dataList = new ArrayList <IIntegerWrapper> ();
		
		if (injIntList != null)
		{			
			for (int index = 0; index < injIntList.length; index++)
			{				
				IIntegerWrapper tmpIW = new IntegerWrapper(injIntList[index]);
				dataList.add(tmpIW);
			}
		}
	}	
	
	public void PrintData() 
	{
		Iterator<IIntegerWrapper> itr = dataList.iterator();
		while (itr.hasNext()) 
		{
			IIntegerWrapper tmpWrapper = (IIntegerWrapper) itr.next();
			int val = tmpWrapper.GetValue();
			System.out.print(val + " ");
		}
		System.out.println();		
	}
	
	public List <IIntegerWrapper> GetData()
	{
		return dataList;		
	}

	public int CountData() 
	{		
		return dataList.size();
	}
	
}
