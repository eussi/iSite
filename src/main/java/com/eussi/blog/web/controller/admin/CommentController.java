package com.eussi.blog.web.controller.admin;

import com.eussi.blog.base.data.Data;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.service.CommentService;
import com.eussi.blog.modules.vo.CommentVO;
import com.eussi.blog.web.controller.BaseController;
import com.eussi.blog.web.controller.site.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wangxm 2019-03-03
 *
 */
@Controller("adminCommentController")
@RequestMapping("/admin/comment")
public class CommentController extends BaseController {
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/list")
	public String list(ModelMap model) {
		Page pageable = wrapPage();
		Page<CommentVO> page = commentService.paging4Admin(pageable);
		model.put("page", page);
		return Views.ADMIN_COMMENT_LIST;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Data delete(@RequestParam("id") List<Long> id) {
		Data data = Data.failure("操作失败");
		if (id != null) {
			try {
				commentService.delete(id);
				data = Data.success("操作成功", Data.NOOP);
			} catch (Exception e) {
				data = Data.failure(e.getMessage());
			}
		}
		return data;
	}
}
