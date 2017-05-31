package hsbclearn.simpleapp.xmlparsers;

public enum ParserName {
	
	XMLDomParser(XMLDomParser.class),
	XMLJaxbParser(XMLJaxbParser.class),
	XMLJaxpParser(XMLJaxpParser.class),
	XMLStaxParser(XMLStaxParser.class);
	
	private Class <? extends IXMLMessageParser> parserType;
	
	private ParserName(Class<? extends IXMLMessageParser> parserType)
	{
		this.parserType = parserType;
	}

	public Class <? extends IXMLMessageParser> getParserType() {
		return parserType;
	}

	public void setParserType(Class <? extends IXMLMessageParser> parserType) {
		this.parserType = parserType;
	}
}
