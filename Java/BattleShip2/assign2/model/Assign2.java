package assign2.model;




import java.rmi.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Assign2 extends Remote {
	public String getWelcomeMessage() throws RemoteException, SQLException, ClassNotFoundException;
	public ArrayList<String> getTopics() throws RemoteException, SQLException,
										ClassNotFoundException;
	public ArrayList<String> getMessages(String topic) throws RemoteException, SQLException,
										ClassNotFoundException;
	public String getMessage(String topic) throws RemoteException, SQLException,
										ClassNotFoundException;
	public String getDescription(String topic) throws RemoteException, SQLException,
										ClassNotFoundException;
	public boolean login(String user, String pass) throws RemoteException, SQLException,
										ClassNotFoundException;
	public boolean register(String user, String pass) throws RemoteException, SQLException,
										ClassNotFoundException;
	public boolean createTopic(String name, String desc) throws RemoteException, SQLException,
										ClassNotFoundException;
}
