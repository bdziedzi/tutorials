package hsbclearn.simpleapp.xmlparsers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import hsbclearn.simpleapp.IntegerWrapper;

@XmlRootElement(name="IntegerWrapperElements")
@XmlAccessorType(XmlAccessType.FIELD)
public class xmlMsg 
{
	@XmlElementWrapper(name="Lista")
	@XmlElement(name="IntegerWrapper")
	private List <IntegerWrapper> dataList;


	public List <IntegerWrapper> GetList()
	{
		if (this.dataList == null) 
			this.dataList = new ArrayList<IntegerWrapper>();
			
	return this.dataList;
	}
	
	public void SetList(List <IntegerWrapper> injList)
	{
			this.dataList = injList;
	}
	
	public static class ParentAdapter extends XmlAdapter<Object,Object>{
        @Override
        public Object marshal( Object arg0 ) throws Exception{ return arg0; }

        @Override
        public Object unmarshal( Object arg0 ) throws Exception{ return arg0; }
    }


}
