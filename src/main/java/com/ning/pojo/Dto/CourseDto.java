package com.ning.pojo.Dto;

import com.ning.pojo.Course;

public class CourseDto extends Course {
    private String code;
    private  String CodeName;
    private String parentCodeName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeName() {
        return CodeName;
    }

    public void setCodeName(String codeName) {
        CodeName = codeName;
    }

    public String getParentCodeName() {
        return parentCodeName;
    }

    public void setParentCodeName(String parentCodeName) {
        this.parentCodeName = parentCodeName;
    }
}
