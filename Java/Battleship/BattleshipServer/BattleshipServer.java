import java.io.*;
import java.net.*;

public class BattleshipServer {
	public static void main(String args[]) {
		Socket player1 = new Socket(), player2 = new Socket();
		PrintWriter outputToP1, outputToP2;
		BufferedReader inputFromP1, inputFromP2;
		
		String p1Message = "", p2Message = "";
		
		final int PORT = 8000;
		final String PLACE_SHIP_SIGNAL = "PlaceShips";
		final String YOUR_TURN = "YourTurn";
		final String READY = "Ready";
		
		try {
			ServerSocket server = new ServerSocket(8000);
			System.out.print("Waiting for Player 1 to connect...");
			
			synchronized(player1) {
				player1 = server.accept();
			}
			System.out.println("Connected!");
			
			System.out.print("Waiting for Player 2 to connect...");
			synchronized(player2) {
				player2 = server.accept();
			}
			System.out.println("Connected!");
			
			outputToP1 = new PrintWriter(new BufferedOutputStream(player1.getOutputStream()));
			outputToP2 = new PrintWriter(new DataOutputStream(player2.getOutputStream()));
			
			inputFromP1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
			inputFromP2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
			
			// Setting up ship placement on the client
			outputToP1.println(PLACE_SHIP_SIGNAL);
			outputToP1.flush();
			
			outputToP2.println(PLACE_SHIP_SIGNAL);
			outputToP2.flush();
			
			//Waits for both players to finish placing their ships
			while((p1Message = inputFromP1.readLine()).compareTo(READY) != 0 ||
				  (p2Message = inputFromP2.readLine()).compareTo(READY) != 0) ;
			System.out.println("Player 1: " + p1Message);
			System.out.println("Player 2: " + p2Message);
			
			//Starts gameplay
			outputToP1.println(YOUR_TURN);
			outputToP1.flush();
			p1Message = "";
			
			while((p1Message = inputFromP1.readLine()) != null);
			//TODO insert code for P2's turn
			//if(inputFromP1.ready());
			
			
			
			
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
