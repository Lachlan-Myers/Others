package ams.model;

import java.util.Collection;

public class CoreCourse extends AbstractCourse 
{
	public static int DEFAULT_CREDIT_POINT = 12;
	private String temp;

	private String code,title;
	public CoreCourse(String code, String title, String[] preReqs) 
	{//Constuctor
		super(code,title,preReqs);
		this.code=code;
		this.title=title;
		
		/*if(preReqs.isEmpty())
		{
            null;
		}
        else
        {
            return (Site[])sites.values().toArray(new Site[0]);*/
	}
	
	public int getCredit()
	{
		return DEFAULT_CREDIT_POINT;
	}

	public String toString()
	{//Prints a string: course_code:course_name:credit_points:prereqs_courseCodes:course_type
		/*String string = code+":"+title+":"+DEFAULT_CREDIT_POINT+":"+preReqs+":"+"CORE";
		return string;*/
			temp = (new StringBuilder(String.valueOf(super.toString()))).append(":").append(DEFAULT_CREDIT_POINT).toString();
			if(preReqs == null)
			{
				return (new StringBuilder(String.valueOf(super.toString()))).append(":").append(DEFAULT_CREDIT_POINT)
						.append(":CORE").toString();
			}
			else
			{
				for(int i=0;i<preReqs.length;i++)
				{
					temp= (new StringBuilder(String.valueOf(temp.toString()))).append(":").append(preReqs[i]).toString();
				}
				return (new StringBuilder(String.valueOf(temp.toString()))).append(":CORE").toString();
			}		
		
		
	}

}
