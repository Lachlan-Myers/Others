package ams.model;

import java.util.*;

import ams.model.exception.EnrollmentException;
import ams.model.exception.ProgramException;

public class University 
{
	//private HashMap courses;
	private HashMap<String,Program> programs;
	ArrayList <Program>programList = new ArrayList<Program>();
	ArrayList <Student> studentList = new ArrayList <Student>();
	private int currentProg;
	private int currentStud;
	//private String temp;
	
	public University()
	{//Constructor
		
		for(int i=0;i< studentList.size();i++)
		{			
				currentStud = i;
				
		}
		//for(int i=0;i< programList.size();i++)
		//{			
				currentProg = 0;
				
		//}
		programs = new HashMap<String, Program>();
	}
	
	public void setStudent(Student newStudent) 
	{//Adds a student
		studentList.add(newStudent);
	}
		
	public Program setProgram()
	{
		return null;
	}
	
	public Program getProgram()
	{//Gets a Program from the List
		 
		//return (Program)programs.get(String.valueOf(programs));
		//return (Program)programs.get(programs.getCode());
		//for(Program pro: programs)
		//{
		//	pro.get();
		//}
		//for(int i=0; i<20;i++)
		//{
		//	return programs.get(values());
		//}		
		 /*Set keySet = programs.keySet();
		 Iterator iterator = keySet.iterator();
		 while(iterator.hasNext())
		 {
			 String key = (String)iterator.next();
			 return key;
		 }
		 return (Program)programs.get(programs.keySet());*/
		/*for(int i=0;i<=programList.size();i++)
		{
				currentProg = i;
				programs.get(String.valueOf(i));
		}
		//return programs.get(i); 
		//Program temp = (Program)programs.get(String.valueOf(i));
		return programList.get(0);*/
		/*for(int i=0;i< programs.size();i++)
		{			
				currentProg = i;
				
		}*/
		
		//return programList.get(currentProg);
		
		//return ((Program) programs).getKey("BP064",0);
		//return (Program)programs.get(String.valueOf(programs.hashCode()));
		return (Program)programs.get("BP062");
		//return (Program[])programs.values().toArray(new Program[0]);
		//return programs.get(currentProg);
		
		
	}
	
	
	public Student getStudent()
	{//Gets a student from the list
		for(int i=0;i< studentList.size();i++)
		{			
				currentStud = i;
				
		}
		return studentList.get(currentStud);
	}
	
	public void addCourse(Course Course) throws ProgramException
	{//Supposed to add a course to the List
		 /*if(!enrollIntoCourse(Course.getCode()))
	            throw new ProgramException("**ERROR**");
	        Program temp = getProgram(Course.getCode());
	        if(temp == null)
	        {
	            temp = new Program(Course.getCode().getZ());
	            addProgram(temp);
	        }
	        temp.addCourse(Course);*/
		/*for(int i=0;i< programList.size();i++)
		{			
				currentProg = i;
				programList.addCourse(course);
				
		}*/
		if(programList.isEmpty())
        {
            //return 
			//System.out.println("Empty");
        } 
		else
        {
			/*Course temp = getProgram().addCourse(Course);
            return temp;*/
			getProgram().addCourse(Course);
        }
			
			//if(!checkLowerStructuralSupport(Course.getCode()))throw new ProgramException("**ERROR**");
	        
			/*Program temp = getProgram();
	        
	        if(temp == null)
	        {
	            temp = new Program(Course.getCode(),null);
	            addProgram(temp);
	        }
	        temp.addCourse(Course);*/
		
		
			
	}
	
	/*private boolean checkLowerStructuralSupport(Course course)
    {
        if(course.getCode() == 1)
            return true;
        Program temp = (Program)programs.get(String.valueOf(ref.getZ() - 1));
        if(temp == null)
            return false;
        return temp.courseExists(new Location(course.getX(), course.getY(), course.getZ() - 1));
    }*/
	
	public Course getCourse(String Course)
	{//Supposed to get a course from the list
		//((Program) programList).getCourse(Course);
		//if(programs.isEmpty());
		
		if(programs.isEmpty())
        {
            return null;
        } 
		else
        {
			Course temp = getProgram().getCourse(Course);
            return temp;
			//return programs.get("BP062").getCourse(Course);
        }
		
	}

