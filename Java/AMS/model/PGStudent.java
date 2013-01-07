// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGStudent.java

package ams.model;

import ams.model.exception.EnrollmentException;
import ams.model.visitor.CourseVisitor;
import java.util.List;

// Referenced classes of package ams.model:
//            AbstractStudent, Course

public class PGStudent extends AbstractStudent
{

    public PGStudent(int studentId, String fullName)
    {
        super(studentId, fullName, 48);
    }

    public void enrollIntoCourse(Course course)
        throws EnrollmentException
    {
        if(calculateCurrentLoad() + course.getCreditPoints() > maxLoad)
            throw new EnrollmentException("STUDENT IS OVERLOADED  ...");
        if(!super.checkPrereqs(course))
        {
            throw new EnrollmentException("PREREQS EXCEPTION ...");
        } else
        {
            validateCourseRequirements(course);
            super.enrollIntoCourse(course);
            return;
        }
    }

    private void validateCourseRequirements(Course course)
        throws EnrollmentException
    {
        CourseVisitor visitor = new CourseVisitor();
        course.accept(visitor);
        boolean flag = false;
        int newLoad = calculateCurrentLoad() + course.getCreditPoints();
        if(newLoad + 12 >= maxLoad)
        {
            if(visitor.getCoreCount() == 1)
            {
                flag = true;
            } else
            {
                for(int i = 0; i < currentCourses.size(); i++)
                {
                    ((Course)currentCourses.get(i)).accept(visitor);
                    if(visitor.getCoreCount() >= 1)
                        flag = true;
                }

            }
            if(!flag)
                throw new EnrollmentException("ERROR ENROLLING INTO COURSE: MUST BE ENROLLED IN AT LEAST ONE CORE COURSE");
        }
    }

    public static final int MAX_LOAD = 48;
}
