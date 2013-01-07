/* This is a test program for bushfireSpread(). It is provided for your
 * information and to understand how the bushfireSpread() function is
 * invoked.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "assign1.h"

int main()
{
  char forest[ Side ][ Side ];
  int density, count;
  bushfireSpread( forest, density);
  return EXIT_SUCCESS;

} 
  
/* Function bushfireSpread(.) will simulate bushfire spread. A forest is modeled 
 * as a square point lattice in which each lattice point represents either a 
 * tree or a treeless location. For detailed information, see Requirement#5.
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
    scanf( "%d", &density );  
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
  scanf( "%s", ans ); /* replace scanf with something else that is better */
  return 'Y' == ans[ 0 ] || 'y' == ans[ 0 ];
}
