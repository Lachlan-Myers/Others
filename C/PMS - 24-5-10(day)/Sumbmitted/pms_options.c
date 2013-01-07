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
	
	if(strcmp(programType,"UG")==0)
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
     
     	ProgramType *current;
	ProgramType *previous;
	ProgramType *program;


     char title[25];
     char type[PROG_TYPE_LEN];
     char major[MAX_TITLE_LEN];
     char code[PROG_CODE_LEN];
     char dur[3];
     char mode[MODE_LEN];
     char progDes[MAX_DESC_LEN];
     char majorDes[MAX_DESC_LEN];
	int match = 0;
	char id[PROG_ID_LEN];
	int number=(pms->numPrograms)+1;
	
	if(number<10)
	{
		sprintf(id,"P000%d",number);
	}
	else
	{
		sprintf(id,"P00%d",number);
	}


	current = pms->headProgram;
	
	
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

	

	   program = (ProgramType *) malloc(sizeof(ProgramType));

	   if(program == NULL)
	   {
	      printf("Malloc failed.\n");
	      return;
	   }

	   program->nextProgram = NULL;

	   strcpy(program->progID, id);
	
	   strtok(title,"\n");
	   strcpy(program->progTitle, title);

	   strtok(type,"\n");
	   strcpy(program->progType, type);

	   strtok(major,"\n");
	   strcpy(program->progMajor, major);

	   strtok(code,"\n");
	   strcpy(program->progCode, code);

	   program->progDuration = atof(dur);

	   strtok(mode,"\n");
	   strcpy(program->progMode, mode);

	   strtok(progDes,"\n");
	   strcpy(program->progDescription, progDes);

	   strtok(majorDes,"\n");
	   strcpy(program->majorDescription, majorDes);

	   current = pms->headProgram;
	   previous = NULL;

	   while(current != NULL && strcmp(current -> progTitle, program -> progTitle) < 0)
	   {
	      previous = current;
	      current = current -> nextProgram;
	   }
	
	   program -> nextProgram = current;
	   (pms -> numPrograms)++;	

	   if(previous == NULL)
	   {
	      pms -> headProgram = program;
	   }

	   else
	   {
	      previous->nextProgram = program;
	   }

	   printf("%s has successfully been added.\n", title);
     
}


/****************************************************************************
* Menu option #5: Delete Program
* Deletes a "prorgam" and all corresponding courses.
****************************************************************************/
void deleteProgram(PMSType* pms)
{
 	char id[6];
     
     	ProgramType *current;
     	ProgramType *previous;
     	
     
     	printf("Delete Program\n");
     	printf("--------------------\n\n");
     	printf("Warning: All course data for a program will be deleted if you proceed.\n\n");
     
     	printf("Program ID: ");
     	fgets(id,7,stdin);
     
     	strtok(id,"\n");

	current=pms->headProgram;
	previous = NULL;

	


	while(current != NULL && strcmp(current->progID,id) !=0)
	{
		previous = current;
		current=current->nextProgram;
	}

	if(current == NULL)
	{
		printf("Program does not exist\n");
	}
	
	if(previous==NULL)
	{
		pms->headProgram = current->nextProgram;
	}
	else
	{
		printf("Program %s - %s was successfully deleted.\n",current->progID,current->progTitle);
		previous->nextProgram=current->nextProgram;
		
	}

}


/****************************************************************************
* Menu option #6: Add Course
* Allows the user to add a new course to an existing "program". An error 
* message is given if the "program" doesn't exist.
****************************************************************************/
void addCourse(PMSType* pms)
{

	ProgramType *current;
	ProgramType *previous;
	CourseType *course;
	CourseType *currCourse;
	CourseType *prevCourse;

     char title[25];
     char credit[2];
     char code[PROG_CODE_LEN];
     char courseDes[MAX_DESC_LEN];
     char sem_one[5];
	 char sem_two[5];
	 char id[COURSE_ID_LEN];
	 char prog_id[PROG_ID_LEN];
	 int number=0;
	 	
    current = pms->headProgram;
    previous = NULL;
    
	 for(current = pms->headProgram; current != NULL; current = current->nextProgram)
	{
	      number += current->numCourses;
	}

    
	
	sprintf(id,"C%04d",(number+1));

	current = pms->headProgram;
	previous = NULL;
	
	
	printf("\nAdd Course\n");
  	printf("-----------------\n");
    	printf("\n");

	printf("Program ID: ");
	fgets(prog_id,PROG_ID_LEN+2,stdin);

     	printf("New course ID is %s\n",id);
     
     	printf("Course Title (1-25 characters): ");
     	fgets(title,25+2,stdin);

     	printf("Course Code: ");
     	fgets(code,COURSE_CODE_LEN+2,stdin);

     	printf("Credit Points: ");
   	    fgets(credit,2+2,stdin);

     	printf("Teaching in Semester 1 (y/n)?:");
     	fgets(sem_one,5+2,stdin);

	    printf("Teaching in Semester 2 (y/n)?:");
     	fgets(sem_two,5+2,stdin);

     	printf("Description (1-250 characters): ");
     	fgets(courseDes,MAX_DESC_LEN+2,stdin);


	   course = (CourseType *) malloc(sizeof(CourseType));

	   if(course == NULL)
	   {
	      printf("Malloc failed.\n");
	      return;
	   }
	
	   course->nextCourse = NULL;

	   strtok(prog_id,"\n");
	   strcpy(course->progID, prog_id);

	   strcpy(course->courseID, id);
	
	   strtok(title,"\n");
	   strcpy(course->courseTitle, title);
	   

	   strtok(code,"\n");
	   strcpy(course->courseCode, code);

	   course->creditPoints = atof(credit);

	   strtok(sem_one,"\n");
	   strcpy(&course->teachPeriods[0].semester, sem_one);

	   strtok(sem_two,"\n");
	   strcpy(&course->teachPeriods[1].semester, sem_two);

	   strtok(courseDes,"\n");
	   strcpy(course->courseDescription, courseDes);
	   
	   currCourse = current->headCourse;
	   prevCourse = NULL;

	   if(currCourse == NULL)
	   {
	      current->headCourse = course;
	   }
	   else
	   {
	      while(currCourse != NULL && strcmp(currCourse->courseTitle, course->courseTitle)<0)
	      {
                 prevCourse = currCourse;
                 currCourse = currCourse->nextCourse;

	      }

              if (prevCourse == NULL) 
	          {
                 course->nextCourse = current->headCourse;
                 current->headCourse = course;
              }
              else 
	          {
                 prevCourse->nextCourse = course;
	             currCourse = course->nextCourse;
              }
	   }
	   (current->numCourses)++;
	   
	   printf(" %s has been successfully added.\n", title);


}


