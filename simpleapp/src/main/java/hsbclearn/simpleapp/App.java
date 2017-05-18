package hsbclearn.simpleapp;

public class App 
{ 
	public static void main(String[] args) 
	{
        System.out.println( "Hello World!" );
        
        int [] tmpIntTab = {1,3,8,7,6,4,3,5,6,6,9,0,4};
        DataInput dti = new DataInput(tmpIntTab);
        DataProcessor dtp = new DataProcessor();
        DataOutput dto = new DataOutput();

        try 
        {
			dto.PrintList(dtp.Execute(dti.GetData()));			
		} 
        catch (Exception e) 
        {			
			e.printStackTrace();
		}
       
    }
}
