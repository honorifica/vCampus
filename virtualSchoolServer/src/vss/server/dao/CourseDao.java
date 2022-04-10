package vss.server.dao;
import vss.common.*;
import java.util.*;

public interface CourseDao {
	/**
	 * get all of the courses' information
	 * @return all courses
	 */
	public List<Course> getAllCourse();
	/**
	 * students choose lessons
	 * @param couseID the course's ID
	 * @param uID the student's Id who choosing the lesson
	 * @return return a value of boolean, showing this function success or fail
	 */
	public boolean sigAddCourse(String couseID,String uID);
	/**
	 * student remove the lesson they have chosen
	 * @param courseID the course's ID
	 * @param uID the student's Id who removing the lesson
	 * @return return a value of boolean, showing this function success or fail
	 */
	public boolean sigRemoveCourse(String courseID,String uID);
	/**
	 * teacher add lesson
	 * @param course all of the course's information which the teacher add
	 * @return return a value of boolean, showing this function success or fail
	 */
	public boolean genAddCourse(Course course);
	/**
	 * teacher remove lesson
	 * @param courseID the course's ID which will be removed
	 * @return return a value of boolean, showing this function success or fail
	 */
	public boolean genRemoveCourse(String courseID);
	/**
	 * search course by its courseID from database
	 * @param coutseID the course's ID
	 * @return a course which id is courseID
	 */
	public Course searchCourseByID(String coutseID);
}
