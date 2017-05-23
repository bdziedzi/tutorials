package hsbclearn.simpleapp;

public class App 
{ 
	public static void main(String[] args) 
	{
        System.out.println( "Hello World!" );
        
        //int [] tmpIntTab = {1,-300,8,7,6,4,-3,5,6,6,9,0,4};
        int [] tmpIntTab = {1,-3,0,2};
        DataInput dti = new DataInput(tmpIntTab);
        DataProcessor dtp = new DataProcessor();
        DataOutput dto = new DataOutput();
        
        String inputXML = "<?xml version=\"1.0\" ?><IntegerWrapperElements><IntegerWrapper><value>100</value></IntegerWrapper><IntegerWrapper><value>-30</value></IntegerWrapper><IntegerWrapper><value>99</value></IntegerWrapper><IntegerWrapper><value>68</value></IntegerWrapper></IntegerWrapperElements>";
        
        String inputXML2 = "<?xml version=\"1.0\" ?><IntegerWrapperElements><Lista><IntegerWrapper><value>100</value></IntegerWrapper><IntegerWrapper><value>-30</value></IntegerWrapper><IntegerWrapper><value>99</value></IntegerWrapper><IntegerWrapper><value>68</value></IntegerWrapper></Lista></IntegerWrapperElements>";
        
        XMLDomParser xmldom = new XMLDomParser();
        
        XMLStaxParser xmlstax = new XMLStaxParser();

        XMLJaxpParser xmljaxp = new XMLJaxpParser();
        
        XMLJaxbParser xmljaxb = new XMLJaxbParser();
        
        try 
        {
			dto.PrintList(dtp.Execute(dti.GetData()));	
			
			System.out.println(xmldom.saveAsXML(dti.GetData())); 			
			
			dto.PrintList(dtp.Execute(xmldom.readXML(inputXML)));	
			
			System.out.println(xmlstax.saveAsXML(dti.GetData())); 	
			
			dto.PrintList(dtp.Execute(xmlstax.readXML(inputXML)));
			
			System.out.println(xmljaxp.saveAsXML(dti.GetData())); 	
						
			dto.PrintList(dtp.Execute(xmljaxp.readXML(inputXML)));
			
			xmljaxb.readXML(inputXML2);			
			
		} 
        catch (Exception e) 
        {			
			e.printStackTrace();
		}
       
    }
}
