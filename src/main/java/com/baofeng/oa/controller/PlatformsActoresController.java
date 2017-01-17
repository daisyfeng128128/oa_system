package com.baofeng.oa.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.ActoresBean;
import com.baofeng.oa.bean.PactBean;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.ResultMsg;
import com.baofeng.utils.SearchFilter;

/**
 * 功能： 艺人管理
 * */

@Controller
@RequestMapping("/actores")
public class PlatformsActoresController extends BaseController {

	@Autowired
	private IPlatformsActoresService actoresService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(Integer platId, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/actoresHome");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("actores/show.do"));
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("platId", platId);
		mav.addObject("platList", platList);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	@RequestMapping(value = "/showOnline", method = RequestMethod.GET)
	public ModelAndView showOnline() {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/actoresOnline");
		return mav;
	}

	@RequestMapping(value = "/showOff", method = RequestMethod.GET)
	public ModelAndView showOff() {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/actoresOff");
		return mav;
	}

	@RequestMapping(value = "/showLeave", method = RequestMethod.GET)
	public ModelAndView showLeave() {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/actoresLeave");
		return mav;
	}

	/**
	 * 功能：直播系统艺人分页
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesPlatformActores", method = RequestMethod.POST)
	public NPageResult<?> readPagesPlatformActores(int pageSize, int curPage, String sortName, String sortOrder, Integer platId, String fastArg, Integer branchs,
			HttpServletRequest request) {
		NPageResult<?> pages = null;
		pages = this.actoresService.readPagesPlatformActores(pageSize, curPage, sortName, sortOrder, platId, fastArg, super.searchFilter(request, branchs));
		return pages;
	}

	/**
	 * 功能：分页
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, Integer platId, Integer type, Integer genrer, String name, Integer branchs,
			HttpServletRequest request) {
		NPageResult<?> pages = null;
		pages = this.actoresService.readAllPages(pageSize, curPage, sortName, sortOrder, platId, type, genrer, name, super.searchFilter(request, branchs));
		return pages;
	}

	/**
	 * 功能：添加修改
	 * */
	@ResponseBody
	@RequestMapping(value = "/saveActores", method = RequestMethod.POST)
	public ResultMsg saveActores(Integer id, Integer empid, Integer platId, Integer channelsId, String beLiveTime, Integer pushMoney, String costArtists, String mainPlatform,
			String number, HttpServletRequest request) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		PlatformsActores pact = new PlatformsActores();
		pact.setId(id);
		pact.setBranchs(branchs);
		pact.setMainPlatform(Integer.valueOf(mainPlatform));
		if (number != null && number != "") {
			pact.setNumber(number);
		}
		if (this.actoresService.addNewActores(pact, empid, platId, costArtists, channelsId, beLiveTime, pushMoney, request)) {
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能：编辑
	 * */
	@ResponseBody
	@RequestMapping(value = "/editActores", method = RequestMethod.GET)
	public ResultMsg edit(String id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.trim().length() > 0) {
			PactBean pact = this.actoresService.readPlatformsActoresBean(Integer.valueOf(id));
			if (pact != null) {
				msg.setData(pact);
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能：编辑
	 * */
	@ResponseBody
	@RequestMapping(value = "/editEmp", method = RequestMethod.GET)
	public ResultMsg read(Integer id, String loadTable, String platId, String channelsId) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.intValue() > 0) {
			if (this.actoresService.readPlatformsActores(id, platId, channelsId)) {
				msg.setResultStatus(101);
			} else {
				Actores act = this.actoresService.readActores(id);
				if (act != null) {
					Platforms plat = this.actoresService.readPlatforms(Integer.valueOf(platId));
					ActoresBean bean = new ActoresBean();
					bean.setId(act.getId());
					bean.setFormGenre(String.valueOf(plat.getFormGenre()));
					bean.setRealname(act.getRealname());
					bean.setAliasname(act.getAliasname());
					bean.setPhone(act.getPhone());
					bean.setSex(act.getSex().toString());
					bean.setGenre(act.getGenre().toString());
					bean.setEntryTime(act.getEntryTime());
					bean.setProbation(act.getProbation());
					bean.setQq(act.getQq());
					bean.setProbationSalary(act.getProbationSalary());
					bean.setMinimumGuarantee(act.getMinimumGuarantee());
					bean.setBasicSalary(act.getBasicSalary());
					bean.setPushMoney(act.getPushMoney());
					bean.setSigned(act.getSigned().toString());
					bean.setIdcard(act.getIdcard());
					bean.setAddress(act.getAddress());
					bean.setLoadTable(String.valueOf(2));
					if (act.getGenrer() == Genres.SHIYONG) {
						bean.setGenrer("试用");
					} else if (act.getGenrer() == Genres.ZHENGSHI || act.getGenrer() == Genres.PENDING) {
						bean.setGenrer("正式");
					}
					msg.setResultStatus(200);
					msg.setData(bean);
				}
			}
		}
		return msg;
	}

	/**
	 * 功能：分页
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesChannel", method = RequestMethod.POST)
	public PageResult<?> readPagesChannel(int page, int rows, String filter, HttpServletRequest request) {
		PageResult<?> pages = null;
		SearchFilter $filter = new SearchFilter();
		pages = this.actoresService.readPagesChannel(rows, page, $filter, filter);
		return pages;
	}

	/**
	 * 功能：分页
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesEmp", method = RequestMethod.POST)
	public PageResult<?> readPagesEmp(int page, int rows, String filter, HttpServletRequest request) {
		PageResult<?> pages = null;
		SearchFilter $filter = new SearchFilter();
		pages = this.actoresService.readPagesEmp(rows, page, $filter, filter);
		return pages;
	}

	/**
	 * 功能：添加修改
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(Integer id, Integer type, Integer genrer, String realname, String aliasname, Integer labour, String phone, String email, Integer sex, Integer islogin,
			String address, String password, String idcard, @RequestParam MultipartFile[] idImage, Integer genre, String entryTime, Integer minimumGuarantee,
			Integer probationSalary, Integer positiveBasicSalary, Integer formal, Integer signed, Integer employeeid) throws Exception {
		ModelAndView mav = null;
		if (type != null && type == 2) {
			mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/actoresOff");
		} else if (genrer != null && genrer == 3) {
			mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/actoresLeave");
		} else {
			mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/actoresOnline");
		}
		Actores post = new Actores();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (id != null && id > 0) {
			post.setId(id);
		}
		post.setRealname(realname);
		post.setAliasname(aliasname);
		post.setPhone(phone);
		post.setEmail(email);
		post.setSex(sex == 1 ? Sex.WOMAN : Sex.MAN);
		post.setAddress(address);
		post.setIdcard(idcard);
		if (idImage != null && idImage.length > 0) {
			for (MultipartFile $file : idImage) {
				if (!$file.isEmpty()) {
					String $name = $file.getOriginalFilename();
					String ext = $name.substring($name.lastIndexOf("."), $name.length());
					String sha1 = DigestUtils.sha1Hex($file.getInputStream());
					String fileName = sha1 + ext;
					String path = Constants.DEFAULT_UPLOADIMAGEPATH + File.separator + Constants.sha1ToPath

					(sha1, File.separator);
					Constants.mkdirs(path);
					FileUtils.copyInputStreamToFile($file.getInputStream(), new File(path, fileName));
					post.setIdImage(Constants.sha1ToPath(sha1, "/") + "/" + fileName);
				}
			}
		}
		post.setGenre(genre == 1 ? LineGenres.ONLINE : LineGenres.OFFLINE);
		if (entryTime != null && entryTime.trim().length() > 0)
			post.setEntryTime(sdf.parse(entryTime));
		if (employeeid != null) {
			post.setEmployee(this.employeeService.readEmployee(employeeid));
		}
		post.setLabour(labour == 1 ? ActoresLabour.SYSTEM : labour == 2 ? ActoresLabour.EPIBOLIC : ActoresLabour.OTHER);
		if (this.actoresService.addActores(post)) {
			mav.addObject("result", "success");
		}
		return mav;
	}

	/**
	 * 功能：删除
	 * */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResultMsg delete(Integer id, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("errors");
		PlatformsActores $actores = new PlatformsActores();
		PlatformsActores actores = this.actoresService.readPlatformsActoresById(id);
		$actores.setActores(actores.getActores());
		$actores.setId(actores.getId());
		$actores.setChannel(actores.getChannel());
		$actores.setPlat(actores.getPlat());
		if (this.actoresService.deletePlatformsActores(id)) {
			if ($actores.getChannel() != null) {
				this.monitorLogService.logsDelete(request, "平台: " + $actores.getPlat().getPlatName() + "频道号：" + $actores.getChannel().getChannels() + "下的 平台艺人"
						+ $actores.getActores().getRealname() + " ID: " + $actores.getId());
			} else {
				this.monitorLogService
						.logsDelete(request, "平台: " + $actores.getPlat().getPlatName() + "下的 平台艺人" + $actores.getActores().getRealname() + " ID: " + $actores.getId());
			}

			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}
		return msg;
	}

}
