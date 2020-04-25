package com.muke.employee.action;

import com.muke.employee.entity.Department;
import com.muke.employee.entity.PageBean;
import com.muke.employee.service.DepartmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ���Ź����Action��
 */
public class DepartmentAction extends ActionSupport implements ModelDriven<Department> {

	// ģ������ʹ�õĶ���
	private Department department = new Department();

	@Override
	public Department getModel() {
		return department;
	}

	// �ṩ��ǰҳ��
	private Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	// ע�벿�Ź����Service
	private DepartmentService departmentService;

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	// �ṩһ����ҳ��ѯ�ķ���
	public String findAll() {
		PageBean<Department> pageBean = departmentService.findByPage(currPage);
		// ��pageBean���뵽ֵջ��
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

	// ��ת����Ӳ��ŵ�ҳ��ķ���
	public String saveUI() {
		return "saveUI";
	}
	
	// ��Ӳ��ŵ�ִ�еķ���
	public String save() {
		departmentService.save(department);
		return "saveSuccess";
	}
	
	// �༭���ŵ�ִ�еķ���
	public String edit() {
		department = departmentService.findById(department.getDid());
		return "editSuccess";
	}
	
	// �޸Ĳ��ŵ�ִ�еķ���
	public String update() {
		departmentService.update(department);
		return "updateSuccess";
	}
	
	// ɾ�����ŵ�ִ�еķ���
	public String delete() {
		// �Ȳ�ѯ��Ӧ�Ĳ���ID����ɾ��
		department = departmentService.findById(department.getDid());
		departmentService.delete(department);
		return "deleteSuccess";
	}
}
