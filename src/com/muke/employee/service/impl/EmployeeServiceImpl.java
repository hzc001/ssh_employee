package com.muke.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.muke.employee.dao.EmployeeDao;
import com.muke.employee.entity.Employee;
import com.muke.employee.entity.PageBean;
import com.muke.employee.service.EmployeeService;

/**
 * 员工管理的业务层的实现类
 */
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	// 业务层注入DAO
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	/**
	 * 业务层的登录的方法
	 */
	public Employee login(Employee employee) {
		Employee existEmployee = employeeDao.findByUsernameAndPassword(employee);
		return existEmployee;
	}

	@Override
	/**
	 * 业务层的分页查询员工的方法
	 */
	public PageBean<Employee> findByPage(Integer currPage) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
		// 封装当前的页数
		pageBean.setCurrPage(currPage);
		// 封装每页显示记录数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 封装每页显示的数据
		int begin = (currPage - 1) * pageSize;
		List<Employee> list = employeeDao.findByPage(begin, pageSize);
		pageBean.setList(list);

		return pageBean;
	}

	@Override
	// 业务层保存员工的方法
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	@Override
	// 业务层根据员工ID查询员工的方法
	public Employee findById(Integer eid) {
		return employeeDao.findById(eid);
	}

	@Override
	// 业务层修改员工的方法
	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	@Override
	// 业务层删除员工的方法
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}

}
