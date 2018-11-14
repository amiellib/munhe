import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
/**
 * 
 * @author Shilo Gilor and Amiel Liberman
 *
 */
public class Main {
	/**
	 * This function calculates the area below the x line given a start point and end point and step size aand the string of the polynom
	 * @param str the string of the polynom
	 * @param x0 start point to calculate area under the x line
	 * @param x1 end point to calculate area under the x line
	 * @param eps size of step to do the calculation
	 * @return the area below the x line
	 * @throws NumberFormatException when number format is bad
	 * @throws wrongDataException when data input is not as required
	 */
	public static double areaUnder(String str,double x0, double x1, double eps) throws NumberFormatException, wrongDataException 
	{
		Polynom poly = new Polynom(str);
		double sumOfArea =0.0;
		for (double current_x = Math.min(x0, x1);current_x<Math.max(x0, x1);current_x+=eps) // looping through the steps for calculating
		{
			sumOfArea = (poly.f(current_x)>0) ? sumOfArea: sumOfArea - poly.f(current_x)*eps; // adding for sum area
		}
		return sumOfArea;
	}
	/**
	 *  This function finds all extremum between 2 given points for a polynom given as a string and uses eps steps 
	 * @param start is the start point to find extremum 
	 * @param end is the end point to find extremum 
	 * @param str the string of the polynom
	 * @param eps size of step to do the calculation
	 * @return an array list of the x value that has a extremum
	 * @throws NumberFormatException when number format is bad
	 * @throws wrongDataException when data input is not as required
	 */
	public ArrayList<Double> extremum(double start , double end , String str , double eps) throws NumberFormatException, wrongDataException 
	{
		Polynom poly = new Polynom(str);
		return extremum_polynom(start,end,poly,eps); // calling the private function
	}
	
	private static ArrayList<Double> extremum_polynom(double start , double end , Polynom poly , double eps) 
	{
		ArrayList<Double> old_array = new ArrayList<Double>();
		ArrayList<Double> new_array = new ArrayList<Double>();
		Iterator<Monom> monom = poly.iteretor();
		if (monom.hasNext()) // making sure poly not null
		{
			Monom biggest_monom = monom.next(); // biggest power monom
			if (biggest_monom.get_power()==2) // stop condition for the regression when power is 2 we have 1 extremum.
			{

				if (monom.hasNext())
				{
					Monom next_monom = monom.next();
					double coefficient = (next_monom.get_power()==1) ? next_monom.get_coefficient() : 0;
					double temp_extremum = -coefficient/(2*biggest_monom.get_coefficient());
					if (temp_extremum < end && temp_extremum > start)
					{
						new_array.add(-coefficient/(2*biggest_monom.get_coefficient()));
					}
					return new_array;
				}
				else
				{
					if (start*end<0)
					{
						new_array.add(0.0);
					}
					return new_array;
				}
			}
			else if (biggest_monom.get_power()>2) // we are here in a regular polynom with a power bigger than 2
			{
				old_array.add(start); // adding starting point
				old_array.add(end); // adding ending point
				old_array.addAll(extremum_polynom(start,end, (Polynom)poly.derivative() , eps)); // adding all derivatives extremum 
				Collections.sort(old_array); // sorting the array like that we have a list of x's of where there is a potentual of a root for this function
				for (int i=1; i<old_array.size();i++) // looping through the array
				{
					if (poly.derivative().f(old_array.get(i-1)) * poly.derivative().f(old_array.get(i))<=0 && !(Math.abs(poly.derivative().f(old_array.get(i)))<eps)) // if we have a root, (this is here so we dont throw an exception)
						new_array.add(poly.derivative().root(old_array.get(i-1), old_array.get(i), eps));				// find the 	extremum of this poly and add them to the list
				}
				return new_array;
			}
		}
		return new_array;
	}
	/**
	 * This is a private function
	 * @param value  is the number giveen to round
	 * @param eps the value to round to
	 * @return the round of the value
	 */
	private static double adjust_round(double value , double eps)
	{
		return Math.round(value / (eps/10)) * (eps/10);
	}
	/**
	 * @param str the string of the polynom
	 * @param start the x0 where we need to start the drawing 
	 * @param end the x1 where we need to end the drawing
	 * @param eps is the value of the length of the lines in the drawing
	 * @throws NumberFormatException when number format is bad
	 * @throws wrongDataException when data input is not as required
	 */
	public static void draw(String str,double start,double end , double eps) throws NumberFormatException, wrongDataException
	{
		Polynom poly = new Polynom(str);
		int steps = (int) ((end-start)/eps)+1; // calculate amount of steps 
		ArrayList<Double> extremum_xs = extremum_polynom(start , end ,poly ,eps); // get the extremum x's dots
		double[] xData = new double[steps] ,yData = new double[steps];
		//loop through to make lines at the size of eps so it will draw a gragh
		for (int i=0; i<steps;i++)
		{
			xData[i] = start +i*eps; // x value for grapgh
			yData[i] = poly.f(start +i*eps);	// y value for graph

		}		
		XYChart chart = QuickChart.getChart(str, "X", "Y", "y(x)", xData, yData); // build initial chart
		for (int i=0; i<extremum_xs.size();i++) // looping through the extremums to add those dots
		{
			chart.addSeries("extremum [" + adjust_round(extremum_xs.get(i),eps)+" , " + adjust_round(poly.f(extremum_xs.get(i)),eps) + " ]"  // define dot location name 
					, new double[] {adjust_round(extremum_xs.get(i),eps)}  // x value of extremum
			, new double[] {adjust_round(poly.f(extremum_xs.get(i)),eps)}, null); // y value of extremum
		}	
		new SwingWrapper(chart).displayChart(); // display chart


		try {
			BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapFormat.PNG); // save it to stay seeing it
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) throws NumberFormatException, wrongDataException {
		String str="x^3";
		draw(str,-2.0,-1.0,0.01);
		System.out.println(areaUnder(str,-5,-6,0.01));
	}


}