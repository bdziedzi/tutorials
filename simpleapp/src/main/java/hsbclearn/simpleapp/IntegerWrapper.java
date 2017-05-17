package hsbclearn.simpleapp;

public class IntegerWrapper implements IIntegerWrapper {

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

}
