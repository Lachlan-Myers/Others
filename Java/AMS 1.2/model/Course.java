package ams.model;



public interface Course 
{

	public abstract String getCode();
	
	public abstract String getTitle();
	
	public abstract int getCreditPoints();
	
	public abstract String[] getPreReqs();
}
