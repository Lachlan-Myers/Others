/*********************************************************************************
* COSC1283/1284 - Programming Techniques
* Semester 1 2010 Assignment #1 
* Full Name        : Lachlan Cameron Myers
* Student Number   : s3167356
* Yallara Username : s3167356
* Course Code      : COSC1284
* Program Code     : BP094
* Start up code provided by Christopher Hoobin, John Thangarajah, and Xiaodong Li
* Some codes are adopted here with permission by an anonymous author
*********************************************************************************/

/* Header files. */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Constants. */
#define NUM_OPTION_STATS 6

/* This is used to compensate for the extra character spaces taken up by
   the '\n' and '\0' when user is asked for input through fgets(). */
#define EXTRA_SPACES 2

/* Specifies the maximum input length a user can enter for the options
   menu. */
#define MAX_OPTION_INPUT 1

/* Specifies the maximum string size for "findSubstring" function */
#define MAX_STRING_SIZE 20

/* Used for input validation. */
#define TRUE 1
#define FALSE 0

/* Function prototypes. */
void translateNumber(int *, char *);
void fibonacciNumbers(int *, int);
void sortLine(int *, char *);
void findSubstring(int *, char *, char *);
void sessionSummary(int *);

/* Following are used by function bushfireSpread() in menu option 4 */
#define Side   (25)    /* matrix side */
#define Tree   'T'   
#define NoTree ' '              
#define Burn   '*'

int bushfireSpread(char forest[ ][ Side ], float density );

/* Following are sub routines called from within bushfireSpread() */
/* Some of the functions are to be developed by you */
int ran( int k );
int get_density( void );
int plant_forest( char forest[ ][ Side ], float density );
int burning( char forest[ ][ Side ], int row, int col );
int go_again( void );

void report( char forest[ ][ Side ], int density, int count );
void print_border( void );
void display( char forest[ ][ Side ], char msg[ ] );
void percolate( char forest[ ][ Side ] );

 
/* My defined utility function prototypes. */
void readRestOfLine();
