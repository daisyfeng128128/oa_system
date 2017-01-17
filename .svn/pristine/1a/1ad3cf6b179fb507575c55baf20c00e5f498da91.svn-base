package com.baofeng.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baofeng.commons.dao.UsersDAO;
import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.Operator.Actores;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.Operator.Login;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.EmployeeBean;
import com.baofeng.oa.bean.EmployeeLeaveBean;
import com.baofeng.oa.dao.EmployeeDAO;
import com.baofeng.oa.dao.EmployeeLeaveDAO;
import com.baofeng.oa.dao.ManagerDAO;
import com.baofeng.oa.entity.BaseEnums.Account;
import com.baofeng.oa.entity.BaseEnums.EmployeType;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.EmployeeLeave;
import com.baofeng.oa.entity.EmployeeTransfer;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.utils.MD5;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private ManagerDAO managerDAO;
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private UsersDAO usersDAO;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private EmployeeDAO employeeDAO;
	@Autowired
	private EmployeeLeaveDAO emplDAO;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	@Override
	public Employee readEmployee(Integer id) {
		if (id != null && id > 0) {
			Employee employee = this.employeeDAO.readEmployee(id);
			return employee;
		}
		return null;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String name, String snumber, String snamepy, Integer sposid, Integer sdepid,
			String fastArg, String type, SearchFilter filter) {
		NPageResult rows = this.employeeDAO.readAllPages(pageSize, curPage, sortName, sortOrder, name, snumber, snamepy, sposid, sdepid, fastArg, type, filter);
		if (rows != null && rows.getData().size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<EmployeeBean> list = new ArrayList<EmployeeBean>();
			BranchOffice branchOffice = null;
			for (Object o : rows.getData()) {
				Employee post = (Employee) o;
				EmployeeBean bean = new EmployeeBean();
				bean.setId(post.getId());
				bean.setAliasname(post.getAliasname());
				bean.setBirth(post.getBirth());
				if (post.getBirth() != null)
					bean.setBirthday(sdf.format(post.getBirth()));
				if (post.getJoinDate() != null)
					bean.setIncomeday(sdf.format(post.getJoinDate()));
				if (post.getRegularDate() != null)
					bean.setOutday(sdf.format(post.getRegularDate()));
				if (post.getDepart() != null)
					bean.setDepName(post.getDepart().getName());
				bean.setEmergencyContact(post.getEmergencyContact());
				bean.setEmergencyPhone(post.getEmergencyPhone());
				if (post.getSupervisor() != null)
					bean.setEmpName(post.getSupervisor().getName());
				bean.setHostRegister(post.getHostRegister());
				bean.setIdcard(post.getIdcard());
				if (branchOffice == null)
					branchOffice = this.branchOfficeService.readBranchOffice(post.getBranchs());
				if (branchOffice != null)
					bean.setNum(branchOffice.getNumberHead() + String.format("%04d", post.getNumber()));
				else
					bean.setNum(String.format("%04d", post.getNumber()));
				bean.setIdImage(post.getIdImage());
				if (post.getGenrer() == Genres.LIZHI) {
					bean.setGenrer("离职");
				} else if (post.getGenrer() == Genres.ZHENGSHI) {
					bean.setGenrer("正式");
				} else if (post.getGenrer() == Genres.SHIYONG) {
					bean.setGenrer("试用");
				}
				if (post.getSex() == Sex.MAN) {
					bean.setSex("男");
				} else {
					bean.setSex("女");
				}

				bean.setIntroducer(post.getIntroducer());
				bean.setJoinDate(post.getJoinDate());
				bean.setLiveAdress(post.getLiveAdress());
				bean.setName(post.getAccounts());
				bean.setQq(post.getQq());
				bean.setPhone(post.getPhone());
				bean.setNamepy(post.getNamepy());
				if (post.getPosition() != null) {
					bean.setPosName(post.getPosition().getName());
				}
				bean.setRegularDate(post.getRegularDate());

				if (post.getContractDT() != null)
					bean.setContractDT2(sdf.format(post.getContractDT()));

				if (post.getAccount() == Account.CHENGZHEN) {
					bean.setAccount("城镇");
				} else {
					bean.setAccount("农村");
				}

				if ("0".equals(post.getJiaojin())) {
					bean.setJiaojin("否");
				} else {
					bean.setJiaojin("是");
				}
				if ("0".equals(post.getSocialSecurity())) {
					bean.setSocialSecurity("否");
				} else {
					bean.setSocialSecurity("是");
				}
				if (post.getEmployeType() == EmployeType.ACTORES) {
					if (post.getActoresBSalary() != null) {
						bean.setActoresBSalary(post.getActoresBSalary());
					}
					if (post.getPushMoney() != null) {
						bean.setPushMoney(post.getPushMoney() == null ? "-" : String.format("%d%% %n", post.getPushMoney().intValue()));
					}
					if (post.getShareBankAddress() != null)
						bean.setShareBankCard(post.getShareBankCard());
					bean.setShareBankAddress(post.getShareBankAddress());
					bean.setSpeciality(post.getSpeciality());
				}
				bean.setCpfAccounts(post.getCpfAccounts());
				bean.setBankCard(post.getBankCard());
				bean.setBankAddress(post.getBankCard());
				Float probationSalary = Float.valueOf(0);
				if (post.getProbationSalary() != null)
					probationSalary = post.getProbationSalary();
				bean.setProbationSalary(probationSalary);
				if (post.getOtherSubsidy() != null)
					bean.setOtherSubsidy(post.getOtherSubsidy());
				if (post.getSalary() != null)
					bean.setSalary(post.getSalary());
				if (post.getFoodSubsidyE() != null) {
					bean.setFoodSubsidyE(post.getFoodSubsidyE());
				}
				if (post.getOtherSubsidyE() != null) {
					bean.setOtherSubsidyE(post.getOtherSubsidyE());
				}
				if (post.getJobSubsidyE() != null) {
					bean.setJobSubsidyE(post.getJobSubsidyE());
				}
				bean.setRemarks(post.getRemarks());
				list.add(bean);
			}
			rows.setData(list);
		}
		return rows;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResult readAllPagesSkip(int rows, int page, SearchFilter filter, String queryFilter) {
		return this.employeeDAO.readAllPagesSkip(rows, page, filter, queryFilter);
	}

	@Override
	public boolean deleteEmployee(Integer id, HttpServletRequest request) {
		if (id != null) {
			Employee employee = this.readEmployee(id);
			if (employee != null) {
				employee.setStatus(EntityStatus.DELETED);
				if (this.employeeDAO.deleteEmployee(employee)) {
					this.monitorLogService.logsDelete(request, "员工: " + employee.getName() + " ID: " + employee.getId());
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public boolean addEmployee(Employee post, HttpServletRequest request) {
		if (post != null && post.getId() != null && post.getId() > 0) { // 修改
			Employee $post = this.readEmployee(post.getId());
			Float basic = post.getSalary();
			Float probation = post.getProbationSalary();
			Float otherSubsidy = post.getOtherSubsidy();
			if ($post != null) {
				if (post.getPassword() != null && post.getPassword().trim().length() > 0) {
					$post.setPassword(MD5.MD5Encode(post.getPassword()));
				}
				if (post.getEmployeType() == EmployeType.ACTORES || $post.getActores() == Actores.YES) {
					$post.setPushMoney(post.getPushMoney());
					$post.setActoresBSalary(post.getActoresBSalary());
					$post.setShareBankCard(post.getShareBankCard());
					$post.setShareBankAddress(post.getShareBankAddress());
					$post.setSpeciality(post.getSpeciality());
				}
				if ($post.getEmployeType() == null && $post.getActores() == Actores.YES) {
					$post.setEmployeType(EmployeType.ACTORES);
				}
				if ($post.getEmployeType() == null && this.managerDAO.readManagers(post.getId())) {
					$post.setEmployeType(EmployeType.MANAGER);
				}
				$post.setPFcardinalNumber(post.getPFcardinalNumber());
				$post.setSScardinalNumber(post.getSScardinalNumber());
				$post.setJiaojin(post.getJiaojin());
				$post.setSocialSecurity(post.getSocialSecurity());
				$post.setIdcard(post.getIdcard());
				$post.setHostRegister(post.getHostRegister());
				$post.setAliasname(post.getAliasname());
				$post.setPhone(post.getPhone());
				$post.setQq(post.getQq());
				$post.setEmail(post.getEmail());
				$post.setLiveAdress(post.getLiveAdress());
				$post.setHostAddress(post.getHostAddress());
				$post.setEmergencyContact(post.getEmergencyContact());
				$post.setEmergencyPhone(post.getEmergencyPhone());
				$post.setIntroducer(post.getIntroducer());
				$post.setBirth(post.getBirth());
				$post.setRegularDate(post.getRegularDate());
				$post.setJoinDate(post.getJoinDate());
				$post.setSupervisor(post.getSupervisor());
				$post.setContractDT(post.getContractDT());
				$post.setCpfAccounts(post.getCpfAccounts());
				$post.setBankCard(post.getBankCard());
				$post.setBankAddress(post.getBankAddress());
				$post.setProbationSalary(post.getProbationSalary());
				$post.setSalary(post.getSalary());
				$post.setOtherSubsidy(post.getOtherSubsidy());
				$post.setFoodSubsidyE(post.getFoodSubsidyE());
				$post.setOtherSubsidyE(post.getOtherSubsidyE());
				$post.setJobSubsidyE(post.getJobSubsidyE());
				$post.setDepart(post.getDepart());
				$post.setPosition(post.getPosition());
				$post.setRemarks(post.getRemarks());
				post = $post;
			}
			if (this.employeeDAO.saveEmp(post)) {
				if (post.getEmployeType() == EmployeType.ACTORES || post.getActores() == Actores.YES) {
					this.workManagers.onEvents(WorkNames.EMPLOYEE_ADDACTORES, post);
					this.monitorLogService.logsUpdate(request, "员工: " + post.getName() + " ID: " + post.getId() + "(线下艺人)");
				} else if (post.getEmployeType() == EmployeType.MANAGER) {
					this.workManagers.onEvents(WorkNames.EMPLOYEE_ADDMANAGERS, post);
					this.monitorLogService.logsUpdate(request, "员工: " + post.getName() + " ID: " + post.getId() + "(线下管理)");
				} else if (post.getEmployeType() == EmployeType.TALENT) {
					this.workManagers.onEvents(WorkNames.EMPLOYEE_TALENT, post);
					this.monitorLogService.logsUpdate(request, "员工: " + post.getName() + " ID: " + post.getId() + "(线下星探)");
				} else {
					this.monitorLogService.logsUpdate(request, "员工: " + post.getName() + " ID: " + post.getId());
				}
				this.monitorLogService.logsUpdate(request, "员工:" + post.getName() + ",number:" + post.getNumber() + ",b:" + basic + ",p:" + probation + ",s:" + otherSubsidy);
				return true;
			}

		} else { // 新增
			Employee emp = this.employeeDAO.readEmployeeByBranchs(post.getBranchs());
			BranchOffice $branchs = this.branchOfficeService.readBranchOffice(post.getBranchs());
			if (emp != null) {
				post.setNumber(emp.getNumber() + 1);
			} else {
				post.setNumber(6);
			}
			if ($branchs != null && $branchs.getSocialsecurity() != null) {
				JSONObject json = JSONObject.parseObject($branchs.getSocialsecurity());
				if (post.getSScardinalNumber() == null)
					post.setSScardinalNumber(json.getFloatValue("SScardinalNumber"));
			}
			List<Employee> list = this.employeeDAO.readEmpByName(post.getName());
			if (list != null && list.size() > 0) {
				post.setAccounts(post.getName() + "0" + list.size());
			} else
				post.setAccounts(post.getName());
			if (this.employeeDAO.saveEmp(post)) {
				this.monitorLogService.logsAdd(request, "员工: " + post.getName() + " ID: " + post.getId());
				if (post.getEmployeType() == EmployeType.TALENT) {
					this.workManagers.onEvents(WorkNames.EMPLOYEE_TALENT, post);
				}
				if (post.getEmployeType() == EmployeType.ACTORES) {
					this.workManagers.onEvents(WorkNames.EMPLOYEE_ADDACTORES, post);
				}
				if (post.getEmployeType() == EmployeType.MANAGER) {
					this.workManagers.onEvents(WorkNames.EMPLOYEE_ADDMANAGERS, post);
				}
				return true;
			}

		}

		return false;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readAllPagesProbationer(int pageSize, int curPage, String sortName, String sortOrder, String fastArg, SearchFilter filter) {
		NPageResult rows = this.employeeDAO.readAllPagesProbationer(pageSize, curPage, sortName, sortOrder, fastArg, filter);

		if (rows != null && rows.getData().size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			BranchOffice branchOffice = null;
			List<EmployeeBean> list = new ArrayList<EmployeeBean>();
			for (Object o : rows.getData()) {
				Employee post = (Employee) o;
				EmployeeBean bean = new EmployeeBean();
				bean.setId(post.getId());
				bean.setAliasname(post.getAliasname());
				bean.setBirth(post.getBirth());
				if (post.getBirth() != null)
					bean.setBirthday(sdf.format(post.getBirth()));
				if (post.getJoinDate() != null)
					bean.setIncomeday(sdf.format(post.getJoinDate()));
				if (post.getRegularDate() != null)
					bean.setOutday(sdf.format(post.getRegularDate()));
				if (post.getDepart() != null)
					bean.setDepName(post.getDepart().getName());
				bean.setEmergencyContact(post.getEmergencyContact());
				bean.setEmergencyPhone(post.getEmergencyPhone());
				if (post.getSupervisor() != null)
					bean.setEmpName(post.getSupervisor().getName());
				bean.setHostRegister(post.getHostRegister());
				bean.setIdcard(post.getIdcard());
				if (branchOffice == null) {
					bean.setNum(String.format("%04d", post.getNumber()));
					branchOffice = this.branchOfficeService.readBranchOffice(post.getBranchs());
				}
				if (branchOffice != null)
					bean.setNum(branchOffice.getNumberHead() + String.format("%04d", post.getNumber()));

				bean.setIdImage(post.getIdImage());
				if (post.getGenrer() == Genres.LIZHI) {
					bean.setGenrer("离职");
				} else if (post.getGenrer() == Genres.ZHENGSHI) {
					bean.setGenrer("正式");
				} else if (post.getGenrer() == Genres.MANAGER) {
					bean.setGenrer("经理");
				} else if (post.getGenrer() == Genres.SUPERS) {
					bean.setGenrer("上单");
				} else if (post.getGenrer() == Genres.SHIYONG) {
					bean.setGenrer("试用");
				}
				bean.setIntroducer(post.getIntroducer());
				bean.setJoinDate(post.getJoinDate());
				bean.setLiveAdress(post.getLiveAdress());
				bean.setName(post.getName());
				bean.setPhone(post.getPhone());
				bean.setNamepy(post.getNamepy());
				if (post.getPosition() != null) {
					bean.setPosName(post.getPosition().getName());
				}
				bean.setRegularDate(post.getRegularDate());
				list.add(bean);
			}
			rows.setData(list);
		}
		return rows;
	}

	// 离职人员
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readAllPagesLeave(int pageSize, int curPage, String sortName, String sortOrder, String fastArg, SearchFilter filter) {
		NPageResult rows = this.emplDAO.readAllPagesLeave(pageSize, curPage, sortName, sortOrder, fastArg, filter);
		if (rows != null && rows.getData().size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<EmployeeLeaveBean> list = new ArrayList<EmployeeLeaveBean>();
			BranchOffice branchOffice = null;
			for (Object o : rows.getData()) {
				EmployeeLeave post = (EmployeeLeave) o;
				EmployeeLeaveBean bean = new EmployeeLeaveBean();
				bean.setId(post.getId());
				bean.setAliasname(post.getEmployee().getAliasname());
				bean.setLeaveMsg(post.getLeaveMsg());
				bean.setLeaveTime(post.getEmployee().getLeaveTime());
				bean.setBirth(post.getEmployee().getBirth());
				if (post.getEmployee().getBirth() != null)
					bean.setBirthday(sdf.format(post.getEmployee().getBirth()));
				if (post.getEmployee().getJoinDate() != null)
					bean.setIncomeday(sdf.format(post.getEmployee().getJoinDate()));
				if (post.getEmployee().getRegularDate() != null)
					bean.setOutday(sdf.format(post.getEmployee().getRegularDate()));

				if (post.getEmployee().getDepart() != null)
					bean.setDepName(post.getEmployee().getDepart().getName());
				bean.setEmergencyContact(post.getEmployee().getEmergencyContact());
				bean.setEmergencyPhone(post.getEmployee().getEmergencyPhone());
				bean.setQq(post.getEmployee().getQq());
				if (post.getEmployee().getSupervisor() != null)
					bean.setEmpName(post.getEmployee().getSupervisor().getName());
				bean.setHostRegister(post.getEmployee().getHostRegister());
				bean.setIdcard(post.getEmployee().getIdcard());

				if (post.getEmployee().getSex() == Sex.MAN) {
					bean.setSex("男");
				} else {
					bean.setSex("女");
				}
				if (branchOffice == null) {
					bean.setNum(String.format("%04d", post.getEmployee().getNumber()));
					branchOffice = this.branchOfficeService.readBranchOffice(post.getEmployee().getBranchs());
				}
				if (branchOffice != null)
					bean.setNum(branchOffice.getNumberHead() + String.format("%04d", post.getEmployee().getNumber()));
				bean.setIdImage(post.getEmployee().getIdImage());
				if (post.getEmployee().getGenrer() == Genres.LIZHI) {
					bean.setGenrer("离职");
				} else if (post.getEmployee().getGenrer() == Genres.ZHENGSHI) {
					bean.setGenrer("正式");
				} else if (post.getEmployee().getGenrer() == Genres.SHIYONG) {
					bean.setGenrer("试用");
				} else if (post.getEmployee().getGenrer() == Genres.PENDING) {
					bean.setGenrer("待处理");
				}
				bean.setIntroducer(post.getEmployee().getIntroducer());
				bean.setJoinDate(post.getEmployee().getJoinDate());
				bean.setLiveAdress(post.getEmployee().getLiveAdress());
				bean.setName(post.getEmployee().getName());
				bean.setPhone(post.getEmployee().getPhone());
				bean.setNamepy(post.getEmployee().getNamepy());
				if (post.getEmployee().getPosition() != null) {
					bean.setPosName(post.getEmployee().getPosition().getName());
				}
				bean.setNamepy(post.getEmployee().getNamepy());
				if (post.getEmployee().getAccount() == Account.CHENGZHEN) {
					bean.setAccount("城镇");
				} else {
					bean.setAccount("农村");
				}
				if (post.getEmployee().getEmployeType() == EmployeType.ACTORES) {
					bean.setShareBankCard(post.getEmployee().getShareBankCard());
					bean.setShareBankAddress(post.getEmployee().getShareBankAddress());
					bean.setSpeciality(post.getEmployee().getSpeciality());
				}
				bean.setContractDT(post.getEmployee().getContractDT());
				bean.setCpfAccounts(post.getEmployee().getCpfAccounts());
				bean.setBankCard(post.getEmployee().getBankCard());
				bean.setBankAddress(post.getEmployee().getBankCard());
				bean.setProbationSalary(post.getEmployee().getProbationSalary());
				bean.setSalary(post.getEmployee().getSalary());

				bean.setRemarks(post.getEmployee().getRemarks());
				bean.setRegularDate(post.getEmployee().getRegularDate());
				list.add(bean);
			}
			rows.setData(list);
		}
		return rows;
	}

	@Override
	public boolean updateEmployee(Integer id, Integer type, String reason, HttpServletRequest request) {
		if (id != null) {
			Employee employee = this.readEmployee(id);
			if (employee != null) {
				if (type == 1) {
					employee.setGenrer(Genres.ZHENGSHI);
					if (this.employeeDAO.saveEmp(employee)) {
						this.workManagers.onEvents(WorkNames.EMPLOYEE_ZHUANZHENG, employee);
						this.monitorLogService.logsUpdate(request, "员工: " + employee.getName() + " ID: " + employee.getId() + "  (转正)");
						return true;
					}
				} else if (type == 2) {
					RoleUsers users = (RoleUsers) this.usersDAO.readRoleUsersByUserId(employee.getId());
					if (users != null) {
						users.setUser(null);
						users.getDetails().clear();
						this.usersDAO.getBaseDAO().delete(users);
					}
					Date leaveTime = new Date();
					if (!this.emplDAO.readempleave(id)) {
						EmployeeLeave empl = new EmployeeLeave(); // 加进离职表
						empl.setBranchs(employee.getBranchs());
						empl.setGenrer(employee.getGenrer());
						empl.setLeaveTime(leaveTime);
						empl.setLeaveMsg(reason);
						empl.setEmployee(employee);
						this.emplDAO.saveEmpl(empl);
					}
					employee.setIslogin(Login.NO);
					employee.setGenrer(Genres.PENDING);
					employee.setLeaveTime(leaveTime);
					if (this.employeeDAO.saveEmp(employee)) {
						this.monitorLogService.logsUpdate(request, "员工: " + employee.getName() + " ID: " + employee.getId() + "  (离职)");
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean addEmp(Employee post) {
		if (post != null && post.getId() != null && post.getId() > 0) {
			Employee $post = this.readEmployee(post.getId());
			if ($post != null) {
				$post.setRegularDate(post.getRegularDate());
				post = $post;
			}
		}
		return this.employeeDAO.saveEmp(post);
	}

	@Override
	public List<Employee> readAllEmpByPid(Integer id) {
		if (id != null) {
			List<Employee> list = this.employeeDAO.readAllEmpByPid(id);
			return list;
		}
		return null;
	}

	@Override
	public List<Employee> findAllEmployee() {
		return this.employeeDAO.readAllEmployee();
	}

	@Override
	public boolean saveEmployee(Employee emp) {
		return this.employeeDAO.saveEmp(emp);
	}

	@Override
	public List<Employee> findEmployeeByPending() {
		return this.employeeDAO.findEmployeeByPending();
	}

	@Override
	public List<Employee> findAllEmployees() {
		return this.employeeDAO.readAllEmployees();
	}

	@Override
	public List<Employee> findAllEmployees(Date date2) {
		return this.employeeDAO.readAllEmployees(date2);
	}

	@Override
	public Employee readEmployeeByNumber(Integer number) {
		if (number != null && number > 0) {
			Employee employee = this.employeeDAO.readEmployeeByNumber(number);
			return employee;
		}
		return null;
	}

	@Override
	public List<Employee> findAllEmployeesNeLeave() {
		return this.employeeDAO.findAllEmployeesNeLeave();
	}

	@Override
	public EmployeeLeave findEmployeeLeaveByEmpId(Integer id) {
		if (id != null && id.intValue() > 0) {
			return this.emplDAO.findEmployeeLeaveByEmpId(id);
		}
		return null;
	}

	@Override
	public EmployeeLeave readEmployeeLeave(Integer id) {
		if (id != null && id.intValue() > 0) {
			return this.employeeDAO.readEmployeeLeave(id);
		}
		return null;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readPagesBirth(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter) {
		NPageResult rows = this.employeeDAO.readPagesBirth(pageSize, curPage, sortName, sortOrder, filter);
		if (rows != null && rows.getData().size() > 0) {
			BranchOffice branchOffice = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<EmployeeBean> list = new ArrayList<EmployeeBean>();
			for (Object o : rows.getData()) {
				Employee post = (Employee) o;
				EmployeeBean bean = new EmployeeBean();
				bean.setId(post.getId());
				bean.setAliasname(post.getAliasname());
				bean.setBirth(post.getBirth());
				if (post.getBirth() != null)
					bean.setBirthday(sdf.format(post.getBirth()));
				if (post.getJoinDate() != null)
					bean.setIncomeday(sdf.format(post.getJoinDate()));
				if (post.getRegularDate() != null)
					bean.setOutday(sdf.format(post.getRegularDate()));
				if (post.getDepart() != null)
					bean.setDepName(post.getDepart().getName());
				bean.setEmergencyContact(post.getEmergencyContact());
				bean.setEmergencyPhone(post.getEmergencyPhone());
				if (post.getSupervisor() != null)
					bean.setEmpName(post.getSupervisor().getName());
				bean.setHostRegister(post.getHostRegister());
				bean.setIdcard(post.getIdcard());

				if (branchOffice == null) {
					bean.setNum(String.format("%04d", post.getNumber()));
					branchOffice = this.branchOfficeService.readBranchOffice(post.getBranchs());
				}
				if (branchOffice != null)
					bean.setNum(branchOffice.getNumberHead() + String.format("%04d", post.getNumber()));
				bean.setIdImage(post.getIdImage());
				if (post.getGenrer() == Genres.LIZHI) {
					bean.setGenrer("离职");
				} else if (post.getGenrer() == Genres.ZHENGSHI) {
					bean.setGenrer("正式");
				} else if (post.getGenrer() == Genres.SHIYONG) {
					bean.setGenrer("试用");
				}
				if (post.getSex() == Sex.MAN) {
					bean.setSex("男");
				} else {
					bean.setSex("女");
				}

				bean.setIntroducer(post.getIntroducer());
				bean.setJoinDate(post.getJoinDate());
				bean.setLiveAdress(post.getLiveAdress());
				bean.setName(post.getAccounts());
				bean.setQq(post.getQq());
				bean.setPhone(post.getPhone());
				bean.setNamepy(post.getNamepy());
				if (post.getPosition() != null) {
					bean.setPosName(post.getPosition().getName());
				}
				bean.setRegularDate(post.getRegularDate());

				if (post.getContractDT() != null)
					bean.setContractDT2(sdf.format(post.getContractDT()));

				if (post.getAccount() == Account.CHENGZHEN) {
					bean.setAccount("城镇");
				} else {
					bean.setAccount("农村");
				}
				if (post.getActores() == Actores.NO) {
					bean.setActores("否");
				} else {
					bean.setActores("是");
				}
				bean.setCpfAccounts(post.getCpfAccounts());
				bean.setBankCard(post.getBankCard());
				bean.setBankAddress(post.getBankCard());
				Float probationSalary = Float.valueOf(0);
				if (post.getProbationSalary() != null)
					probationSalary = post.getProbationSalary();
				bean.setProbationSalary(probationSalary);
				if (post.getOtherSubsidy() != null)
					bean.setOtherSubsidy(post.getOtherSubsidy());
				if (post.getSalary() != null)
					bean.setSalary(post.getSalary());
				if (post.getShareBankAddress() != null)
					bean.setShareBankCard(post.getShareBankCard());
				bean.setShareBankAddress(post.getShareBankAddress());
				bean.setSpeciality(post.getSpeciality());
				bean.setRemarks(post.getRemarks());
				list.add(bean);
			}
			rows.setData(list);
		}
		return rows;
	}

	@Override
	public boolean saveTransferEmployee(EmployeeTransfer post, HttpServletRequest request) {
		return this.employeeDAO.saveTransferEmployee(post);
	}

	@Override
	public Employee readEmployeebyNumber(Integer number, Integer branchs) {
		return this.employeeDAO.readEmployeebyNum(number, branchs);
	}

	@Override
	public boolean updateEmp(Employee post) {
		if (post != null && post.getId() != null && post.getId() > 0) {
			Employee $post = this.readEmployee(post.getId());
			if ($post != null) {
				$post.setOtherSubsidy(post.getOtherSubsidy());
				$post.setPushMoney(post.getPushMoney());
				post = $post;
			}
		}
		return this.employeeDAO.saveEmp(post);
	}

}
