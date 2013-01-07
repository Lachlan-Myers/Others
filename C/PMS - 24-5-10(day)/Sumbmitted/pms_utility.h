/**********************************************************************************
* COSC1283/1284 - Programming Techniques
* Semester 1 2010 Assignment #2 - SCSIT Program Management System
* Full Name        : Lachlan Cameron Myers
* Student Number   : s3167356
* Yallara Username : s3167356
* Course Code      : COSC1284
* Program Code     : BP094
* Start up code provided by Christopher Hoobin, John Thangarajah, and Xiaodong Li
***********************************************************************************/

#ifndef PMS_UTILITY_H
#define PMS_UTILITY_H

/* Function prototypes. */
void readRestOfLine();
int systemInit(PMSType* pms);
int loadData(PMSType* pms, char* programFile, char* courseFile);
void systemFree(PMSType* pms);

#endif
