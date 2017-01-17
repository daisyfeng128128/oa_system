package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.DepartmentBean;
import com.baofeng.oa.bean.DepartmentComboxBean;
import com.baofeng.oa.bean.TreeviewBean;
import com.baofeng.oa.dao.DepartmentDAO;
import com.baofeng.oa.entity.Department;
import com.baofeng.oa.service.IDepartmentService;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentDAO departmentDAO;
	@Autowired
	private IMonitorLogService monitorLogService;

	@Override
	public boolean deleteDepartment(Integer id, HttpServletRequest request) {
		if (id != null && id.intValue() > 0) {
			Department department = this.readDepartment(id);
			if (department != null) {
				department.setStatus(EntityStatus.DELETED);

				if (this.departmentDAO.deleteDepartment(department)) {
					this.monitorLogService.logsDelete(request, "部门: " + department.getName() + " ID: " + department.getId());
					return true;
				}
			}
		}
		return false;
	}

	public Department readDepartment(Integer id) {
		if (id != null && id.intValue() > 0) {
			Department department = this.departmentDAO.readDepartment(id);
			return department;
		}
		return null;
	}

	@Override
	public boolean addDepartment(Department poster, HttpServletRequest request) {
		if (poster != null && poster.getId() != null && poster.getId().intValue() > 0) {
			Department $post = this.readDepartment(poster.getId());
			if ($post != null) {
				$post.setManager(poster.getManager());
				$post.setName(poster.getName());
				$post.setParent(poster.getParent());
				poster = $post;
			}
			if (this.departmentDAO.saveDepartment(poster)) {
				this.monitorLogService.logsUpdate(request, "部门: " + poster.getName() + " ID: " + poster.getId());
				return true;
			}
			return false;
		}

		if (this.departmentDAO.saveDepartment(poster)) {
			this.monitorLogService.logsAdd(request, "部门: " + poster.getName() + " ID: " + poster.getId());
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResult readAllPagesSkip(int rows, int page, SearchFilter filter, String queryFilter) {
		return this.departmentDAO.readAllPagesSkip(rows, page, filter, queryFilter);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResult readPagesParent(int rows, int page, Integer did, SearchFilter filter, String queryFilter) {
		PageResult $page = this.departmentDAO.readAllPagesParent(rows, page, filter, queryFilter);
		if (did != null && did > 0) {
			if ($page != null && $page.getRows().size() > 0) {
				List<DepartmentComboxBean> list = new ArrayList<DepartmentComboxBean>();
				for (Iterator<Department> it = $page.getRows().iterator(); it.hasNext();) {
					Department post = it.next();
					if (did != post.getId() && (post.getParent() == null || did != post.getParent().getId())) {
						DepartmentComboxBean bean = new DepartmentComboxBean();
						bean.setId(post.getId());
						bean.setName(post.getName());
						list.add(bean);
					}
				}
				$page.setRows(list);
			}
		} else {
			if ($page != null && $page.getRows().size() > 0) {
				List<DepartmentComboxBean> list = new ArrayList<DepartmentComboxBean>();
				for (Iterator<Department> it = $page.getRows().iterator(); it.hasNext();) {
					Department post = it.next();
					DepartmentComboxBean bean = new DepartmentComboxBean();
					bean.setId(post.getId());
					bean.setName(post.getName());
					list.add(bean);
				}
				$page.setRows(list);
			}
		}
		return $page;
	}

	@Override
	public boolean readDepartmentByParentId(Integer parentid) {
		return this.departmentDAO.readDepartmentByParentId(parentid);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String name, SearchFilter filter) {
		NPageResult rows = this.departmentDAO.readAllPages(pageSize, curPage, sortName, sortOrder, name, filter);
		if (rows != null && rows.getData().size() > 0) {
			List<DepartmentBean> list = new ArrayList<DepartmentBean>();
			for (Object o : rows.getData()) {
				Department post = (Department) o;
				DepartmentBean bean = new DepartmentBean();
				bean.setName(post.getName());
				bean.setCreateDT(post.getCreateDT());
				bean.setId(post.getId());
				if (post.getManager() != null)
					bean.setManager(post.getManager().getName());
				if (post.getParent() != null)
					bean.setParent(post.getParent().getName());
				list.add(bean);
			}
			rows.setData(list);
		}
		return rows;
	}

	@Override
	public List<Department> readAllDepartment(SearchFilter filter) {
		return this.departmentDAO.readAllDepartment(filter);
	}

	@Override
	public List<TreeviewBean> readTreeViewBeans(SearchFilter filter) {
		List<TreeviewBean> result = new ArrayList<TreeviewBean>();
		List<Department> deptList = this.readAllDepartment(filter);
		if (deptList != null && deptList.size() > 0) {
			for (Department dept : deptList) {
				if (dept != null && dept.getParent() == null) {
					TreeviewBean bean = new TreeviewBean();
					bean.setId(dept.getId());
					bean.setText(dept.getName());
					bean.setNodes(buildNodes(dept, deptList));
					result.add(bean);
				}
			}
		}
		return result;
	}

	private List<TreeviewBean> buildNodes(Department dept, List<Department> deptList) {
		List<TreeviewBean> result = new ArrayList<TreeviewBean>();
		if (deptList != null && deptList.size() > 0) {
			for (Department $dept : deptList) {
				if ($dept != null && $dept.getParent() != null && $dept.getParent().getId() == dept.getId()) {
					TreeviewBean bean = new TreeviewBean();
					bean.setId($dept.getId());
					bean.setText($dept.getName());
					List<TreeviewBean> $rs = buildNodes($dept, deptList);
					if($rs != null && $rs.size() > 0){
						bean.setNodes($rs);
					}
					result.add(bean);
				}
			}
		}
		return result;
	}
}
