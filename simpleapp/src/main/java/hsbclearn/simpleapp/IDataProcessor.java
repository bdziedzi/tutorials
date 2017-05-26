package hsbclearn.simpleapp;

import java.util.List;

public interface IDataProcessor 
{
	List <IntegerWrapper> Execute(List <IntegerWrapper> inputIWList);
}
