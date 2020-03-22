package com.ning.mapper;

import com.ning.pojo.Admin;
import com.ning.pojo.Course;
import com.ning.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseMapper extends MyMapper<Course> {
    List<Course> queryThreeCoursesByTime();
    List<Course> queryThreeCoursesByDZ();
    List<Course> queryThreeCoursesByGZ();

    /**
     * 计算有多少课程
     * @return
     */
    @Select("select count(*) from course c")
    Long countAllCourses();

    /**
     * 返回查询的数据
     * @param offset
     * @param limit
     * @return
     */
    @Select("select * from course t order by t.cid limit #{startPosition},#{limit}")
    List<Course> getAllCoursesByPage(@Param("startPosition")Integer offset,  @Param("limit")Integer limit);
}