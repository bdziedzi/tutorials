package hsbclearn.simpleapp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.Test;

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
}
