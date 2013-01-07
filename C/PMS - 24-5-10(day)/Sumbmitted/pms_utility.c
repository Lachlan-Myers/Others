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

#include "pms.h"
#include "pms_options.h"
#include "pms_utility.h"

/****************************************************************************
* Function readRestOfLine() is used for buffer clearing. 
* Please refer to "test_fgets.c" on Blackboard: 
* "Course Documents"->"Function Examples"->"Input Validation Examples" 
****************************************************************************/
void readRestOfLine()
{
   int c;

   /* Read until the end of the line or end-of-file. */   
   while ((c = fgetc(stdin)) != '\n' && c != EOF)
      ;

   /* Clear the error and end-of-file flags. */
   clearerr(stdin);
}


/****************************************************************************
* Initialises the system to a safe empty state.
****************************************************************************/
int systemInit(PMSType* pms)
{
	/*int result = FAILURE;*/
	
	if(pms)
	{
		pms->headProgram = NULL;
		pms-> numPrograms = 0;
		return SUCCESS;
	}
	
	return FAILURE;
}


/****************************************************************************
* Loads all data into the system.
****************************************************************************/
int loadData(PMSType* pms, char* programFile, char* courseFile)
{

    FILE *program1;
    FILE *course1;

    char program[PROG_SIZE];
    ProgramType *program_info = NULL;
    ProgramType *current = NULL;
    ProgramType *previous = NULL;

    char *token;
    char *newTok;
    char teach[100];
    
    char course[COURSE_SIZE];
    CourseType *course_info = NULL;
    CourseType *currCourse = NULL;
    CourseType *prevCourse = NULL;
    

    
    program1= fopen(programFile,"r");
    course1= fopen(courseFile,"r");
    if(program1==NULL || course1==NULL || (program1==NULL && course1==NULL))
    {
       printf("FAIL - Incorrect File(s)\n");
       return FAILURE;
    }



	

    while(fgets(program,PROG_SIZE,program1)!=NULL)
    {
	 current=pms->headProgram;
	 previous=NULL;	
	 

	program_info= (ProgramType *) malloc(sizeof(ProgramType));
	if(program_info == NULL)
	{
		printf("Malloc failed.\n");
		return EXIT_FAILURE;
	}

	 program_info->headCourse = NULL;

	 token = strtok(program, DELIMS);

	 while(token !=NULL)
	 {
		strcpy(program_info->progID,token);
		token = strtok(NULL,DELIMS);

		strcpy(program_info->progType,token);
		token = strtok(NULL,DELIMS);

		strcpy(program_info->progTitle,token);
		token = strtok(NULL,DELIMS);

		strcpy(program_info->progMajor,token);
		token = strtok(NULL,DELIMS);

		strcpy(program_info->progCode,token);
		token = strtok(NULL,DELIMS);

		program_info->progDuration = atof(token);
		token = strtok(NULL,DELIMS);

		strcpy(program_info->progMode,token);
		token = strtok(NULL,DELIMS);

		strcpy(program_info->progDescription,token);
		token = strtok(NULL,DELIMS);

		strcpy(program_info->majorDescription,token);
		token = strtok(NULL,DELIMS);
	 }

   	 current = pms->headProgram;
	 previous = NULL;

	 while(current != NULL && (strcmp(current->progTitle,program_info->progTitle))< 0)
    	 {
		previous=current;
		current=current->nextProgram;
    	 }
	
	 program_info->nextProgram = current;
	 (pms-> numPrograms)++;

  	 if(previous == NULL)
	 {
		pms->headProgram = program_info;
	 }
	 else
	 {
		previous->nextProgram = program_info;
	 }
}



	while(fgets(course,COURSE_SIZE,course1)!=NULL)
    {

	 
	 current=pms->headProgram;
	 previous=NULL;	
	 

	course_info= (CourseType *) malloc(sizeof(CourseType));
	if(course_info == NULL)
	{
		printf("Malloc failed.\n");
		return EXIT_FAILURE;
	}

	course_info->nextCourse = NULL;

	 token = strtok(course, DELIMS);

		strcpy(course_info->courseID,token);
		token = strtok(NULL,DELIMS);

		strcpy(course_info->progID,token);
		token = strtok(NULL,DELIMS);

		strcpy(course_info->courseTitle,token);
		token = strtok(NULL,DELIMS);

		strcpy(course_info->courseCode,token);
		token = strtok(NULL,DELIMS);

		course_info->creditPoints= atof(token);
		token = strtok(NULL,DELIMS);

		strcpy(teach,token);
		token = strtok(NULL,DELIMS);

		strcpy(course_info->courseDescription,token);
		
		newTok = strtok(teach,",");
		newTok = strtok(NULL,",");

		course_info->teachPeriods[SEM_ONE].year = YEAR;
		course_info->teachPeriods[SEM_ONE].semester = newTok[0];
		newTok = strtok(NULL,",");
		
		course_info->teachPeriods[SEM_TWO].year = YEAR;
		course_info->teachPeriods[SEM_TWO].semester = newTok[0];
		token = strtok(NULL,DELIMS);
		
	 current=pms->headProgram;

	 while(current != NULL && (strcmp(current->progID,course_info->progID)) != 0)
    	 {
		current=current->nextProgram;
    	 }

  	 if(current == NULL)
	 {
		printf("FAIL - Current program is NULL\n");
		return FAILURE;
	 }

	 currCourse= current->headCourse;
	 prevCourse = NULL;
	
	 if(currCourse == NULL)
	 {
		current->headCourse=course_info;
	 }
	 else
	 {
		while(currCourse != NULL && strcmp(currCourse->courseTitle, course_info->courseTitle)<0)
		{
			prevCourse = currCourse;
			currCourse = currCourse->nextCourse;
		}
		if(prevCourse == NULL)
		{
			course_info->nextCourse = current->headCourse;
			current->headCourse=course_info;
		}
		else
		{
			prevCourse->nextCourse = course_info;
			currCourse = course_info->nextCourse;
		}
	 }
	 (pms->headProgram->numCourses)++;

    }

	 


    return SUCCESS;
    
}


/****************************************************************************
* Deallocates memory used in the program.
****************************************************************************/
void systemFree(PMSType* pms)
{
     free(pms);
}
