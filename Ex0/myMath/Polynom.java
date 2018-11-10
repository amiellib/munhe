package myMath;

import java.util.Iterator;
import java.util.LinkedList;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral.
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able
{
	// The polynom will be in linkedlist structure.

	private LinkedList<Monom> list; 
	/**
	 * This is a default constructor for the polynom.
	 * The new polynom will be 0*x^0= 0.
	 *  
	 */
	public Polynom()  
	{
		this.list = new LinkedList<>();
		list.add(new Monom(0,0));
	}
	// Zero Polygon constructor 
	/**
	 * This is a copy constructor from one polynom to another.
	 * 
	 * @param other The polynom we want to copy from.
	 */
	public Polynom(Polynom other) 
	{
		this.list = new LinkedList<>();
		Iterator<Monom> it = other.iteretor();
		while(it.hasNext()) 
		{
			list.add(new Monom(it.next()));
		}
	}

	/**
	 * This is a constructor that transforms a string to a polynom.
	 * we assume that the polynom can be like those syntax:
	 * 1. 3*x^2+x-5
	 * 2. -2x^3
	 * 3. x or X
	 * 4. 2
	 * 
	 * @param str The string that will be polynom.
	 */
	public Polynom(String str)
	{
		if (str.contains("^-"))
			throw new RuntimeException("The power can't be negative"); 
		this.list = new LinkedList<>();
		if (str == "" || str == "0")
			list.add(new Monom(0,0));
		String tempS = "";
		for (int i=0; i< str.length(); i++)
		{
			if (str.charAt(i) == '+')
			{
				this.add(new Monom(tempS));
				tempS = "";
			}
			else if (str.charAt(i) == '-')
			{
				if (i!=0)
					this.add(new Monom(tempS));
				tempS = "-";
			}
			else
				tempS += str.charAt(i); 
		}
		this.add(new Monom(tempS));
	}

	@Override
	public Polynom_able copy() 
	{	
		return new Polynom(this);	
	}

	@Override
	public void add(Polynom_able p1) 
	{
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext())
			this.add(it.next());		
	}
	@Override
	public void add(Monom m1) 
	{
		if (m1.isZero())
			return;
		Polynom_able p1 = this;
		Iterator<Monom> it = p1.iteretor();
		int position=0;
		Monom next = new Monom(0,0);
		while(it.hasNext())
		{
			next = it.next();
			int compare =  m1.compareTo(next);
			if (compare == 0) 
			{
				if(m1.get_coefficient()+next.get_coefficient()==0) 
				{ //when we have ax-ax we need to delete this monom from the polynom.
					list.remove(next);
					if (list.isEmpty())
						list.add(new Monom (0,0));
				}
				else
					next.add(m1);
				return;
			}
			else if (compare > 0)
			{
				list.add(position, m1);
				return;
			}
			position++; //if compare < 0, check with the next smaller monom.	

		}
		list.add(m1); //m1 is the element with the smallest power.
	}
	@Override
	public void substract(Polynom_able p1) 
	{ // multiply by -1 and add
		Polynom copy = new Polynom((Polynom)p1);
		copy.multiply(new Monom(-1,0));
		this.add(copy);
	}
	/**
	 *  This function is compute the product between Polynom 
	 *  to Monom.
	 * 
	 * @param m1 The Monom that we multiply by the polynom
	 */
	private void multiply(Monom m1) 
	{ 
		if (m1.isZero())
		{
			list.clear();
			list.add(new Monom(0,0));
			return;
		}
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext())
			it.next().multiply(m1);
	}
	@Override
	public void multiply(Polynom_able p1) 
	{
		if(!this.equals(p1)) 
		{
			Polynom copy = new Polynom(this);
			this.list.clear();
			this.list.add(new Monom(0,0));
			Iterator<Monom> it = p1.iteretor();
			while (it.hasNext()) 
			{
				Polynom temp = new Polynom(copy);
				temp.multiply(it.next()); //the answer of multiply (Polynom * Monom) from the private function we done.
				this.add(temp);
			}
		}
		else // Multiply be himself
		{
			Polynom ans = new Polynom(new Polynom());
			Iterator<Monom> it2 = this.iteretor();
			while (it2.hasNext()) 
			{
				Polynom copy2 = new Polynom(this);
				copy2.multiply(it2.next()); //the answer of multiply (Polynom * Monom) from the private function we done.
				ans.add(copy2);
			}
			this.list.clear();
			this.list.add(new Monom(0,0));
			this.add(ans);
		}
	}
	@Override
	public boolean isZero() 
	{
		Iterator<Monom> it = this.iteretor();
		if (it.next().equals(new Monom(0,0)))
			return true;
		return false;
	}
	@Override
	public double f(double x) 
	{
		double sumF = 0;
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext())
			sumF += it.next().f(x);
		return sumF;
	}

	@Override
	public Polynom_able derivative() 
	{
		Polynom_able answer = new Polynom();
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()) 
			answer.add(it.next().derivative());
		return answer;
	}
	@Override
	public double area(double x0, double x1, double eps) 
	{
		if (x0 >= x1)
			return 0;
		if (eps <=0)
			return 0;
		double step = x0;
		double sumArea = 0;
		while (step + eps <= x1)
		{
			sumArea += Math.abs(this.f(step)) * eps;
			step += eps;
		}
		sumArea += Math.abs(this.f(step)) * (x1 - step); //Sum the last square, his width<eps
		return sumArea;
	}
	@Override
	public double root(double x0, double x1, double eps) 
	{
		double fx0 = f(x0);
		double fx1 = f(x1);
		if (fx0 * fx1 > 0 || eps <= 0) 
		{
			System.out.println("Invaild input");
			return Double.MAX_VALUE;
		}
		if (Math.abs(fx0) <= eps)
			return x0;
		if (Math.abs(fx1) <= eps)
			return x1;
		while(true)
		{
			double x2 = (x0 + x1) /2;
			double fx2 = f(x2);
			if (Math.abs(fx2) <= eps)
				return x2;
			if (fx0 * fx2 < 0) 
			{
				x1=x2;
				fx1 = fx2;
			}
			else // fx2 * fx1 < 0
			{ 
				x0=x2;
				fx0 = fx2;
			}
		}
	}
	@Override
	public Iterator<Monom> iteretor() 
	{
		return list.iterator();
	}
	@Override
	public boolean equals(Polynom_able p1)
	{
		Iterator<Monom> itThis = this.iteretor();
		Iterator<Monom> itP1 = p1.iteretor();
		while (itThis.hasNext() && itP1.hasNext())
			if (!itThis.next().equals(itP1.next())) //the monoms not equals
				return false;
		if (itThis.hasNext() || itP1.hasNext()) //one polynom longer than the other
			return false;
		return true;
	}

	/**
	 * This is the override to the print function.
	 * This function transform an object, from this class(polynom), 
	 * and make it a string.
	 * 
	 * @return string that expresses the polynom.
	 */
	public String toString()
	{
		if (this.isZero())
			return "0";
		Iterator<Monom> it = this.iteretor();
		String s = it.next().toString();
		Monom temp = new Monom(0,0);
		while(it.hasNext()) {
			temp = it.next();
			if (temp.get_coefficient() == 0)
				break;
			if (temp.get_coefficient() > 0)
				s += "+";
			s += temp.toString();
		}
		return s;
	}

}
