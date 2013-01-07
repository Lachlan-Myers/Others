// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Result.java

package ams.model;


// Referenced classes of package ams.model:
//            Course

public class Result
{

    public Result(Course course, boolean grade)
    {
        this.course = course;
        this.grade = grade;
    }

    public Course getCourse()
    {
        return course;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }

    public boolean getGrade()
    {
        return grade;
    }

    public void setGrade(boolean grade)
    {
        this.grade = grade;
    }

    public String toString()
    {
        String resultString = (new StringBuilder(String.valueOf(course.getCode()))).append(":").toString();
        if(grade)
            resultString = (new StringBuilder(String.valueOf(resultString))).append("pass").toString();
        else
            resultString = (new StringBuilder(String.valueOf(resultString))).append("fail").toString();
        return resultString;
    }

    public boolean equals(Object in)
    {
        Result obj = (Result)in;
        return course == obj.getCourse();
    }

    private Course course;
    private boolean grade;
}
