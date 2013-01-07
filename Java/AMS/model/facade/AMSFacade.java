// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AMSFacade.java

package ams.model.facade;

import ams.model.*;
import ams.model.exception.EnrollmentException;
import ams.model.exception.ProgramException;

// Referenced classes of package ams.model.facade:
//            AMSModel

public class AMSFacade
    implements AMSModel
{

    public AMSFacade()
    {
        university = new University();
    }

    public AMSFacade(Student student, Program program)
    {
        university = new University();
        university.setStudent(student);
        university.setProgram(program);
    }

    public void addStudent(Student student)
    {
        university.setStudent(student);
    }

    public Student getStudent()
    {
        return university.getStudent();
    }

    public void addProgram(Program program)
        throws ProgramException
    {
        university.setProgram(program);
        university.populateData();
    }

    public Program getProgram()
    {
        return university.getProgram();
    }

    public void addCourse(Course course)
        throws ProgramException
    {
        university.addCourse(course);
    }

    public void removeCourse(String courseCode)
        throws ProgramException
    {
        university.removeCourse(courseCode);
    }

    public Course getCourse(String courseCode)
    {
        return university.getCourse(courseCode);
    }

    public Course[] getAllCourses()
    {
        return university.getProgramCourses();
    }

    public void enrollIntoCourse(String courseCode)
        throws EnrollmentException
    {
        university.enrollStudentIntoCourse(courseCode);
    }

    public void withdrawFromCourse(String courseCode)
        throws EnrollmentException
    {
        university.withdrawStudentFromCourse(courseCode);
    }

    public boolean addResult(Result result)
    {
        return university.addStudentResult(result);
    }

    public Result[] getResults()
    {
        return university.getStudentResults();
    }

    public Course[] getCurrentEnrollment()
    {
        return university.getCurrentStudentEnrollment();
    }

    public int calculateCurrentLoad()
    {
        return university.calculateCurrentStudentLoad();
    }

    public int calculateCareerPoints()
    {
        return university.calculateTotalCareerPoints();
    }

    private University university;
}
