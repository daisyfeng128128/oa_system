package com.baofeng.oa.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.ElectEquipentBean;
import com.baofeng.oa.bean.OfficeSuppliesBean;
import com.baofeng.oa.dao.PurchaseAuditDAO;
import com.baofeng.oa.dao.PurchaseDAO;
import com.baofeng.oa.entity.BaseEnums.Auditp;
import com.baofeng.oa.entity.BaseEnums.Purchase;
import com.baofeng.oa.entity.ElectEquipent;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.OfficeSupplies;
import com.baofeng.oa.service.IDepartmentService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IPurchaseAuditService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("purchaseAuditService")
public class PurchaseAuditServiceImpl implements IPurchaseAuditService {
	
	@Autowired
	private PurchaseAuditDAO purchaseAuditDAO;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private PurchaseDAO purchaseDAO;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readPages(int pageSize, int curPage, String sortName,String sortOrder, Integer id,String fastArg,Integer type,SearchFilter searchFilter,String classQuery) {
		NPageResult rows = this.purchaseAuditDAO.readPages(pageSize, curPage, sortName, sortOrder, id, fastArg, searchFilter,type,classQuery);
		if (rows != null && rows.getData().size() > 0) {
			List<ElectEquipentBean> beanList = new ArrayList<ElectEquipentBean>();
			for (Object o : rows.getData()) {
				ElectEquipent electEquipent = (ElectEquipent) o;
				ElectEquipentBean bean = new ElectEquipentBean();
				bean.setId(electEquipent.getId());
				bean.setPurType(electEquipent.getPurType());
				bean.setBranchs(electEquipent.getBranchs().toString());
				bean.setNum(String.valueOf(electEquipent.getEmployee().getNumber()));
				bean.setRealname(electEquipent.getRealname());
				bean.setDepart(electEquipent.getDepart());
				bean.setPassType(electEquipent.getPassType());
				bean.setAuditp(electEquipent.getAuditp() == Auditp.PENDING ? "待审核" : (electEquipent.getAuditp() == Auditp.UNKNOWN)? "审核中" : (electEquipent.getAuditp() == Auditp.PASSED ? "通过" : "未通过"));
				bean.setPurchase(electEquipent.getPurchase() == Purchase.UNKNOWN ? "采购中" : (electEquipent.getPurchase() == Purchase.PASSED ? "采购完成" : electEquipent.getPurchase() == Purchase.PENDING ? "待采购" : ""));
				bean.setApplyDT(electEquipent.getApplyDT());
				bean.setRemarks(electEquipent.getRemarks());
				if (electEquipent.getCompleteDT() != null) {
					bean.setCompleteDT(electEquipent.getCompleteDT());
				}
				if (electEquipent.getRejectMsg() != null) {
					bean.setRejectMsg(electEquipent.getRejectMsg());
				}
				bean.setGoodsname(electEquipent.getGoodsname());
				bean.setModel(electEquipent.getModel());
				bean.setNumbers(electEquipent.getNumbers().toString());
				bean.setPlace(electEquipent.getPlace());
				bean.setTaxMoney(electEquipent.getTaxMoney().toString());
				bean.setPriceMoney(electEquipent.getPriceMoney().toString());
				bean.setTotalMoney(electEquipent.getTotalMoney().toString());
				bean.setProcess(electEquipent.getProcess());
				beanList.add(bean);
			}
			rows.setData(beanList);
		}
		return rows;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NPageResult<?> readPagesR(int pageSize, int curPage,String sortName, String sortOrder, Integer type,Integer id,String fastArg,SearchFilter searchFilter,String classQuery) {
		NPageResult rows = this.purchaseAuditDAO.readPagesOff(pageSize, curPage, sortName, sortOrder, fastArg,searchFilter,type,id,classQuery);
		if (rows != null && rows.getData().size() > 0) {
			List<OfficeSuppliesBean> beanList = new ArrayList<OfficeSuppliesBean>();
			for (Object o : rows.getData()) {
				OfficeSupplies officeSupplies = (OfficeSupplies) o;
				OfficeSuppliesBean bean = new OfficeSuppliesBean();
				bean.setId(officeSupplies.getId());
				bean.setBranchs(officeSupplies.getBranchs().toString());
				bean.setPurType(officeSupplies.getPurType());
				bean.setPassType(officeSupplies.getPassType());
				bean.setNum(String.valueOf(officeSupplies.getEmployee().getNumber()));
				bean.setRealname(officeSupplies.getRealname());
				bean.setDepart(officeSupplies.getDepart());
				bean.setAuditp(officeSupplies.getAuditp() == Auditp.PENDING ? "待审核" : (officeSupplies.getAuditp() == Auditp.UNKNOWN)? "审核中" : (officeSupplies.getAuditp() == Auditp.PASSED ? "通过" : "未通过"));
				bean.setApplyDT(officeSupplies.getApplyDT());
				bean.setGoodsname(officeSupplies.getGoodsname());
				bean.setModel(officeSupplies.getModel());
				bean.setRemarks(officeSupplies.getRemarks());
				if (officeSupplies.getRejectMsg() != null) {
					bean.setRejectMsg(officeSupplies.getRejectMsg());
				}
				bean.setPurchase(officeSupplies.getPurchase() == Purchase.UNKNOWN ? "采购中" : (officeSupplies.getPurchase() == Purchase.PASSED ? "采购完成" : officeSupplies.getPurchase() == Purchase.PENDING ? "待采购" : ""));
				bean.setApplyDT(officeSupplies.getApplyDT());
				if (officeSupplies.getCompleteDT() != null) {
					bean.setCompleteDT(officeSupplies.getCompleteDT());
				}
				bean.setNumbers(officeSupplies.getNumbers().toString());
				bean.setTaxMoney(officeSupplies.getTaxMoney().toString());
				bean.setPriceMoney(officeSupplies.getPriceMoney().toString());
				bean.setTotalMoney(officeSupplies.getTotalMoney().toString());
				bean.setProcess(officeSupplies.getProcess());
				beanList.add(bean);
			}
			rows.setData(beanList);
		}
		return rows;
	}

	@Override
	public boolean updatedeptPass(List<String> list, RoleUsers users,HttpServletRequest request) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
			ElectEquipent electEquipent = this.purchaseDAO.readElectEquipent(Integer.valueOf(id));
			Employee employee = this.employeeService.readEmployee(users.getUser().getId());
			if (electEquipent != null) {
				electEquipent.setAuditp(Auditp.UNKNOWN);
				electEquipent.setPassType(1);
				String date = CustomDateUtils.format(new Date(), CustomDateUtils.format6);
				electEquipent.setReason(date+"  "+employee.getName() + ":通过部门审核"+ "</br>");
				electEquipent.setProcess(2);
				this.purchaseDAO.saveElectEquipent(electEquipent);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updateequiPass(List<String> list, RoleUsers users,HttpServletRequest request) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
			ElectEquipent electEquipent = this.purchaseDAO.readElectEquipent(Integer.valueOf(id));
			Employee employee = this.employeeService.readEmployee(users.getUser().getId());
			if (electEquipent != null) {
				String $applies = electEquipent.getReason() != null ? electEquipent.getReason() : "";
				electEquipent.setAuditp(Auditp.UNKNOWN);
				electEquipent.setPassType(2);
				String date = CustomDateUtils.format(new Date(), CustomDateUtils.format6);
				electEquipent.setReason(date+"  "+employee.getName() + ":通过设备人员审核"+ "</br>" + $applies);
				electEquipent.setProcess(3);
				this.purchaseDAO.saveElectEquipent(electEquipent);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updatepersonPass(List<String> list, RoleUsers users,HttpServletRequest request) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
			ElectEquipent electEquipent = this.purchaseDAO.readElectEquipent(Integer.valueOf(id));
			Employee employee = this.employeeService.readEmployee(users.getUser().getId());
			if (electEquipent != null) {
				electEquipent.setAuditp(Auditp.UNKNOWN);
				electEquipent.setPassType(3);
				String date = CustomDateUtils.format(new Date(), CustomDateUtils.format6);
				String $applies = electEquipent.getReason() != null ? electEquipent.getReason() : "";
				electEquipent.setReason(date+"  "+employee.getName() + ":通过负责人审核"+ "</br>" + $applies);
				electEquipent.setProcess(4);
				this.purchaseDAO.saveElectEquipent(electEquipent);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updateheadPass(List<String> list, RoleUsers users,HttpServletRequest request) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
			ElectEquipent electEquipent = this.purchaseDAO.readElectEquipent(Integer.valueOf(id));
			if (electEquipent != null) {
				electEquipent.setAuditp(Auditp.PASSED);
				electEquipent.setPassType(4);
				electEquipent.setPurchase(Purchase.PENDING);
				electEquipent.setProcess(5);
				this.purchaseDAO.saveElectEquipent(electEquipent);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updatestartEl(List<String> list, RoleUsers users,HttpServletRequest request) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
			ElectEquipent electEquipent = this.purchaseDAO.readElectEquipent(Integer.valueOf(id));
			if (electEquipent != null) {
				electEquipent.setPurchase(Purchase.UNKNOWN);
				electEquipent.setProcess(6);
				this.purchaseDAO.saveElectEquipent(electEquipent);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updatendEl(ElectEquipentBean bean, RoleUsers users,HttpServletRequest request) {
		if (bean.getId() != null && bean.getId().intValue() > 0) {
			ElectEquipent electEquipent = this.purchaseDAO.readElectEquipent(bean.getId());
			if (electEquipent != null) {
				DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				try {
					electEquipent.setCompleteDT(sdf.parse(sdf.format(new Date())));
					electEquipent.setPurchase(Purchase.PASSED);
					electEquipent.setRemarks(bean.getRemarks());
					electEquipent.setProcess(7);
					if (this.purchaseDAO.saveElectEquipent(electEquipent)) {
						return true;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public boolean updatedeptPassOff(List<String> list, RoleUsers users,HttpServletRequest request) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
			OfficeSupplies officeSupplies = this.purchaseDAO.readOfficeSupplies(Integer.valueOf(id));
			Employee employee = this.employeeService.readEmployee(users.getUser().getId());
			if (officeSupplies != null) {
				officeSupplies.setAuditp(Auditp.UNKNOWN);
				officeSupplies.setPassType(1);
				officeSupplies.setProcess(2);
				String date = CustomDateUtils.format(new Date(), CustomDateUtils.format6);
				officeSupplies.setReason(date+"  "+employee.getName() +":通过部门审核"+ "</br>");
				this.purchaseDAO.saveOfficeSupplies(officeSupplies);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updatepersonPassOff(List<String> list,RoleUsers users, HttpServletRequest request) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
			OfficeSupplies officeSupplies = this.purchaseDAO.readOfficeSupplies(Integer.valueOf(id));
			Employee employee = this.employeeService.readEmployee(users.getUser().getId());
			if (officeSupplies != null) {
				officeSupplies.setAuditp(Auditp.UNKNOWN);
				officeSupplies.setPassType(2);
				officeSupplies.setProcess(3);
				String date = CustomDateUtils.format(new Date(), CustomDateUtils.format6);
				String $applies = officeSupplies.getReason() != null ? officeSupplies.getReason() : "";
				officeSupplies.setReason(date+"  "+employee.getName() +":通过负责人审核"+ "</br>" + $applies);
				this.purchaseDAO.saveOfficeSupplies(officeSupplies);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updateheadPassOff(List<String> list, RoleUsers users,HttpServletRequest request) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
			OfficeSupplies officeSupplies = this.purchaseDAO.readOfficeSupplies(Integer.valueOf(id));
			if (officeSupplies != null) {
				officeSupplies.setAuditp(Auditp.PASSED);
				officeSupplies.setPassType(3);
				officeSupplies.setProcess(4);
				officeSupplies.setPurchase(Purchase.PENDING);
				this.purchaseDAO.saveOfficeSupplies(officeSupplies);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updatestartOff(List<String> list, RoleUsers users,HttpServletRequest request) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
			OfficeSupplies officeSupplies = this.purchaseDAO.readOfficeSupplies(Integer.valueOf(id));
			if (officeSupplies != null) {
				officeSupplies.setPurchase(Purchase.UNKNOWN);
				officeSupplies.setProcess(5);
				this.purchaseDAO.saveOfficeSupplies(officeSupplies);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updatendOff(OfficeSuppliesBean bean, RoleUsers users,HttpServletRequest request) {
		if (bean.getId() != null && bean.getId().intValue() > 0) {
			OfficeSupplies officeSupplies = this.purchaseDAO.readOfficeSupplies(bean.getId());
			if (officeSupplies != null) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
				try {
					officeSupplies.setCompleteDT(sdf.parse(sdf.format(new Date())));
					officeSupplies.setPurchase(Purchase.PASSED);
					officeSupplies.setRemarks(bean.getRemarks());
					officeSupplies.setProcess(6);
					if (this.purchaseDAO.saveOfficeSupplies(officeSupplies)) {
						return true;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public boolean updateRejEl(ElectEquipentBean bean, RoleUsers users,HttpServletRequest request) {
		if (bean.getId() != null && bean.getId().intValue() > 0) {
			ElectEquipent electEquipent = this.purchaseDAO.readElectEquipent(bean.getId());
			if (electEquipent != null) {
				electEquipent.setAuditp(Auditp.NOTPASS);
				electEquipent.setRejectMsg(bean.getRejectMsg());
				if (this.purchaseDAO.saveElectEquipent(electEquipent)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean updateRejOff(OfficeSuppliesBean bean, RoleUsers users,HttpServletRequest request) {
		if (bean.getId() != null && bean.getId().intValue() > 0) {
			OfficeSupplies officeSupplies = this.purchaseDAO.readOfficeSupplies(bean.getId());
			if (officeSupplies != null) {
				officeSupplies.setAuditp(Auditp.NOTPASS);
				officeSupplies.setRejectMsg(bean.getRejectMsg());
				if (this.purchaseDAO.saveOfficeSupplies(officeSupplies)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<ElectEquipent> readElectEquipentbyBranch(Integer branchs) {
		return this.purchaseAuditDAO.findElectEquipentbyBranch(branchs);
	}

	@Override
	public List<OfficeSupplies> readOfficeSuppliesbyBranch(Integer branchs) {
		return this.purchaseAuditDAO.findOfficeSuppliesbyBranch(branchs);
	}

	@Override
	public ElectEquipent checkReasonEl(ElectEquipentBean bean, RoleUsers users,HttpServletRequest request) {
		return this.purchaseDAO.readElectEquipent(bean.getId());
	}

	@Override
	public ElectEquipent checkReason(Integer id) {
		return this.purchaseDAO.readElectEquipent(id);
	}

	@Override
	public OfficeSupplies checkReasonOff(Integer id) {
		return this.purchaseDAO.readOfficeSupplies(id);
	}


}