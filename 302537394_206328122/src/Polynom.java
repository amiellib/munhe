
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * @author Shilo Gilor and Amiel Liberman
 *
 */
public class Polynom implements Polynom_able
{

	private ArrayList<Monom> monoms = new ArrayList<Monom>();
	/**
	 * this is an empty constructor
	 */
	public Polynom() 
	{
		super();
	}
	/**
	 * this constructor is the copy constructor 
	 * @param polynom is the polynom that we are copying
	 */
	public Polynom(Polynom polynom) 
	{
		super();	
		monoms = ((Polynom) polynom.copy()).monoms;
	}
	/**
	 * This constructor gets a string and brakes it down to the coefficient and power and adds them as monoms
	 * @param string is the string of the polynom 
	 * @throws wrongDataException  when given data is not compatible
	 * @throws NumberFormatException when given data is not compatible
	 */
	public Polynom(String string) throws NumberFormatException, wrongDataException 
	{
		super();
		boolean is_coefficient = true;
		String coefficient = "" , power = "";
		// replace +- to -
		string = string.replaceAll("\\+-", "-");
		// remove spaces
		string = string.replaceAll(" ", "");
		// remove [ . . . ] 
		string = (string.charAt(0) =='[' && string.charAt(string.length()-1) ==']') ? string.substring(1, string.length()-1) : string;
		for (int i = 0; i < string.length(); i++)
		{	
			// now in coefficient
			if (is_coefficient)
			{
				// not last char
				if (i+1<string.length())
				{
					// end of coefficient because of x X *
					if(string.charAt(i) =='x' || string.charAt(i) =='*' || string.charAt(i) =='X')	
					{
						//if my coefficient is blank so it means the the coefficient is 1
						if (coefficient.equals("") || coefficient.equals("-"))
						{
							coefficient += "1";
						}
						// i already have an x and the + or - means that i dont have a power so it means we are at power 1
						if(string.charAt(i+1)=='+' || string.charAt(i+1) == '-')
						{
							add(new Monom(Double.parseDouble(coefficient),1));
							coefficient = "";
							power = "";
						}
						// i already have an x		
						// regular done with coefficient so we are moving to power so coefficient is now false
						else
						{
							is_coefficient = false;
						}
					}
					// not x nor X nor *
					// if next value is + or - so no x's and its a monom where x^0
					else if (string.charAt(i+1)=='+' || string.charAt(i+1) == '-')
					{
						add(new Monom(Double.parseDouble(coefficient + string.charAt(i)), 0));
						coefficient = "";
					}
					// not x nor X nor * nor + nor -
					// if this value is - or . or digit so add it to coefficient	
					else if((Character.isDigit(string.charAt(i))|| string.charAt(i) == '-' || string.charAt(i) == '.'))
					{
						coefficient = coefficient + string.charAt(i);
					}
					// number starting is + so do nothing
					else if (string.charAt(i) == '+')
					{

					}
					// all other cases where this value is not valid
					else
					{
						throw new wrongDataException("A polynom can not have a not number value =  " + string.charAt(i) + " at location " + i);
					}
				}
				// this is the last char in the string
				// no need the if since its only case that its not "i+1<string.length()" so it would be easier to debug
				else if (i+1==string.length())
				{	
					//  when last char is x or X 
					if(string.charAt(i) =='x' || string.charAt(i) =='X')		
					{
						// make sure coefficient has a value
						if (coefficient.equals("") || coefficient.equals("-"))
						{
							coefficient += "1";
						}
						add(new Monom(Double.parseDouble(coefficient), 1));	
					}
					// when last char is number
					else if (Character.isDigit(string.charAt(i)))
					{
						add(new Monom(Double.parseDouble(coefficient + string.charAt(i)), 0));	
					}
					// all other cases where this value is not valid
					else
					{
						throw new wrongDataException("A polynom can not have a not number value =  " + string.charAt(i) + " at location " + i);
					}
				}
			}
			// now in power
			else
			{
				
				// not last char
				if (i+1<string.length())
				{
					// making sure that the beginning of power is written correctly
					 if (string.charAt(i) == '^' && !Character.isDigit(string.charAt(i+1)) || ((string.charAt(i) == 'x' || string.charAt(i) == 'X') && (string.charAt(i+1) != '^' && string.charAt(i+1) != ',')))
					{
						throw new wrongDataException("A power can not be a negitave power or a random symbal at location " + (i+1) + string.charAt(i) );
					}
					// when the string is presented as the toString value
					// for example "3.0*X^8, -8.0*X^3, 1.1*X, -3.0" 
					// we will need to skip the 2 values ", " and this means we are done with this monom
					if (string.charAt(i+1) ==',')
					{
						add(new Monom(Double.parseDouble(coefficient),(power=="" && (string.charAt(i)=='x' || string.charAt(i)=='X')) ? 1 :Integer.parseInt(power+ string.charAt(i))));
						coefficient = "";
						power = "";
						is_coefficient = true;
						i+=1;
					}
					// please notice && 
					// this case when the power is - we throw an exception
					else if (string.charAt(i+1) == '-' && string.charAt(i)=='^')
					{
						throw new wrongDataException("A power can not be a negitave power at location " + i );
					}
					// if next value is + or - it means we are done with this monom
					else if(string.charAt(i+1)=='+' || string.charAt(i+1) == '-' )
					{
						add(new Monom(Double.parseDouble(coefficient),Integer.parseInt(power+ string.charAt(i))));
						coefficient = "";
						power = "";
						is_coefficient = true;
					}
					// if we are not - nor + 
					// and we are a digit so add it to the power
					else if (Character.isDigit(string.charAt(i)))				
					{
						power = power + string.charAt(i);
					}
					else if (string.charAt(i) == 'x' || string.charAt(i) == 'X' || string.charAt(i) == '^')
					{

					}
					else if (string.charAt(i) == ',' && power =="")				
					{
						add(new Monom(Double.parseDouble(coefficient),1));
						coefficient = "";
						power = "";
						is_coefficient = true;			
					}
					// if not number 
					// all other cases where this value is not valid
					else
					{
						throw new wrongDataException("A polynom can not have a not number value =  " + string.charAt(i) + " at location " + i);
					}
				}
				// this is the last char in the string
				// no need the if since its only case that its not "i+1<string.length()" so it would be easier to debug
				else if (i+1==string.length())
				{
					// making sure last char is a digit
					if (Character.isDigit(string.charAt(i)))
					{
						add(new Monom(Double.parseDouble(coefficient), Integer.parseInt(power+ string.charAt(i))));
					}
					// all other cases where this value is not valid
					else
					{
						throw new wrongDataException("A polynom can not have a not number value = '" + string.charAt(i) + "' at location " + i);
					}
				}
			}
		}
	}
	/**
	 * The add polynom function gets a given polynom and the polynom we have and adds them together
	 * @param p1 is the polynom given to add
	 */
	@Override
	public void add(Polynom_able p1) 
	{
		Iterator<Monom> monom = p1.iteretor();
		while (monom.hasNext())
		{
			add(monom.next());
		}
	}
	/**
	 * the add monom function gets a given monom and adds it to the polynom
	 * of course if that monom already exists it just adds the coefficient to the given monom
	 * and if the sum of the coefficient is 0 so it removes it from the array
	 * @param m1 is the monom given to add
	 */
	@Override
	public void add(Monom m1)
	{
		Iterator<Monom> monom = iteretor();
		while (monom.hasNext())
		{
			Monom temp = monom.next();
			if (temp.get_power()==m1.get_power())
			{
				try {
					temp.add(m1);
				} catch (wrongDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// when the coefficient became 0 after adding monom so remove it
				if (temp.get_coefficient()==0)
				{
					monom.remove();
				}
				//  since we found a match we do not want to continue so we end this function
				return;
			}
		}
		// when the adding monom is not 0 then add it
		if (m1.get_coefficient()!=0)
		{
			try {
				monoms.add(new Monom (m1.get_coefficient() , m1.get_power()));
			} catch (wrongDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		monoms.sort(new Monom_Comperator());
	}
	/**
	 * the substract polynom function gets a given polynom and substracts it to the polynom
	 * we use it by adding athe same monom but with a opposite value like that it subtracts
	 * @param p1 is the polynom given to subtract from this polynom
	 */
	@Override
	public void substract(Polynom_able p1) 
	{
		Iterator<Monom> monom = p1.iteretor();
		while (monom.hasNext())
		{
			Monom temp = monom.next();
			try {
				add(new Monom (0 - temp.get_coefficient(),temp.get_power()));
			} catch (wrongDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * the multiply polynom function gets a given polynom and multiplies it to the polynom
	 * temp_polynom is a temp polynom with the result of the multiple and then we change this monoms to point at the temp variable
	 * @param p1 is the polynom given to multiply this polynom
	 */
	@Override
	public void multiply(Polynom_able p1) 
	{
		Polynom temp_poly = new Polynom();
		Iterator<Monom> monom = iteretor();
		while (monom.hasNext())
		{
			Monom temp_monom_p1= (monom.next());
			Iterator<Monom> monomP1 = p1.iteretor();
			while (monomP1.hasNext())
			{
				temp_poly.add(monomP1.next().multiply(temp_monom_p1));
			}
		}
		monoms = temp_poly.monoms;
	}
	/**
	 * the equals function gets a given polynom and loops through its monoms and checks if the monoms are the same as in this polynom
	 * we iterate through both and check since both array have to be sorted already
	 * @param p1 is the polynom given to check if it is equal to this polynom
	 * @return a boolean if the 2 polynoms are the same
	 */
	@Override
	public boolean equals(Polynom_able p1)
	{	
		Iterator<Monom> monomP1 = p1.iteretor();
		Iterator<Monom> monom = iteretor();
		while (monomP1.hasNext())
		{
			if (!monom.hasNext())
			{
				return false;
			}
			else if (!monom.next().equals(monomP1.next()))
			{
				return false;
			}
		}
		return (monom.hasNext()) ? false : true;
	}
	/**
	 * isZero function checks if all this polynom coefficient are zero
	 * of course a zero polynom has to be an empty array, but we are looping through just to double check
	 * @return boolean if all coefficient are zeros
	 */
	@Override
	public boolean isZero() 
	{
		Iterator<Monom> monom = iteretor();
		while (monom.hasNext())
		{
			if(monom.next().get_coefficient()!=0)
			{
				return false;
			}
		}		
		return true;
	}
	/**
	 * The root function finds a root value of a polynom while given 2 values that 1 is positive and the other negative
	 * and eps which will make sure that we are eps close to the 0.
	 * xt is the current x value being checked
	 * value is the polynom value of xt 
	 * @param x0 should be the lower value of the x's provided
	 * @param x1 should be the higher value of the x's provided
	 * @param eps is the threshold value to check if the x value is close to the correct answer
	 * @return 999999 when we are not sure if there is a result (hense x0*x1 is positive) or the x value that is close to the root
	 */
	@Override
	public double root(double x0, double x1, double eps) 
	{
		if (f(x0)*f(x1)>0)
		{
			try {
				throw new wrongDataException("you're 2 values of x do not have opposite values for their y's");
			} catch (wrongDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
		}
		double last_value = f(Math.min(x0, x1));
		double current_xt_value = Math.min(x0, x1);
		for (;current_xt_value<=Math.max(x0, x1);current_xt_value+=eps)
		{
			if (last_value*f(current_xt_value)<=0)
			{
				return current_xt_value;
			}
			else
			{
				last_value = f(current_xt_value);
			}
		}
		// the function should never reach this
		return current_xt_value;
	}
	/**
	 * the copy function returns a polynom with the same monoms as it has
	 * temp_polynom is the temp polynom where we are adding all the monoms
	 * @return a copy of itself
	 */
	@Override
	public Polynom_able copy() 
	{
		Polynom temp_polynom = new Polynom();
		Iterator<Monom> monom = iteretor();
		while (monom.hasNext())
		{
			Monom current = monom.next();
			try {
				temp_polynom.add(new Monom (current.get_coefficient() , current.get_power()));
			} catch (wrongDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
		}		
		return temp_polynom;
	}
	/**
	 * the derivative function takes this polynom and returns its derivative
	 * temp_polynom is the new polynom as the derivative
	 * @return the derivative as a new polynom
	 */
	@Override
	public Polynom_able derivative()
	{
		Polynom temp_polynom = new Polynom();
		Iterator<Monom> monom = iteretor();
		while (monom.hasNext())
		{
			Monom temp_monom = (monom.next());
			if (temp_monom.get_power()>0)
			{
				temp_polynom.add(temp_monom.derivative());
			}
		}
		return temp_polynom;
	}
	/**
	 * the area function uses Riemann's Integral to calculate the area 
	 * in this function we only calculate the area of the graph above the x line
	 * sumOfArea is the current sum of area being calculated 
	 * @param x0 should be the lower value of the x's provided
	 * @param x1 should be the higher value of the x's provided
	 * @param eps is the step value to calculate the area
	 * @return the final sum of area calculated
	 */
	@Override
	public double area(double x0, double x1, double eps) 
	{
		double sumOfArea =0.0;
		for (double current_x = Math.min(x0, x1);current_x<Math.max(x0, x1);current_x+=eps)
		{
			sumOfArea = (f(current_x)<0) ? sumOfArea: sumOfArea + f(current_x)*eps;
		}
		return sumOfArea;
	}
	/**
	 * the iterator functions returns a iterator of monoms
	 * @return an iterator of monoms
	 */
	@Override
	public Iterator<Monom> iteretor()
	{
		return monoms.iterator();
	}
	/**
	 * the toString function returns the monoms as a printable string
	 * @return the string to be printed
	 */
	@Override
	public String toString() 
	{
		return monoms.toString();
	}
	/**
	 * the function f calculates the value of the polynom given an x value
	 * value is the running value of the monoms
	 * @param x is the x value to be calculated
	 * @return the final value of all monoms
	 */
	@Override
	public double f(double x) 
	{
		double value =0;
		Iterator<Monom> monom = iteretor();
		while (monom.hasNext())
		{
			value = value + monom.next().f(x);
		}		
		return value;
	}
}