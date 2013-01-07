package ams.model;


public class PGStudent extends AbstractStudent
{

	public static int MAX_LOAD = 48;
	
	public PGStudent(int i, String string) 
	{
		super(i,string,MAX_LOAD);
	}
	
	public void enrollIntoCourse()
	{
		
	}
	
	protected void checkPrereqs()
	{
		
	}

}
