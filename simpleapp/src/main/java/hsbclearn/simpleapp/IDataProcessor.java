package hsbclearn.simpleapp;

import java.util.List;

public interface IDataProcessor 
{
	List <IIntegerWrapper> Execute(List <IIntegerWrapper> inputIWList);
}
