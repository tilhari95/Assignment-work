package nerosystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;  
import java.util.List;
import java.util.Scanner;  
	
/**
 * 
 * @author Suyash
 * I tried my best to understand execute this program correctly. 
 * 
 * The class Multithread takes required input from the user and calculates the Arithmetic Progression
 * @COMMENT 'calculates' is understood as generate, as its not clear what actually calculating AP means and
 * which functionality is to be implemented by multithread.
 * Concept of multithreading is been implemented which seems to be the most important part of the question.
 * 
 * To demonstrate this each value of AP is generated on a separate thread and finally the whole series is stored in the 
 * array and printed as required.
 *   
 */
	public class Multithread implements Runnable   
	{  
	  public boolean running = false;  
	   static Scanner sc=new Scanner(System.in);
	   int nth;
	   static double a,d;
	   static double collect[];
	  public Multithread (int nth)  
	  {  
		this.nth=nth;
	    Thread thread = new Thread(this);  
	    thread.start();  
	  }  
	    
	  public static void main (String[] args) throws InterruptedException  
	  {  
		  System.out.println("Enter value of first element, total number of elements and common difference");
		  a=sc.nextDouble();
		  int n=sc.nextInt();
		  d=sc.nextDouble();
		  collect=new double[n];
	    List<Multithread> thread = new ArrayList<Multithread>();  
	      
	    System.out.println("This is currently running on the main thread, " +  
	        "the id is: " + Thread.currentThread().getId());  
	  
	    Date start = new Date();  
	    /*
	     * starting n threads each for separate term.
	     */
	    for (int i=0; i<n; i++)  
	    {  
	      thread.add(new Multithread(i));   
	    }  
	      
	    /* We force the main thread to wait for all the threads  
	     * to finish their work before we check to see how long it  
	     * took to complete
	     */   
	    for (Multithread w : thread)  
	    {  
	      while (w.running)  
	      {  
	        Thread.sleep(100);  
	      }  
	    }  
	   
	      
	    Date end = new Date();  
	    Arrays.sort(collect);
	    double time = end.getTime() - start.getTime();  
	      
	    System.out.println ("This whole process took: " + time + " miliseconds"); 
	    display(collect , n);
	  }  
	    
	  @Override  
	  public void run()   
	  {  
	    this.running = true;  
	    collect[this.nth]=(a+(this.nth-1+1)*d);
	    System.out.println("This is currently running on a separate thread, " +  
	        "the id is: " + Thread.currentThread().getId()+ " value "+ collect[this.nth]);  
	    /**
	     * try-catch block to handle exceptions. 
	     */
	    try   
	    {  
	      Thread.sleep(100);  
	    }   
	    catch (InterruptedException e)   
	    {   
	      Thread.currentThread().interrupt();  
	    }  
	    this.running = false;  
	  }
	  /**
	   *This display function is to display the generated AP series 
	   * @param AP
	   * @param n
	   */
	  static void display(double[] AP , int n)
	  {
		  System.out.println("Arithmetic Progression is : ");
		  if(d>=0)
			  for(int i=0;i<n;i++)
				  System.out.print(AP[i]+" , ");
		  else
			  for(int i=n-1;i>=0;i--)
				  System.out.print(AP[i]+" , ");
	  }
	}  

