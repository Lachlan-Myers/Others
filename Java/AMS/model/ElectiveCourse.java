// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ElectiveCourse.java

package ams.model;

import ams.model.visitor.Visitor;

// Referenced classes of package ams.model:
//            AbstractCourse

public class ElectiveCourse extends AbstractCourse
{

    public ElectiveCourse(String code, String title, String preReqs[])
    {
        super(code, title, 6, preReqs);
    }

    public ElectiveCourse(String code, String title, int creditPoints, String preReqs[])
    {
        super(code, title, creditPoints, preReqs);
    }

    public String toString()
    {
        return (new StringBuilder(String.valueOf(super.toString()))).append(":ELECTIVE").toString();
    }

    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

    public static final int DEFAULT_CREDIT_POINTS = 6;
}
