package hsbclearn.simpleapp;

import java.util.List;

public interface IXMLMessageParser {

	String saveAsXML(List<IIntegerWrapper> injIWlist);
	List<IIntegerWrapper> readXML(String inXML); 

}
