// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CoreCourse.java

package ams.model;

import ams.model.visitor.Visitor;

// Referenced classes of package ams.model:
//            AbstractCourse

public class CoreCourse extends AbstractCourse
{

    public CoreCourse(String code, String title, String preReqs[])
    {
        super(code, title, 12, preReqs);
    }

    public String toString()
    {
        return (new StringBuilder(String.valueOf(super.toString()))).append(":CORE").toString();
    }

    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

    public static final int DEFAULT_CREDIT_POINTS = 12;
}
