#!/usr/bin/env perl -w
use strict;
use warnings;
use LWP::Simple;
use XML::Parser;


my $test;
#$test = get("http://www.rmit.edu.au");
#$test = LWP::Simple->get("http://www.engadget.com/rss.xml");
#print $test;


#my $title = 'Title';
#my $creator = 'Creator';
#my $published = '12/12/12';
#my $content = 'Content';
#$~ = "rss";
#format rss =
#	Title: @<<<<<<<<<<<<<<<<<<<<	Author: @||||||||||||||||||||	
#	$title,$creator,
#	Date Published: @>>>>>>>
#	$published, 
#	Content: @<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
#	$content
#.

$test = get($ARGV[0]);

if(!defined($test))
{
	#print $ARGV[0];
	print "\nAddress is non-existant or invalid\n";
}
else
{
	print "hello\n";
	#foreach(@ARGV)
	#{

#		print $test;
		#getprint(@ARGV);






	#}
}


#get address

#validate address

#display content

