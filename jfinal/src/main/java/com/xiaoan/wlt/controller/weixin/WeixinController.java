package com.xiaoan.wlt.controller.weixin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.xiaoan.wlt.common.Constants;
import com.xiaoan.wlt.common.bean.Result;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.t.WechatUsers;
import com.xiaoan.wlt.model.zyk.ActLog;
import com.xiaoan.wlt.model.zyk.Category;
import com.xiaoan.wlt.model.zyk.ResourceTemplate;
import com.xiaoan.wlt.model.zyk.ShopsResource;
import com.xiaoan.wlt.utils.CommUtil;

public class WeixinController extends BaseController {

	/**
	 * 微信端列表的展示数量
	 */
	private static final int WECHAT_LIST_PAGE_SIZE = 3;

	private static final String TEMPLATES = "/weixin/templates.jsp";

	private static final String SHOPS_RESOURCE = "/weixin/resources.jsp";

	private static final String ARTICLE = "/weixin/article.jsp";

	/**
	 * 首页-我的资源库（商家资源库）
	 */
	public void index() {
		sResources();
	}

	/**
	 * 公共资源库
	 */
	public void templates() {
		WechatUsers wechatUser = (WechatUsers) getSession().getAttribute(
				Constants.WECHAT_USER_SESSION_NAME);
		if (wechatUser != null) {
			List<Long> list = Db
					.query("select DISTINCT templateId from zyk_shops_resource where shopsId=? ",
							wechatUser.getId());
			setAttr("favlist", list);
			setAttr("favs", StringUtils.join(list, ","));
		}
		ResourceTemplate resourceTemplate = getModel(ResourceTemplate.class);
		Integer pageNumber = getParaToInt("pageNumber");
		if (pageNumber != null) {
			resourceTemplate.setPageNumber(pageNumber);
		}
		resourceTemplate.setPageSize(WECHAT_LIST_PAGE_SIZE);

		String[] catIds = getParaValues("catIds");
		String startTime = getPara("startTime");
		String endTime = getPara("endTime");

		String catIdsStr = StringUtils.join(catIds, ",");

		setAttr("catIds", catIdsStr);
		setAttr("startTime", startTime);
		setAttr("endTime", endTime);

		// injection risk
		String extWhere = "";
		List<Object> extParas = new ArrayList<Object>();
		if (StringUtils.isNotBlank(catIdsStr)) {
			extWhere += " and category in (" + catIdsStr + ")";
		}
		if (StringUtils.isNotBlank(startTime)) {
			extWhere += " and createTime >= ? ";
			extParas.add(startTime);
		}
		if (StringUtils.isNoneBlank(endTime)) {
			extWhere += " and createTime <= ?";
			extParas.add(endTime + " 23:59:59");
		}
		Page<ResourceTemplate> page = ResourceTemplate.dao.paginateSpec(
				resourceTemplate, extWhere, extParas.toArray());
		// this.setAttr("template", resourceTemplate);
		this.setAttr("page", page);

		List<Category> cats = Category.dao.getAllCats();
		this.setAttr("cats", cats);

		render(TEMPLATES);
	}

	public void templatesData() {
		ResourceTemplate resourceTemplate = getModel(ResourceTemplate.class);
		Integer pageNumber = getParaToInt("pageNumber");
		if (pageNumber != null) {
			resourceTemplate.setPageNumber(pageNumber);
		}
		Integer pageNum = getParaToInt("pageNum");
		if (pageNum != null) {
			resourceTemplate.setPageNumber(pageNum);
		}
		resourceTemplate.setPageSize(WECHAT_LIST_PAGE_SIZE);
		String catIds = getPara("catIds");
		String startTime = getPara("startTime");
		String endTime = getPara("endTime");

		// injection risk
		String extWhere = "";
		List<Object> extParas = new ArrayList<Object>();
		if (StringUtils.isNotBlank(catIds)) {
			extWhere += " and category in (" + catIds + ")";
		}
		if (StringUtils.isNotBlank(startTime)) {
			extWhere += " and createTime >= ? ";
			extParas.add(startTime);
		}
		if (StringUtils.isNoneBlank(endTime)) {
			extWhere += " and createTime <= ?";
			extParas.add(endTime + " 23:59:59");
		}
		Page<ResourceTemplate> page = ResourceTemplate.dao.paginateSpec(
				resourceTemplate, extWhere, extParas.toArray());

		JSONArray result = new JSONArray();
		JSONObject json = null;
		for (ResourceTemplate rt : page.getList()) {
			json = new JSONObject();
			json.accumulate("id", rt.getId());
			// json.accumulate("mainPic", rt.getMainPic());
			json.accumulate("title", rt.getTitle());
			json.accumulate("description", rt.getDescription());
			json.accumulate("createTime",
					DateFormatUtils.format(rt.getCreateTime(), "yyyy-MM-dd"));
			json.accumulate("hits", rt.getHits() == null ? 0 : rt.getHits()
					.intValue());
			json.accumulate("voteUp", rt.getVoteUp() == null ? 0 : rt
					.getVoteUp().intValue());
			result.add(json);
		}
		renderJson("list", result);
	}

