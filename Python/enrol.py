#!/usr/bin/env python

import re
import os.path
import sys
import glob
#import string

#Part 1

#makes list an array, checks that the path exists
#and then opens the file as a read-only. Gets the
#input and puts it into the array and ignores the
#hash. Appends the array and closes the file.
#finally printing out the array.
def readlines(filename):
    list = []
    if os.path.exists(filename):
        try:
            f = open(filename,'r')
        except IOError:
            print "File Error."
        else:
	    for line in f:
	        line = line.rstrip('\n')
	        if line[0] == '#':
	            continue
	        else:
	            list.append(line)
            f.close()			
    return list

#Same general start. this time removes the newline char
#ignores hash. appends the line and splits at the ':'.
#before closing the file and printing the result.
def readtable(filename):
    list=[]
    if os.path.exists(filename):
        try:
            f = open(filename,'r')
        except IOError:
            print "File Error."
        else:
	    for line in f:
                line = line.rstrip('\n')
	        if line[0] == '#':
	            continue
	        else:
	            list.append(line.split(':'))
            f.close()			
    return list
    
 # this one took me a while as it turns out i
 #went in the wrong direction (see below for the
 #alternative that came out of it).
 #changes filename to a temp file, opens the file to
 #write or else prints error. gets the lines in the
 #sentence taken and adds them to the temp file with
 #a newline character. Closes the file and renames it.
def writelines(filename,lines):
    temp = filename + ".tmp"
    list = []
    try:
        f = open(temp,'w+')
    except OSError:
        print "File Error."
	return 0
    else:
        for sentence in lines:
            f.write(sentence + '\n')                   
        f.close()
        #os.remove(filename)
        os.rename(temp,filename)
    return 1

# An alternative method that writes the words
# to the file, requires import string
#
#Better in that it reads the file and adds
#the words individually to the file but not
#what the assignment spec asked for.
#
#def writelines(filename,lines):
#    #temp = filename + ".tmp"
#    list = []
#    try:
#        f = open(filename+".tmp",'w')
#    except OSError:
#        print "File Error."
#	return 0
#    else:
#        for line in open(filename,'r'):
#            f.write(line)
#            if line[0]=='#':
#                continue
#        else:
#            str = lines
#            stringarr = string.split(str)
#            for i in range(len(stringarr)):
#                f.write(stringarr[i]+'\n')
#                   
#        f.close()
#        os.remove(filename)
#        os.rename(filename+".tmp",filename)
#    return 1


#Part 2
class Enrol(object):
    def __init__(self,object):
        os.chdir(object)

# The following appears to work as the
# Test Harness has no quarms with it.
    def subjects():
        list = readlines("CLASSES")
        for line in list:
            #line = line.rstrip('\n')
            list.append(line)
        return list
        
    
#
        def subjectName(subjectCode):
            list = []
            list=readtable("SUBJECTS")
            #for sub in list:              
            find = list.search(subjectCode)
            for i in list:
                if subjectCode in  list:
                        title = subjectCode
                        #for i in list:
                        sub=list[i+1]
                        #ans = sub[i]
                else:
                    continue            
            return sub


        def classes(subjectCode):
            list = []
            find = re.search(subjectCode)
            # if subjectCode in
            return 0

        


        def classInfo(classid):
            #do something
            return 0



        def checkStudent(studid,classid):
            #do something
            return 0


        def enrol(studid,classid):
            #do something
            return 0


	        
