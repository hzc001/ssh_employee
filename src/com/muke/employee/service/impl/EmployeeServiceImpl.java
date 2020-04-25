package com.muke.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.muke.employee.dao.EmployeeDao;
import com.muke.employee.entity.Employee;
import com.muke.employee.entity.PageBean;
import com.muke.employee.service.EmployeeService;

/**
 * Ա�������ҵ����ʵ����
 */
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	// ҵ���ע��DAO
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	/**
	 * ҵ���ĵ�¼�ķ���
	 */
	public Employee login(Employee employee) {
		Employee existEmployee = employeeDao.findByUsernameAndPassword(employee);
		return existEmployee;
	}

	@Override
	/**
	 * ҵ���ķ�ҳ��ѯԱ���ķ���
	 */
	public PageBean<Employee> findByPage(Integer currPage) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
		// ��װ��ǰ��ҳ��
		pageBean.setCurrPage(currPage);
		// ��װÿҳ��ʾ��¼��
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		// ��װ�ܼ�¼��
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		// ��װ��ҳ��
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ��װÿҳ��ʾ������
		int begin = (currPage - 1) * pageSize;
		List<Employee> list = employeeDao.findByPage(begin, pageSize);
		pageBean.setList(list);

		return pageBean;
	}

	@Override
	// ҵ��㱣��Ա���ķ���
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	@Override
	// ҵ������Ա��ID��ѯԱ���ķ���
	public Employee findById(Integer eid) {
		return employeeDao.findById(eid);
	}

	@Override
	// ҵ����޸�Ա���ķ���
	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	@Override
	// ҵ���ɾ��Ա���ķ���
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}

}
