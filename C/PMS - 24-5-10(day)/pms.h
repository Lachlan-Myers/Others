/**********************************************************************************
* COSC1283/1284 - Programming Techniques
* Semester 1 2010 Assignment #2 - SCSIT Program Management System
* Full Name        : EDIT HERE
* Student Number   : EDIT HERE
* Yallara Username : EDIT HERE
* Course Code      : EDIT HERE
* Program Code     : EDIT HERE
* Start up code provided by Christopher Hoobin, John Thangarajah, and Xiaodong Li
***********************************************************************************/

#ifndef PMS_H
#define PMS_H

/* System-wide header files. */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* System-wide constants. */
#define PROG_ID_LEN 5
#define PROG_TYPE_LEN 2
#define MODE_LEN 2
#define MIN_TITLE_LEN 1
#define MAX_TITLE_LEN 40
#define PROG_CODE_LEN 6
#define COURSE_ID_LEN 5
#define COURSE_CODE_LEN 8
#define MIN_DESC_LEN 1
#define MAX_DESC_LEN 500
#define NUM_TEACHPERIODS 2 /* Semester 1 and 2 */
#define SUCCESS 1
#define FAILURE 0

#define DELIMS "|"

#define COURSE_SIZE (COURSE_ID_LEN+PROG_ID_LEN+MAX_TITLE_LEN+COURSE_CODE_LEN+2+NUM_TEACHPERIODS+MAX_DESC_LEN)/*miss creditpoints*/
#define PROG_SIZE (PROG_ID_LEN+PROG_TYPE_LEN+MAX_TITLE_LEN+MAX_TITLE_LEN+PROG_CODE_LEN+2+MODE_LEN+MAX_DESC_LEN+MAX_DESC_LEN) /*miss major,duraTION,*/
#define SEM_ONE 0
#define SEM_TWO 1
#define YEAR 2010


typedef struct program* ProgramTypePtr;
typedef struct course* CourseTypePtr;

/* Structure definitions. */
typedef struct teachPeriod
{
   char semester;  /* 'y' or 'n' for each semester */
   unsigned year;  /* 2010 by default */
} TeachPeriodType;

typedef struct course
{
   char courseID[COURSE_ID_LEN + 1];  /* Unique ID for a course */
   char progID[PROG_ID_LEN + 1];
   char courseTitle[MAX_TITLE_LEN + 1];
   char courseCode[COURSE_CODE_LEN + 1];
   unsigned creditPoints;
   TeachPeriodType teachPeriods[NUM_TEACHPERIODS];
   char courseDescription[MAX_DESC_LEN];
   CourseTypePtr nextCourse;
} CourseType;

typedef struct program
{
   char progID[PROG_ID_LEN + 1];   /* Unique ID for a program */
   char progTitle[MAX_TITLE_LEN + 1];
   char progMajor[MAX_TITLE_LEN + 1];
   char progCode[PROG_CODE_LEN + 1];
   float progDuration;
   char progType[PROG_TYPE_LEN + 1];      /* Undergrade(UG) or Postgrad (PG) */
   char progMode[MODE_LEN + 1];  /* Full-time (FT) or Part-time (PT) */
   char progDescription[MAX_DESC_LEN];
   char majorDescription[MAX_DESC_LEN];
   ProgramTypePtr nextProgram;
   CourseTypePtr headCourse;
   unsigned numCourses;
} ProgramType;

typedef struct pms
{
   ProgramTypePtr headProgram;
   unsigned numPrograms;
} PMSType;

#endif
