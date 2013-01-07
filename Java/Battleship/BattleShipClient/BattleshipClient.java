import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.regex.*;
import Utility.*;

public class BattleshipClient 
{
	
	static int player[][] = new int[6][6];
	
	public static void main(String args[]) 
	{
		String text;
		BufferedReader serverReader;
		PrintWriter writer;
		Scanner stdin = new Scanner(System.in);
		String buffer;
		int top_left = 0;
		final String READY_SIGNAL = "Ready";
		String elements[] = new String[10];
		
		
		int i,j,r=0;
		
		for(i=0;i<6;i++)
		{
			for(j=0;j<6;j++)
			{
				player[i][j] = r++;
			}
		}		
		
		try 
		{
			if(args.length != 1) 
			{
				System.err.println("Usage: java BattleshipClient [ip]");
				System.exit(1);
			}
			
			Socket socket = new Socket(args[0], 8000);
			serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
			
			if(serverReader.readLine().compareTo("PlaceShips") == 0) 
			{
				System.out.println("Place your ships please!");
				System.out.println("Format: top-left-cordinate [xy] orientation (H or V)"); 
				System.out.println("=====================================================");
				
				//Placement of Battleship
			//	do 
			//	{
					System.out.print("Battleship: ");
					buffer = stdin.nextLine();
					
					if(buffer.matches("[a-f][1-6] [HV]")) 
					{
						buffer.concat(" B");
						writer.println(buffer);
						//write size,orin,top_left = number
						//4,H/V,					
						writer.flush();
					}
					else 
					{
						System.err.println("Incorrect format!");
				//		continue;
					}
				//} while(serverReader.readLine().compareTo("Invalid Dimensions") == 0);

				//Placement of Cruiser
			//	do 
			//	{
					System.out.print("Cruiser: ");
					buffer = stdin.nextLine();
					
					if(buffer.matches("[a-f][1-6] [HV]")) 
					{
						buffer.concat(" C");
						elements = buffer.split("");
						top_left = getTopLeft(elements);
						System.out.println(top_left);					
						
						writer.println(buffer);
						writer.flush();
					}
					else 
					{
						System.err.println("Incorrect format!");
			//			continue;
					}
			//	} while(serverReader.readLine().compareTo("Invalid Dimensions") == 0);
				
				//Placement of Destroyer
				//do 
				//{
					System.out.print("Destroyer: ");
					buffer = stdin.nextLine();
					
					if(buffer.matches("[a-f][1-6] [HV]")) 
					{
						buffer.concat(" D");
						writer.println(buffer);						
						writer.flush();
					}
					else 
					{
						System.err.println("Incorrect format!");
					//	continue;
					}
				//} while(buffer != null);//serverReader.readLine().compareTo("Invalid Dimensions") == 0);
				
				//Placement of Submarine
				//do 
				//{
					System.out.print("Submarine: ");
					buffer = stdin.nextLine();
					
					if(buffer.matches("[a-f][1-6] [HV]")) 
					{
						buffer.concat(" S");
						writer.println(buffer);						
						writer.flush();
					}
					else 
					{
						System.err.println("Incorrect format!");
				//		continue;
					}
				//} while(serverReader.readLine().compareTo("Invalid Dimensions") == 0);
				
				//do 
				//{
					writer.println(stdin.nextLine());
				//} while(serverReader.readLine().compareTo("Invalid Dimensions") == 0);


				writer.println(READY_SIGNAL);
				writer.flush();
			}
		}		
		catch(Exception e) 
		{
		System.err.println(e.getMessage());
		}
		
		
		
		//TODO create grid with ships placed, 
		//create function do take in a location and call hit() function (followed by miss())
		//create win/lose function (ie if(win))
		
		
		
		
		
		
	}


		
		public static int getTopLeft(String[] string)
		{
			int top_left;
		
			if(string[1].contains("a"))
			{
				string[1]=Integer.toString(0);
			}
			else if(string[1].contains("b"))
			{
				string[1]=Integer.toString(1);
			}
			else if(string[1].contains("c"))
			{
				string[1]=Integer.toString(2);
			}
			else if(string[1].contains("d"))
			{
				string[1]=Integer.toString(3);
			}
			else if(string[1].contains("e"))
			{
				string[1]=Integer.toString(4);
			}
			else if(string[1].contains("f"))
			{
				string[1]=Integer.toString(5);
			}
		
			top_left = player[Integer.parseInt(string[1])][Integer.parseInt(string[2])];
		
			return top_left;
		}
}
