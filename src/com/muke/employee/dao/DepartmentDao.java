package com.muke.employee.dao;

import java.util.List;

import com.muke.employee.entity.Department;

/**
 * 部门管理的DAO的接口
 */
public interface DepartmentDao {

	int findCount();

	List<Department> findByPage(int begin, int pageSize);

	void save(Department department);

	Department findById(Integer did);

	void update(Department department);

	void delete(Department department);

	List<Department> findAll();

}
