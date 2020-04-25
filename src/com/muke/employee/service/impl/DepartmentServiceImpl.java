package com.muke.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.muke.employee.dao.DepartmentDao;
import com.muke.employee.entity.Department;
import com.muke.employee.entity.PageBean;
import com.muke.employee.service.DepartmentService;

/**
 * ���Ź����ҵ����ʵ����
 */
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	// ע�벿�Ź����DAO
	private DepartmentDao departmentDao;

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	// ��ҳ��ѯ���ŵķ���
	public PageBean<Department> findByPage(Integer currPage) {
		PageBean<Department> pageBean = new PageBean<Department>();
		// ��װ��ǰ��ҳ��
		pageBean.setCurrPage(currPage);
		// ��װÿҳ��ʾ��¼��
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		// ��װ�ܼ�¼��
		int totalCount = departmentDao.findCount();
		pageBean.setTotalCount(totalCount);
		// ��װ��ҳ��
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ��װÿҳ��ʾ������
		int begin = (currPage - 1) * pageSize;
		List<Department> list = departmentDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	// ҵ��㱣�沿�ŵķ���
	public void save(Department department) {
		departmentDao.save(department);
	}

	@Override
	// ҵ�����ݲ���ID��ѯ���ŵķ���
	public Department findById(Integer did) {
		return departmentDao.findById(did);
	}

	@Override
	// ҵ����޸Ĳ��ŵķ���
	public void update(Department department) {
		departmentDao.update(department);
	}

	@Override
	// ҵ���ɾ�����ŵķ���
	public void delete(Department department) {
		departmentDao.delete(department);
	}

	@Override
	// ҵ����ѯ���в��ŵķ���
	public List<Department> findAll() {
		return departmentDao.findAll();
	}
}
