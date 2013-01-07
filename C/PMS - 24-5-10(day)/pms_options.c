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
* Menu option #1 and #2: Summary of Programs
* Allows the user to display a summary of all undergrad or postgrad programs
* and associated courses.
****************************************************************************/
void displaySummary(PMSType* pms, char* programType)
{
	ProgramType *current;
	CourseType *currCourse;
	
	current=pms->headProgram;

	printf("Summary of Undergrad Programs\n");
	printf("--------------------------------\n");
	
	while(current != NULL)
	{
		if(strcmp(current->progType, programType) == 0)
		{
			printf("\n%s - %s \n",current->progID,current->progTitle);
			printf("        (%s) (%d courses)\n",current->progMajor,current->numCourses);
			printf("------------------------------------------------------------\n");
			printf("   ID	   Course Title			   Teach Period\n"); 
			printf("   ----- --------------------------------- -----------------\n");
			
			currCourse=current->headCourse;
			
			while(currCourse != NULL)
			{
				printf("   %s --------------------------------- -----------------\n",currCourse->courseID);
				
				/*
				if(strcmp(&currCourse->teachPeriods[0].semester, "y") == 0)
				{
					printf("1");
				}
				if(strcmp(&currCourse->teachPeriods[1].semester, "y") == 0)
				{
					printf("2");
				}
				if(strcmp(&currCourse->teachPeriods[0].semester, "y") == 0 && strcmp(&currCourse->teachPeriods[1].semester, "y") == 0)
				{
					printf("1,2");
				}
				*/
				
				currCourse = currCourse->nextCourse;
			}
		}
		current = current->nextProgram;
	}

/*
// ProgramType * curr;
	CourseType *Coursecurr
	while(curr!= NULL)
	{
	if(strcmp(curr->progType, "UG") == 0)
	{
		while(curCourse!=NULL)
		{
			if(strcmp(&currCourse->teachPeriods[0].semester, "y" == 0
			printf("1")
			if(jhedkfhhrt[1].semester,y==0
			printf("2")
			if(strcmp(&currCourse->teachPeriods[0].semester, "y" == 0 && strcmp(&currCourse->teachPeriods[1].semester, "y" == 0 
			printf("1,2")

			currCourse= currCourse->next;
		}
	}
	curr = curr->nextProgram;
	
	}
*/
}


/****************************************************************************
* Menu option #3: Detailed Program Report
* Allows the user to make a new report file for a chosen program.
****************************************************************************/
void programReport(PMSType* pms)
{
     char id[7];
     FILE *fp;
     ProgramType *current;
     char title[10];

     current=pms->headProgram;
     
     printf("\n Detailed Program Report\n");
     printf("--------------------\n");
     printf("\n");
     printf("Enter program id (5 characters): ");
     fgets(id,7,stdin);
     strtok(id,"\n");
     
     sprintf(title,"%s.report",id);

     fp = fopen(title, "w");
     
     while(current != NULL)
	{
		if(strcmp(current->progID, id) == 0)
		{
			fprintf(fp,"\n%s -",current->progID);
			fprintf(fp," %s \n",current->progTitle);
			fprintf(fp,"        (%s) - Detailed Report\n",current->progMajor);
			fprintf(fp,"------------------------------------------------------------\n");
			/*fprintf("Course ID     : %s\n");
            fprintf("Course Title  : %s\n"); 
            fprintf("Course Code   : %s\n"); 
            fprintf("Credit Points : %d\n"); 
            fprintf("Teaching Period : Semester %d,%d\n");  
			fprintf("Description   : %s\n");*/
		}
		current = current->nextProgram;
	}
  
    printf("\nFile %s has been created.\n",title);
    fclose(fp);
}


/****************************************************************************
* Menu option #4: Add Program
* Allows the user to add a new "program" record to the linked list.
****************************************************************************/
void addProgram(PMSType* pms)
{
     
     char id[PROG_ID_LEN] = "P0008";
     char title[25];
     char type[PROG_TYPE_LEN];
     char major[MAX_TITLE_LEN];
     char code[PROG_CODE_LEN];
     char dur[1];
     char mode[MODE_LEN];
     char progDes[MAX_DESC_LEN];
     char majorDes[MAX_DESC_LEN];
     
     ProgramType *current;
     ProgramType *previous;
     ProgramType *program_info;
     
     printf("\nAdd Program\n");
     printf("-----------------\n");
     printf("\n");
     printf("New Program ID is %s\n",id);
     
     printf("Program Title (1-25 characters): ");
     fgets(title,25+2,stdin);
     printf("Undergrad (UG) or Postgrad (PG) program?: ");
     fgets(type,PROG_TYPE_LEN+2,stdin);
     printf("Program Major: ");
     fgets(major,MAX_TITLE_LEN+2,stdin);
     printf("Program Code: ");
     fgets(code,PROG_CODE_LEN+2,stdin);
     printf("Program Duration (years): ");
     fgets(dur,1+2,stdin);
     printf("Program Mode: ");
     fgets(mode,MODE_LEN+2,stdin);
     printf("Program Description (1-250 characters): ");
     fgets(progDes,MAX_DESC_LEN+2,stdin);
     printf("Major Description (1-250 characters): ");
     fgets(majorDes,MAX_DESC_LEN+2,stdin);
     
     current=pms->headProgram;	 

	/*program_info= (ProgramType *) malloc(sizeof(ProgramType));
	if(program_info == NULL)
	{
		printf("malloc crapped out\n");
		return EXIT_FAILURE;
	}*/

		program_info->nextProgram = NULL;

		strcpy(program_info->progID,id);

		strcpy(program_info->progType,type);

		strcpy(program_info->progTitle,title);

		strcpy(program_info->progMajor,major);

		strcpy(program_info->progCode,code);

		program_info->progDuration = atof(dur);

		strcpy(program_info->progMode,mode);

		strcpy(program_info->progDescription,progDes);

		strcpy(program_info->majorDescription,majorDes);

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


/****************************************************************************
* Menu option #5: Delete Program
* Deletes a "prorgam" and all corresponding courses.
****************************************************************************/
void deleteProgram(PMSType* pms)
{
}


/****************************************************************************
* Menu option #6: Add Course
* Allows the user to add a new course to an existing "program". An error 
* message is given if the "program" doesn't exist.
****************************************************************************/
void addCourse(PMSType* pms)
{
}


/****************************************************************************
* Menu option #7: Delete Course
* Deletes a single course from a particular "program".
****************************************************************************/
void deleteCourse(PMSType* pms)
{
}


/****************************************************************************
* Menu option #8: Save and Exit
* Saves all system data back to the original files. This function does not
* terminate the program - this is left to the main() function instead.
****************************************************************************/
void saveData(PMSType* pms, char* programFile, char* courseFile)
{
}
