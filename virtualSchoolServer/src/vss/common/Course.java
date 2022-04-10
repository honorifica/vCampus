package vss.common;
//import vss.server.bz.user;

import java.util.Vector;

public class Course {
	private String courseID;
	private String courseName;
	private String courseHours;
	private String cMaxnum;
	private String cNownum;

	public Course() {
	}

	public Course(String courseID, String courseName, String courseHours, String cMaxnum, String cNownum) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseHours = courseHours;
		this.cMaxnum = cMaxnum;
		this.cNownum = cNownum;
	}

	public String getCourseID() {
		return this.courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseHours() {
		return this.courseHours;
	}

	public void setCourseHours(String courseHours) {
		this.courseHours = courseHours;
	}

	public String getCMaxnum() {
		return this.cMaxnum;
	}

	public void setCMaxnum(String cMaxnum) {
		this.cMaxnum = cMaxnum;
	}

	public String getCNownum() {
		return this.cNownum;
	}

	public void setCNownum(String cNownum) {
		this.cNownum = cNownum;
	}

	@Override
	public String toString() {
		return "{" +
			" courseID='" + getCourseID() + "'" +
			", courseName='" + getCourseName() + "'" +
			", courseHours='" + getCourseHours() + "'" +
			", cMaxnum='" + getCMaxnum() + "'" +
			", cNownum='" + getCNownum() + "'" +
			"}";
	}
	public Vector<String> getContent() {
		Vector<String> courseContents = new Vector<String>();
		courseContents.add(courseID);
		courseContents.add(courseName);
		courseContents.add(courseHours);
		courseContents.add(cMaxnum);
		courseContents.add(cNownum);
		return courseContents;
	}
	public void setContent(Vector<String> content) {
		 courseID = content.get(0);
		 courseName = content.get(1);
		 courseHours = content.get(2);
		 cMaxnum = content.get(3);
		 cNownum = content.get(4);
	}
}
