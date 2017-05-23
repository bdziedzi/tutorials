package hsbclearn.simpleapp;

import java.util.Comparator;
import java.util.Objects;
import javax.xml.bind.annotation.*;


public class IntegerWrapper implements IIntegerWrapper, Comparable<IIntegerWrapper>
{
	
	@XmlElement(name="value")
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

	@Override
    public boolean equals(Object oo) {

        if (oo == this) 
        	return true;
        if (!(oo instanceof IntegerWrapper)) {
            return false;
        }

        IntegerWrapper other = (IntegerWrapper) oo;

        return (other.GetValue() == value);
    }

    @Override
    public int hashCode() 
    {   
        return  Objects.hash(value);
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
