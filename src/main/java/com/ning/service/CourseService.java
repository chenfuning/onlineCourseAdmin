package com.ning.service;

import com.ning.pojo.Course;
import com.ning.pojo.Dto.CourseDto;
import com.ning.result.Results;

public interface CourseService {
    Results<Course> getAllCoursesByPage(Integer offset, Integer limit);

    void addcourse(CourseDto courseDto);

    Course getCourseBycid(String cid);

    Results updata(CourseDto courseDto);


    int deletecourse(String cid);
}
