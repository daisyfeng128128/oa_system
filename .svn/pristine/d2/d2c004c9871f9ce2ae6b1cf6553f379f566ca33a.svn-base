package com.baofeng.oa.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.InterviewBean;
import com.baofeng.oa.dao.InterviewDAO;
import com.baofeng.oa.entity.BaseEnums.Interviews;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.Interview;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.service.IInterviewService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Service("interviewService")
public class InterviewServiceImpl implements IInterviewService {

	@Autowired
	private InterviewDAO interviewDAO;
	@Autowired
	private IPlatformsService platformsService; 
	

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<String> readAllInterviewDate() {
		List<Interview> list = this.interviewDAO.readAllInterviewDate();
		Set<String> $list = new TreeSet<>(new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				String[] str1 = s1.split("-");
				String[] str2 = s2.split("-");
				int num = Integer.valueOf(str2[0]) - Integer.valueOf(str1[0]);
				int num1 = num == 0 ? Integer.valueOf(str2[1]) - Integer.valueOf(str1[1]) : num;
				return num1;
			}
		});
		if (list != null && list.size() > 0) {
			for (Interview months : list) {
				$list.add(Constants.convert(months.getCreateDT(), Constants.format7));
			}
		}
		return new ArrayList($list);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, String date, Integer type, String fastArg, List<RoleDetailsAtts> platList,SearchFilter searchFilter,boolean flag) {
		Date create = CustomDateUtils.format(date, CustomDateUtils.format7);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(create);
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(create);
		NPageResult page = this.interviewDAO.readPages(pageSize, curPage, sortName, sortOrder, date1, date2, type, fastArg,platList,searchFilter,flag);
		List<InterviewBean> listBean = new ArrayList<InterviewBean>();
		if (page != null && page.getData() != null && page.getData().size() > 0) {
			for (Object o : page.getData()) {
				Interview post = (Interview) o;
				InterviewBean bean = new InterviewBean();
				bean.setLaunchTime(post.getLaunchTime());
				bean.setAliasname(post.getAliasname());
				bean.setChTransition(post.getChTransition());
				if (post.getPhoto() != null)
					bean.setPhoto(Constants.DEFAULT_HTTPIMAGES + "/" + post.getPhoto());
				if (post.getVideoPhoto() != null)
					bean.setVideoPhoto(Constants.DEFAULT_HTTPIMAGES + "/" + post.getVideoPhoto());
				bean.setCreateDT(post.getCreateDT());
				bean.setGenre(post.getGenre() == LineGenres.OFFLINE ? "线下" : "线上");
				bean.setId(post.getId().toString());
				bean.setInterview(post.getInterview() == Interviews.CHECK ? "审核中" : post.getInterview() == Interviews.NOTPASS ? "未通过"
						: post.getInterview() == Interviews.PASS ? "通过" : post.getInterview() == Interviews.PENDING ? "待处理" : post.getInterview()==Interviews.WAITENTRY ?"待入职":"初试");
				bean.setIntroducer(post.getIntroducer());
				bean.setLinkUrl(post.getLinkUrl());
				bean.setName(post.getName());
				bean.setPhone(post.getPhone());
				bean.setPlat(post.getPlat() != null ? post.getPlat().getPlatName() : "");
				bean.setQq(post.getQq());
				bean.setRemark(post.getRemark());
				bean.setSex(post.getSex() == Sex.WOMAN ? "女" : "男");
				bean.setStatusTracking(post.getStatusTracking());
				bean.setTrackingResults(post.getTrackingResults());
				listBean.add(bean);
			}
			page.setData(listBean);
		}
		return page;
	}

	@Override
	public PageResult<?> readAllPagesSkip(int rows, int page, SearchFilter filter, String queryFilter) {
		return this.interviewDAO.readAllPagesSkip(rows, page, filter, queryFilter);
	}

	@Override
	public boolean addInterview(Interview post) {
		if (post != null && post.getId() != null && post.getId().intValue() > 0) {
			Interview $post = this.interviewDAO.readInterview(post.getId());
			if ($post != null) {
				$post.setAliasname(post.getAliasname());
				$post.setGenre(post.getGenre());
				$post.setIntroducer(post.getIntroducer());
				$post.setLinkUrl(post.getLinkUrl());
				$post.setPhone(post.getPhone());
				$post.setQq(post.getQq());
				$post.setSex(post.getSex());
				$post.setRemark(post.getRemark());
				if (post.getPhoto() != null) {
					$post.setPhoto(post.getPhoto());
				}
				if (post.getVideoPhoto() != null) {
					$post.setVideoPhoto(post.getVideoPhoto());
				}
				post = $post;
			}
		}
		if (this.interviewDAO.addInterview(post)) {
			return true;
		}
		return false;
	}

	@Override
	public Interview readInterview(String id) {
		if(id != null && id.trim().length()>0){
			return this.interviewDAO.readInterview(Integer.valueOf(id));
		}
		return null;
	}

	@Override
	public boolean delete(String id) {
		if(id != null && id.trim().length()>0){
			Interview inter = this.interviewDAO.readInterview(Integer.valueOf(id));
			if(inter != null){
				inter.setPlat(null);
				inter.setStatus(EntityStatus.DELETED);
				return this.interviewDAO.addInterview(inter);
			}
		}
		return false;
	}

	@Override
	public boolean addInterviewSuccess(String interId,String platId) {
		if(interId != null && interId.trim().length()>0){
			Interview inter = this.interviewDAO.readInterview(Integer.valueOf(interId));
			if(inter != null){
				inter.setInterview(Interviews.PASS);
				return this.interviewDAO.addInterview(inter);
			}
		}
		return false;
	}

	@Override
	public boolean addNoEntry(String id) {
		if(id != null && id.trim().length()>0){
			Interview interview = this.interviewDAO.readInterview(Integer.valueOf(id));
			if(interview != null ){
				String statusTracking = interview.getStatusTracking()+"</br>"+"艺人未前来入职";
				interview.setStatusTracking(statusTracking);
				interview.setInterview(Interviews.NOTPASS);
				return this.interviewDAO.addInterview(interview);
			}
		}
		return false;
	}

	@Override
	public boolean addPlat(Interview inter) {
		if(inter != null && inter.getId() != null && inter.getId().intValue()>0){
		     Interview $inter = this.interviewDAO.readInterview(inter.getId());
		     $inter.setChTransition(inter.getChTransition());
		     $inter.setInterview(Interviews.CHECK);
		     $inter.setPlat(inter.getPlat());
		     inter = $inter;
		}
		if(this.interviewDAO.addInterview(inter)){
			return true;
		}
		return false;
	}

	@Override
	public boolean addReje(String id, String track, RoleUsers users) {
		if(id != null && id.trim().length()>0){
			Interview inter = this.interviewDAO.readInterview(Integer.valueOf(id));
			if(inter != null ){
				track = inter.getStatusTracking()+"</br>"+inter.getPlat().getPlatName()+users.getUser().getAccounts() + "拒收:"+track;
				inter.setStatusTracking(track);
				inter.setInterview(Interviews.PENDING);
				return this.interviewDAO.addInterview(inter);
			}
		}
		return false;
	}

	@Override
	public boolean addEmpl(String id, String launchTime, RoleUsers users) {
		if(id != null && id.trim().length()>0){
			Interview inter = this.interviewDAO.readInterview(Integer.valueOf(id));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date =null;
			try {
				date = sdf.parse(launchTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(inter != null ){
				String track = inter.getStatusTracking()+"</br>"+inter.getPlat().getPlatName()+users.getUser().getAccounts() + ":"+"录用艺人";
				inter.setStatusTracking(track);
				inter.setLaunchTime(date);
				inter.setInterview(Interviews.WAITENTRY);
				return this.interviewDAO.addInterview(inter);
			}
		}
		return false;
	}

	@Override
	public boolean addInter(String interId, RoleUsers users,String beliveTime) {
		if(interId != null && interId.trim().length()>0){
			
			Interview inter = this.interviewDAO.readInterview(Integer.valueOf(interId));
			if(inter != null  ){
				String track = inter.getStatusTracking()+"</br>"+inter.getPlat().getPlatName()+users.getUser().getAccounts() + ":"+"录用艺人";
				inter.setLaunchTime(Constants.convert(beliveTime, Constants.format1));
				inter.setStatusTracking(track);
				inter.setInterview(Interviews.PASS);
				return this.interviewDAO.addInterview(inter);
			}
		}
		return false;
	}

	@Override
	public boolean addTrackingResults(String id, String trackingResults) {
		if(id != null && id.trim().length()>0){
			Interview interview = this.interviewDAO.readInterview(Integer.valueOf(id));
			if(interview != null){
				interview.setTrackingResults(trackingResults);
				return this.addInterview(interview);
			}
		}
		return false;
	}

	@Override
	public boolean updateNoPassPre(String id,RoleUsers users) {
		if(id != null && id.trim().length()>0){
			Interview inter = this.interviewDAO.readInterview(Integer.valueOf(id));
			if(inter != null){
				inter.setInterview(Interviews.NOTPASS);
				inter.setStatusTracking(inter.getStatusTracking()+"</br>"+users.getUser().getAccounts()+"拒收此艺人");
				if(this.interviewDAO.addInterview(inter)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean addPrePass(String id, String chTransition, String plat,RoleUsers users) {
		if(id != null && id.trim().length()>0){
			Interview inter = this.interviewDAO.readInterview(Integer.valueOf(id));
			if(inter != null){
				inter.setChTransition(chTransition);
				inter.setStatusTracking(inter.getStatusTracking()+"</br>"+users.getUser().getAccounts()+"初试通过了此艺人");
				inter.setInterview(Interviews.CHECK);
				if(plat != null && plat.trim().length()>0){
					Platforms platforms = this.platformsService.readPlatforms(Integer.valueOf(plat));
					inter.setPlat(platforms);
				}
				if(this.interviewDAO.addInterview(inter)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<Interview> readAllInterview(String date, SearchFilter searchFilter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		List<Interview> list = this.interviewDAO.readAllInterview(date1, date2,searchFilter);
		return list;
	}

	@Override
	public HSSFWorkbook export(List<Interview> listOff) {
		String[] excelHeader = { "姓名", "艺名", "性别","手机号","QQ","艺人类型","试播平台","介绍人","当前状态","平台交接人","创建时间"};
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("面试总表");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
			sheet.setColumnWidth(i, 32 * 150);
		}
		
		if (listOff != null && listOff.size() > 0) {
			for (int i = 0; i < listOff.size(); i++) {
				row = sheet.createRow(i + 1);
				row.setRowStyle(style);
				Interview  interview = listOff.get(i);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String $date = sdf.format(interview.getCreateDT());
				row.createCell(0).setCellValue(interview.getName());
				row.createCell(1).setCellValue(interview.getAliasname());
				row.createCell(2).setCellValue(interview.getSex() == Sex.MAN ?"男": "女" );
				row.createCell(3).setCellValue(interview.getPhone());
				row.createCell(4).setCellValue(interview.getQq());
				row.createCell(5).setCellValue(interview.getGenre()== LineGenres.OFFLINE ? "线下":"线上");
				row.createCell(6).setCellValue(interview.getPlat() == null ? "":interview.getPlat().getPlatName() );
				row.createCell(7).setCellValue(interview.getIntroducer());
				if(interview.getInterview() ==Interviews.CHECK)
					row.createCell(8).setCellValue("审核中");
				else if(interview.getInterview() ==Interviews.NOTPASS)
					row.createCell(8).setCellValue("未通过");
				else if(interview.getInterview() ==Interviews.PASS)
					row.createCell(8).setCellValue("通过");
				else if(interview.getInterview() ==Interviews.PENDING)
					row.createCell(8).setCellValue("待处理");
				else if(interview.getInterview() ==Interviews.PRETEST)
					row.createCell(8).setCellValue("初试");
				else if(interview.getInterview() ==Interviews.WAITENTRY)
					row.createCell(8).setCellValue("待入职");
				row.createCell(9).setCellValue(interview.getChTransition());
				row.createCell(10).setCellValue($date);
			}
		}
		return wb;
	}
	
}
