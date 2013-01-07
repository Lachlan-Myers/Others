// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractCourse.java

package ams.model;

import ams.model.visitor.Visitable;
import ams.model.visitor.Visitor;

// Referenced classes of package ams.model:
//            Course

public abstract class AbstractCourse
    implements Course, Visitable
{

    public AbstractCourse(String code, String title, int creditPoints, String preReqs[])
    {
        this.code = code;
        this.title = title;
        this.creditPoints = creditPoints;
        this.preReqs = preReqs;
    }

    public String getCode()
    {
        return code;
    }

    public String getTitle()
    {
        return title;
    }

    public int getCreditPoints()
    {
        return creditPoints;
    }

    public String[] getPreReqs()
    {
        return preReqs;
    }

    public String toString()
    {
        String returnString = (new StringBuilder(String.valueOf(code))).append(":").append(title).append(":").append(creditPoints).toString();
        if(preReqs != null)
        {
            returnString = (new StringBuilder(String.valueOf(returnString))).append(":").toString();
            for(int i = 0; i < preReqs.length; i++)
                returnString = (new StringBuilder(String.valueOf(returnString))).append(preReqs[i]).append(",").toString();

            returnString = returnString.substring(0, returnString.length() - 1);
        }
        return returnString;
    }

    public boolean equals(Object in)
    {
        Course obj = (Course)in;
        return code == obj.getCode();
    }

    public abstract void accept(Visitor visitor);

    protected String code;
    protected String title;
    protected int creditPoints;
    protected String preReqs[];
}
