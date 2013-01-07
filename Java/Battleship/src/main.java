import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;


public class main 
{
	static int player[][] = new int[6][6];
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		/*int i,j;
		int field=5;
		String player1[][] = new String[6][6];
		String temp;
		String dir;
		String place[];
		int hr;
		int hc;
		
		for(i=0;i<field;i++)
		{			
			for(j=0;j<field;j++)
			{				
				player1[i][j] = " ";
			}			
			
		}
		
		
		
		System.out.println("Enter a place on the grid: ");
		
		//BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
		try 
		{
			do
			{
				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
				temp = stdin.readLine();
				place = temp.split(",");
				hr = Integer.parseInt(place[0])-1;
				hc = Integer.parseInt(place[1])-1;
				
				System.out.println("Enter a direction on the grid(N,S,E,W): ");
				temp = stdin.readLine();
				dir = temp;
			}			
			while(hr > field || hc>field||hr > field && hc>field);
		//	player1[hr][hc] = "S";
			
			if(dir == "N")
			{
				player1[hr][hc] = "S";
				player1[hr+1][hc] = "S";
				player1[hr+2][hc] = "S";
			}
			if(dir == "S")
			{
				player1[hr][hc] = "S";
				player1[hr+1][hc] = "S";
				player1[hr+2][hc] = "S";
			}
			if(dir == "E")
			{
				player1[hr][hc] = "S";
				player1[hr][hc+1] = "S";
				player1[hr][hc+2] = "S";
			}
			if(dir == "W")
			{
				player1[hr][hc] = "S";
				player1[hr][hc-1] = "S";
				player1[hr][hc-2] = "S";
			}
			

			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
		
		       
		
		System.out.println("\t Player 1\t\t\t\tPlayer 2");
		System.out.println("\n  1   2   3   4   5   6\t\t\t  1   2   3   4   5   6");
		System.out.println("  -------------------------\t\t  -------------------------");
		//System.out.println("|   |   |   |   |    |    |\t\t|   |   |   |   |    |    |");
		//System.out.println("  -------------------------\t\t  -------------------------");
		
		for(i=0;i<field;i++)
		{
			System.out.print("|");
			for(j=0;j<field;j++)
			{	
				if(j==(field-1))
				{
					System.out.println(" "+player1[i][j]+" |");					
				}
				else
				{
					System.out.print(" "+player1[i][j]+" |");
				}
				
			}			
			System.out.println(" ---------------------------");
		}*/
		
		
		
		String text;
		BufferedReader serverReader = null;
		PrintWriter writer = null;
	//	Scanner stdin = new Scanner(System.in);
		String buffer;
		int i,j,r=0;
		String elements[] = new String[10];
		final String READY_SIGNAL = "Ready";
		
		int field = 6,top_left;
		for(i=0;i<field;i++)
		{			
			for(j=0;j<field;j++)
			{				
				player[i][j] = r++;
			}			
			
		}
		
		try{
			System.out.println("Place your ships please!");
			System.out.println("Format: top-left-cordinate [xy] orientation (H or V)"); 
			System.out.println("=====================================================");
			
			//Placement of Battleship
			do 
			{
				System.out.print("Battleship: ");
				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
				buffer = stdin.readLine();
				
				if(buffer.matches("[a-f][1-6] [HV]")) 
				{
					//buffer.concat(" B");
					buffer.toString();
					elements = buffer.split("");

					
					top_left = getTopLeft(elements[]);
					/*for(i=0;i<10;i++)
					{
						System.out.println(elements[i]);						
					}*/
					System.out.println(top_left);
					
					writer.println(buffer);
					//write size,orin,top_left = number
					//4,H/V,
					
					writer.flush();
				}
				else 
				{
					System.err.println("Incorrect format!");
					continue;
				}
			} while(serverReader.readLine().compareTo("Invalid Dimensions") == 0);			
			
			do 
			{
				//writer.println(stdin.nextLine());
			} while(serverReader.readLine().compareTo("Invalid Dimensions") == 0);


			writer.println(READY_SIGNAL);
			writer.flush();
		}
	catch(Exception e) 
	{
		System.err.println(e.getMessage());
	}
		
		
		
		

	}



	public int getTopLeft(String element[])
	{
		int top_left;
	
		if(element[1].contains("a"))
		{
			element[1]=Integer.toString(0);
		}
		else if(element[1].contains("b"))
		{
			element[1]=Integer.toString(1);
		}
		else if(element[1].contains("c"))
		{
			element[1]=Integer.toString(2);
		}
		else if(element[1].contains("d"))
		{
			element[1]=Integer.toString(3);
		}
		else if(element[1].contains("e"))
		{
			element[1]=Integer.toString(4);
		}
		else if(element[1].contains("f"))
		{
			element[1]=Integer.toString(5);
		}
	
		top_left = player[Integer.parseInt(element[1])][Integer.parseInt(element[2])];
	
		return top_left;
	}
}