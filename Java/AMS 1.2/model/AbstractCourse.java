package ams.model;

public abstract class AbstractCourse implements Course
{
	protected String code;
	protected String title;
	protected int creditPoints;
	protected String[] preReqs;
	//protected ElectiveCourse elective;
	//protected CoreCourse core;
	//private int credit =12;
	
	
	AbstractCourse(String code, String title,String[] preReqs)
	{//constructor
		this.code=code;
		this.title=title;
		this.preReqs=preReqs;
		//this.creditPoints=creditPoints;
	}
	
	public String getCode()
	{//returns the course code
		return code;
	}
	
	public String getTitle()
	{//returns the course title
		return title;
	}

	public int getCreditPoints()
	{
		
		return creditPoints;
	}
	
	/*public String type()
	{
		String temp ="CORE";
		return temp;
	}*/
	
	public String[] getPreReqs()
	{
		 /*if(!courseExists(course))
		 {
	            return null;
		 }
	     else
	     {
	           return (Course)courses.get(course);
	     }*/
		return preReqs;
	}
	
	public String toString()
	{//Builds a string: course_code:course_title:course_creditPoints:preReqs_courseCode
		//String course = code+":"+title+":"+creditPoints+":"+preReqs+"";
        /*return (new StringBuilder(String.valueOf(code))).append(":").append(title)
        		.append(":").append(Integer.valueOf(creditPoints)).append(":")
        		.append(String.valueOf(preReqs)).append(":CORE")
        		.toString();*/
		return (new StringBuilder(String.valueOf(code))).append(":").append(title).toString(); 
	}
	
	public boolean equals()
	{
		/*if(!(in instanceof Location))
            return false;
        AbstractCourse temp = (AbstractCourse)in;
        return code == temp.getCode() && title == temp.getTitle() && creditPoints == temp.getCreditPoints();*/
		//return toString().compareTo(in.toString()); //int
		return true;
	}
}
