package com.eussi.blog.web.controller.site;

import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.service.PostSearchService;
import com.eussi.blog.modules.vo.PostVO;
import com.eussi.blog.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文章搜索
 * @author wangxm
 *
 */
@Controller
public class SearchController extends BaseController {
	@Autowired
	private PostSearchService postSearchService;

	@RequestMapping("/search")
	public String search(String find, ModelMap model) {
		Page pageable = wrapPage();
		try {
			if (StringUtils.isNotEmpty(find)) {
				Page<PostVO> page = postSearchService.search(pageable, find);
				model.put("page", page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("find", find);
		return view(Views.BROWSE_SEARCH);
	}
	
}
