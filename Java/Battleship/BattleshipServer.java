package assign1.server;
 
import java.io.*;
import java.net.*;
import Utility.*;

public class BattleshipServer {
	public static void main(String args[]) {
		Socket player1 = new Socket(), player2 = new Socket();
		PrintWriter outputToP1, outputToP2;
		BufferedReader inputFromP1, inputFromP2;
		
		NavalForce forceP1 = new NavalForce();
		NavalForce forceP2 = new NavalForce();
		
		String p1Message = "", p2Message = "";
		
		Thread threadP1, threadP2;
		
		final int PORT = 8000;
		final String PLACE_SHIP_SIGNAL = "PlaceShips";
		final String YOUR_TURN = "YourTurn";
		final String READY = "Ready";
		final String LOSS = "Lose";
		
		try {
			ServerSocket server = new ServerSocket(PORT);
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
			outputToP2 = new PrintWriter(new BufferedOutputStream(player2.getOutputStream()));
			
			inputFromP1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
			inputFromP2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
			
			// Setting up ship placement on the client
			outputToP1.println(PLACE_SHIP_SIGNAL);
			outputToP1.flush();
			threadP1 = new Thread(new ShipSetter(forceP1, player1));
			threadP1.start();
			
			outputToP2.println(PLACE_SHIP_SIGNAL);
			outputToP2.flush();
			threadP2 = new Thread(new ShipSetter(forceP2, player2));
			threadP2.start();
			//Waits for both players to finish placing their ships
			while(p1Message.compareTo(READY) != 0 || p2Message.compareTo(READY) != 0) ;
			
			//Starts gameplay
			while(p1Message.compareTo(LOSS) != 0 || p2Message.compareTo(LOSS) != 0) {
			
				outputToP1.println(YOUR_TURN);
				outputToP1.flush();
				p1Message = "";
				
				while((p1Message = inputFromP1.readLine()) != null);
				if(forceP1.isHit(Integer.parseInt(p1Message))) {
					forceP1.destroy(Integer.parseInt(p1Message));
					outputToP1.println("Hit!");
					outputToP1.flush();
				}
				else {
					outputToP1.println("Miss!");
					outputToP1.flush();
				}

				outputToP2.println(YOUR_TURN);
				outputToP2.flush();
				
				while((p2Message = inputFromP2.readLine()) != null);
				if(forceP2.isHit(Integer.parseInt(p2Message))) {
					forceP2.destroy(Integer.parseInt(p2Message));
					outputToP2.println("Hit!");
					outputToP2.flush();
				}
			}
			
			if(p1Message.compareTo(LOSS) == 0) {
				outputToP2.println("You Win");
				outputToP2.flush();
			}
			else {
				outputToP1.println("You Win");
				outputToP1.flush();
			}
			
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
