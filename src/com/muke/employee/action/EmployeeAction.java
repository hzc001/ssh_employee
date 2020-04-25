package com.muke.employee.action;

import java.util.List;

import com.muke.employee.entity.Department;
import com.muke.employee.entity.Employee;
import com.muke.employee.entity.PageBean;
import com.muke.employee.service.DepartmentService;
import com.muke.employee.service.EmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Ա�������Action��
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee> {

	// ģ������ʹ�õĶ���
	private Employee employee = new Employee();

	@Override
	public Employee getModel() {
		return employee;
	}

	// ע��ҵ������
	private EmployeeService employeeService;
	private DepartmentService departmentService;

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// ���յ�ǰ��ҳ��
	private Integer currPage = 1;
	
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	/**
	 * ��¼ִ�еķ���
	 * @return
	 */
	public String login() {
		System.out.println("loginִ���ˡ�����");
		// ����ҵ������
		Employee existEmployee = employeeService.login(employee);
		if (existEmployee == null) {
			// ��¼ʧ��
			this.addActionError("�û������������");
			return INPUT;
		} else {
			// ��¼�ɹ������û�����Ϣ����session��
			ActionContext.getContext().getSession().put("existEmployee", existEmployee);
			return SUCCESS;
		}
	}
	
	/**
	 * ��ҳ��ѯԱ����ִ�еķ���
	 */
	public String findAll() {
		PageBean<Employee> pageBean = employeeService.findByPage(currPage);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * ��ת�����Ա����ҳ���ִ�еķ���
	 */
	public String saveUI() {
		// ��ѯ���в���
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list); // ������set���棬������push����
		return "saveUI";
	}
	
	/**
	 * ���Ա����ִ�еķ���
	 */
	public String save() {
		employeeService.save(employee);
		return "saveSuccess";
	}
	
	/**
	 * �༭Ա����ִ�еķ���
	 */
	public String edit() {
		// ����Ա��ID��ѯԱ��
		employee = employeeService.findById(employee.getEid());
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list); 
		return "editSuccess";
	}
	
	/**
	 * �޸�Ա����ִ�еķ���
	 */
	public String update() {
		employeeService.update(employee);
		return "updateSuccess";
	}
	
	/**
	 * ɾ��Ա����ִ�еķ���
	 */
	public String delete() {
		// �Ȳ�ѯ��Ӧ��Ա��ID����ɾ��
		employee = employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}
}
