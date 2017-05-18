package hsbclearn.simpleapp;

import java.util.Iterator;
import java.util.List;

public class DataOutput implements IDataOutput 
{

	public void PrintList(List<IIntegerWrapper> listToPrint) throws Exception 
	{
		if (listToPrint != null && !listToPrint.isEmpty())
		{
			Iterator<IIntegerWrapper> itr = listToPrint.iterator();
			System.out.print("Lista: ");
			while (itr.hasNext()) 
			{
				IntegerWrapper tmpWrapper = (IntegerWrapper) itr.next();
				
				System.out.print(tmpWrapper.GetValue() + ", ");
			}	
			
			System.out.println();
		}
		else
			throw new Exception("List passed as argument is null or empty");

	}

}
