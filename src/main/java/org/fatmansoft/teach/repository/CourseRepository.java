package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    Optional<Course> findByCourseNum(String courseNum);
    List<Course> findByCourseName(String courseName);

    @Query(value = "select max(id) from Course  ")
    Integer getMaxId();//获取最大id

    @Query(value = "from Course where ?1='' or courseNum like %?1% or courseName like %?1% ")
    List<Course> findCourseListByNumName(String numName);//在课程数据库中以课程名，课号查找数据

    @Query(value = "select * from course  where ?1='' or courseNum like %?1% or courseName like %?1% ", nativeQuery = true)
    List<Course> findCourseListByNumNameNative(String numName);
}
