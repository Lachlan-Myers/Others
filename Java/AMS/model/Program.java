// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Program.java

package ams.model;

import ams.model.exception.ProgramException;
import java.util.*;

// Referenced classes of package ams.model:
//            Course

public class Program
{

    public Program(String programCode, String title)
    {
        courses = new HashMap();
        this.programCode = programCode;
        this.title = title;
    }

    public void addCourse(Course course)
        throws ProgramException
    {
        if(course.getPreReqs() != null)
        {
            for(int i = 0; i < course.getPreReqs().length; i++)
                if(!courses.containsKey(course.getPreReqs()[i]))
                    throw new ProgramException("** ERROR ADDING A COURSE: PRE-REQS DO NOT EXIST **");

        }
        courses.put(course.getCode(), course);
    }

    public void removeCourse(String courseCode)
        throws ProgramException
    {
        if(!courses.containsKey(courseCode))
            throw new ProgramException("** ERROR REMOVING A COURSE: COURSE NOT FOUND **");
        for(Iterator iterator = courses.values().iterator(); iterator.hasNext();)
        {
            Course course = (Course)iterator.next();
            String preReqs[] = course.getPreReqs();
            if(preReqs != null)
            {
                for(int i = 0; i < preReqs.length; i++)
                    if(preReqs[i].equalsIgnoreCase(courseCode))
                        throw new ProgramException("** ERROR REMOVING A COURSE: CANNOT REMOVE PREREQUSITE COURSE**");

            }
        }

        courses.remove(courseCode);
    }

    public Course getCourse(String courseCode)
    {
        return (Course)courses.get(courseCode);
    }

    public Course[] getAllCourses()
    {
        if(courses.isEmpty())
            return null;
        else
            return (Course[])courses.values().toArray(new Course[courses.size()]);
    }

    public String toString()
    {
        String programString = (new StringBuilder(String.valueOf(programCode))).append(":").append(title).toString();
        if(courses.size() != 0)
        {
            for(int i = 0; i < courses.size(); i++)
                programString = (new StringBuilder(String.valueOf(programString))).append(":").append(courses.get(Integer.valueOf(i))).toString();

        }
        return programString;
    }

    private String programCode;
    private String title;
    private Map courses;
}
