package hsbclearn.simpleapp.xmlparsers;

import java.util.List;

import hsbclearn.simpleapp.IntegerWrapper;

public interface IXMLMessageParser {

	String saveAsXML(List<IntegerWrapper> injIWlist);
	List<IntegerWrapper> readXML(String inXML); 

}
