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

int main(int argc, char* argv[])
{
   PMSType pms;
   
   int initFlag, dataFlag;
   char input[4];
   int loop=0;
   
   if(argc!=3)
   {
              printf("Critical FAIL\n");
   }


   /* Initialise the program management system to a safe empty state. */
   initFlag = systemInit(&pms);
   
   /* Populate the program management system with data from the data files. */
   /* Uncomment this line when you are ready to use command line arguments: */
   dataFlag = loadData(&pms, argv[1], argv[2]);
   
   /* Testing to see if both systemInit(.) and loadData(.) are ok */
   if (initFlag == FAILURE || dataFlag == FAILURE)
     exit(EXIT_FAILURE); 
     
   /* Interactive menu providing user with access to the 9 menu options */
    while(loop==0) 
    {
    	printf("\nWelcome!\n");
    	printf("Main Menu:\n");
    	printf("1) Summary of Undergrad Programs\n");
    	printf("2) Summary of Postgrad Programs\n");
    	printf("3) Detailed Program Report\n");
    	printf("4) Add Program\n");
    	printf("5) Delete Program\n");
    	printf("6) Add Course\n");
    	printf("7) Delete Course\n");
    	printf("8) Save & Exit\n");
    	printf("9) Abort\n");
    	printf("\n");
    	printf("Select your option (1-9):\n");
    	fgets(input,4,stdin);

    	switch(atoi(input))
    	{
		case 1:
			displaySummary(&pms, "UG");
			break;
		
		case 2:
			displaySummary(&pms, "PG");
			break;
			
		case 3:
            programReport(&pms);
            break; 
            
        case 4:
             addProgram(&pms);
             break;
             
        case 5:
             deleteProgram(&pms);
             break;
             
        case 6:
             addCourse(&pms);
             break;
        
        case 7:
             deleteCourse(&pms);
             break;
             
        case 8:
             saveData(&pms, argv[1], argv[2]);
             loop=1;
             break;

		case 9:
			loop=1;
			/*exit(0);*/
			break;
    	}
    }


   /* Deallocate all dynamically allocated memory. */
   systemFree(&pms);
 
   exit(EXIT_SUCCESS);

}
