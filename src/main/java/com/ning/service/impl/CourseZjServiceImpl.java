package com.ning.service.impl;

import com.ning.mapper.CourseZjMapper;
import com.ning.pojo.CourseZj;
import com.ning.service.CourseZjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Comparator;
import java.util.List;

@Service
public class CourseZjServiceImpl implements CourseZjService {
    @Autowired
    private CourseZjMapper courseZjMapper;
    @Override
    public List<CourseZj> getCourseZlistsBycid(String cid) {
        Example coursezjExample=new Example(CourseZj.class);
        Example.Criteria criteria=coursezjExample.createCriteria();
        criteria.andEqualTo("courseId",cid);
        criteria.andEqualTo("parentId","0");
        List<CourseZj> list=courseZjMapper.selectByExample(coursezjExample);
        //list排序
        list.sort(Comparator.naturalOrder());//正序比较
        return list;
    }

    @Override
    public List<CourseZj> getCourseJlistsBysort(String sort,String cid) {
        Example coursezjExample=new Example(CourseZj.class);
        Example.Criteria criteria=coursezjExample.createCriteria();
        criteria.andEqualTo("courseId",cid);
        criteria.andEqualTo("parentId",sort);
        List<CourseZj> list=courseZjMapper.selectByExample(coursezjExample);
        //list排序
        list.sort(Comparator.naturalOrder());//正序比较
        return list;
    }

    @Override
    public int getCountbyCid(String cid) {
        Example coursezjExample=new Example(CourseZj.class);
        Example.Criteria criteria=coursezjExample.createCriteria();
        criteria.andEqualTo("courseId",cid);
        criteria.andEqualTo("parentId","0");
        return courseZjMapper.selectCountByExample(coursezjExample);
    }

    @Override
    public void addcoursezj(CourseZj courseZj) {
        courseZjMapper.insert(courseZj);
    }

    @Override
    public void delcoursezj(String cid) {
        List<CourseZj> list= getCourseZlistsBycid(cid);
        if(list.size()>0) {
            Example coursezjExample=new Example(CourseZj.class);
            Example.Criteria criteria = coursezjExample.createCriteria();
            criteria.andEqualTo("courseId", cid);
            criteria.andEqualTo("parentId", "0");
            criteria.andEqualTo("sort", list.size());
            courseZjMapper.deleteByExample(coursezjExample);
        }
    }

    @Override
    public void delcoursezjByzid(String zid) {
        courseZjMapper.deleteByPrimaryKey(zid);
    }


    @Override
    public int getCountJbyCidAndsort(String cid, String sort) {
            Example coursezjExample=new Example(CourseZj.class);
            Example.Criteria criteria = coursezjExample.createCriteria();
            criteria.andEqualTo("courseId", cid);
            criteria.andEqualTo("parentId", sort);
        return  courseZjMapper.selectCountByExample(coursezjExample);
    }

    @Override
    public CourseZj getcourseJByzid(String zid) {
        return   courseZjMapper.selectByPrimaryKey(zid);
    }

    @Override
    public void updateVideoUrl(CourseZj courseZj) {
        courseZjMapper.updateByPrimaryKeySelective(courseZj);
    }


}
