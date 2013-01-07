/********************************************************************************
* COSC1283/1284 - Programming Techniques
* Semester 1 2010 Assignment #1 
* Full Name        : Lachlan Cameron Myers
* Student Number   : s3167356
* Yallara Username : s3167356
* Course Code      : COSC1284
* Program Code     : BP094
*  Start up code provided by Christopher Hoobin, John Thangarajah, and Xiaodong Li
*********************************************************************************/

#include "assign1.h"

/* Function readRestOfLine() is used for buffer clearing. 
 * Please refer to "test_fgets.c" on Blackboard: 
 * "Course Documents"->"Function Examples"->"Input Validation Examples" */
void readRestOfLine()
{
   int c;

   /* Read until the end of the line or end-of-file. */   
   while ((c = fgetc(stdin)) != '\n' && c != EOF)
      ;

   /* Clear the error and end-of-file flags. */
   clearerr(stdin);
}


/* Delete this comment and add other utility functions here. */
