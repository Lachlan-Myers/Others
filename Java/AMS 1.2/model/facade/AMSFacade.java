package ams.model.facade;


import ams.model.*;
import ams.model.exception.EnrollmentException;
import ams.model.exception.ProgramException;

public class AMSFacade implements AMSModel 
{
	
	private University university;
	
	public AMSFacade()
	{
		university = new University();
		//university.addProgram(new Program("BP062", "Bachelor of Computer Science"));
	}
	
	/*public AMSFacade()
	{
		
	}*/
	
	
	public Program getProgram()
	{
		return university.getProgram();
	}
	
	public Student getStudent()
	{
		return university.getStudent();		
	}
	
	public void addCourse(Course Course) throws ProgramException
	{
		university.addCourse(Course);
	}
	

	public Course[] getAllCourses()
	{
		return university.getAllCourses();
	}	

	public void removeCourse(String courseId) throws ProgramException
	{
		university.removeCourse(courseId);
	}
	

	
	public Result[] getResults()
	{
		//return getCourse(null).getResults();
		return null;
	}
	
	public void enrollIntoCourse(String courseID) throws EnrollmentException 
	{
		

	}

	public void withdrawFromCourse(String courseID) throws EnrollmentException 
	{

	}
		
	public Course[] getCurrentEnrollment()
	{
		return null;
	}
	
	public int calculateCurrentLoad() 
	{
		return 0;
	}

	public int calculateCareerPoints() 
	{
		return 0;
	}

	public boolean addResult(Result result) 
	{
		return university.addResult(result);
	}
	
	public void addStudent(Student newStudent) 
	{
		university.setStudent(newStudent);
	}

	public Course getCourse(String courseCode) 
	{
		return university.getCourse(courseCode);
	}

	public void addProgram(Program program) 
	{
		university.addProgram(program);
	}	
	

}
