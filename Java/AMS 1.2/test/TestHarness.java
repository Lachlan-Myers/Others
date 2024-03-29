package ams.test;

/**
 * <b>Programming 2, Assignment 1B, 2009</b><br/>
 * 
 * This class will test the AMS system.
 * 
 * USAGE -----
 * 
 * Run it for an interactive test menu.
 * 
 * Test #13 ("Test all") will dump any debugging output made by the test
 * program, your code, and the JRE, into a text file named "tests.txt".
 * 
 * It will also estimate the completeness of the "functionality" section of the Assignment.
 * 
 * The test #13 can be invoked from the command line with the "all" argument.
 * 
 */

import java.io.*;
import java.util.*;
import ams.model.*;
import ams.model.exception.*;
import ams.model.facade.*;

/**
 * @author Mikhail Perepletchikov 03/08/2009
 */

public class TestHarness {

	private static final String P1 = "COSC1073";
	private static final String P2 = "COSC1076";
	private static final String MATHS = "MATH1074";
	private static final String COMP_THEORY = "COSC1107";
	private static final String MAD = "COSC2309";
	private static final String DISTRIBUTED = "COSC1197";
	private static final String ADV_DISTRIBUTED = "ISYS2403";
	private static final String ADV_DISTRIBUTED2 = "ISYS2222";

	private static AMSModel model;

	private static BufferedReader stdin = new BufferedReader(
			new InputStreamReader(System.in));

	private static Course coreCourse1, coreCourse2, coreCourse3, coreCourse4;
	private static Course electiveCourse1, electiveCourse2, electiveCourse3, electiveCourse4;

	// use AMS engine (model) to initialise the University with a default
	// Program
	public static void initialiseEngine() {
		model = new AMSFacade();
		model.addProgram(new Program("BP062", "Bachelor of Computer Science"));
		System.out.println("Initialised AMS engine with Program "
				+ model.getProgram().toString());
	}

	public static void initialiseCourses() {
		// create sample courses
		coreCourse1 = new CoreCourse(P1, "Programming 1", null);
		coreCourse2 = new CoreCourse(MATHS, "Mathematics for Computing", null);
		coreCourse3 = new CoreCourse(P2, "Programming 2", new String[] { P1 });
		coreCourse4 = new CoreCourse(COMP_THEORY, "Computing Theory",
				new String[] { P2, MATHS });

		electiveCourse1 = new ElectiveCourse(MAD,
				"Mobile Application Development", 6, new String[] { P2 });
		electiveCourse2 = new ElectiveCourse(DISTRIBUTED,
				"Distributed Systems", 12, new String[] { COMP_THEORY, MAD });
		electiveCourse3 = new ElectiveCourse(ADV_DISTRIBUTED,
				"Advanced Topics in Distributed Systems", 12,
				new String[] { DISTRIBUTED });
	}

	// this method is used only when checking the overload condition upon course enrollment
	public static void initialiseCoursesWithNoPrereqs() {
		// create sample courses
		coreCourse1 = new CoreCourse(P1, "Programming 1", null);
		coreCourse2 = new CoreCourse(MATHS, "Mathematics for Computing", null);
		coreCourse3 = new CoreCourse(P2, "Programming 2", null);
		coreCourse4 = new CoreCourse(COMP_THEORY, "Computing Theory",null);
		electiveCourse1 = new ElectiveCourse(MAD, "Mobile Application Development", 6, null);
		electiveCourse2 = new ElectiveCourse(DISTRIBUTED, "Distributed Systems", 12, null);
		electiveCourse3 = new ElectiveCourse(ADV_DISTRIBUTED, "Advanced Topics in Distributed Systems", 12,null);
		electiveCourse4 = new ElectiveCourse(ADV_DISTRIBUTED2, "Advanced Topics in Distributed Systems 2", 12,null);
	}
	// ************************************************************************
	// ************************************************************************
	// ************************************************************************
	// ************************************************************************
	// ************************************************************************

	// BE VERY CAREFUL CHANGING ANYTHING PAST HERE

	// You may add your own lines of code while debugging
	// your work, but DO NOT change the existing code ...

	// YOUR SYSTEM MUST BE ABLE TO WORK WITH ANYTHING AFTER
	// THIS POINT, LEFT UNMODIFIED !!

	// ************************************************************************
	// ************************************************************************
	// ************************************************************************
	// ************************************************************************
	// ************************************************************************

	// --- test1 ----------------------------------------------------------
	// Check that Student and Program are represented correctly
	public static boolean test1() throws Exception {
		initialiseEngine();
		System.out.println("Adding UG student ...");
		model.addStudent(new UGStudent(3000001, "Joe Bloggs"));
		// check UG student string representation
		String s = model.getStudent().toString();
		System.out.print("Student string is: " + s);
		if (s.indexOf("3000001:Joe Bloggs:60") != 0) {
			System.out.println(" ... not correct");
			return false;
		} else {
			System.out.println(" ... correct");
		}

		// initialiseEngine();
		System.out.println("Replacing UG student with PG student ...");
		model.addStudent(new PGStudent(3000002, "Fred Bloggs"));
		// check PG student string representation
		s = model.getStudent().toString();
		System.out.print("Student string is: " + s);
		if (s.indexOf("3000002:Fred Bloggs:48") != 0) {
			System.out.println(" ... not correct");
			return false;
		} else {
			System.out.println(" ... correct");
		}
		// check Program string representation
		s = model.getProgram().toString();
		System.out.print("Program string is: " + s);
		if (s.indexOf("BP062:Bachelor of Computer Science") != 0) {
			System.out.println(" ... not correct");
			return false;
		} else {
			System.out.println(" ... correct");
			return true;
		}
	}

