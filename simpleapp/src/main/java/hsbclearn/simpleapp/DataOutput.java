package hsbclearn.simpleapp;

import java.util.Iterator;
import java.util.List;

public class DataOutput implements IDataOutput 
{

	public String listout(List<IntegerWrapper> listToPrint) throws Exception 
	{
		String output = "";
		
		if (listToPrint != null && !listToPrint.isEmpty())
		{
			Iterator<IntegerWrapper> itr = listToPrint.iterator();
			System.out.print("Lista: ");
			while (itr.hasNext()) 
			{
				IntegerWrapper tmpWrapper = (IntegerWrapper) itr.next();
				
				//System.out.print(tmpWrapper.GetValue() + ", ");
				output += tmpWrapper.GetValue() + ", ";
			}	
						
			System.out.println(output);
			//System.out.println();
		}
		else
			throw new Exception("List passed as argument is null or empty");

		return output;
	}

}
