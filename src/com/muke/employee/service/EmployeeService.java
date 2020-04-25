package com.muke.employee.service;

import com.muke.employee.entity.Employee;
import com.muke.employee.entity.PageBean;

/**
 * Ա�������ҵ���Ľӿ�
 */
public interface EmployeeService {

	Employee login(Employee employee);

	PageBean<Employee> findByPage(Integer currPage);

	void save(Employee employee);

	Employee findById(Integer eid);

	void update(Employee employee);

	void delete(Employee employee);

}
