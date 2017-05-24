package hsbclearn.simpleapp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="IntegerWrapperElements")
public class xmlMsg 
{
	@XmlElementWrapper(name="Lista")
	@XmlElement(name="IntegerWrapper")
	private List <IIntegerWrapper> dataList;
	//private List <IntegerWrapper> dataList;

	public List <IIntegerWrapper> GetList()
	//public List <IntegerWrapper> GetList()
	{
		if (this.dataList == null) 
			this.dataList = new ArrayList();
			
	return this.dataList;
	}
	
	public void SetList(List <IIntegerWrapper> injList)
//	public void SetList(List <IntegerWrapper> injList)
	{
			this.dataList = injList;
	}
}