	public void favorite() {
		WechatUsers wechatUser = (WechatUsers) getSession().getAttribute(
				Constants.WECHAT_USER_SESSION_NAME);
		if (wechatUser == null) {
			// 跳到主页重新获取
			index();
			return;
		}
		Result result = new Result();
		String idValue = getPara("id");
		ResourceTemplate resourceTemplate = ResourceTemplate.dao
				.findById(idValue);
		if (resourceTemplate != null) {
			Number count = Db
					.queryNumber(
							"select count(1) from zyk_shops_resource where shopsId=? and templateId=?",
							wechatUser.getId(), Long.valueOf(idValue));
			if (count != null && count.intValue() > 0) {
				// 错误提示，已经加入到了我的资源
				result.setErrcode("1001");
				result.setErrmsg("已经在我的资源库中存在！");
				renderJson(result);
				return;
			} else {
				ShopsResource shopsResource = new ShopsResource();

				// shopsId暂时使用wechatUsers的id
				try {
					BeanUtils.copyProperties(shopsResource, resourceTemplate);
				} catch (Exception e) {
					e.printStackTrace();
				}
				shopsResource.setShopsId(wechatUser.getId());
				shopsResource.setShopsName(wechatUser.getNickname());
				shopsResource.setTemplateId(resourceTemplate.getId());

				shopsResource.setId(null);
				shopsResource.setRate(0);
				shopsResource.setHits(0);
				shopsResource.setVoteUp(0);
				shopsResource.setState("USE");

				shopsResource.save();

				result.setErrcode(Result.SUCCESS_CODE);
				result.setErrmsg("成功收藏到我的资源库");
				renderJson(result);
				return;
			}
		} else {
			// 错误提示
			result.setErrcode("-1");
			result.setErrmsg("系统错误，请刷新后重试！");
		}
	}

	/**
	 * 商家资源库（我的资源库）
	 */
	// 根据微信号，查找系统用户，根据系统用户，查找该用户所有的资源
	public void sResources() {
		WechatUsers wechatUser = (WechatUsers) getSession().getAttribute(
				Constants.WECHAT_USER_SESSION_NAME);
		if (wechatUser == null) {
			// 跳到主页重新获取
			index();
			return;
		}
		ShopsResource shopsResource = getModel(ShopsResource.class);
		Integer pageNumber = getParaToInt("pageNumber");
		if (pageNumber != null) {
			shopsResource.setPageNumber(pageNumber);
		}
		shopsResource.setPageSize(WECHAT_LIST_PAGE_SIZE);

		String[] catIds = getParaValues("catIds");
		String startTime = getPara("startTime");
		String endTime = getPara("endTime");

		String catIdsStr = StringUtils.join(catIds, ",");

		setAttr("catIds", catIdsStr);
		setAttr("startTime", startTime);
		setAttr("endTime", endTime);

		// injection risk
		String extWhere = "";
		List<Object> extParas = new ArrayList<Object>();

		if (StringUtils.isNotBlank(catIdsStr)) {
			extWhere += " and category in (" + catIdsStr + ")";
		}
		if (StringUtils.isNotBlank(startTime)) {
			extWhere += " and createTime >= ? ";
			extParas.add(startTime);
		}
		if (StringUtils.isNoneBlank(endTime)) {
			extWhere += " and createTime <= ?";
			extParas.add(endTime + " 23:59:59");
		}

		// 根据用户过滤
		extWhere += " and shopsId=? ";
		extParas.add(wechatUser.getId());

		Page<ShopsResource> page = ShopsResource.dao.paginateSpec(
				shopsResource, extWhere, extParas.toArray());
		// this.setAttr("sResource", shopsResource);
		this.setAttr("page", page);

		List<Category> cats = Category.dao.getAllCats();
		this.setAttr("cats", cats);

		render(SHOPS_RESOURCE);
	}

