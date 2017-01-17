package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.ActoresOfflineBean;
import com.baofeng.oa.bean.ActoresOnlineBean;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IActoresService {

	NPageResult<?> readPagesActoresOnline(int pageSize, int curPage, String sortName, String sortOrder, String genre, String isleave, SearchFilter filter,
			List<RoleDetailsAtts> platList, List<RoleDetailsAtts> attsList, RoleUsers users, String srealname, String saliasname, String sphone, String fastArg, String number);

	boolean addNewActores(Actores post, Integer pushMoney, Integer empid,String beLiveTime, String loadTable, HttpServletRequest request);

	boolean deleteActores(Integer id,HttpServletRequest request);

	Actores readActoresById(Integer id);

	boolean updateActorsOnline(Integer id, Integer type, String reason,HttpServletRequest request);

	Actores readActoresId(Integer id);

	Actores readActoresByPhone(String phone);

	boolean readActoresByNum(Integer number);

	NPageResult<?> readPagesActoresLeave(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter);

	ActoresOnlineBean editActoresById(Integer id);


	boolean saveActoresBySession(Actores actores);

	Actores findActoresBySession(Integer empId);

	List<Actores> findAllActoresOffline();
	
	List<Actores> findAllActoresOnline();

	Actores findActoresById(Integer offlineActoreId);

	boolean addActoresLinkUrl(Actores post);

	boolean addLastNewActores(Actores post, Integer pushMoney, Integer empid, String beLiveTime, String loadTable, Integer channel, Integer plat,
			String beliveTime2, HttpServletRequest request);

	boolean checkActores(Actores post);

	List<Platforms> finPlat(List<RoleDetailsAtts> platList, Integer id);

	ActoresOfflineBean editActoresofflineById(Integer id);

}