/****************************************************************************
* Menu option #7: Delete Course
* Deletes a single course from a particular "program".
****************************************************************************/
void deleteCourse(PMSType* pms)
{
     ProgramType *current;
	 ProgramType *previous;
	 CourseType *course;
	 CourseType *prevCourse;
	 CourseType *currCourse;
	
	char id[COURSE_ID_LEN+1];
	char title[MAX_TITLE_LEN+1];
	char prog_id[PROG_ID_LEN+1];
	int number;
	int true1 = 1;
	int false1 = 0;

	printf("Delete Course\n");
	printf("----------------\n\n");
	printf("Program ID (5 characters): ");
	fgets(prog_id, PROG_ID_LEN+2, stdin);
	printf("Course ID (5 characters): ");
	fgets(id, COURSE_ID_LEN+2, stdin);
	
	current = pms->headProgram;
	previous = NULL;
             
	   currCourse = current->headCourse;
	   prevCourse = NULL;

	   while(currCourse != NULL)
	   {
	      if(strcmp(currCourse->courseID, id) != 0)
	      {
	        prevCourse = currCourse; 
		    currCourse = currCourse->nextCourse;
	      }
	      else
	      {
	         false1 = 1;
	      }
	   }

	  if(true1)
	   {
	      course = (CourseType *) malloc(sizeof(CourseType));
	      if(course == NULL)
	      {
	         printf("Malloc failed.\n");
	         return;	
	      }
	      
	      course->nextCourse = NULL;
	      
	      strcpy(title, currCourse->courseTitle);
	      
	      number = current->numCourses;

	      if(prevCourse == NULL)
	      {
	         current->headCourse = currCourse->nextCourse;
	      }
	      else
	      {
	         prevCourse->nextCourse = currCourse->nextCourse;
	      }

	      current->numCourses = number--;
	      printf("Course %s - %s deleted ", id, title);


}
     
     
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
     FILE *program1;
     FILE *course1;
     ProgramType *current;
     CourseType *currCourse;
     CourseType *prevCourse;
     char title[10];
     char prevTitle[10];
     int i=0,j=0;
     int number;

     current=pms->headProgram;
     
     printf("\n Detailed Program Report\n");
     printf("--------------------\n");
     printf("\n");

     program = fopen(programFile, "r");
     /*program1= fopen("ProgTest1.txt", "w");*/


     while(current != NULL)
	{
		for(i=0;i<=(pms->numPrograms);i++)
		{
			sprintf(title,"P%04d",i);
			
				if(strcmp(title,current->progID)==0)
				{
				fprintf(program,"%s|",current->progID);
				fprintf(program,"%s|",current->progType);
				fprintf(program,"%s|",current->progTitle);
				fprintf(program,"%s|",current->progMajor);
				fprintf(program,"%s|",current->progCode);
				fprintf(program,"%.1f|",current->progDuration);
				fprintf(program,"%s|",current->progMode);
				fprintf(program,"%s|",current->progDescription);
				fprintf(program,"%s",current->majorDescription);
				}			
			
		}
		current = current->nextProgram;
	}

       printf("\nProgram file has been created.\n");
    
    fclose(program);
    
 /*   
   	for(current = pms->headProgram; current != NULL; current = current->nextProgram)
	{
	      number += current->numCourses;
	}
    
    currCourse = current->headCourse;
    prevCourse = NULL;
    
    course = fopen(courseFile, "r");
    course1= fopen("courseTest1.txt", "w");
    
    while(current != NULL)
	{
		for(i=0;i<=number;i++)
		{
			sprintf(title,"C%04d",i);
			
				if(strcmp(title,current->courseID)==0)
				{
				fprintf(course1,"%s|",current->courseID);
				fprintf(course1,"%s|",current->progID);
				fprintf(course1,"%s|",current->courseTitle);
				fprintf(course1,"%s|",current->courseCode);
				fprintf(course1,"%.1f|",current->creditPoints);
				fprintf(course1,"Semester,%s,",current->teachPeriods[0].semester);
				fprintf(course1,"%s|",current->teachPeriods[1].semester);
				fprintf(course1,"%s|",current->courseDescription);
				}			
			currCourse = current->nextCourse;
		}
		current = current->nextProgram;
	}

       printf("\nCourse file has been created.\n");
    
    fclose(course);
    */
}
