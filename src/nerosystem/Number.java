package nerosystem;

import java.util.Scanner;

/**
 * @author Suyash
 * The class Number is use to check whether the given input is a rational or irrational number.
 * If the input is a rational number then it is been converted to form of a/b else 'number is irrational' is displayed.  
 * 
 * @COMMENT that the logic for conversion of rational number into form of a/b is original and not been copy pasted from any
 * source best to my knowledge (but in case that it is on Internet and I might not have seen).
 * 
 * @COMMENT if  a or b of the a/b form of the inputted number exceeds the range of 
 * integer data type ( probably for number with more than 9 decimal places), this logic fails and garbage value is displayed
 * ( i.e. 2147483647/2147483647).
 * 
 * @COMMENT that any input given to any program normally in form of INTEGER, FLOAT, DOUBLE, BIG INTEGER, BIG DECIMAL
 *  will be a terminating one regardless of its length hence any condition for checking whether the number is rational
 *  or irrational is neither a necessary nor a sufficient condition.   
 * 
 * @logic for conversion of rational number to the form of a/b :
 * every number is in form of X.Y where Y is the decimal part,mathematically when we remove decimal the number becomes
 * XY/ 10^(no of decimal digits) [let XY/Z where XY means X and Y are concatenated, not multiplied]
 * then the by finding the GCD of XY and Z we can reduce the rational number to proper
 * NUMERATOR/DENOMINATOR form.
 * 
 * @logic for checking whether the number is rational or irrational:
 *  
 * Let square root of inputted number x be y
 * then for MOST of the cases if x-(y*y) is equal to zero. 
 * This logic works for most of the practically inputted value but in some cases like 1.125125125125..
 * which is inputted as 1.125125125 it takes it as rational. 
 * 
 */
public class Number {
	
	/**
	 * This is the main method,the execution begins from here.
	 * @param args
	 */
	public static void main(String args[])
	{
		double input=0;
		try{
		Scanner sc=new Scanner(System.in);
		System.out.println("Input the number :");
		input=sc.nextDouble();      /*value upto 16 decimal is considered else truncated by double. */
		sc.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		boolean flag=check(input);
		if(flag==true)
		{
			System.out.println("The inputted number is rational ");
			convert(input);
		}
		else
			System.out.println("The inputted number is irrational ");
	}
	/**
	 * Function to check whether number is rational or not.
	 * @param input
	 * @return boolean
	 */
	static boolean check(double input)
	{
		double sqrt=Math.sqrt(input);
		if(input-(Math.pow(sqrt, 2))==0.0)
			return true;
		else
			return false;
	}
	/**
	 * This function converts the rational number to a/b form ,input is taken in form of double and converted to String
	 * now number part and decimal part are extracted, after this appropriate power of 10 is calculated for denominator
	 * and the string without decimal point is merged which forms the numerator.
	 * The numerator and denominator part converted back to double and GCD of both part is found.
	 * After reducing the numerator and denominator to simplest ratio they are converted to integer type and displayed 
	 * in form of a/b.
	 *  
	 * @param input
	 */
	
	static void convert(double input)
	{
		String sign="";
		String convert=Double.toString(input);
		if(convert.charAt(0)=='-')
		{
			convert=convert.substring(1);
			sign="-";
		}
		int n=convert.indexOf('.');
		String decimalpart=convert.substring(n+1);
		String numberpart=convert.substring(0,n)+decimalpart;
		int l=decimalpart.length();
		double denominator=Math.pow(10, l);
		double numerator=Double.parseDouble(numberpart);
		double b=denominator,a=numerator;
		while (b != 0)
		{
			double temp = b;
			b = a % b;
			a = temp;
		}
		System.out.println("NUMERATOR / DENOMINATOR form is " +sign + (int)(numerator/a) + "/" +(int)(denominator/a) );
		
	}

}
