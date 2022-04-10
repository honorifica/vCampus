package vss.server.dao;

import java.util.List;

import vss.common.Course;
import vss.server.db.SqlHelperImp;

public class CourseDaoImp implements CourseDao{
	@Override
    public Course searchCourseByID(String courseID) {
        // TODO Auto-generated method stub
        String sql = "select * from tb_Class where cID = ?";
        String[] paras = new String[1];
        paras[0] = courseID;
        List<Course> cList=new SqlHelperImp().sqlCourseQuery(sql, paras);
        if(cList!=null&&cList.size()>0) {
            return cList.get(0);
        }else
            return null;
    }
    @Override
    public List<Course> getAllCourse() {
        // TODO Auto-generated method stub
        String sql = "select * from tb_Class";
		return new SqlHelperImp().sqlCourseQuery(sql, new String[] {});
    }

    @Override
    public boolean sigAddCourse(String courseID,String uID) {
        String[] paras1 = new String[2];
        String[] paras2 = new String[3];
        paras1[1] = courseID;
        Course course = this.searchCourseByID(courseID);
        int left = Integer.valueOf(course.getCNownum());
        left += 1;
        if(left>Integer.valueOf(course.getCMaxnum()))
        {
            return false;
        }
        else{
            paras1[0] = String.valueOf(left);
            paras2[0]=uID+courseID;
            paras2[1]=uID;
            paras2[2]=courseID;
            String sql1 = "update tb_Class set cNownum = ? where cID = ?";
            String sql2 = "insert into tb_Stc(scID,uID,cID) values(?,?,?)";
            return new SqlHelperImp().sqlUpdate(sql1, paras1) && new SqlHelperImp().sqlUpdate(sql2, paras2);
        }
    }

    @Override
    public boolean sigRemoveCourse(String courseID,String uID) {
        String[] paras1=new String[2];
        String[] paras2=new String[2];
        String sql1="update tb_Class set cNownum = ? where cID = ?";
        String sql2="delete from tb_Stc where uID = ? and cID = ?";
        Course course = this.searchCourseByID(courseID);
        int left = Integer.valueOf(course.getCNownum());
        left--;
        if(left<0){
            return false;
        } else{
            paras1[0]=String.valueOf(left);
            paras1[1]=courseID;
            paras2[0] = uID;
            paras2[1] = courseID;
            return new SqlHelperImp().sqlUpdate(sql2, paras2) && new SqlHelperImp().sqlUpdate(sql1, paras1);
        }
    }

    @Override
    public boolean genAddCourse(Course course) {
        // TODO Auto-generated method stub
        String sql="insert into tb_Class(cID,cName,cHours,cMaxnum,cNownum) values (?,?,?,?,?)";
        String[] paras=new String[5];
        paras[0]=course.getCourseID();
        paras[1]=course.getCourseName();
        paras[2]=course.getCourseHours();
        paras[3]=course.getCMaxnum();
        paras[4]=course.getCNownum();
        return new SqlHelperImp().sqlUpdate(sql, paras);
    }

    @Override
    public boolean genRemoveCourse(String courseName) {
        // TODO Auto-generated method stub
        String sql="delete from tb_Class where cName = ?";
        String[] paras=new String[1];
        paras[0]=courseName;
        return new SqlHelperImp().sqlUpdate(sql, paras);
    }
}
