// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractStudent.java

package ams.model;

import ams.model.exception.EnrollmentException;
import java.util.*;

// Referenced classes of package ams.model:
//            Student, Result, Course

public abstract class AbstractStudent
    implements Student
{

    public AbstractStudent(int studentId, String fullName, int maxLoad)
    {
        results = new ArrayList();
        currentCourses = new ArrayList();
        this.fullName = fullName;
        this.studentId = studentId;
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

    public Result[] getResults()
    {
        if(results.isEmpty())
            return null;
        else
            return (Result[])results.toArray(new Result[results.size()]);
    }

    public boolean addResult(Result result)
    {
        boolean flag = false;
        if(currentCourses.contains(result.getCourse()))
        {
            if(results.contains(result))
                results.remove(result);
            results.add(result);
            currentCourses.remove(result.getCourse());
            flag = true;
        }
        return flag;
    }

    public Course[] getCurrentEnrollment()
    {
        if(currentCourses.isEmpty())
            return null;
        else
            return (Course[])currentCourses.toArray(new Course[currentCourses.size()]);
    }

    public int calculateCurrentLoad()
    {
        int load = 0;
        for(Iterator iterator = currentCourses.iterator(); iterator.hasNext();)
        {
            Course course = (Course)iterator.next();
            load += course.getCreditPoints();
        }

        return load;
    }

    public int calculateCareerPoints()
    {
        int points = 0;
        for(int i = 0; i < results.size(); i++)
        {
            Result temp = (Result)results.get(i);
            if(temp.getGrade())
                points += temp.getCourse().getCreditPoints();
        }

        return points;
    }

    public void withdrawFromCourse(Course course)
        throws EnrollmentException
    {
        if(!currentCourses.contains(course))
        {
            throw new EnrollmentException("COURSE NOT FOUND");
        } else
        {
            currentCourses.remove(course);
            return;
        }
    }

    public String toString()
    {
        String returnString = (new StringBuilder(String.valueOf(studentId))).append(":").append(fullName).append(":").append(maxLoad).toString();
        return returnString;
    }

    public void enrollIntoCourse(Course course)
        throws EnrollmentException
    {
        if(currentCourses.contains(course))
            currentCourses.remove(course);
        if(calculateCurrentLoad() + course.getCreditPoints() > maxLoad)
        {
            throw new EnrollmentException("STUDENT IS OVERLOADED  ...");
        } else
        {
            currentCourses.add(course);
            return;
        }
    }

    protected boolean checkPrereqs(Course course)
    {
        boolean flag = false;
        int counter = 0;
        if(course.getPreReqs() != null)
        {
            for(int i = 0; i < course.getPreReqs().length; i++)
            {
                for(int j = 0; j < results.size(); j++)
                    if(((Result)results.get(j)).getCourse().getCode() == course.getPreReqs()[i] && ((Result)results.get(j)).getGrade())
                        counter++;

            }

            if(counter == course.getPreReqs().length)
                flag = true;
        } else
        {
            flag = true;
        }
        return flag;
    }

    protected int studentId;
    protected String fullName;
    protected int maxLoad;
    protected List results;
    protected List currentCourses;
}
