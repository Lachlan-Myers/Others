// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CourseVisitor.java

package ams.model.visitor;

import ams.model.CoreCourse;
import ams.model.ElectiveCourse;

// Referenced classes of package ams.model.visitor:
//            Visitor

public class CourseVisitor
    implements Visitor
{

    public CourseVisitor()
    {
        coreCount = 0;
        electivesCount = 0;
    }

    public void visit(CoreCourse course)
    {
        coreCount++;
    }

    public void visit(ElectiveCourse course)
    {
        electivesCount++;
    }

    public int getCoreCount()
    {
        return coreCount;
    }

    public int getElectivesCount()
    {
        return electivesCount;
    }

    private int coreCount;
    private int electivesCount;
}
