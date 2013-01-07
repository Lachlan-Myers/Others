// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Visitor.java

package ams.model.visitor;

import ams.model.CoreCourse;
import ams.model.ElectiveCourse;

public interface Visitor
{

    public abstract void visit(ElectiveCourse electivecourse);

    public abstract void visit(CoreCourse corecourse);
}
