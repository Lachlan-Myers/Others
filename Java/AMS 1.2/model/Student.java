package ams.model;


public interface Student
{
	
	public String getFullName();
	
	public int getStudentId();
	
	public int getResults();
	
	public String addResult();
	
	public void getCurrentEnrollment();
	
	public int calculateCurrentLoad();
	
	public int calculateCareerPoints();
	
	public String toString();

}
