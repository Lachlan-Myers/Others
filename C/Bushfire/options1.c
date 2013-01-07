/********************************************************************************
* COSC1283/1284 - Programming Techniques
* Semester 1 2010 Assignment #1 
* Full Name        : Lachlan Cameron Myers
* Student Number   : s3167356
* Yallara Username : s3167356
* Course Code      : COSC1284
* Program Code     : BP094
* Start up code provided by Christopher Hoobin, John Thangarajah, and Xiaodong Li
* Some codes are adopted here with permission by an anonymous author
********************************************************************************/

#include "assign1.h"

/* This source file contains important functions to be developed and
 * used by various menu options, as indicated. Note that every
 * function has as its first parameter the optionsStats array, which
 * will be appropriately updated for later reporting of menu option 6.
 * You may ignore this parameter and its relevance to each function
 * until you develop the sessionSummary() function.
 */


/* See requirement #2  "Translating alphabetic number" of the 
 * assignment specs. 
 */
void translateNumber(int * optionStats, char * phoneNumber)
{
     /*char neeto[2] = phoneNumber;
     char letter=tolower(phoneNumber[0]);
     printf("%c",*phoneNumber);
     printf("%c",phoneNumber[strlen(phoneNumber)-2]);
     printf("DIE:%c", phoneNumber[strlen(phoneNumber)-1]);*/
     int i=0;
     
     if(phoneNumber[strlen(phoneNumber)-1] != '\n')
     {
       for(i=0;i<strlen(phoneNumber);i++)
        {
         printf("%c",phoneNumber[i]);
        }
     }
     else
     {
         /*printf("%d %d %d", phoneNumber[1], '\n', '\0');*/
         phoneNumber[strlen(phoneNumber)-1]='\0';
     }
     printf("\n");
      
    /* if(letter =='0')
     {
           strcat(num[0],'0');
     }
     else if(letter =='1')
     {
           num='1';
     }
     else if(letter == 'a'||letter=='b'|| letter =='c')
     {
            num='2';   
     }
     else if(letter == 'd'||letter=='e'|| letter =='f')
     {
            num='3';   
     }
     else if(letter == 'g'||letter=='h'|| letter =='i')
     {
            num='4';   
     }
     else if(letter == 'j'||letter=='k'|| letter =='l')
     {
            num='5';   
     }
     else if(letter == 'm'||letter=='n'|| letter =='o')
     {
            num='6';   
     }
     else if(letter == 'p'||letter=='q'|| letter =='r'|| letter =='s')
     {
            num='7';   
     }
     else if(letter == 't'||letter=='u'|| letter =='v')
     {
            num='8';   
     }
     else if(letter == 'w'||letter=='x'|| letter =='y'|| letter =='z')
     {
            num='9';   
     }
     else
     {
         printf("ERROR\n");
     }
     
     for(i=0;i<num[0];i++)
     {
        printf("%c",num[i]);
     }*/
     
}


/* See requirement #3  "Fibonacci numbers" of the assignment specs. 
 */
void fibonacciNumbers(int * optionStats, int num)
{     
     int i=0,a=0,b=1,n=0;
     int loop=(num-2);
     printf("Number: "); 
     for(i=1;i<=num;i++)
     {                               
             printf("%d ",i);
     }     
     printf("\n--------------------------------------\n"); 
     printf("Fib: 0 1 ");
      do{          
          if(loop==0)
          {
               
          }
          else
          {
              n=a+b;
              printf("%d ",n);
              a=b;
              b=n;
              i++;
              loop-=1;
          }
      }
      while(loop!=0);
      printf("\n"); 
     optionStats+=1;
}


/* See requirement #4  "Sort line" of the assignment specs. 
 */
