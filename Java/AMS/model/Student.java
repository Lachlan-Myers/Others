// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Student.java

package ams.model;

import ams.model.exception.EnrollmentException;

// Referenced classes of package ams.model:
//            Result, Course

public interface Student
{

    public abstract String getFullName();

    public abstract int getStudentId();

    public abstract Result[] getResults();

    public abstract boolean addResult(Result result);

    public abstract Course[] getCurrentEnrollment();

    public abstract int calculateCurrentLoad();

    public abstract int calculateCareerPoints();

    public abstract void enrollIntoCourse(Course course)
        throws EnrollmentException;

    public abstract void withdrawFromCourse(Course course)
        throws EnrollmentException;
}
