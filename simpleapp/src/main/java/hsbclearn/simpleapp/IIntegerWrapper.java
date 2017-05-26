package hsbclearn.simpleapp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlJavaTypeAdapter(xmlMsg.ParentAdapter.class)
public interface IIntegerWrapper {

	//@XmlElement(name="value")
	int GetValue();
	
	void SetValue(int newValue);
	
}
