package hsbclearn.simpleapp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AppTest 
{
    //IntegerWrapper:
	
    private static final int TAB_MAX_SIZE = 6;

	@Test
    public void testApp() 
    {
        assertTrue(true);
    }
    
    @Test
    public void IW_testIfIntegerWrapperSetValueSetsProperValue() 
    {
    	int testValue = 5;
    	int returnedValue = -1;
    	
    	IntegerWrapper testIntWrapper = new IntegerWrapper();
    	
    	testIntWrapper.SetValue(testValue);
    	returnedValue = testIntWrapper.GetValue();
    	
    	assertEquals("SetValue() does not set Value!", testValue, returnedValue);
    }
    
    @Test
    public void IW_testIfIntegerWrapperGetValueGetsProperValue() 
    {
    	int InitValue = 5;
    	int returnedValue = 0;
    	
    	IntegerWrapper testIntWrapper = new IntegerWrapper(InitValue);
    	
    	returnedValue = testIntWrapper.GetValue();
    	
    	assertEquals("GetValue() does not get Value!", InitValue, returnedValue);
    }
    
    //DataInput:
    @Test
    public void DI_testIfDataInputObjectCreateNewListIfInitializedWithNull1() 
    {
    	ArrayList<IIntegerWrapper> inputList = null;
    	DataInput testData = new DataInput(inputList);    	
    	
    	// if new list not created CountData() returns NullPointerException
        assertTrue(testData.CountData() == 0);
    }

    @Test
    public void DI_testIfDataInputObjectCreateNewListIfInitializedWithNull2() 
    {
    	int [] inputList = null;
    	DataInput testData = new DataInput(inputList);    	
    	
    	// if new list not created CountData() returns NullPointerException
        assertTrue(testData.CountData() == 0);
    }
    
    @Test
    public void DI_testIfDataInputObjectInitializeWithIntList() 
    {    	
    	int [] inputIntList = new int[TAB_MAX_SIZE];
    	int [] outputIntList = new int[TAB_MAX_SIZE];
    	int index = 0;
    	
    	List <IIntegerWrapper> outputData;
    
		Random generator = new Random(); 
		for (int ii=0;ii<TAB_MAX_SIZE; ii++)
			inputIntList[ii] = generator.nextInt(10);

    	DataInput testDatainputObject =  new DataInput(inputIntList);
    	
    	outputData = testDatainputObject.GetData();    	
    	
    	Iterator<IIntegerWrapper> itr = outputData.iterator();
		while (itr.hasNext()) 
		{
			IIntegerWrapper tmpWrapper = (IIntegerWrapper) itr.next();
			int val = tmpWrapper.GetValue();
			
			if (index < TAB_MAX_SIZE)
			{
				outputIntList[index] = val;
				index++;
			}			
		}		
		
        assertTrue(Arrays.equals(inputIntList, outputIntList));
    }
    
    @Test
    public void DI_testIFDataInputObjectInitializeWithInegerWrapperList() 
    {
    	List<IIntegerWrapper> inputData;
    	IntegerWrapper tmpIntWrapper;
    	List <IIntegerWrapper> outputData;  
    	Random generator;
    	DataInput testDataInputObject;
    	
    	inputData = new ArrayList<IIntegerWrapper>();
    	tmpIntWrapper = new IntegerWrapper();    	
    	generator = new Random(); 
    	
		for (int ii=0;ii<TAB_MAX_SIZE; ii++)
		{
			tmpIntWrapper.SetValue(generator.nextInt(10));
			inputData.add(tmpIntWrapper);		
		}
			
		testDataInputObject = new DataInput(inputData);		
		
        assertTrue(inputData == testDataInputObject.GetData());
    }

    //DataProcessor 
    @Test
    public void DP_testIfExecuteNotThrowingExceptionIfNullPassedAsArgument() 
    {    	
    	List<IIntegerWrapper> inputData = null;
    	IntegerWrapper tmpIntWrapper;    	
    	Random generator;
    	DataInput testDataInputObject;
        		
		testDataInputObject = new DataInput(inputData);				
		
    	DataProcessor dp = new DataProcessor();
    	
    	dp.Execute(testDataInputObject.GetData());
    	
    	assertTrue(true);
    }
    
    @Test
    public void DP_testIfExecuteRemovesDuplicatesAndSortList()
    {       	
    	int [] inputData = {3,2,3,2,1,3};
    	List<IIntegerWrapper> outputData = null;    	
    	int [] inputExpectedData = {1,2,3,6};
    	List<IIntegerWrapper> outputExpectedData = null;    	
    	IntegerWrapper tmpIntWrapper;    	
    	DataInput testDataInputObject, testDataExpectedObject;
    	int ii = 0;
		
		testDataInputObject = new DataInput(inputData);
		
		testDataExpectedObject = new DataInput(inputExpectedData);
		
    	DataProcessor dp = new DataProcessor();
    	
    	try 
    	{
			dp.setSortMode("ASC");
		} 
    	catch (Exception e) 
    	{			
			e.printStackTrace();
		}
    	
    	outputData = dp.Execute(testDataInputObject.GetData());
    	
    	Iterator<IIntegerWrapper> itr = outputData.iterator();
    	
    	while (itr.hasNext())
    	{
    		IIntegerWrapper tmpWrapper = (IIntegerWrapper) itr.next();
    		assertTrue(tmpWrapper.GetValue() == inputExpectedData[ii]);
    		ii++;
    	}    	
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void DP_testIfsetSortModeThrowsExceptionForNullMode() throws Exception
    {
    	int [] inputData = {3,2,3,2,1,3};
    	DataInput tmpDataInputObject;
    	DataProcessor dp;
    	  		
    	tmpDataInputObject = new DataInput(inputData);
		
    	dp = new DataProcessor();
    	
    	thrown.expect(Exception.class);
    	thrown.expectMessage("sortType cannot be null or empty");
    	
    	dp.setSortMode(null);
    }
    
    @Test
    public void DP_testIfsetSortModeThrowsExceptionForEmptyMode() throws Exception
    {
    	int [] inputData = {3,2,3,2,1,3};
    	DataInput tmpDataInputObject;
    	DataProcessor dp;
    	  		
    	tmpDataInputObject = new DataInput(inputData);
		
    	dp = new DataProcessor();
    	
    	thrown.expect(Exception.class);
    	thrown.expectMessage("sortType cannot be null or empty");
    	
    	dp.setSortMode("");
    }
    
    
    @Test
    public void DP_testIfsetSortModeThrowsExceptionForUnknownMode() throws Exception
    {
    	int [] inputData = {3,2,3,2,1,3};
    	DataInput tmpDataInputObject;
    	DataProcessor dp;
    	  		
    	tmpDataInputObject = new DataInput(inputData);
		
    	dp = new DataProcessor();
    	
    	thrown.expect(Exception.class);
    	thrown.expectMessage("Unknown sort mode");
    	
    	dp.setSortMode("Unknown mode");
    }
    
    @Test
    public void DO_testIfPrintListThrowsExceptionWhenInputListIsNullOrEmpty() throws Exception
    {
    	DataOutput testDataOut;
		List<IIntegerWrapper> outputData = null;  
    	
    	testDataOut = new DataOutput();
    	
    	thrown.expect(Exception.class);
    	thrown.expectMessage("List passed as argument is null or empty");
    	
    	testDataOut.PrintList(outputData);

    }
    
}
