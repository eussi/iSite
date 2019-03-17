package com.eussi.blog.web.controller.site.posts;

import com.eussi.blog.base.data.Data;
import com.eussi.blog.base.exception.BlogException;
import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.utils.DomainUtils;
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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * post
 * @author wangxueming 2019-02-18
 */
@Controller
@RequestMapping("/post")
public class PostController extends BaseController {
	@Autowired
	private PostService postService;
	@Autowired
	private ChannelService channelService;

	/**
	 * 发布文章页
	 * @return
	 */
	@GetMapping("/editing")
	public String view(Long id, String editor, ModelMap model) {
		//如果是编辑文章
        PostVO view = null;
        if (null != id && id > 0) {
			AccountProfile profile = getProfile();
			view = postService.get(id);

			Assert.notNull(view, "该文章已被删除");
			Assert.isTrue(view.getAuthorId() == profile.getId(), "该文章不属于你");
			model.put("view", view);
		}

        model.put("channels", channelService.findAll(Consts.STATUS_NORMAL));

        if(Consts.UE.equals(editor) //editor参数或者编辑时文档时是UE
                || (view!=null&&Consts.UEDITOR==view.getIsMarkdown()))
            return view(Views.ROUTE_POST_UE_EDITING);
        else
            return view(Views.ROUTE_POST_MD_EDITING);
	}

    /**
     * 切换编辑器
     * @return
     */
    @GetMapping("/switch/{id}/{type}")
    public String switchEditor(@PathVariable("id")Long id, @PathVariable("type")String type, ModelMap model) {
        Assert.isTrue(type == null || "".equals(type), "参数传递错误！");
        //如果是编辑文章
        if (null != id && id > 0) {
            AccountProfile profile = getProfile();
            PostVO view = postService.get(id);

            Assert.notNull(view, "该文章已被删除");
            Assert.isTrue(view.getAuthorId() == profile.getId(), "该文章不属于你");
            model.put("view", view);
        }

        model.put("channels", channelService.findAll(Consts.STATUS_NORMAL));
        if("md".equals(type)) {
            return view(Views.ROUTE_POST_MD_EDITING);
        } else if("ue".equals(type)) {
            return view(Views.ROUTE_POST_UE_EDITING);
        } else {
            throw new BlogException("编辑器传入类型错误！");
        }
    }

	/**
	 * 提交发布
	 * @param post
	 * @return
	 */
	@PostMapping("/submit")
	public String post(PostVO post) throws IOException {
		Assert.notNull(post, "参数不完整");
		Assert.state(StringUtils.isNotBlank(post.getTitle()), "标题不能为空");
		Assert.state(StringUtils.isNotBlank(post.getContent()), "内容不能为空");

		AccountProfile profile = getProfile();
		post.setAuthorId(profile.getId());

		// 修改时, 验证归属
		if (post.getId() !=null ) {
			PostVO exist = postService.get(post.getId());
			Assert.notNull(exist, "文章不存在");
			Assert.isTrue(exist.getAuthorId() == profile.getId(), "该文章不属于你");
			postService.update(post);
		} else {
			postService.post(post);
		}
		return Views.REDIRECT_USER_POSTS;
	}

	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete/{id}")
	public @ResponseBody
	Data delete(@PathVariable Long id) {
		Data data = Data.failure("操作失败");
		if (id != null) {
			AccountProfile up = getProfile();
			try {
				postService.delete(id, up.getId());
				data = Data.success("操作成功", Data.NOOP);
			} catch (Exception e) {
				data = Data.failure(e.getMessage());
			}
		}
		return data;
	}

}