	// --- test2 ----------------------------------------------------------
	// Test course creation, addition and lookup
	public static boolean test2() throws Exception {
		initialiseEngine();

		String[] expected = { "COSC1073:Programming 1:12:CORE",
				"MATH1074:Mathematics for Computing:12:CORE",
				"COSC1076:Programming 2:12:COSC1073:CORE",
				"COSC2309:Mobile Application Development:6:COSC1076:ELECTIVE" };
		try {
			System.out.println("Creating example courses ... ");
			initialiseCourses();

			System.out.print("Adding first core course...");
			model.addCourse(coreCourse1);
			System.out.print("Adding second core course...");
			model.addCourse(coreCourse2);
			System.out.print("Adding third core course...");
			model.addCourse(coreCourse3);
			System.out.println("Adding elective course...");
			model.addCourse(electiveCourse1);

			// find first core course
			System.out.println("Finding first core course with getCourse()...");
			if (failureCheck(model.getCourse(P1).toString().equalsIgnoreCase(
					expected[0]), true))
				return false;
			// find second core course
			System.out
					.println("Finding second core course with getCourse()...");
			if (failureCheck(model.getCourse(MATHS).toString()
					.equalsIgnoreCase(expected[1]), true))
				return false;
			// find third core course
			System.out.println("Finding third core course with getCourse()...");
			if (failureCheck(model.getCourse(P2).toString().equalsIgnoreCase(
					expected[2]), true))
				return false;
			// find elective course
			System.out.println("Finding elective course with getCourse()...");
			if (failureCheck(model.getCourse(MAD).toString().equalsIgnoreCase(
					expected[3]), true))
				return false;

			// find all courses included in the program
			System.out.print("Checking courses with getAllCourses()...");
			if (failureCheck(checkForCourses(model.getAllCourses(), expected),
					true))
				return false;

		} catch (AMSException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	// --- test3 ----------------------------------------------------------
	// Test course removal
	public static boolean test3() throws Exception {
		initialiseEngine();

		String[] expected = { "COSC1073:Programming 1:12:CORE" };
		try {
			System.out.println("Creating example courses ... ");
			initialiseCourses();
			System.out.print("Adding first course...");
			model.addCourse(coreCourse1);
			System.out.print("Adding second course...");
			model.addCourse(coreCourse3);
			System.out.println("Adding third course...");
			model.addCourse(electiveCourse1);

			System.out.print("Removing third course with removeCourse()...");
			model.removeCourse(MAD);
			System.out.print("Removing second course with removeCourse()...");
			model.removeCourse(P2);

			System.out.print("Checking courses with getAllCourses()...");
			if (failureCheck(checkForCourses(model.getAllCourses(), expected),
					true))
				return false;

			System.out.println("Removing first course with removeCourse()...");
			model.removeCourse(P1);

			System.out.println("Checking course with getCourse()...");
			if (model.getCourse(P1) != null)
				return false;
			System.out.println("Checking courses with getAllCourses()...");
			if (model.getAllCourses() != null)
				return false;

		} catch (AMSException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	// --- test4 ----------------------------------------------------------
	// Adding courses - program feasibility check
	public static boolean test4() throws Exception {
		initialiseEngine();
		String[] expected = { "COSC1073:Programming 1:12:CORE" };
		try {
			System.out.println("Creating example courses ... ");
			initialiseCourses();

			System.out.println("Adding supported course...");
			model.addCourse(coreCourse1);

			// try adding unsupported courses (the required prereqs are not in
			// the program).
			try {
				System.out.println("Adding unsupported core course...");
				model.addCourse(coreCourse4);
			} catch (ProgramException e) {
				System.out.println(e.getMessage() + "  ... correct");
			}

			try {
				System.out.println("Adding unsupported elective course...");
				model.addCourse(electiveCourse2);
			} catch (ProgramException e) {
				System.out.println(e.getMessage() + "  ... correct");
			}

			System.out.print("Checking courses with getAllCourses()...");
			if (failureCheck(checkForCourses(model.getAllCourses(), expected),
					true))
				return false;

		} catch (AMSException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	// --- test5 ----------------------------------------------------------
	// Removing courses - program feasibility check
	public static boolean test5() throws Exception {
		initialiseEngine();

		String[] expected = { "COSC1073:Programming 1:12:CORE",
				"COSC1076:Programming 2:12:COSC1073:CORE",
				"COSC2309:Mobile Application Development:6:COSC1076:ELECTIVE" };
		try {
			System.out.println("Creating example courses ... ");
			initialiseCourses();

			System.out.print("Adding first core course...");
			model.addCourse(coreCourse1);
			System.out.print("Adding second core course...");
			model.addCourse(coreCourse3);
			System.out.println("Adding elective course...");
			model.addCourse(electiveCourse1);

			// try removing prerequisite courses (expect to catch
			// ProgramException)
			try {
				System.out.println("Removing first prerequisite course ...");
				model.removeCourse(P1);
			} catch (ProgramException e) {
				System.out.println(e.getMessage() + "  ... correct");
			}

			try {
				System.out.println("Removing second prerequisite course ...");
				model.removeCourse(P2);
			} catch (ProgramException e) {
				System.out.println(e.getMessage() + "  ... correct");
			}

			System.out.print("Checking courses with getAllCourses()...");
			if (failureCheck(checkForCourses(model.getAllCourses(), expected),
					true))
				return false;

		} catch (AMSException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	// --- test6 ----------------------------------------------------------
	// Enrolling student into a course
	public static boolean test6() throws Exception {
		initialiseEngine();
		System.out.print("Adding UG student ...");
		model.addStudent(new UGStudent(3000001, "Joe Bloggs"));

		String[] expected = { "COSC1073:Programming 1:12:CORE" };
		String[] expected1 = { "MATH1074:Mathematics for Computing:12:CORE" };
		initialiseCourses();
		try {
			// check basic enrollment procedure for UG student
			System.out.print("Adding first core course...");
			model.addCourse(coreCourse1);
			System.out.println("Enrolling UG student into course...");
			model.enrollIntoCourse(P1);
			System.out
					.print("Checking enrolled courses with getCurrentEnrollment() ...");
			if (failureCheck(checkForCourses(model.getCurrentEnrollment(),
					expected), true))
				return false;

			// check basic enrollment procedure for PG student
			System.out.println("Replacing UG student with PG student ...");
			model.addStudent(new PGStudent(3000002, "Fred Bloggs"));
			System.out.print("Adding second core course...");
			model.addCourse(coreCourse2);
			System.out.println("Enrolling PG student into course...");
			model.enrollIntoCourse(MATHS);
			System.out
					.print("Checking enrolled courses with getCurrentEnrollment() ...");
			if (failureCheck(checkForCourses(model.getCurrentEnrollment(),
					expected1), true))
				return false;
		} catch (AMSException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	// --- test7 ----------------------------------------------------------
	// Withdrawing student from a course
	public static boolean test7() throws Exception {
		initialiseEngine();
		model.addStudent(new UGStudent(3000001, "Joe Bloggs"));
		String[] expected = { "COSC1073:Programming 1:12:CORE" };
		initialiseCourses();

		try {
			System.out.print("Adding first course...");
			model.addCourse(coreCourse1);
			// try withdrawing from course without enrolling into it first
			// (expects to catch EnrollmentException)
			try {
				System.out.println("Withdraw from 'incorrect' course ...");
				model.withdrawFromCourse(P1);
			} catch (EnrollmentException e) {
				System.out.println(e.getMessage() + "  ... correct");
			}

			System.out.println("Enrolling student into course...");
			model.enrollIntoCourse(P1);
			System.out.print("Checking courses with getCurrentEnrollment() ...");
			if (failureCheck(checkForCourses(model.getCurrentEnrollment(),
					expected), true))
				return false;
			System.out.println("Withdraw from 'correct' course ...");
			model.withdrawFromCourse(P1);
			System.out.println("Checking courses with getCurrentEnrollment()...");
			// expecting to receive null (no currently enrolled courses)
			if (model.getCurrentEnrollment() != null)
				return false;

		} catch (AMSException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	// --- test8 ----------------------------------------------------------
	// Results management
	public static boolean test8() throws Exception {
		initialiseEngine();
		model.addStudent(new UGStudent(3000001, "Joe Bloggs"));
		initialiseCourses();
		String[] expected = { "COSC1073:FAIL" };
		String[] expected1 = { "COSC1073:PASS", "COSC1076:PASS" };

		try {
			System.out.print("Adding first course...");
			model.addCourse(coreCourse1);
			System.out.print("Adding second course...");
			model.addCourse(coreCourse3);

			System.out.println("Enrolling student into first course...");
			model.enrollIntoCourse(P1);

			System.out.print("Checking results with getResults() ...");
			// expecting to receive null (no results)
			if (model.getResults() != null)
				return false;
			else
				System.out.println(" OK ...");

			System.out.println("Adding FAIL result for the first enrolled course...");
			model.addResult(new Result(coreCourse1, false));

			System.out.print("Checking results with getResults() ...");
			if (failureCheck(checkForResults(model.getResults(), expected),
					true))
				return false;

			System.out.println("Re-enrolling student into first course...");
			model.enrollIntoCourse(P1);
			System.out.println("Adding PASS result for the first enrolled course...");
			model.addResult(new Result(coreCourse1, true));

			System.out.println("Enrolling student into second course...");
			model.enrollIntoCourse(P2);
			System.out.println("Adding PASS result for the second enrolled course...");
			model.addResult(new Result(coreCourse3, true));

			System.out.print("Checking results with getResults() ...");
			if (failureCheck(checkForResults(model.getResults(), expected1),
					true))
				return false;

			System.out.print("Adding result for invalid course ...");
			if (failureCheck(model.addResult(new Result(coreCourse1, true)),
					false))
				return false;

		} catch (AMSException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	// --- test9 ----------------------------------------------------------
	// Calculate current load
	public static boolean test9() throws Exception {
		initialiseEngine();
		model.addStudent(new UGStudent(3000001, "Joe Bloggs"));
		initialiseCourses();
		try {
			System.out
					.println("Checking current load points with calculateCurrentLoad()...");
			if (model.calculateCurrentLoad() != 0)
				return false;
			else
				System.out.println("Current load = "
						+ model.calculateCurrentLoad() + "; correct ...");
			System.out.print("Adding first course...");
			model.addCourse(coreCourse1);
			System.out.print("Adding second course...");
			model.addCourse(coreCourse2);
			System.out.println("Adding third course...");
			model.addCourse(coreCourse3);

			System.out.print("Enrolling student into first course...");
			model.enrollIntoCourse(P1);
			System.out.println("Enrolling student into second course...");
			model.enrollIntoCourse(MATHS);
			System.out.println("Checking current load points with calculateCurrentLoad()...");
			if (model.calculateCurrentLoad() != 24)
				return false;

			System.out.println("Adding PASS result for the first course...");
			model.addResult(new Result(coreCourse1, true));
			System.out.println("Checking current load points with calculateCurrentLoad()...");
			if (model.calculateCurrentLoad() != 12)
				return false;

			System.out.println("Withdraw from the second course ...");
			model.withdrawFromCourse(MATHS);
			System.out.println("Checking current load points with calculateCurrentLoad()...");
			if (model.calculateCurrentLoad() != 0)
				return false;

			System.out.println("Enrolling student into third course...");
			model.enrollIntoCourse(P2);
			System.out.println("Checking current load points with calculateCurrentLoad()...");
			if (model.calculateCurrentLoad() != 12)
				return false;

		} catch (AMSException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	// --- test10 ----------------------------------------------------------
	// Calculate career points
	public static boolean test10() throws Exception {
		initialiseEngine();
		model.addStudent(new UGStudent(3000001, "Joe Bloggs"));
		initialiseCourses();
		int expectedPoints = 42;

		try {
			System.out
					.println("Checking current career points with calculateCareerPoints()...");
			if (model.calculateCareerPoints() != 0)
				return false;
			else
				System.out.println("Career points = "
						+ model.calculateCareerPoints() + "; correct ...");
			System.out.print("Adding first course...");
			model.addCourse(coreCourse1);
			System.out.print("Adding second course...");
			model.addCourse(coreCourse2);
			System.out.print("Adding third course...");
			model.addCourse(coreCourse3);
			System.out.print("Adding fourth course...");
			model.addCourse(electiveCourse1);
			System.out.println("Adding fifth course...");
			model.addCourse(coreCourse4);

			System.out.print("Enrolling student into first course...");
			model.enrollIntoCourse(P1);
			System.out.println(" Adding PASS result for the first enrolled course...");
			model.addResult(new Result(coreCourse1, true));

			System.out.print("Enrolling student into second course...");
			model.enrollIntoCourse(MATHS);
			System.out.println(" Adding PASS result for the second enrolled course...");
			model.addResult(new Result(coreCourse2, true));

			System.out.print("Enrolling student into third course...");
			model.enrollIntoCourse(P2);
			System.out.println(" Adding PASS result for the third enrolled course...");
			model.addResult(new Result(coreCourse3, true));

			System.out.print("Enrolling student into fourth course...");
			model.enrollIntoCourse(MAD);
			System.out.println(" Adding PASS result for the fourth enrolled course...");
			model.addResult(new Result(electiveCourse1, true));

			System.out.print("Enrolling student into fifth course...");
			model.enrollIntoCourse(COMP_THEORY);
			System.out.println(" Adding FAIL result for the fifth enrolled course...");
			model.addResult(new Result(coreCourse4, false));

			System.out.println("Checking current career points with calculateCareerPoints() ...");
			if (model.calculateCareerPoints() != expectedPoints)
				return false;

		} catch (AMSException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	// --- test11 ----------------------------------------------------------
	// Enrollment constraints checking - UG student
	public static boolean test11() throws Exception {
		initialiseEngine();
		model.addStudent(new UGStudent(3000001, "Joe Bloggs"));
		boolean [] results = {false, false};
		try {			
			// ----- check overload constraint -----
			initialiseCoursesWithNoPrereqs();
			System.out.print("Adding first core course...");
			model.addCourse(coreCourse1);
			System.out.print("Adding second core course...");
			model.addCourse(coreCourse2);
			System.out.print("Adding third core course...");
			model.addCourse(coreCourse3);
			System.out.println("Adding fourth core course...");
			model.addCourse(coreCourse4);
			System.out.print("Adding first elective course...");
			model.addCourse(electiveCourse1);
			System.out.print("Adding second elective course...");
			model.addCourse(electiveCourse2);
			System.out.println("Adding third elective course...");
			model.addCourse(electiveCourse3);
			System.out.println("Enrolling student into first core course...");
			model.enrollIntoCourse(P1);
			System.out.println("Enrolling student into second core course...");
			model.enrollIntoCourse(MATHS);
			System.out.println("Enrolling student into third core course...");
			model.enrollIntoCourse(P2);
			System.out.println("Enrolling student into fourth core course...");
			model.enrollIntoCourse(COMP_THEORY);			
			System.out.println("Enrolling student into first elective course...");
			model.enrollIntoCourse(MAD);			
			// exception should be thrown due to overload
			try {
				System.out.println("Enrolling student into second elective course...");
				model.enrollIntoCourse(DISTRIBUTED);
			} catch (EnrollmentException e) {
				System.out.println(e.getMessage() + " correct");
				results[0] = true;
			}

			// ----- check prereqs constraint for core courses -----	
			model.addStudent(new UGStudent(3000001, "Joe Bloggs"));
			initialiseCourses();
			System.out.print("Adding first core course...");
			model.addCourse(coreCourse1);
			System.out.print("Adding second core course...");
			model.addCourse(coreCourse2);
			System.out.print("Adding third core course...");
			model.addCourse(coreCourse3);
			System.out.println("Adding fourth core course...");
			model.addCourse(coreCourse4);
			System.out.print("Enrolling student into first core course...");
			model.enrollIntoCourse(P1);
			System.out.println(" Adding PASS result for the first enrolled course...");
			model.addResult(new Result(coreCourse1, true));

			System.out.print("Enrolling student into second core course...");
			model.enrollIntoCourse(MATHS);
			System.out.println(" Adding FAIL result for the second enrolled course...");
			model.addResult(new Result(coreCourse2, false));

			// exception should be thrown because one of the prereqs is not passed 
			try {
				System.out
						.println("Enrolling student into third course...");				
				model.enrollIntoCourse(COMP_THEORY);
			} catch (EnrollmentException e) {
				System.out.println(e.getMessage() + "  ... correct");
				results[1] = true;
			}
			
			// ----- check prereqs constraint for elective courses -----	
			model.addStudent(new UGStudent(3000001, "Joe Bloggs"));
			initialiseCourses();
			System.out.print("Adding first core course...");
			model.addCourse(coreCourse1);
			System.out.println("Adding second core course...");
			model.addCourse(coreCourse3);
			System.out.print("Adding first elective course...");
			model.addCourse(electiveCourse1);
			System.out.println("Adding second elective course...");
			model.addCourse(electiveCourse2);
			
			System.out.print("Enrolling student into first core course...");
			model.enrollIntoCourse(P1);
			System.out.println(" Adding PASS result for the first enrolled course...");
			model.addResult(new Result(coreCourse1, true));

			System.out.print("Enrolling student into second core course...");
			model.enrollIntoCourse(P2);
			System.out.println(" Adding PASS result for the second enrolled course...");
			model.addResult(new Result(coreCourse3, true));
			System.out.print("Enrolling student into first elective course...");
			model.enrollIntoCourse(MAD);
			System.out.println(" Adding PASS result for the third enrolled course...");
			model.addResult(new Result(electiveCourse1, true));
			// exception should NOT be thrown because UG student only needs one prereq to be completed 
			try {
				System.out
						.println("Enrolling student into second elective course...");						
				model.enrollIntoCourse(DISTRIBUTED);
			} catch (EnrollmentException e) {
				System.out.println(e.getMessage() + "  ... not correct");
				return false;
			}
		} catch (AMSException e) {
			System.out.println(e.getMessage());
			return false;
		}
		//check that the exceptions were thrown correctly
		for (int i=0; i<results.length; i++)
		{
			if (results[i]!=true)
			{
				System.out.println("The exceptions are not thrown correctly");
				return false;
			}			
		}
		System.out.println("All exceptions are thrown correctly");
		return true;
	}

	// --- test12 ----------------------------------------------------------
	// Enrollment constraints checking - PG student
	public static boolean test12() throws Exception {
		initialiseEngine();
		model.addStudent(new PGStudent(3000002, "Fred Bloggs"));
		boolean [] results = {false, false, false, false};
		try {			
			// ----- check overload constraint -----
			initialiseCoursesWithNoPrereqs();
			System.out.print("Adding first core course...");
			model.addCourse(coreCourse1);
			System.out.print("Adding second core course...");
			model.addCourse(coreCourse2);
			System.out.print("Adding third core course...");
			model.addCourse(coreCourse3);
			System.out.println("Adding fourth core course...");
			model.addCourse(coreCourse4);
			System.out.print("Adding first elective course...");
			model.addCourse(electiveCourse1);
			System.out.print("Adding second elective course...");
			model.addCourse(electiveCourse2);
			System.out.println("Adding third elective course...");
			model.addCourse(electiveCourse3);
			System.out.println("Enrolling student into first core course...");
			model.enrollIntoCourse(P1);
			System.out.println("Enrolling student into second core course...");
			model.enrollIntoCourse(MATHS);
			System.out.println("Enrolling student into third core course...");
			model.enrollIntoCourse(P2);
			System.out.println("Enrolling student into fourth core course...");
			model.enrollIntoCourse(COMP_THEORY);	
			// exception should be thrown due to overload
			try {
				System.out.println("Enrolling student into first elective course...");
				model.enrollIntoCourse(MAD);	
			} catch (EnrollmentException e) {
				System.out.println(e.getMessage() + " ... correct");
				results[0] = true;
			}

			// ----- check prereqs constraint (1)  -----	
			model.addStudent(new PGStudent(3000002, "Fred Bloggs"));
			initialiseCourses();
			System.out.print("Adding first core course...");
			model.addCourse(coreCourse1);
			System.out.print("Adding second core course...");
			model.addCourse(coreCourse2);
			System.out.print("Adding third core course...");
			model.addCourse(coreCourse3);
			System.out.println("Adding fourth core course...");
			model.addCourse(coreCourse4);
			System.out.print("Enrolling student into first core course...");
			model.enrollIntoCourse(P1);
			System.out.println(" Adding PASS result for the first enrolled course...");
			model.addResult(new Result(coreCourse1, true));

			System.out.print("Enrolling student into second core course...");
			model.enrollIntoCourse(MATHS);
			System.out.println(" Adding FAIL result for the second enrolled course...");
			model.addResult(new Result(coreCourse2, false));
			// exception should be thrown because one of the prereqs is not passed 
			try {
				System.out.println("Enrolling student into third core course...");				
				model.enrollIntoCourse(COMP_THEORY);
			} catch (EnrollmentException e) {
				System.out.println(e.getMessage() + "  correct");
				results[1] = true;
			}
			
			// ----- check prereqs constraint (2)  -----	
			model.addStudent(new PGStudent(3000002, "Fred Bloggs"));
			initialiseCourses();
			System.out.print("Adding first core course...");
			model.addCourse(coreCourse1);
			System.out.println("Adding second core course...");
			model.addCourse(coreCourse3);
			System.out.print("Adding first elective course...");
			model.addCourse(electiveCourse1);
			System.out.println("Adding second elective course...");
			model.addCourse(electiveCourse2);
			
			System.out.print("Enrolling student into first core course...");
			model.enrollIntoCourse(P1);
			System.out.println(" Adding PASS result for the first enrolled course...");
			model.addResult(new Result(coreCourse1, true));

			System.out.print("Enrolling student into second core course...");
			model.enrollIntoCourse(P2);
			System.out.println(" Adding PASS result for the second enrolled course...");
			model.addResult(new Result(coreCourse3, true));
			System.out.print("Enrolling student into first elective course...");
			model.enrollIntoCourse(MAD);
			System.out.println(" Adding PASS result for the third enrolled course...");
			model.addResult(new Result(electiveCourse1, true));
			// exception should be thrown because PG student needs to complete ALL prereqs
			try {
				System.out.println("Enrolling student into second elective course...");						
				model.enrollIntoCourse(DISTRIBUTED);
			} catch (EnrollmentException e) {
				System.out.println(e.getMessage() + " ... correct");	
				results[2] = true;
			}
			
			// ----- check that the student is enrolled (or can still be enrolled) in at least one core course   -----	
			model.addStudent(new PGStudent(3000002, "Fred Bloggs"));
			initialiseCoursesWithNoPrereqs();
			System.out.print("Adding first elective course...");
			model.addCourse(electiveCourse1);
			System.out.println("Adding second elective course...");
			model.addCourse(electiveCourse2);
			System.out.print("Adding third elective course...");
			model.addCourse(electiveCourse3);
			System.out.println("Adding fourth elective course...");
			model.addCourse(electiveCourse4);
			
			System.out.print("Enrolling student into first course...");
			model.enrollIntoCourse(MAD);			
			System.out.print("Enrolling student into second  course...");
			model.enrollIntoCourse(DISTRIBUTED);			
			System.out.print("Enrolling student into third course...");
			model.enrollIntoCourse(ADV_DISTRIBUTED);
			
			// exception should be thrown because not enrolled in core course and after this enrollment,
			// the student won't be able to enrol  into a core course because of the overload issue
			try {
				System.out
						.println("Enrolling student into fourth course...");						
				model.enrollIntoCourse(ADV_DISTRIBUTED2);
			} catch (EnrollmentException e) {
				System.out.println(e.getMessage() + "  ... correct");	
				results[3] = true;
			}
			
		} catch (AMSException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		//check that the exceptions were thrown correctly
		for (int i=0; i<results.length; i++)
		{
			if (results[i]!=true)
			{
				System.out.println("The exceptions are not thrown correctly");
				return false;
			}			
		}
		System.out.println("All exceptions are thrown correctly");
		return true;
	}

	// --- test13 ----------------------------------------------------------
	public static void test13() {
		// boolean anyFail = false;
		int failedTests = 0;
		int completedFunctionality = 100;

		// TBA (these are not final .. will be changed for final marking)
		int[] testMarks = { 7, 8, 7, 10, 10, 8, 8, 8, 7, 7, 10, 10 };

		System.out.println("These tests failed: ");
		PrintStream realErr, realOut;

		PrintStream outFile = null;
		try {
			outFile = new PrintStream(new FileOutputStream(
					new File("tests.txt")));
		} catch (FileNotFoundException e) {
			System.out
					.println("test harness can't open a file for summarising");
			return;
		}

		realErr = System.err;
		realOut = System.out;
		System.setErr(outFile);
		System.setOut(outFile);

		// remember here that System.out and System.err are now the file
		// the real output streams are in realOut and realErr!
		try {
			System.out.println("--- Test 1 --------------------------");
			if (!test1()) {
				realOut.println("Test 1 **FAIL** (incorrect implementation)");
				failedTests++;
				completedFunctionality -= testMarks[0];
			}
		} catch (Exception e) {
			realOut.println("Test 1 **FAIL** (unexpected exception occurred)");
			failedTests++;
			completedFunctionality -= testMarks[0];
		}
		try {
			System.out.println("--- Test 2 --------------------------");
			if (!test2()) {
				realOut.println("Test 2 **FAIL** (incorrect implementation)");
				failedTests++;
				completedFunctionality -= testMarks[1];
			}
		} catch (Exception e) {
			realOut.println("Test 2 **FAIL** (unexpected exception occurred)");
			failedTests++;
			completedFunctionality -= testMarks[1];
		}
		try {
			System.out.println("--- Test 3 --------------------------");
			if (!test3()) {
				realOut.println("Test 3 **FAIL** (incorrect implementation)");
				failedTests++;
				completedFunctionality -= testMarks[2];
			}
		} catch (Exception e) {
			realOut.println("Test 3 **FAIL** (unexpected exception occurred)");
			failedTests++;
			completedFunctionality -= testMarks[2];
		}
		try {
			System.out.println("--- Test 4 --------------------------");
			if (!test4()) {
				realOut.println("Test 4 **FAIL** (incorrect implementation)");
				failedTests++;
				completedFunctionality -= testMarks[3];
			}
		} catch (Exception e) {
			realOut.println("Test 4 **FAIL** (unexpected exception occurred)");
			failedTests++;
			completedFunctionality -= testMarks[3];
		}
		try {
			System.out.println("--- Test 5 --------------------------");
			if (!test5()) {
				realOut.println("Test 5 **FAIL** (incorrect implementation)");
				failedTests++;
				completedFunctionality -= testMarks[4];
			}
		} catch (Exception e) {
			realOut.println("Test 5 **FAIL** (unexpected exception occurred)");
			failedTests++;
			completedFunctionality -= testMarks[4];
		}
		try {
			System.out.println("--- Test 6 --------------------------");
			if (!test6()) {
				realOut.println("Test 6 **FAIL** (incorrect implementation)");
				failedTests++;
				completedFunctionality -= testMarks[5];
			}
		} catch (Exception e) {
			realOut.println("Test 6 **FAIL** (unexpected exception occurred)");
			failedTests++;
			completedFunctionality -= testMarks[5];
		}
		try {
			System.out.println("--- Test 7 --------------------------");
			if (!test7()) {
				realOut.println("Test 7 **FAIL** (incorrect implementation)");
				failedTests++;
				completedFunctionality -= testMarks[6];
			}
		} catch (Exception e) {
			realOut.println("Test 7 **FAIL** (unexpected exception occurred)");
			failedTests++;
			completedFunctionality -= testMarks[6];
		}
		try {
			System.out.println("--- Test 8 --------------------------");
			if (!test8()) {
				realOut.println("Test 8 **FAIL** (incorrect implementation)");
				failedTests++;
				completedFunctionality -= testMarks[7];
			}
		} catch (Exception e) {
			realOut.println("Test 8 **FAIL** (unexpected exception occurred)");
			failedTests++;
			completedFunctionality -= testMarks[7];
		}
		try {
			System.out.println("--- Test 9 --------------------------");
			if (!test9()) {
				realOut.println("Test 9 **FAIL** (incorrect implementation)");
				failedTests++;
				completedFunctionality -= testMarks[8];
			}
		} catch (Exception e) {
			realOut.println("Test 9 **FAIL** (unexpected exception occurred)");
			failedTests++;
			completedFunctionality -= testMarks[8];
		}
		try {
			System.out.println("--- Test 10 --------------------------");
			if (!test10()) {
				realOut.println("Test 10 **FAIL** (incorrect implementation)");
				failedTests++;
				completedFunctionality -= testMarks[9];
			}
		} catch (Exception e) {
			realOut.println("Test 10 **FAIL** (unexpected exception occurred)");
			failedTests++;
			completedFunctionality -= testMarks[9];
		}
		try {
			System.out.println("--- Test 11 --------------------------");
			if (!test11()) {
				realOut.println("Test 11 **FAIL** (incorrect implementation)");
				failedTests++;
				completedFunctionality -= testMarks[10];
			}
		} catch (Exception e) {
			realOut.println("Test 11 **FAIL** (unexpected exception occurred)");
			failedTests++;
			completedFunctionality -= testMarks[10];
		}
		try {
			System.out.println("--- Test 12 --------------------------");
			if (!test12()) {
				realOut.println("Test 12 **FAIL** (incorrect implementation)");
				failedTests++;
				completedFunctionality -= testMarks[11];
			}
		} catch (Exception e) {
			realOut.println("Test 12 **FAIL** (unexpected exception occurred)");
			failedTests++;
			completedFunctionality -= testMarks[11];
		}

		// set the output streams back to normal, and close the file
		System.setErr(realErr);
		System.setOut(realOut);
		outFile.close();

		if (failedTests == 0)
			System.out.println("NONE -- Fully Working AMS system");
		else
			System.out.println("Some tests failed...");

		System.out.println("\nTests failed:  " + failedTests);
		System.out.println("Tests passed:  " + (12 - failedTests));
		System.out
				.println("\nFunctionality completed (estimation only - based on the preliminary test weights): "
						+ completedFunctionality + "%");
	}

	/***************************************************************************
	 * ************************************************************************ //
	 * ************************************************************************ //
	 * ************************************************************************ //
	 * ************************************************************************ //
	 * ************************************************************************ //
	 * DO NOT TOUCH ANYTHING PAST THIS POINT //
	 * ************************************************************************ //
	 * ************************************************************************ //
	 * ************************************************************************ //
	 * ************************************************************************ //
	 **************************************************************************/

	public static void main(String[] args) {

		int menuOption = -1;
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("all")) {
				// test16();
				System.exit(0);
			}
		}

		// repeat processing menu items until quit is entered
		while (menuOption != 14) {
			menuOption = mainMenu();
			try {
				switch (menuOption) {
				case 1:
					if (test1())
						System.out.println("**PASS**");
					else
						System.out.println("**FAIL**");
					break;
				case 2:
					if (test2())
						System.out.println("**PASS**");
					else
						System.out.println("**FAIL**");
					break;
				case 3:
					if (test3())
						System.out.println("**PASS**");
					else
						System.out.println("**FAIL**");
					break;
				case 4:
					if (test4())
						System.out.println("**PASS**");
					else
						System.out.println("**FAIL**");
					break;
				case 5:
					if (test5())
						System.out.println("**PASS**");
					else
						System.out.println("**FAIL**");
					break;
				case 6:
					if (test6())
						System.out.println("**PASS**");
					else
						System.out.println("**FAIL**");
					break;
				case 7:
					if (test7())
						System.out.println("**PASS**");
					else
						System.out.println("**FAIL**");
					break;
				case 8:
					if (test8())
						System.out.println("**PASS**");
					else
						System.out.println("**FAIL**");
					break;
				case 9:
					if (test9())
						System.out.println("**PASS**");
					else
						System.out.println("**FAIL**");
					break;
				case 10:
					if (test10())
						System.out.println("**PASS**");
					else
						System.out.println("**FAIL**");
					break;
				case 11:
					if (test11())
						System.out.println("**PASS**");
					else
						System.out.println("**FAIL**");
					break;
				case 12:
					if (test12())
						System.out.println("**PASS**");
					else
						System.out.println("**FAIL**");
					break;
				case 13:
					test13();

				}
			} catch (Exception e) {
				System.out.println("Unexpected exception! " + e.getMessage());
				e.printStackTrace();
				System.out.println("**FAIL**");
			}
		}
	}

	/**
	 * Display the test menu.
	 */
	public static int mainMenu() {
		int answer = -1;
		boolean valid = false;

		// continue prompting until valid data given
		while (!valid) {
			// display menu and prompt for entry
			System.out.println();
			System.out
					.println("===============================================================================");
			System.out.println("Tests");
			System.out
					.println("===============================================================================");
			System.out.println();
			System.out.println("AMS SYSTEM TESTS                      \n");
			System.out
					.println(" 1. initialise university with student and program ");
			System.out
					.println(" 2. add/retrieve courses                          9. calculate current load");
			System.out
					.println(" 3. remove course                                 10. calculate career points");
			System.out
					.println(" 4. program feasibility - adding courses          11. enrollment feasibility checks - UG student");
			System.out
					.println(" 5. program feasibility - removing courses        12. enrollment feasibility checks - PG student");
			System.out
					.println(" 6. enroll into course                            13. TEST ALL");
			System.out
					.println(" 7. withdraw from course                          ");
			System.out
					.println(" 8. add/retrieve results                          14. quit");

			System.out.println();
			System.out.print("Enter an option: ");

			// try to read and validate entered data
			try {
				answer = Integer.parseInt(stdin.readLine());
				System.out.println();
				if ((answer >= 1) && (answer <= 14))
					valid = true;
			} catch (NumberFormatException e) {
				System.out.println("Unparsable number entered");
			} catch (IOException e) {
				System.out.println("I/O Exception");
				System.exit(1);
			}
			// if data was invalid, print an error
			if (!valid) {
				System.out.println();
				System.out.println("Please enter a valid option (1-14).");
			}
		}
		// return the entered data
		return answer;
	}

	public static boolean checkForCourses(Course[] courses, String[] expected)
			throws Exception {
		// sort the returned courses (OPTIONAL)
		Arrays.sort(expected);
		// create comparator for sort
		Comparator<Course> courseComparator = new Comparator<Course>() {
			public int compare(Course course1, Course course2) {
				return course1.getCode().compareTo(course2.getCode());
			}
		};
		Arrays.sort(courses, courseComparator);

		try {
			// if number of courses is different from expected, return failure
			if (courses.length != expected.length)
				return false;
			// make some spots to tick
			boolean[] tickbox = new boolean[expected.length];

			// for each expected course string...
			for (int i = 0; i < expected.length; i++) {
				// try to find a match
				boolean found = false;
				for (int j = 0; (j < courses.length) && (!found); j++) {
					if (expected[i].equalsIgnoreCase(courses[j].toString()))
						found = true;
				}
				// store result for this course string in the tick box
				tickbox[i] = found;
			}
			// check there's a tick in every box
			for (int i = 0; i < tickbox.length; i++) {
				if (!tickbox[i])
					return false;
			}
		} catch (Exception e) {
			System.out
					.println("Parsing error: could not check Course list successfully!");
			return false;
		}
		return true;
	}

	public static boolean checkForResults(Result[] results, String[] expected)
			throws Exception {

		// sort the returned results (OPTIONAL)
		Arrays.sort(expected);
		// create comparator for sort
		Comparator<Result> resultComparator = new Comparator<Result>() {
			public int compare(Result result1, Result result2) {
				return result1.getCourse().getCode().compareTo(
						result2.getCourse().getCode());
			}
		};
		Arrays.sort(results, resultComparator);

		try {
			// if number of results is different from expected, return failure
			if (results.length != expected.length)
				return false;
			// make some spots to tick
			boolean[] tickbox = new boolean[expected.length];

			// for each expected course string...
			for (int i = 0; i < expected.length; i++) {
				// try to find a match
				boolean found = false;
				for (int j = 0; (j < results.length) && (!found); j++) {
					if (expected[i].equalsIgnoreCase(results[j].toString()))
						found = true;
				}
				// store check outcome for this result string in the tick box
				tickbox[i] = found;
			}
			// check there's a tick in every box
			for (int i = 0; i < tickbox.length; i++) {
				if (!tickbox[i])
					return false;
			}
		} catch (Exception e) {
			System.out
					.println("Parsing error: could not check Results list successfully!");
			return false;
		}
		return true;
	}

	public static boolean failureCheck(boolean check, boolean expected) {
		if (check == expected) {
			System.out.println(" OK");
			return false;
		} else {
			System.out.println(" not OK!");
			return true;
		}
	}

}
