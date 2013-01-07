package ams.model;

import java.util.*;

import ams.model.exception.ProgramException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Program {
	private String code;
	private String title;
	// ArrayList <Course> courseList = new ArrayList <Course>();
	private int currentCourse;
	private HashMap<String, Course> courses;

	public Program(String code, String title) {
		super();
		courses = new HashMap<String, Course>();
		this.code = code;
		this.title = title;

	}

	public String getCode() {// returns the Program Code
		return code;
	}

	public String getTitle() {// returns the Program title
		return title;
	}

	public void addCourse(Course course) throws ProgramException {// Supposed
		// to add a
		// course to
		// a program

		// if(!isValidReference(course.getCode())) throw new
		// ProgramException("**INVALID**");

		// courses.put(course.getCode(),course);
		// courseList.add(course);
		if (courses.containsKey(course.getCode())) {
			throw new ProgramException("**COURSE ALREADY EXISTS**");
		} else {

			// return
			courses.put(course.getCode(), course);

			// return;
		}

		// courseList.add(course);
		// courses.put(course, course);

	}

	public Course removeCourse(String courseId) throws ProgramException {// removes
																			// a
																			// course
																			// from
																			// the
																			// course
																			// list

		if (!courses.containsKey(courseId)) {
			throw new ProgramException("**ERROR: COURSE DOES NOT EXIST**");
		} else {// get all courses returns with courseid
			// check prereqs
			// find match throw exp
			Course temp;
			Course[] temp1 = getAllCourses();
			for (int i = 0; i < courses.size(); i++) 
			{

				String[] temp2 = temp1[i].getPreReqs();
				for (int j = 0; j < temp2.length; j++)
				{
					
						if (temp2[j].equals(courseId)) 
						{
							throw new ProgramException("ERROR: COURSE A PREREQ");
						}
					
				}
				

			}
			return courses.remove(courseId);
			// return temp;
		}

		/*
		 * //working version if(!courses.containsKey(courseId)) { throw new
		 * ProgramException("**ERROR: COURSE DOES NOT EXIST**"); } else { return
		 * courses.remove(courseId); }
		 */
		/*
		 * if(!courses.containsKey(course)) { throw new
		 * ProgramException("**ERROR REMOVING COURSE, COURSE DOES NOT EXIST**"); }
		 * else { courses.remove(course); return; }
		 */

	}

	public boolean courseExists(String course) {
		return courses.containsKey(course);
	}

	public Course getCourse(String course) {// Supposed to get a course from a
		// program
		/*
		 * int correct=0; for(int i=0;i< courseList.size();i++) { currentCourse =
		 * i; if(courseList.get(currentCourse).getCode() == course) { correct =
		 * currentCourse; } else { continue; } } return courseList.get(correct);
		 */

		// if(!courseExists(course))
		/*
		 * if(!courses.containsKey(course)) { return null; } else {
		 */
		// return courses.get(course);
		// return courses.get(course.getCode(), course);
		// return Course.get(course);
		/*
		 * Set<String> keys = courses.keySet(); for(String key:keys) { }
		 */

		// return courses.get(course).toString();
		return (Course) courses.get(course);

		// }

	}

	public Course[] getAllCourses() {
		// return null;
		if (courses.isEmpty()) {
			return null;
		} else { /*
					 * int temp = 0; for(int i = 0; i<courses.size();i++) {
					 * temp = i; }
					 */
			return (Course[]) courses.values().toArray(new Course[0]);
		}
	}

	public String toString() {// Prints a string: program_code:program_title
		// String program = code+":"+title;
		// return program;
		return (new StringBuilder(String.valueOf(code))).append(":").append(
				title).toString();

	}

}
