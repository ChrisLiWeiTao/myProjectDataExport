package com.lwt.dataexport.dao;

import com.lwt.dataexport.entity.Student;

public interface ManageStu {
    int addSingleStu(Student stu);
    Boolean queryStu(int dept);
}
