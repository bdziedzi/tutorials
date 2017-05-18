package hsbclearn.simpleapp;

import java.util.Comparator;

public class IntegerWrapper implements IIntegerWrapper, Comparable<IIntegerWrapper>
{

	private int value;
	
	public IntegerWrapper()
	{
		value = 0;	
	}
	
	public IntegerWrapper(int initValue)
	{
		value = initValue;	
	}
	
	public int GetValue() 
	{

		return this.value;
	}

	public void SetValue(int newValue) 
	{
		
		value = newValue;
	}

	public int compareTo(IIntegerWrapper arg0) 
	{
		return Comparators.ASC.compare(this, arg0);
	}
	
	 public static class Comparators {

        public static Comparator<IIntegerWrapper> ASC = new Comparator<IIntegerWrapper>() 
        {            
            public int compare(IIntegerWrapper o1, IIntegerWrapper o2) 
            {
                return o1.GetValue() - o2.GetValue();
            }
        };
        
        public static Comparator<IIntegerWrapper> DSC = new Comparator<IIntegerWrapper>() 
        {            
            public int compare(IIntegerWrapper o1, IIntegerWrapper o2) 
            {
                return o2.GetValue() - o1.GetValue();
            }
        };

	 }
}
