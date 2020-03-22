package com.ning.service.impl;

import com.ning.mapper.CourseMapper;
import com.ning.pojo.Course;
import com.ning.pojo.CourseSort;
import com.ning.pojo.Dto.CourseDto;
import com.ning.result.Results;
import com.ning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public Results<Course> getAllCoursesByPage(Integer offset, Integer limit) {
        return Results.success(courseMapper.countAllCourses().intValue(),courseMapper.getAllCoursesByPage(offset,limit));
    }

    @Override
    public void addcourse(CourseDto courseDto) {
        courseMapper.insert(courseDto);
    }

    @Override
    public Course getCourseBycid(String cid) {
        return courseMapper.selectByPrimaryKey(cid);
    }

    @Override
    public Results updata(CourseDto courseDto) {
        courseMapper.updateByPrimaryKeySelective(courseDto);
        return Results.success();
    }

    @Override
    public int deletecourse(String cid) {
        int result=courseMapper.deleteByPrimaryKey(cid);
        return result;
    }


}