	public void sResourcesData() {
		WechatUsers wechatUser = (WechatUsers) getSession().getAttribute(
				Constants.WECHAT_USER_SESSION_NAME);
		if (wechatUser == null) {
			// 跳到主页重新获取
			index();
			return;
		}
		ShopsResource shopsResource = getModel(ShopsResource.class);
		Integer pageNumber = getParaToInt("pageNumber");
		if (pageNumber != null) {
			shopsResource.setPageNumber(pageNumber);
		}
		Integer pageNum = getParaToInt("pageNum");
		if (pageNum != null) {
			shopsResource.setPageNumber(pageNum);
		}
		shopsResource.setPageSize(WECHAT_LIST_PAGE_SIZE);
		String catIds = getPara("catIds");
		String startTime = getPara("startTime");
		String endTime = getPara("endTime");

		// injection risk
		String extWhere = "";
		List<Object> extParas = new ArrayList<Object>();
		if (StringUtils.isNotBlank(catIds)) {
			extWhere += " and category in (" + catIds + ")";
		}
		if (StringUtils.isNotBlank(startTime)) {
			extWhere += " and createTime >= ? ";
			extParas.add(startTime);
		}
		if (StringUtils.isNoneBlank(endTime)) {
			extWhere += " and createTime <= ?";
			extParas.add(endTime + " 23:59:59");
		}
		// 根据用户过滤
		extWhere += " and shopsId=? ";
		extParas.add(wechatUser.getId());
		Page<ShopsResource> page = ShopsResource.dao.paginateSpec(
				shopsResource, extWhere, extParas.toArray());

		JSONArray result = new JSONArray();
		JSONObject json = null;
		for (ShopsResource rt : page.getList()) {
			json = new JSONObject();
			json.accumulate("id", rt.getId());
			// json.accumulate("mainPic", rt.getMainPic());
			json.accumulate("title", rt.getTitle());
			json.accumulate("description", rt.getDescription());
			json.accumulate("createTime",
					DateFormatUtils.format(rt.getCreateTime(), "yyyy-MM-dd"));
			json.accumulate("hits", rt.getHits() == null ? 0 : rt.getHits()
					.intValue());
			json.accumulate("voteUp", rt.getVoteUp() == null ? 0 : rt
					.getVoteUp().intValue());
			result.add(json);
		}
		renderJson("list", result);
	}

	/**
	 * 公共模板详情页
	 */
	public void template() {
		String idValue = getPara();
		ResourceTemplate template = ResourceTemplate.dao.findById(idValue);

		// 点击数，增加
		template.setHits(CommUtil.zero2Integer(template.getHits()) + 1);
		template.update();

		setAttr("template", template);
		render(ARTICLE);
	}

	/**
	 * 资源详情页
	 *
	 */
	// /article/1234abcd
	public void article() {
		String idValue = getPara();
		ShopsResource article = ShopsResource.dao.findById(idValue);
		// 点击数增加
		article.setHits(CommUtil.zero2Integer(article.getHits()) + 1);
		article.update();
		setAttr("article", article);
		render(ARTICLE);
	}

	/**
	 * 点赞
	 */
	public void voteUp() {
		WechatUsers wechatUser = (WechatUsers) getSession().getAttribute(
				Constants.WECHAT_USER_SESSION_NAME);
		if (wechatUser == null) {
			// 跳到主页重新获取
			index();
			return;
		}
		String idValue = getPara("id");
		String cat = getPara("cat");
		if (StringUtils.isBlank(cat))
			cat = "A";
		Result result = new Result();

		String resourceType = "SHOPS_RESOURCE";
		Long id = CommUtil.null2Long(idValue);
		String act = "VOTE_UP";
		if ("T".equals(cat)) {
			resourceType = "TEMPLATE_RESOURCE";
		}
		Number count = Db
				.queryNumber(
						"select count(*) from zyk_act_log where resourceType=? and resourceId=? and act=? and userId=?",
						resourceType, id, act, wechatUser.getId());
		// 已经赞过
		if (count != null && count.intValue() > 0) {
			result.setErrcode("1001");
			result.setErrmsg("已经赞过");
			renderJson(result);
			return;
		}
		// template
		ActLog actlog = new ActLog();
		if ("T".equals(cat)) {
			ResourceTemplate resourceTemplate = ResourceTemplate.dao
					.findById(idValue);
			resourceTemplate.setVoteUp(CommUtil.zero2Integer(resourceTemplate
					.getVoteUp()) + 1);
			resourceTemplate.update();
		} else {
			ShopsResource shopsResource = ShopsResource.dao.findById(idValue);
			shopsResource.setVoteUp(CommUtil.zero2Integer(shopsResource
					.getVoteUp()) + 1);
			shopsResource.update();
		}
		actlog.setResourceType(resourceType);
		actlog.setResourceId(id);
		actlog.setActTime(new Date());
		actlog.setAct(act);
		actlog.setUserId(wechatUser.getId());
		actlog.save();

		result.setErrcode(Result.SUCCESS_CODE);
		renderJson(result);
	}
}
