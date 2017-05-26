package hsbclearn.simpleapp;

import java.util.ArrayList;
import java.util.List;

public class DataInput implements IDataInput
{

	private List <IntegerWrapper> dataList;
	
	//  constructor with no arguments will be enable when Insert method implemented
	//DataInput()
	//{
		//dataList = new ArrayList <IIntegerWrapper> ();
	//}
	
	DataInput(List <IntegerWrapper> injIWList)
	{
		if (injIWList != null)
			dataList = injIWList;
		else
			dataList = new ArrayList <IntegerWrapper> ();
	}
	
	DataInput(int[] injIntList)
	{
		dataList = new ArrayList <IntegerWrapper> ();
		
		if (injIntList != null)
		{			
			for (int index = 0; index < injIntList.length; index++)
			{				
				IntegerWrapper tmpIW = new IntegerWrapper(injIntList[index]);
				dataList.add(tmpIW);
			}
		}
	}	
	
	/*
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
	} */
	
	public List<IntegerWrapper> GetData()
	{
		return dataList;		
	}

	/*
	public int CountData() 
	{		
		return dataList.size();
	}
	*/
	
}
