package ams.model;

public abstract class AbstractStudent implements Student
{
	protected int studentId;
	protected String fullName;
	protected int maxLoad;
	protected String results;
	protected String currentEnrollment;
	
	public AbstractStudent(int studentId, String fullName,int maxLoad)
	{
		this.studentId = studentId;
		this.fullName = fullName;
		this.maxLoad = maxLoad;
	}
	
	public String getFullName()
	{
		return fullName;
	}
	
	public int getStudentId()
	{
		return studentId;
		
	}
	
	public int getResults()
	{
		return 0;
	}
	
	public String addResult()
	{
		return null;
		
	}
	
	public void getCurrentEnrollment()
	{
		
	}
	
	public int calculateCurrentLoad()
	{
		return 0;
	}
	
	public int calculateCareerPoints()
	{
		return 0;
	}
	
	public void withdrawFromCourse()
	{
		
	}
	
	public String toString()
	{
		String student = studentId+":"+fullName+":"+maxLoad;
		return student;
		
	}
	
	public void enrollIntoCourse()
	{
		
	}
	
	protected void checkPrereqs()
	{
		
	}

}
