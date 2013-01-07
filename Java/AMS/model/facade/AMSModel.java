// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AMSModel.java

package ams.model.facade;

import ams.model.*;
import ams.model.exception.EnrollmentException;
import ams.model.exception.ProgramException;

public interface AMSModel
{

    public abstract Student getStudent();

    public abstract Program getProgram();

    public abstract void addProgram(Program program)
        throws ProgramException;

    public abstract void addStudent(Student student);

    public abstract void addCourse(Course course)
        throws ProgramException;

    public abstract void removeCourse(String s)
        throws ProgramException;

    public abstract Course getCourse(String s);

    public abstract Course[] getAllCourses();

    public abstract boolean addResult(Result result);

    public abstract Result[] getResults();

    public abstract Course[] getCurrentEnrollment();

    public abstract void enrollIntoCourse(String s)
        throws EnrollmentException;

    public abstract void withdrawFromCourse(String s)
        throws EnrollmentException;

    public abstract int calculateCurrentLoad();

    public abstract int calculateCareerPoints();
}
