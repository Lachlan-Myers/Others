/*********************************************************************************
* COSC1283/1284 - Programming Techniques
* Semester 1 2010 Assignment #1 
* Full Name        : Lachlan Cameron Myers
* Student Number   : s3167356
* Yallara Username : s3167356
* Course Code      : COSC1284
* Program Code     : BP094
* Start up code provided by Christopher Hoobin, John Thangarajah, and Xiaodong Li
*********************************************************************************/

#include "assign1.h"


#define OPTION_BUF_SIZE 10

int main(void)
{
    char buffer[OPTION_BUF_SIZE + EXTRA_SPACES];
    char buffer2[OPTION_BUF_SIZE + EXTRA_SPACES];
    int optionStats[NUM_OPTION_STATS] = {0};
    int loop = 1;
    int fibbInc = optionStats[1];
   
   while(loop==1)
   {
   printf("\tMain Menu:\n"); 
   printf("----------------------\n");
   printf("1) Translating alphabetic number\n");
   printf("2) Fibonacci numbers\n");
   printf("3) Sort line\n");
   printf("4) Find substrings\n");
   printf("5) Bushfire spread simulation\n");
   printf("6) Session summary\n");
   printf("7) Exit\n");
   printf("Select your option:\n");
   
   fgets(buffer, MAX_OPTION_INPUT + EXTRA_SPACES, stdin);
   printf("\n\n\n");
   
   switch(atoi(buffer))
   {
                 
          case 1: 
                  printf("Translating alphabetic numbers\n");
                  printf("------------------------------------\n");
                  printf("Enter in a letter:\n");
                  fgets(buffer, OPTION_BUF_SIZE + EXTRA_SPACES, stdin);
                  translateNumber(&optionStats[0], buffer);         
                  break;
                  
          case 2:
                  printf("Fibonacci Numbers\n");
                  printf("-----------------------\n");
                  printf("Enter in a number: \n");
                  fgets(buffer, OPTION_BUF_SIZE + EXTRA_SPACES, stdin);
                  printf("\n");
                  fibonacciNumbers(&fibbInc, atoi(buffer));
                  printf("\n");
                  break;
                  
          case 3:
                  printf("Sort Line\n");
                  printf("------------------\n");
                  printf("\n");
                  printf("Enter a string (1-40 characters): ");
                  fgets(buffer, OPTION_BUF_SIZE + EXTRA_SPACES, stdin);
                  printf("\n");
                  sortLine(&optionStats[2],buffer);
                  break;
                  
          case 4: 
                  printf("Find substring\n");
                  printf("--------------------------\n");
                  printf("\n");
                  printf("Enter in a string (1-20 characters): ");
                  fgets(buffer, OPTION_BUF_SIZE + EXTRA_SPACES, stdin);
                  printf("\n");
                  printf("Enter in a substring (1-20 characters): ");
                  fgets(buffer2, OPTION_BUF_SIZE + EXTRA_SPACES, stdin);
                  findSubstring(&optionStats[3],buffer,buffer2);
                  break;
                  
          case 7:
                  exit(-1);
                  break;
   }
}
   system("PAUSE");
   return EXIT_SUCCESS;
}