void sortLine(int * optionStats, char * line)
{
     int i=0;
     int j=0;
     char temp;
     char result[strlen(line)];

     for(i=0;i<strlen(line);i++)
     {         
         if(line[i]=='\n')
         {
            line[i]='\0'; 
         }
         else
         { 
               j=0;
               int p=0;
             do
             {
                  line[i] = tolower(line[i]);
                  line[i+1] = tolower(line[i+1]);
                  
                  if(line[j]=='\n'||line[i+1]=='\n')
                  {
                     line[j]=='\0';              
                     j=strlen(line);
                  }
                  else if(line[i]>line[j])
                  {
                     temp=line[i];
                     line[i]=line[i+1];
                     line[i+1]=temp;
                  }
                  else
                  {
                       
                        /*if(line[j]=='1'||line[j]=='2'||line[j]=='3'||line[j]=='4'||
                            line[j]=='5'||line[j]=='6'||line[j]=='7'||line[j]=='8'||
                            line[j]=='9'||line[j]=='['||line[j]==']')*/

                        if(line[i+1]=='1'||line[i+1]=='2'||line[i+1]=='3'||line[i+1]=='4'||
                            line[i+1]=='5'||line[i+1]=='6'||line[i+1]=='7'||line[i+1]=='8'||
                            line[i+1]=='9'||line[i+1]=='['||line[i+1]==']')
                       {                      
                           memset(line[j],0,sizeof(line[j]));                          
                       }
                  }
                  
                  j++;
                  p++;
             }while(j<strlen(line));

         }
     }

     printf("\nOutput: %s\n",line);
     
     
     
     
     
/*     for(i=0;i<strlen(line);i++)
     {         
         if(line[i]!='\n')
         {
            line[i] = tolower(line[i]);
            line[i+1] = tolower(line[i+1]);           
            
            if(line[i+1]=='\n')
            {
                line[i+1]='\0';
                CBA123fde[]aa
                do
                {
                   if(line[i]>line[j])
                   {
                       temp=line[i];
                       line[i]=line[j];
                       line[j]=temp;
                
                       printf("\n   %c  -line i ",line[i]);
                       printf("\n   %c  -linej ",line[j]);
                       printf("\n %d - j",j);
                   }
                   else
                   {
                       
                   }
                   
                   j++;
                }while(j<=strlen(line));
            }
            else if(line[i]>line[i+1])
            {       
                temp=line[i];
                line[i]=line[i+1];
                line[i+1]=temp;
                
                printf("%c",line[i]);
            }
            
            else
            {
                printf("%c",line[i]);
            }
            
         }
         else
         {
             line[i]='\0';
         }
     }*/

}


/* See requirement #5  "Find substring" of the assignment specs.  
 * you must implement your own version of the strstr() standard library function. 
 * You cannot use this function or its equivalents in your solution.
 */
void findSubstring(int * optionStats, char * string, char * substring)
{
     /*strstr() is here due to confusion as to where it should go*/
     int i=0;
     int j=0;
     
     printf("The Output is: \" ");

     for(i=0;i<strlen(string);i++)
     {
         if(string[i]!='\n')
         {
             for(j=0;j<strlen(substring);j++)
             {
                  if(substring[j]!='\n')
                  {
                       if(string[i]==substring[j])
                       {
                            printf("%c",string[i]);
                            /*printf("string= %c\n",string[i]);
                            printf("substring = %c\n",substring[j]);*/
                       }
                  }
                  else
                  {
                      substring[j]='\0';
                  }
              }
         }
         else
         {
             string[i]='\0';
         }                        
                                  
     }
     printf(" \" is a substring of \"%s\" ",string);
     printf("\n");
}


/********************************************************************************
* Beginning of the start up code for requirement #6 - Bushfire spread simulation.
********************************************************************************/ 

/* Function bushfireSpread() simulates a bushfire spread. A forest is modeled 
 * as a square point lattice in which each lattice point represents either a 
 * tree or a treeless location. For detailed information, see Requirement#6.
 * This function will be used in menu option 5.
 */  
