package hsbclearn.simpleapp;

import java.util.Comparator;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
public class IntegerWrapper implements IIntegerWrapper, Comparable<IntegerWrapper>
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
    
	public int compareTo(IntegerWrapper arg0) 
	{
		return Comparators.ASC.compare(this, arg0);
	}
	
	 public static class Comparators {

		public static Comparator<IntegerWrapper> ASC = new Comparator<IntegerWrapper>() 
        {            
            public int compare(IntegerWrapper o1, IntegerWrapper o2) 
            {
                return o1.GetValue() - o2.GetValue();
            }
        };
        
        public static Comparator<IntegerWrapper> DSC = new Comparator<IntegerWrapper>() 
        {            
            public int compare(IntegerWrapper o1, IntegerWrapper o2) 
            {
                return o2.GetValue() - o1.GetValue();
            }
        };

	 }
}
