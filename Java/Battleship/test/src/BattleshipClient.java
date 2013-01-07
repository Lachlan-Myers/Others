import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.regex.*;

public class BattleshipClient 
{
	public static void main(String args[]) 
	{
		String text;
		BufferedReader serverReader;
		PrintWriter writer;
		Scanner stdin = new Scanner(System.in);
		String buffer;
		final String READY_SIGNAL = "Ready";
		
		int player1[][] = new int[6][6];
		int i,j,r=1;
		
		for(i=0;i<6;i++)
		{
			for(j=0;j<6;j++)
			{
				player1[i][j] = r++;
			}
		}
		
		for(i=0;i<6;i++)
		{
			System.out.print("|");
			for(j=0;j<6;j++)
			{	
				if(j==(6-1))
				{
					System.out.println(" "+player1[i][j]+" |");					
				}
				else
				{
					System.out.print(" "+player1[i][j]+" |");
				}
				
			}			
			
			System.out.println(" ---------------------------");
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
				
				do 
				{
					System.out.print("Battleship: ");
					buffer = stdin.nextLine();
					
					if(buffer.matches("[a-f][1-6] [HV]")) 
					{
						buffer.concat(" B");
						writer.println(buffer);
						//4,
						//write size,orin,top_left = number
						/*
						 * 
						 * place = buffer.split(""); 
						 * hc = Integer.parseInt(place[1])-1;
						 * 
						 * if(place[0]=='a')
						 * {
						 * 		hr = 0;
						 * 
						 * 
						 * 
						 * for(i=0;i<6;i++)
						 * {
						 * 		for(j=0;j<6;j++)
						 * 		{
						 * 			
						 * 
						 * 
						 * 
						 * 
						 */
						
						writer.flush();
					}
					else 
					{
						System.err.println("Incorrect format!");
						continue;
					}
				} while(serverReader.readLine().compareTo("Invalid Dimensions") == 0);

				//TODO Other Ships				
				//
				do 
				{
					System.out.print("Cruiser: ");
					buffer = stdin.nextLine();
					
					if(buffer.matches("[a-f][1-6] [HV]")) 
					{
						buffer.concat(" C");
						writer.println(buffer);
						//3,
						//write size,orin,top_left = number
						/*
						 * for(i=0;i<6;i++)
						 * {
						 * 		for(j=0;j<6;j++)
						 * 		{
						 * 			
						 * 
						 * 
						 * 
						 * 
						 */
						
						writer.flush();
					}
					else 
					{
						System.err.println("Incorrect format!");
						continue;
					}
				} while(serverReader.readLine().compareTo("Invalid Dimensions") == 0);
				//
				do 
				{
					System.out.print("Destroyer: ");
					buffer = stdin.nextLine();
					
					if(buffer.matches("[a-f][1-6] [HV]")) 
					{
						buffer.concat(" D");
						writer.println(buffer);
						//2,
						//write size,orin,top_left = number
						/*
						 * for(i=0;i<6;i++)
						 * {
						 * 		for(j=0;j<6;j++)
						 * 		{
						 * 			
						 * 
						 * 
						 * 
						 * 
						 */
						
						writer.flush();
					}
					else 
					{
						System.err.println("Incorrect format!");
						continue;
					}
				} while(serverReader.readLine().compareTo("Invalid Dimensions") == 0);
				//
				do 
				{
					System.out.print("Submarine: ");
					buffer = stdin.nextLine();
					
					if(buffer.matches("[a-f][1-6] [HV]")) 
					{
						buffer.concat(" S");
						writer.println(buffer);
						//1,
						//write size,orin,top_left = number
						/*
						 * for(i=0;i<6;i++)
						 * {
						 * 		for(j=0;j<6;j++)
						 * 		{
						 * 			
						 * 
						 * 
						 * 
						 * 
						 */
						
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
					writer.println(stdin.nextLine());
				} while(serverReader.readLine().compareTo("Invalid Dimensions") == 0);


				writer.println(READY_SIGNAL);
				writer.flush();
			}
			

		}
		catch(Exception e) 
		{
			System.err.println(e.getMessage());
		}

	}
}
