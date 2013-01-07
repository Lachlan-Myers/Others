// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   University.java

package ams.model;

import ams.model.exception.EnrollmentException;
import ams.model.exception.ProgramException;

// Referenced classes of package ams.model:
//            Program, Student, CoreCourse, ElectiveCourse, 
//            Course, Result

public class University
{

    public University()
    {
    }

    public Student getStudent()
    {
        return student;
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }

    public Program getProgram()
    {
        return program;
    }

    public void setProgram(Program program)
    {
        this.program = program;
    }

    public void addCourse(Course course)
        throws ProgramException
    {
        program.addCourse(course);
    }

    public void removeCourse(String courseCode)
        throws ProgramException
    {
        program.removeCourse(courseCode);
    }

    public Course getCourse(String courseCode)
    {
        return program.getCourse(courseCode);
    }

    public Course[] getProgramCourses()
    {
        return program.getAllCourses();
    }

    public void enrollStudentIntoCourse(String courseCode)
        throws EnrollmentException
    {
        student.enrollIntoCourse(program.getCourse(courseCode));
    }

    public void withdrawStudentFromCourse(String courseCode)
        throws EnrollmentException
    {
        student.withdrawFromCourse(program.getCourse(courseCode));
    }

    public boolean addStudentResult(Result result)
    {
        return student.addResult(result);
    }

    public Result[] getStudentResults()
    {
        return student.getResults();
    }

    public Course[] getCurrentStudentEnrollment()
    {
        return student.getCurrentEnrollment();
    }

    public int calculateCurrentStudentLoad()
    {
        return student.calculateCurrentLoad();
    }

    public int calculateTotalCareerPoints()
    {
        return student.calculateCareerPoints();
    }

    public void populateData()
        throws ProgramException
    {
        coreCourse1 = new CoreCourse("COSC1073", "Programming 1", null);
        coreCourse2 = new CoreCourse("MATH1074", "Mathematics for Computing", null);
        coreCourse3 = new CoreCourse("COSC1076", "Programming 2", new String[] {
            "COSC1073"
        });
        coreCourse4 = new CoreCourse("COSC1107", "Computing Theory", new String[] {
            "COSC1076", "MATH1074"
        });
        electiveCourse1 = new ElectiveCourse("COSC2309", "Mobile Application Development", 6, new String[] {
            "COSC1076"
        });
        electiveCourse2 = new ElectiveCourse("COSC1197", "Distributed Systems", 12, new String[] {
            "COSC1107", "COSC2309"
        });
        electiveCourse3 = new ElectiveCourse("ISYS2403", "Advanced Topics in Distributed Systems", 12, new String[] {
            "COSC1197"
        });
        electiveCourse4 = new ElectiveCourse("ISYS2222", "Advanced Topics in Distributed Systems 2", 12, new String[] {
            "ISYS2403"
        });
        addCourse(coreCourse1);
        addCourse(coreCourse2);
        addCourse(coreCourse3);
        addCourse(coreCourse4);
        addCourse(electiveCourse1);
        addCourse(electiveCourse2);
        addCourse(electiveCourse3);
        addCourse(electiveCourse4);
    }

    private Student student;
    private Program program;
    private static final String P1 = "COSC1073";
    private static final String P2 = "COSC1076";
    private static final String MATHS = "MATH1074";
    private static final String COMP_THEORY = "COSC1107";
    private static final String MAD = "COSC2309";
    private static final String DISTRIBUTED = "COSC1197";
    private static final String ADV_DISTRIBUTED = "ISYS2403";
    private static final String ADV_DISTRIBUTED2 = "ISYS2222";
    private Course coreCourse1;
    private Course coreCourse2;
    private Course coreCourse3;
    private Course coreCourse4;
    private Course electiveCourse1;
    private Course electiveCourse2;
    private Course electiveCourse3;
    private Course electiveCourse4;
}
