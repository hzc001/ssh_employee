package com.muke.employee.service;

import java.util.List;

import com.muke.employee.entity.Department;
import com.muke.employee.entity.PageBean;

/**
 * ���Ź����ҵ���Ľӿ�
 */
public interface DepartmentService {

	PageBean<Department> findByPage(Integer currPage);

	void save(Department department);

	Department findById(Integer did);

	void update(Department department);

	void delete(Department department);

	List<Department> findAll();

}
