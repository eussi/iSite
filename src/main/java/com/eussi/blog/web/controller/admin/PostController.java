package com.eussi.blog.web.controller.admin;

import com.eussi.blog.base.data.Data;
import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.service.ChannelService;
import com.eussi.blog.modules.service.PostService;
import com.eussi.blog.modules.vo.AccountProfile;
import com.eussi.blog.modules.vo.PostVO;
import com.eussi.blog.web.controller.BaseController;
import com.eussi.blog.web.controller.site.Views;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文章管理
 * @author wangxm
 *
 */
@Controller("adminPostController")
@RequestMapping("/admin/post")
public class PostController extends BaseController {
	@Autowired
	private PostService postService;
	@Autowired
	private ChannelService channelService;
	
	@RequestMapping("/list")
	public String list(String title, ModelMap model, HttpServletRequest request) {
		long id = ServletRequestUtils.getLongParameter(request, "id", Consts.ZERO);
		int group = ServletRequestUtils.getIntParameter(request, "group", Consts.ZERO);

		Page pageable = wrapPage();
		Page<PostVO> page = postService.paging4Admin(pageable, id, title, group);
		model.put("page", page);
		model.put("title", title);
		model.put("id", id);
		model.put("group", group);
		return Views.ADMIN_POST_LIST;
	}
	
	/**
	 * 跳转到文章编辑方法
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String toUpdate(Long id, ModelMap model) {
		if (null != id && id > 0) {
			PostVO ret = postService.get(id);
			model.put("view", ret);
		}
		model.put("groups", channelService.findAll(Consts.IGNORE));
		return Views.ADMIN_POST_UPDATE;
	}
	
	/**
	 * 更新文章方法
	 * @author LBB
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String subUpdate(PostVO post, @RequestParam(value = "file", required=false) MultipartFile file) throws Exception {
		if (post != null) {
			/**
			 * 保存预览图片
			 */
			if (file != null && !file.isEmpty()) {
				String thumbnail = storageFactory.get().storeScale(file, Consts.thumbnailPath, 360, 200);

				if (StringUtils.isNotBlank(post.getThumbnail())) {
					storageFactory.get().deleteFile(post.getThumbnail());
				}

				post.setThumbnail(thumbnail);
			}

			if (post.getId() > 0) {
				postService.update(post);
			} else {
				AccountProfile profile = getProfile();
				post.setAuthorId(profile.getId());
				postService.post(post);
			}
		}
		return Views.REDIRECT_ADMIN_POST_LIST;
	}

	@RequestMapping("/featured")
	@ResponseBody
	public Data featured(Long id, HttpServletRequest request) {
		Data data = Data.failure("操作失败");
		int featured = ServletRequestUtils.getIntParameter(request, "featured", Consts.FEATURED_ACTIVE);
		if (id != null) {
			try {
				postService.updateFeatured(id, featured);
				data = Data.success("操作成功", Data.NOOP);
			} catch (Exception e) {
				data = Data.failure(e.getMessage());
			}
		}
		return data;
	}

	@RequestMapping("/weight")
	@ResponseBody
	public Data weight(Long id, HttpServletRequest request) {
		Data data = Data.failure("操作失败");
		int weight = ServletRequestUtils.getIntParameter(request, "weight", Consts.FEATURED_ACTIVE);
		if (id != null) {
			try {
				postService.updateWeight(id, weight);
				data = Data.success("操作成功", Data.NOOP);
			} catch (Exception e) {
				data = Data.failure(e.getMessage());
			}
		}
		return data;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Data delete(@RequestParam("id") List<Long> id) {
		Data data = Data.failure("操作失败");
		if (id != null) {
			try {
				postService.delete(id);
				data = Data.success("操作成功", Data.NOOP);
			} catch (Exception e) {
				data = Data.failure(e.getMessage());
			}
		}
		return data;
	}
}
