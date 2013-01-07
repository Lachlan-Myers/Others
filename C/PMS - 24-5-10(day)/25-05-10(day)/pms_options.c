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
	
	if(programType="UG")
	{
		printf("Summary of Undergrad Programs\n");
	}
	else
	{
		printf("Summary of Postgrad Programs\n");
	}
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
				printf("   %s %s  \t\t   Semester ",currCourse->courseID,currCourse->courseTitle);
				
				if(strcmp(&currCourse->teachPeriods[0].semester, "y") == 0 && strcmp(&currCourse->teachPeriods[1].semester, "y") == 0)
				{
					printf("1,2\n");
				} 
                else if(strcmp(&currCourse->teachPeriods[0].semester, "y") == 0 && strcmp(&currCourse->teachPeriods[1].semester, "y") != 0)
				{
					printf("1\n");
				}
				else if(strcmp(&currCourse->teachPeriods[1].semester, "y") == 0 && strcmp(&currCourse->teachPeriods[0].semester, "y") != 0)
				{
					printf("2\n");
				}			
				
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
     CourseType *currCourse;
     char title[10];

     current=pms->headProgram;
     
     printf("\n Detailed Program Report\n");
     printf("--------------------\n");
     printf("\n");
     printf("Enter program id (5 characters): ");
     fgets(id,7,stdin);
     strtok(id,"\n");
     
     if(strlen(id)<5 || strlen(id)>5)
     {
           printf("ERROR - incorrect Program ID entered\n");
     }
     
     sprintf(title,"%s.report",id);

     fp = fopen(title, "w");
     
     while(current != NULL)
	{
		if(strcmp(current->progID, id) == 0)
		{
			fprintf(fp,"\n%s -",current->progID);
			fprintf(fp," %s \n",current->progTitle);
			fprintf(fp,"        (%s) - Detailed Report\n",current->progMajor);
			fprintf(fp,"------------------------------------------------------------\n");\
			
			currCourse=current->headCourse;
			
			while(currCourse != NULL)
			{
				fprintf(fp,"Course ID     : %s\n",currCourse->courseID);
                fprintf(fp,"Course Title  : %s\n",currCourse->courseTitle); 
                fprintf(fp,"Course Code   : %s\n",currCourse->courseCode); 
                fprintf(fp,"Credit Points : %d\n",currCourse->creditPoints); 
                fprintf(fp,"Teaching Period : Semester ");  
				if(strcmp(&currCourse->teachPeriods[0].semester, "y") == 0 && strcmp(&currCourse->teachPeriods[1].semester, "y") == 0)
				{
					fprintf(fp,"1,2");
				} 
                else if(strcmp(&currCourse->teachPeriods[0].semester, "y") == 0 && strcmp(&currCourse->teachPeriods[1].semester, "y") != 0)
				{
					fprintf(fp,"1");
				}
				else if(strcmp(&currCourse->teachPeriods[1].semester, "y") == 0 && strcmp(&currCourse->teachPeriods[0].semester, "y") != 0)
				{
					fprintf(fp,"2");
				}
              
			    fprintf(fp,"\nDescription   : %s\n",currCourse->courseDescription);
				fprintf(fp,"\n");
				currCourse = currCourse->nextCourse;
			}
			
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
     
     	ProgramType *curr;
	ProgramType *prev;
	ProgramType *newProgram;

	char id[PROG_ID_LEN] = "P0008";
     char title[25];
     char type[PROG_TYPE_LEN];
     char major[MAX_TITLE_LEN];
     char code[PROG_CODE_LEN];
     float dur;
     char mode[MODE_LEN];
     char progDes[MAX_DESC_LEN];
     char majorDes[MAX_DESC_LEN];
	char prompt[100];
	int match = 0;
	
	
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
   /*fgets(dur,1+2,stdin);*/  scanf("%f",&dur);
     printf("Program Mode: ");
     fgets(mode,MODE_LEN+2,stdin);
     printf("Program Description (1-250 characters): ");
     fgets(progDes,MAX_DESC_LEN+2,stdin);
     printf("Major Description (1-250 characters): ");
     fgets(majorDes,MAX_DESC_LEN+2,stdin);

	curr = pms->headProgram;

	while(curr != NULL && !match)
	{
	   if(strcmp(curr->progID, id) == 0)
	   {
	      printf("This Program already existed.\n");
	      return;
	   }

	   else
	      curr = curr->nextProgram;
	}

	   newProgram = (ProgramType *) malloc(sizeof(ProgramType));

	   if(newProgram == NULL)
	   {
	      printf("Malloc failed for current pointer!\n");
	      return;
	   }
	
	   newProgram->nextProgram = NULL;

	   strcpy(newProgram->progID, id);
	   strcpy(newProgram->progTitle, title);
	   strcpy(newProgram->progType, type);
	   strcpy(newProgram->progMajor, major);
	   strcpy(newProgram->progCode, code);
	   newProgram->progDuration = dur;
	   strcpy(newProgram->progMode, mode);
	   strcpy(newProgram->progDescription, progDes);
	   strcpy(newProgram->majorDescription, majorDes);

	   curr = pms->headProgram;
	   prev = NULL;

	   while(curr != NULL && strcmp(curr -> progTitle, newProgram -> progTitle) < 0)
	   {
	      prev = curr;
	      curr = curr -> nextProgram;
	   }
	
	   newProgram -> nextProgram = curr;
	   (pms -> numPrograms)++;	

	   if(prev == NULL)
	   {
	      pms -> headProgram = newProgram;
	   }

	   else
	   {
	      prev->nextProgram = newProgram;
	   }

	   printf("The program \"%s\" has been added to the system.\n", title);
     
     
     
     /*
     
     char id[PROG_ID_LEN] = "P0008";
     char title[25];
     char type[PROG_TYPE_LEN];
     char major[MAX_TITLE_LEN];
     char code[PROG_CODE_LEN];
     char dur[1];
     char mode[MODE_LEN];
     char progDes[MAX_DESC_LEN];
     char majorDes[MAX_DESC_LEN];
     
     char *token;
     
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
	 
	 while(current != NULL )
    	 {
		previous=current;
		current=current->nextProgram;
    	 }
	 
	 if(current == NULL)
	 {        
        token = strtok(id,DELIMS);
        strcpy(program_info->progID,token);
		
        token = strtok(type,DELIMS);
		strcpy(program_info->progType,token);
		
        token = strtok(title,DELIMS);
		strcpy(program_info->progTitle,token);
		
        token = strtok(major,DELIMS);
		strcpy(program_info->progMajor,token);
		
        token = strtok(code,DELIMS);
		strcpy(program_info->progCode,token);
		
        token = strtok(dur,DELIMS);
		program_info->progDuration = atof(token);
		
        token = strtok(mode,DELIMS);
		strcpy(program_info->progMode,token);
		
        token = strtok(progDes,DELIMS);
		strcpy(program_info->progDescription,token);
		
        token = strtok(majorDes,DELIMS);
		strcpy(program_info->majorDescription,token);
		
    }
     */
 /*    
     while(fgets(program,PROG_SIZE,program1)!=NULL)
    {
	 current=pms->headProgram;
	 previous=NULL;	
	 

	program_info= (ProgramType *) malloc(sizeof(ProgramType));
	if(program_info == NULL)
	{
		printf("malloc crapped out\n");
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
}*/

     
}


/****************************************************************************
* Menu option #5: Delete Program
* Deletes a "prorgam" and all corresponding courses.
****************************************************************************/
void deleteProgram(PMSType* pms)
{
     char id[6];
     char *token;
     
     ProgramType *current;
     ProgramType *previous;
	 CourseType *currCourse;
     current=pms->headProgram;
     
     printf("Delete Program\n");
     printf("--------------------\n\n");
     printf("Warning: All course data for a program will be deleted if you proceed.\n\n");
     
     printf("Program ID: ");
     fgets(id,7,stdin);
     
     strtok(id,"\n");
     
     while(current != NULL && strcmp(current->progID, id) == 0)
     {
         previous = current;
         current = current->nextProgram;                      
  
			printf("Program \"%s - %s\" deleted from the system.",current->progID,current->progTitle);
	 }
	 
	 if(current == NULL)
	 {
        printf("FAILURE\n");
     }
     
     if(previous == NULL)
     {
        pms->headProgram = current->nextProgram;
     }
     else
     {
         previous->nextProgram = current->nextProgram;
     }
     
     free(current);
     pms->numPrograms--;
     
     /*
     ProgramType *curr;
	ProgramType *prev;
	ProgramType *newProgram;

	char delete_progID[PROG_ID_LEN];
	char prompt[1];
	int match = FALSE;


	printf("Delete Program\n");
	printf("-------------------------\n\n");
	printf("Warning: All course data for a program will be deleted if you proceed.\n\n");
	printf("Program ID: ");
	getString(delete_progID, PROG_ID_LEN+1, prompt);

	curr = pms->headProgram;
	prev = NULL;

	while(curr != NULL && !match)
	{
	   if(strcmp(curr->progID, delete_progID) != 0)
	   {
	      curr = curr->nextProgram;
	   }

	   else
	   {
		match = TRUE;
	   }
	}


	if(match)
	{

	   printf("%s", curr->progTitle);

	   newProgram = (ProgramType *) malloc(sizeof(ProgramType));

	   if(newProgram == NULL)
	   {
		printf("Malloc failed for current pointer!\n");
		return;
	   }

	   newProgram->nextProgram = NULL;

	   strcpy(newProgram->progID, delete_progID);

	   curr = pms->headProgram;
	   prev = NULL;

	   while(curr != NULL && strcmp(curr->progID, delete_progID) != 0)
		curr = curr->nextProgram;

	   if(prev == NULL)
	   {
		pms->headProgram = curr->nextProgram;
	   } 

	   else
	   {
		prev->nextProgram = curr->nextProgram;
	   }

	   printf("\n\nProgram %s deleted from the system.\n", delete_progID);

	}

	else
		printf("no found\n");
		*/
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
   
     FILE *program;
     FILE *course;
     ProgramType *current;
     CourseType *currCourse;
     char title[10];

     current=pms->headProgram;
     
     printf("\n Detailed Program Report\n");
     printf("--------------------\n");
     printf("\n");
     printf("Enter program id (5 characters): ");

     program = fopen(programFile, "w");
     
     while(current != NULL)
	{
			fprintf(program,"%s|",current->progID);
			fprintf(program,"%s|",current->progType);
			fprintf(program,"%s|",current->progTitle);
			fprintf(program,"%s|",current->progMajor);
			fprintf(program,"%s|",current->progCode);
			fprintf(program,"%s|",current->progDuration);
			fprintf(program,"%s|",current->progMode);
			fprintf(program,"%s|",current->progDescription);
			fprintf(program,"%s",current->majorDescription);
			fprintf(program,"\n");

		current = current->nextProgram;
	}

       printf("\nProgram file has been created.\n");
    
    fclose(program);
    /*
    course = fopen(courseFile, "w");
    
     while(currCourse != NULL)
	{
			fprintf(program,"%s|",current->progID);
			fprintf(program,"%s|",current->progType);
			fprintf(program,"%s|",current->progTitle);
			fprintf(program,"%s|",current->progMajor);
			fprintf(program,"%s|",current->progCode);
			fprintf(program,"%s|",current->progDuration);
			fprintf(program,"%s|",current->progMode);
			fprintf(program,"%s|",current->progDescription);
			fprintf(program,"%s|",current->majorDescription);
			fprintf(program,"\n");

		current = current->nextProgram;
	}
	
    fclose(course);
    
    */
    
}
