package hsbclearn.simpleapp;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;

@Dependent
public class DataInput implements IDataInput
{
	private List <IntegerWrapper> dataList;
	
	public DataInput()
	{		
		dataList = new ArrayList <IntegerWrapper> ();
		System.out.println("DataInput()");
	}
	
	public DataInput(List <IntegerWrapper> injIWList)
	{
		if (injIWList != null)
			dataList = injIWList;
		else
			dataList = new ArrayList <IntegerWrapper> ();
	}
	
	public DataInput(int[] injIntList)
	{
		dataList = new ArrayList <IntegerWrapper> ();
		
		InsertInput(injIntList);
	}
	
	public void InsertInput (int[] injIntList)
	{
		if (injIntList != null)
		{		
			for (int index = 0; index < injIntList.length; index++)
			{				
				IntegerWrapper tmpIW = new IntegerWrapper(injIntList[index]);
				this.dataList.add(tmpIW);
			}			
		}		
		System.out.println("count: "+ this.dataList.size());
	}

	
	public List<IntegerWrapper> GetData()
	{
		return dataList;		
	}

	
}
