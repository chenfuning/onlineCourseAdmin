package com.ning.service;

import com.ning.pojo.CourseSort;
import com.ning.result.Results;

import java.util.List;

public interface CourseSortService {
    Results getSortlist();

    /**
     * 查询父级标签
     * @return
     */
    Results getfuCourseSortList();

    Results sortadd(CourseSort courseSort);

    /**
     * 根据parentcode的名称查询code
     * @param parentCode
     * @return
     */
    Byte selectCodeByname(String parentCode);

    /**
     * 根据id查询courseSort
     * @param csid
     * @return
     */
    CourseSort getCourseSortBycsid(String csid);

    /**
     * 根据Code获取courseSort对象
     * @param Code
     * @return
     */
    CourseSort getCourseSortNameBycode(Byte Code);

    /**
     * 更新coursesort
     * @param courseSort
     * @return
     */
    Results updateCourseSort(CourseSort courseSort);

    /**
     * 删除coursesort
     * @param csid
     * @return
     */
    Results delete(String csid);

    List<CourseSort> getSeCourseSortsByparentCode(String parentCodeName);
}
