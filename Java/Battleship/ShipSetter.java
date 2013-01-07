package assign1.server;

import Utility.*;
import java.net.Socket;
import java.io.*;

public class ShipSetter implements Runnable {
	private NavalForce force;
	private Socket socket;
	ObjectInputStream input;
	
	public ShipSetter(NavalForce f, Socket s) throws IOException {
		force = f;
		socket = s;
		
		input = new ObjectInputStream(s.getInputStream());
	}
	
	public void run() {
		try {
			force = (NavalForce)input.readObject();
		}
		catch(Exception e) {
			
		}
		
	}
	
}

