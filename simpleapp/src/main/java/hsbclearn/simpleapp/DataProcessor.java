package hsbclearn.simpleapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class DataProcessor implements IDataProcessor 
{
	private int sortMode;
	
	DataProcessor()
	{
		sortMode = 1;
	}
	
	/*private int[] toInt(HashSet<Integer> set) 
	{
		int[] rettab = new int[set.size()];
		int ii = 0;
		for (Integer val : set) 
			rettab[ii++] = val;
		return rettab;
	}
	
	private List<IIntegerWrapper> RemoveDuplicatesFromList(List<IIntegerWrapper> inputIWList)
	{
		//HashSet<IIntegerWrapper> removeDuplicatesSet = new HashSet<IIntegerWrapper>(inputIWList);
		List ivals = new ArrayList();
		
		Iterator<IIntegerWrapper> itr = inputIWList.iterator();
		while (itr.hasNext()) 
		{
			IntegerWrapper tmpWrapper = (IntegerWrapper) itr.next();
			
			ivals.add( tmpWrapper.GetValue() );
		}			
		
		
		HashSet<Integer> removeDuplicatesSet = new HashSet<Integer>(ivals);
		List<IIntegerWrapper> retList = new ArrayList<IIntegerWrapper>(( new DataInput ( toInt(removeDuplicatesSet) ) ).GetData()); 
		
		return retList;
	} */
	
	private List<IntegerWrapper> RemoveDuplicatesFromList(List<IntegerWrapper> inputIWList)
	{
		//HashSet<IIntegerWrapper> removeDuplicatesSet = new HashSet<IIntegerWrapper>(inputIWList);
		List<IntegerWrapper> ivals = new ArrayList<IntegerWrapper>();
		
		Iterator<IntegerWrapper> itr = inputIWList.iterator();
		while (itr.hasNext()) 
		{
			IntegerWrapper tmpWrapper = (IntegerWrapper) itr.next();
			
			ivals.add( tmpWrapper );
		}			
		
		
		HashSet<IntegerWrapper> removeDuplicatesSet = new HashSet<IntegerWrapper>(inputIWList);
		
		List<IntegerWrapper> retList = new ArrayList<IntegerWrapper>(removeDuplicatesSet); 
		
		return retList;
	}
	
	private void AddElementWithSumOfElementsToList(List<IntegerWrapper> inputIWList)
	{
		int sum = 0;
		
		Iterator<IntegerWrapper> itr = inputIWList.iterator();
		while (itr.hasNext()) 
		{
			IntegerWrapper tmpWrapper = (IntegerWrapper) itr.next();
			
			sum += tmpWrapper.GetValue();
		}			
		
		inputIWList.add(new IntegerWrapper(sum));
	}
	
	private void SortIntegerWrapperList(List<IntegerWrapper> inputIWList)
	{
		Collections.sort(inputIWList, ((sortMode == 1) ? IntegerWrapper.Comparators.ASC : IntegerWrapper.Comparators.DSC) );			
	}
	
	public void setSortMode(String sortType) throws Exception
	{
		if (sortType != null && !sortType.isEmpty())
		{
			if (sortType == "DSC" )
				sortMode = 0;
			else if (sortType == "ASC")
				sortMode = 1;
			else
				throw new Exception("Unknown sort mode");
		}
		else
			throw new Exception("sortType cannot be null or empty");
	}
	
	public List<IntegerWrapper> Execute (List<IntegerWrapper> inputIWList) 
	{
		List<IntegerWrapper> returnList = null;
		
		if (inputIWList != null)
		{
			returnList = RemoveDuplicatesFromList(inputIWList);			
			AddElementWithSumOfElementsToList(returnList);	
			SortIntegerWrapperList(returnList);
		}

		return returnList;
	}

}
