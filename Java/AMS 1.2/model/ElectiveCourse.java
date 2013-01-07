package ams.model;


public class ElectiveCourse extends AbstractCourse 
{
	private int credit_points;
	private String temp;
	public ElectiveCourse(String code, String title, int points, String[] preReqs) 
	{//Constructor
		super(code,title,preReqs);
		credit_points = points;
	}
	
	//public ElectiveCourse()
	//{ //Unknown implementation
	//
	//}
	
	public String toString()
	{//Builds a string: course_code:course_name:credit_points:prereqs_courseCodes:course_type
		
		temp = (new StringBuilder(String.valueOf(super.toString()))).append(":").append(credit_points).toString();
		if(preReqs == null)
		{
			return (new StringBuilder(String.valueOf(super.toString()))).append(":").append(credit_points)
					.append(":ELECTIVE").toString();
		}
		else
		{
			for(int i=0;i<preReqs.length;i++)
			{
				temp= (new StringBuilder(String.valueOf(temp.toString()))).append(":").append(preReqs[i]).toString();
			}
			return (new StringBuilder(String.valueOf(temp.toString()))).append(":ELECTIVE").toString();
		}
	}

}
