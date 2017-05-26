package hsbclearn.simpleapp.jms;

import java.util.List;

import hsbclearn.simpleapp.IDataProcessor;
import hsbclearn.simpleapp.IIntegerWrapper;
import hsbclearn.simpleapp.IntegerWrapper;

public class JmsDataProcessor implements IDataProcessor 
{
	@Override
	public List<IntegerWrapper> Execute(List<IntegerWrapper> inputIWList) {

		for (IntegerWrapper element : inputIWList)
		{
			int newval = element.GetValue();
			
			element.SetValue(++newval);
		}
		
		return inputIWList;
	}


}
