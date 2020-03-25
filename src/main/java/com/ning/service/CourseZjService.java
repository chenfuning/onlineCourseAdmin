package com.ning.service;

import com.ning.pojo.CourseZj;

import java.util.List;

public interface CourseZjService {
    List<CourseZj> getCourseZlistsBycid(String cid);

    List<CourseZj> getCourseJlistsBysort(String sort,String cid);

    int getCountbyCid(String cid);

    void addcoursezj(CourseZj courseZj);

    void delcoursezj(String cid);

    void delcoursezjByzid(String zid);

    int getCountJbyCidAndsort(String cid, String sort);

    CourseZj getcourseJByzid(String zid);

    void updateVideoUrl(CourseZj courseZj);


}
