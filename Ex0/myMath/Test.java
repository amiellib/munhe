package myMath;
/**
 * This class Performs a comprehensive test for all functions that
 * Required to do on Polynom class.
 * Therefore you can run in the main class and Check all function with a boolean function called 'checkAll',
 * or a function can be checked separately by a class object 'test'
 *
 * @author yoavG and eladN
 * @version 1.0
 */

public class Test 
{
//checks all functions.	
	public boolean checkAll() 
	{
		return checkInit() &&
				checkAdd() &&
				checkSubstract() &&
				checkMultiply() &&
				checkF() &&
				checkDerivative() &&
				checkArea() &&
				checkRoot();
	}
	
	public boolean checkInit() 
	{
		Polynom p1 = new Polynom("-20*x^10+3*x^4-5*x^2+7*x-995");
		Polynom p2 = new Polynom(p1.toString());
		if (!p1.equals(p2))
			return false;
		p2 = new Polynom(p1);
		if (!p1.equals(p2))
			return false;
		Polynom_able p3 = p2.copy();
		if (!p1.equals(p3))
			return false;
		p3.add(new Polynom("1"));
		if (p1.equals(p3))
			return false;
		Polynom p4 = new Polynom();
		Polynom p5 = new Polynom("0");
		if (!p4.equals(p5))
			return false;
		return true;
	}
	
	public boolean checkAdd() 
	{
		Polynom p1 = new Polynom("3*x^2+2*x-5");
		Polynom p2 = new Polynom("0");
		p1.add(p2);
		if (!p1.equals(new Polynom("3*x^2+2*x-5")))
			return false;
		p2.add(p2);
		if (!p2.equals(new Polynom("0")))
			return false;
		p1.add(p1);
		if (!p1.equals(new Polynom("6*x^2+4*x-10")))
			return false;
		Polynom p3 = new Polynom("2*x^8+3*x^4-5*x^2+7*x-995");
		Polynom p4 = new Polynom("3*x^2+2*x-5");
		p3.add(p4);
		if (!p3.equals(new Polynom("2*x^8+3*x^4-2*x^2+9*x-1000")))
			return false;
		return true;
	}
	
	public boolean checkSubstract()
	{
		Polynom p1 = new Polynom("3*x^2+2*x-5");
		Polynom p2 = new Polynom("0");
		p1.substract(p2);
		if (!p1.equals(new Polynom("3*x^2+2*x-5")))
			return false;
		p2.substract(p2);
		if (!p2.equals(new Polynom("0")))
			return false;
		p1.substract(p1);
		if (!p1.equals(p2))
			return false;
		Polynom p3 = new Polynom("2*x^8+3*x^4-5*x^2+7*x-995");
		Polynom p4 = new Polynom("22*x^7+3*x^4+5*x-5");
		p3.substract(p4);
		if (!p3.equals(new Polynom("2*x^8-22*x^7-5*x^2+2*x-990")))
			return false;
		p4.substract(new Polynom("22*x^7+3*x^4+5*x-5"));
		if (!p4.equals(p2))
			return false;
		return true;
	}
	
	public boolean checkMultiply() 
	{
		Polynom p1 = new Polynom("3*x^2+2*x-5");
		Polynom p2 = new Polynom("0");
		p2.multiply(p2);
		if (!p2.equals(new Polynom("0")))
			return false;
		p2.multiply(p1);
		if (!p2.equals(new Polynom("0")))
			return false;
		p1.multiply(p2);
		if (!p1.equals(new Polynom("0")))
			return false;
		Polynom p3 = new Polynom("3*x^2+2*x-5");
		Polynom p4 = new Polynom(p3);
		p3.multiply(p4);
		Polynom answer = new Polynom("9*x^4+12*x^3-26*x^2-20*x+25");
		if (!p3.equals(answer))
			return false;
		return true;
	}
	
	public boolean checkF() 
	{
		Polynom p1 = new Polynom("3*x^2+2*x-5");
		if (p1.f(0) != -5)
			return false;
		if (p1.f(-2) != 3)
			return false;
		Polynom p2 = new Polynom("5");
		if (p2.f(-2) != 5)
			return false;
		Polynom p3 = new Polynom();
		if (p3.f(-2) != 0)
			return false;
		return true;
	}
	
	public boolean checkDerivative() 
	{
		Polynom p1 = new Polynom("3*x^2+2*x-5");
		Polynom p2 = new Polynom("6*x+2");
		p1 = (Polynom)p1.derivative();
		if (!p1.equals(p2))
			return false;
		p1 = (Polynom)p1.derivative();
		p2 = new Polynom("6");
		if (!p1.equals(p2))
			return false;
		p1 = (Polynom)p1.derivative();
		p2 = new Polynom("0");
		if (!p1.equals(p2))
			return false;
		p1 = (Polynom)p1.derivative();
		if (!p1.equals(p2))
			return false;
		return true;
	}
	
	public boolean checkArea() 
	{
		Polynom p1 = new Polynom("10");
		double answer = p1.area(0, 10, 2);
		if (answer != 100)
			return false;
		answer = p1.area(0, 9, 2);
		if (answer != 90)
			return false;
		p1 = new Polynom();
		answer = p1.area(-5, 9, 0.1);
		if (answer != 0)
			return false;
		Polynom p2 = new Polynom("3*x^2+2*x-5");
		answer = 0;
		for (int i=0; i<=9; i++)
			answer += Math.abs(p2.f(i));
		if (answer != p2.area(0, 10, 1))
			return false;
		return true;
	}
	
	public boolean checkRoot() 
	{
		Polynom p1 = new Polynom("8*x");
		double answer = p1.root(-8, 5, 0.01);
		if (answer > 0.01 || answer < -0.01)
			return false;
		p1 = new Polynom("x^2-6*x+5");
		answer = p1.root(-8, 3.5, 0.01);
		if (answer > 1.01 || answer < -1.01)
			return false;
		answer = p1.root(1.015, 12, 0.01);
		if (answer > 5.01 || answer < -5.01)
			return false;
		return true;
					
	}
	
}
