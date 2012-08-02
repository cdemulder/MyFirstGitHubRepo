package org.tof.bowling.model;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.tof.bowling.exception.WrongThrowValueException;



public class TestFrameManager {

	protected FrameManager turnManager; 
	
	@Before
	public void setUp(){
		turnManager = new FrameManager();
	}
	
	
	@Test
	public void testAllStrikes() throws Exception
	{
		//Frame 1, lancer 1
		Assert.assertNull(turnManager.lancer(15));
		//Frame 2, lancer 1
		Assert.assertNull(turnManager.lancer(15));
		//Frame 3, lancer 1
		Assert.assertNull(turnManager.lancer(15));
		//Frame 4, lancer 1
		Assert.assertNull(turnManager.lancer(15));
		//Frame 5, lancer 1
		Assert.assertNull(turnManager.lancer(15));
		//Frame 5, lancer 2
		Assert.assertNull(turnManager.lancer(15));
		//Frame 5, lancer 3
		Assert.assertNull(turnManager.lancer(15));
		//Frame 5, lancer 4
		Assert.assertEquals(new Integer(300),turnManager.lancer(15));
		
	}
	
	@Test
	public void testAllSpareIn2Throws() throws Exception
	{
		//Frame 1
		Assert.assertEquals(new Integer(1),turnManager.lancer(1));
		Assert.assertNull(turnManager.lancer(14));
		//Frame 2
		Assert.assertNull(turnManager.lancer(5));
		Assert.assertNull(turnManager.lancer(10));
		//Frame 3
		Assert.assertNull(turnManager.lancer(10));
		Assert.assertNull(turnManager.lancer(5));
		//Frame 4
		Assert.assertNull(turnManager.lancer(6));
		Assert.assertNull(turnManager.lancer(9));
		//Frame 5
		Assert.assertNull(turnManager.lancer(0));
		Assert.assertNull(turnManager.lancer(15));
		Assert.assertNull(turnManager.lancer(8));
		Assert.assertEquals(new Integer(150),turnManager.lancer(7));
		
	}
	
	@Test
	public void testAllSpareIn3Throws() throws Exception
	{
		//Frame 1
		Assert.assertEquals(new Integer(1),turnManager.lancer(1));
		Assert.assertEquals(new Integer(1),turnManager.lancer(0));
		Assert.assertNull(turnManager.lancer(14));
		//Frame 2
		Assert.assertNull(turnManager.lancer(5));
		Assert.assertEquals(new Integer(25),turnManager.lancer(0));
		Assert.assertNull(turnManager.lancer(10));
		//Frame 3
		Assert.assertNull(turnManager.lancer(10));
		Assert.assertEquals(new Integer(61),turnManager.lancer(3));
		Assert.assertNull(turnManager.lancer(2));
		//Frame 4
		Assert.assertNull(turnManager.lancer(6));
		Assert.assertEquals(new Integer(89),turnManager.lancer(7));
		Assert.assertNull(turnManager.lancer(2));
		//Frame 5
		Assert.assertNull(turnManager.lancer(0));
		Assert.assertEquals(new Integer(99),turnManager.lancer(4));
		Assert.assertNull(turnManager.lancer(11));
		Assert.assertNull(turnManager.lancer(8));
		Assert.assertEquals(new Integer(125),turnManager.lancer(7));
			
	}
	
	@Test
	public void testSparesAndStrikes() throws Exception
	{
		//Frame 1 - spare 3 throws
		Assert.assertEquals(new Integer(1),turnManager.lancer(1));
		Assert.assertEquals(new Integer(1),turnManager.lancer(0));
		Assert.assertNull(turnManager.lancer(14));
		
		//Frame 2 - strike
		Assert.assertNull(turnManager.lancer(15));
	
		//Frame 3 - spare 3 throws
		Assert.assertNull(turnManager.lancer(10));
		Assert.assertNull(turnManager.lancer(3));
		Assert.assertNull(turnManager.lancer(2));
		
		//Frame 4 - strike
		Assert.assertNull(turnManager.lancer(15));
		
		//Frame 5 - spare 2 throws
		Assert.assertNull(turnManager.lancer(0));
		Assert.assertNull(turnManager.lancer(15));
		Assert.assertNull(turnManager.lancer(11));
		Assert.assertEquals(new Integer(167),turnManager.lancer(0));
			
	}
	
	
	@Test
	public void testExemple1() throws Exception
	{
		//Frame 1
		Assert.assertEquals(new Integer(8),turnManager.lancer(8));
		Assert.assertEquals(new Integer(9),turnManager.lancer(1));
		Assert.assertEquals(new Integer(10),turnManager.lancer(1));
		
		//Frame 2
		Assert.assertEquals(new Integer(18),turnManager.lancer(8));
		Assert.assertNull(turnManager.lancer(7));
		
		//Frame 3
		Assert.assertNull(turnManager.lancer(1));
		Assert.assertEquals(new Integer(31),turnManager.lancer(2));
		Assert.assertEquals(new Integer(32),turnManager.lancer(1)); //Erreur dans énoncé?
		
		//Frame 4
		Assert.assertNull(turnManager.lancer(15));
		
		//Frame 5
		Assert.assertNull(turnManager.lancer(1));
		Assert.assertNull(turnManager.lancer(2));
		Assert.assertEquals(new Integer(55),turnManager.lancer(1)); //Erreur dans énoncé?
		
		
	}
	
	@Test
	public void testExemple2() throws Exception
	{
		//Frame 1
		Assert.assertNull(turnManager.lancer(15));
		
		//Frame 2
		Assert.assertNull(turnManager.lancer(8));
		Assert.assertNull(turnManager.lancer(1));
		Assert.assertEquals(new Integer(37),turnManager.lancer(2));
		
		//Frame 3
		Assert.assertEquals(new Integer(38),turnManager.lancer(1));
		Assert.assertEquals(new Integer(40),turnManager.lancer(2));
		Assert.assertNull(turnManager.lancer(12));
		
		//Frame 4
		Assert.assertNull(turnManager.lancer(6));
		Assert.assertEquals(new Integer(72),turnManager.lancer(4));
		Assert.assertEquals(new Integer(73),turnManager.lancer(1));
		
		//Frame 5
		Assert.assertNull(turnManager.lancer(15));
		Assert.assertNull(turnManager.lancer(8));
		Assert.assertNull(turnManager.lancer(2));
		Assert.assertEquals(new Integer(101),turnManager.lancer(3));
		
		
	}
	
	@Test
	public void testEmptyScore() throws Exception
	{
		//Frame 1
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));
		
		//Frame 2
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));	
		
		//Frame 3
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));
				
		//Frame 4
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));	
		
		//Frame 5
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));
		Assert.assertEquals(new Integer(0),turnManager.lancer(0));	
		
		
		
		
	}
	
	@Test(expected=WrongThrowValueException.class)
	public void testOutOfBounds1() throws Exception
	{
		turnManager.lancer(16);	
	}
	
	@Test(expected=WrongThrowValueException.class)
	public void testOutOfBounds2() throws Exception
	{
		turnManager.lancer(-89);	
	}
	
	@Test(expected=WrongThrowValueException.class)
	public void testOutOfBounds3() throws Exception
	{
		turnManager.lancer(8);
		turnManager.lancer(8);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testEndOfGame() throws Exception
	{
		for (int i=0;i<16;i++)
			turnManager.lancer(1);
	}
	
}
