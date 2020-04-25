package com.muke.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.muke.employee.dao.DepartmentDao;
import com.muke.employee.entity.Department;
import com.muke.employee.entity.PageBean;
import com.muke.employee.service.DepartmentService;

/**
 * 部门管理的业务层的实现类
 */
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	// 注入部门管理的DAO
	private DepartmentDao departmentDao;

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	// 分页查询部门的方法
	public PageBean<Department> findByPage(Integer currPage) {
		PageBean<Department> pageBean = new PageBean<Department>();
		// 封装当前的页数
		pageBean.setCurrPage(currPage);
		// 封装每页显示记录数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		int totalCount = departmentDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 封装每页显示的数据
		int begin = (currPage - 1) * pageSize;
		List<Department> list = departmentDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	// 业务层保存部门的方法
	public void save(Department department) {
		departmentDao.save(department);
	}

	@Override
	// 业务层根据部门ID查询部门的方法
	public Department findById(Integer did) {
		return departmentDao.findById(did);
	}

	@Override
	// 业务层修改部门的方法
	public void update(Department department) {
		departmentDao.update(department);
	}

	@Override
	// 业务层删除部门的方法
	public void delete(Department department) {
		departmentDao.delete(department);
	}

	@Override
	// 业务层查询所有部门的方法
	public List<Department> findAll() {
		return departmentDao.findAll();
	}
}