int bushfireSpread(char forest[ ][ Side ], float density )
{
  int count; /* count of trees in forest */
  
  do {  
    /* Prompt for density percentage. */
    density = get_density();
    /* Initialize forest cells to Tree or NoTree. */ 
    count = plant_forest( forest, density); 
    /* Display forest in initial state. */
    display( forest, "Initial state" );
    /* Run simulation. */
    percolate( forest );
    /* Display forest in final state. */
    display( forest, "Final state" );
    /* Report statistics. */
    report( forest, density, count );
  } while ( go_again() );
  
  return 0;
}


/* Return a random integer in the range 1..k. */
int ran( int k )
{
  double x = RAND_MAX + 1.0;
  return 1 + rand() * ( k / x );  
}   
  
/* Get tree density from user, in range 1..100. 
 * A density of 100 means that a tree occupies
 * a spot with a probability of 1.0. 
 */
int get_density( void )
{  
  int density;
  do { 
    printf( "\n\tDensity (range is 1 thru 100): " );
    scanf( "%d", &density );  /* Replace scanf with something else */
  } while ( density < 1 || density > 100 );
  return density;
}
        
/* Set forest cell to Tree or NoTree, depending on whether a randomly
 * generated integer equals or exceeds the density probability.
 */
int plant_forest( char forest[ ][ Side ], float density )
{
  int i, j; 
  int val; 
  int count = 0;
  for ( i = 0; i < Side; i++ )
    for ( j = 0; j < Side; j++ ) {
      val = ran( 100 ); 
      if ( val > density )
        forest[ i ][ j ] = NoTree;
      else {
        forest[ i ][ j ] = Tree;
        count++;
      }
    }
  return count;  /* count of trees in forest */
} 
  
/* Print report on forest before and after fire percolates.
 * Contrast requested with actual tree density.
 */
void report( char forest[ ][ Side ], int density, int count )
{             
  /* Require code to be developed by you! */
}   

/* Display a horizontal border. */
void print_border( void )
{
  int i;         
  printf( "\n" );
  for ( i = 0; i < Side; i++ )
    printf( "+-" );
  printf( "+" );
}                  

/* Display forest. */
void display( char forest[ ][ Side ], char msg[ ] )
{
  int i, j;                                   
  
  printf( "\n\t\t%s\n", msg );
  print_border();
  
  for ( i = 0; i < Side; i++ ) {
    printf( "\n" );
    for ( j = 0; j < Side; j++ )
      printf( "|%c", forest[ i ][ j ] ); 
    printf( "|" ); 
    print_border();
  } 
  printf( "\n" );
} 
 
/* Determine whether a given tree's immediate neighbors in the
 * preceding row are burning. 
 */    
int burning( char forest[ ][ Side ], int row, int col )
{
  /* Require code to be developed by you! */
  
  /* Return a flag to indicate burning or not */
  /* This can be either TRUE or FALSE */
}

/* Spread the fire across all rows */
void percolate( char forest[ ][ Side ] )
{        
  /*int i, j; */ 
  /* Declare other variables as needed */
    
  /* The next 2 comments require code to be developed by you! */
  
  /* Ignite first row of trees. */
         
  /* Percolate fire through remaining rows. */
  /* Hint: consider here to use function burning() to determine */
  /* if a forest cell is burning or not */
}
           
/* Check whether user wants to continue. */
int go_again( void )
{
  char ans[ 10 ];
  printf( "\n\n\t\tAgain? (y/n) " );
  scanf( "%s", ans ); /* Replace scanf with something else */
  return 'Y' == ans[ 0 ] || 'y' == ans[ 0 ];
}

/***************************************************************************
* End of the start up code for option 4 - Bushfire spread simulation.
***************************************************************************/ 


/* See requirement #7  "Session summary" of the assignment specs. 
 * Function sessionSummary() reports the program usage.  
 */
void sessionSummary(int * optionStats)
{
}
