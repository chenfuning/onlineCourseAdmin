package com.ning.service.impl;

import com.ning.mapper.CourseSortMapper;
import com.ning.pojo.CourseSort;
import com.ning.result.Results;
import com.ning.service.CourseSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CourseSortServiceimpl implements CourseSortService {
    @Autowired
    private CourseSortMapper courseSortMapper;
    @Override
    public Results<CourseSort> getSortlist() {
        List<CourseSort> list=courseSortMapper.selectAll();
        return Results.success(0,courseSortMapper.selectAll());
    }

    @Override
    public Results getfuCourseSortList() {
        Example fusort=new Example(CourseSort.class);
        Example.Criteria criteria=fusort.createCriteria();
        criteria.andEqualTo("parentCode",0);
        List<CourseSort> fusortlist=courseSortMapper.selectByExample(fusort);
        return Results.success(0,fusortlist);
    }

    @Override
    public Results sortadd(CourseSort courseSort) {
            courseSortMapper.insert(courseSort);
        return Results.success();
    }

    @Override
    public Byte selectCodeByname(String parentCode) {
        Example csExample=new Example(CourseSort.class);
        Example.Criteria criteria=csExample.createCriteria();
        criteria.andEqualTo("name",parentCode);
        List<CourseSort> list=courseSortMapper.selectByExample(csExample);
        return list.get(0).getCode();
    }

    @Override
    public CourseSort getCourseSortBycsid(String csid) {
        return courseSortMapper.selectByPrimaryKey(csid);
    }

    @Override
    public CourseSort getCourseSortNameBycode(Byte Code) {
        Example csExample=new Example(CourseSort.class);
        Example.Criteria criteria=csExample.createCriteria();
        criteria.andEqualTo("code",Code);
        List<CourseSort> list=courseSortMapper.selectByExample(csExample);
        return list.get(0);
    }

    @Override
    public Results updateCourseSort(CourseSort courseSort) {
        courseSortMapper.updateByPrimaryKeySelective(courseSort);
        return Results.success();
    }

    @Override
    public Results delete(String csid) {
        courseSortMapper.deleteByPrimaryKey(csid);
        return Results.success();
    }

    @Override
    public List<CourseSort> getSeCourseSortsByparentCode(String parentCodeName) {
        Example csparentExample=new Example(CourseSort.class);
        Example.Criteria criteria=csparentExample.createCriteria();
        criteria.andEqualTo("name",parentCodeName);
        List<CourseSort> list=courseSortMapper.selectByExample(csparentExample);
        if(!list.isEmpty()) {
            Example csExample = new Example(CourseSort.class);
            Example.Criteria criteria1 = csExample.createCriteria();
            criteria1.andEqualTo("parentCode", list.get(0).getCode());
            List<CourseSort> result = courseSortMapper.selectByExample(csExample);
            return result;
        }else
            return null;
    }
}
