// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Visitable.java

package ams.model.visitor;


// Referenced classes of package ams.model.visitor:
//            Visitor

public interface Visitable
{

    public abstract void accept(Visitor visitor);
}
