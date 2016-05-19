package nerosystem;

import java.util.Scanner;
/**
 * @author Suyash, this question not been copy pasted from any source.
 * @class Intersection is to check whether the given two lines in 3D space will intersect or not
 * For a 2D line simply input 0 for the coordinate you want to neglect. 
 * Logic which is implemented is that two lines intersect if and only if they are coplaner and non-parallel.
 * Let line one coordinates be (x1,y1,z1) and (x2,y2,z2) and line two coordinates be (a1,b1,c1) and (a2,b2,c2)
 * then,
 * condition for two lines to be parallel is :
 * 
 * (x2-x1):(y2-y1):(z2-z1) = (a2-a1):(b2-b1):(c2-c1)
 * 
 * and
 * 
 * condition for two lines to be coplaner is :
 * 
 * determinant of 3X3 matrix formed by -
 *   (x2-x1),(y2-y1),(z2-z1)
 *   (a2-a1),(b2-b1),(c2-c1)
 *   (a1-x1),(b1-y1),(c1-z1)
 *   is equal to 0;
 */
public class Intersect {
	
	static Scanner sc=new Scanner(System.in);
	/**
	 * This is the main method,the execution begins from here.
	 * @param args
	 */
	public static void main(String args[])
	{
		int line1[] =new int[6];
		int line2[] =new int[6];
		System.out.println("Input coodinates of line");
		System.out.println("Enter line one coordinates, (x1,y1,z1) and (x2,y2,z2)");
		line1=input(); 
		System.out.println("Enter line two coordinates, (a1,b1,c1) and (a2,b2,c2)");
		line2=input();
		int linedirective[][]={{(line1[3]-line1[0]),(line1[4]-line1[1]),(line1[5]-line1[2])},
				{(line2[3]-line2[0]),(line2[4]-line2[1]),(line2[5]-line2[2])},
				{(line2[0]-line1[0]),(line2[1]-line1[1]),(line2[2]-line1[2])}};
		boolean b=parallel(linedirective);
		if(b==false)
		{
			System.out.println("Lines do not intesect");
			System.exit(0);
		}
		else
		{
			b=coplaner(linedirective);
		}
		if(b==true)
			System.out.println("Lines intersect");
		else
			System.out.println("Lines do not intersect");
	}
	
	/**
	 * The function parallel(integer [][]) is use to check whether the given two lines are parallel or not. 
	 * @param linedirective
	 * @return test (a boolean variable)
	 */
	static boolean parallel(int[][] linedirective)
	{
		
		int result1 = linedirective[0][0];
		int result2 = linedirective[1][0];
		boolean test=false;
		for (int i = 1; i < 3; i++)
		{
			result1 = gcd(result1, linedirective[0][i]);
			result2 = gcd(result2, linedirective[1][i]);
		}
		if((linedirective[0][0]>0)||(linedirective[0][1]>0)||(linedirective[0][2]>0))
		{
			result1=Math.abs(result1);
		}
		if((linedirective[1][0]>0)||(linedirective[1][1]>0)||(linedirective[1][2]>0))
		{
			result2=Math.abs(result2);
		}

		for(int i=0;i<3;i++)
		{
			
			if((linedirective[0][i]/result1) != (linedirective[1][i]/result2))
			{
				test=true;
				break;
			}
		}
		return test;
	}
	/**
	 * The function gcd(integer,integer) in use to find greatest common divisor of the two numbers.
	 * @param a
	 * @param b
	 * @return a (integer type)
	 */
	static int gcd(int a, int b)
	{
		while (b != 0)
		{
			int temp = b;
			b = a % b;
			a = temp;
		}
		
		return a;
	}
	
	/**
	 * The function input() is use to take Cartesian coordinates as input from the user. 
	 * @return line (array of inputted coordinates of 'this'line).
	 */
	static int[] input()
	{
		int line[] =new int[6];
		try{
		for(int i=0;i<6;i++)
		{
			line[i]=sc.nextInt();
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}	
		return line;
	}
	/**
	 * The function parallel(integer [][]) is use to check whether the given two lines are coplaner or not.
	 * @param a
	 * @return boolean type
	 */
	static boolean coplaner(int[][] a)
	{
		double j=a[0][1]*((a[1][1]*a[2][2])-(a[1][2]*a[2][1]))-a[0][1]*((a[1][0]*a[2][2])-(a[1][2]*a[2][0]))+
				a[0][2]*((a[1][0]*a[2][1])-(a[1][1]*a[2][0]));
		if(j==0)
			return true;
		else
			return false;
	}
	
}
