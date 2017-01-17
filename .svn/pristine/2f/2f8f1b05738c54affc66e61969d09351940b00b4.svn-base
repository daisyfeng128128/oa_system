package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.oa.bean.PactBean;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.oa.entity.PlatformsOnlineIncome;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

public interface IPlatformsActoresService {

	PageResult<?> readPagesChannel(int rows, int page, SearchFilter $filter, String filter);

	PageResult<?> readPagesEmp(int rows, int page, SearchFilter $filter, String filter);

	boolean addActores(Actores post);

	Actores readActores(Integer id);

	NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, Integer platId, Integer type,

	Integer genrer, String name, SearchFilter filter);

	boolean deletePlatformsActores(Integer id);

	NPageResult<?> readPagesPlatformActores(int pageSize, int curPage, String sortName, String sortOrder, Integer platId, String fastArg, SearchFilter filter);

	boolean readPlatformsActores(Integer id, String platId, String channelsId);

	PlatformsActores readPlatformsActoresById(Integer id);

	boolean savePlatformsOnlineIncome(PlatformsOnlineIncome online);

	boolean savePlatformsOfflineIncome(PlatformsOfflineIncome offline);

	List<Actores> readActoresByEmpId(Integer id);

	boolean saveActores(Actores actores);

	boolean addNewActores(PlatformsActores pact, Integer actId, Integer platId, String costArtists, Integer channelsId, String beLiveTime, Integer pushMoney,
			HttpServletRequest request);

	boolean savePlatformsActoresBySession(PlatformsActores pact);

	boolean deletePlatformsActoresBySession(Integer actId);

	List<PlatformsActores> findAllPlatformsActores(LineGenres genrer);

	PlatformsOnlineIncome findPlatformsActoresByPactToOnline(Integer pactId);

	PlatformsOfflineIncome findPlatformsActoresByPactToOffline(Integer pactId);

	Actores findValidationSession(Integer empId);

	List<PlatformsActores> findPlatformsActoresList(Integer id);

	PactBean readPlatformsActoresBean(Integer id);

	List<PlatformsActores> findAllPlatformsActores();

	List<PlatformsActores> findAllPlatformsActoresByActId(Actores actores);

	PlatformsActores findPlatformsActoresById(Integer pactId);

	String readAllPlatNames(Integer id);
	
	Platforms readPlatforms(Integer id);

	List<PlatformsActores> readPlatformsActoresByActoresId(Integer id);

	PlatformsActores readPlatformsActoresByNUmber(Integer numbers);



}