	public Course[] getAllCourses()
	{
		/*if(programs.isEmpty())
		 {
            return null;
            }
        List resultList = new LinkedList();
        for(Iterator iterator = programs.values().iterator(); iterator.hasNext();)
        {
            Program program = (Program)iterator.next();
            Course courses[] = program.getCourse();
            for(int i = 0; i < courses.length; i++)
            {
                resultList.add(courses[i]);
            }
        }
        return (Course[])resultList.toArray(new Course[resultList.size()]);*/
        
        
            if(programs.isEmpty())
            {
            	return null;
            }
        	Program temp[] = (Program[])programs.values().toArray(new Program[0]);
        	Vector<Course> temp1 = new Vector<Course>();
        	if (temp == null)
        	{
        		return null;
        	}
        	
        	for(int i = 0; i < temp.length; i++)
        	{
        		if(temp[i].getAllCourses()==null)
       			{
       				return null;
       			}
        		else
        		{
        			for(int j = 0; j < temp[i].getAllCourses().length; j++)
        			{
        					temp1.addElement(temp[i].getAllCourses()[j]);
        			}
        		}
            }
        	return (Course[])temp1.toArray(new Course[0]);   
		//return null;
	}	
	

	public void removeCourse(String courseId) throws ProgramException
	{
		/*if(getProgram(courseId) == null || !getProgram(courseId).siteExists(courseId) || !checkUpperStructuralSupport(courseId)) throw new StructuralException("**ERROR REMOVING SITE, PROVIDES STRUCTURAL SUPPORT ABOVE**");
        Program temp = getProgram(courseId);
        if(temp != null && temp.getCourse(courseId) != null)
        {
            temp.removeCourse(courseId);
            if(temp.getCourse() == null)
                removeProgram(temp.getLevelNumber());
        } else
        {
            throw new ProgramException("**ERROR: COURSE DOES NOT EXIST**");
        }*/
        
        /*Course course=null;
        if(getProgram() == null || !getProgram().courseExists(courseId))
        {
            throw new ProgramException("**STRUCTURAL HOUSE SUPPORT ERROR**");
        }
        Program temp = getProgram();
        if(temp != null || temp.getCourse(courseId) != null)
        {
            course = temp.removeCourse(courseId);
            if(temp.getAllCourses() == null)
            {
                removeCourse(temp.getCode());
            } 
            else
            {
            throw new ProgramException("**ROOM DOES NOT EXIST ERROR**");
            }
        }
        return;*/
		getProgram().removeCourse(courseId);

	}
	
	
	public Result[] getResults()
	{
		return null;
	}
	
	public void enrollIntoCourse(String courseID) throws EnrollmentException 
	{
		/*if(courseID.getZ() == 1)
            return true;
        Level temp = (Level)levels.get(Integer.valueOf(ref.getZ() - 1));
        if(temp == null)
            return false;
        return temp.siteExists(new Location(ref.getX(), ref.getY(), ref.getZ() - 1));
*/
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
		/*if(!getProgram().courseExists())
        {
            return false;
        } else
        {
            getCourse(location).addResults(item);
            return true;
        }*/
		return false;
	}

	/*public Course getCourse(String courseCode) 
	{
		if(programs.isEmpty())
        {
            return null;
        } 
	 	else
        {
            Program temp = getCourse(courseCode).getProgram(courseCode);
            return temp;
        }
		for(int i=0;i< studentList.size();i++)
		{			
				currentStud = i;
				
		}
		
		return getProgram().get(courseCode);
		
	}*/

	public void addProgram(Program program) 
	{//Adds a program to the list
		//programs.put(Integer.valueOf(storey.getStoreyNumber()), storey);
		//levels.put(Integer.valueOf(level.getLevelNumber()), level);
		//programs.put(program);
		//programs.put(new Program(program));
		//if(!programs.containsKey("BP064"))
		//{
		
			//programList.add(program);
			/*for(int i=0;i<programList.size();i++)
			{
				int temp = i;
			}*/
			//programs.put(program.code, program);
			programs.put(program.getCode(), program);
			programList.add(program);
			//programs.put(String.valueOf(program), program);
		//}
		//else
		//{
		//	System.out.println("Program already exists!");
		//}	
		
	}	

}
