// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UGStudent.java

package ams.model;

import ams.model.exception.EnrollmentException;
import ams.model.visitor.CourseVisitor;
import java.util.List;

// Referenced classes of package ams.model:
//            AbstractStudent, Course, Result

public class UGStudent extends AbstractStudent
{

    public UGStudent(int studentId, String fullName)
    {
        super(studentId, fullName, 60);
    }

    public void enrollIntoCourse(Course course)
        throws EnrollmentException
    {
        CourseVisitor visitor = new CourseVisitor();
        course.accept(visitor);
        if(visitor.getElectivesCount() == 1)
        {
            if(!checkPrereqs(course))
                throw new EnrollmentException("PREREQS EXCEPTION ...");
            super.enrollIntoCourse(course);
        } else
        {
            if(!super.checkPrereqs(course))
                throw new EnrollmentException("PREREQS EXCEPTION ...");
            super.enrollIntoCourse(course);
        }
    }

    protected boolean checkPrereqs(Course course)
    {
        boolean flag = false;
        if(course.getPreReqs() != null)
        {
            for(int i = 0; i < course.getPreReqs().length; i++)
            {
                for(int j = 0; j < results.size(); j++)
                    if(((Result)results.get(j)).getCourse().getCode() == course.getPreReqs()[i] && ((Result)results.get(j)).getGrade())
                        return true;

            }

        } else
        {
            flag = true;
        }
        return flag;
    }

    public static final int MAX_LOAD = 60;
}
