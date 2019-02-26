package com.eussi.blog.modules.freemarker.directive;

import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.freemarker.DirectiveHandler;
import com.eussi.blog.modules.freemarker.TemplateDirective;
import com.eussi.blog.modules.service.PostService;
import com.eussi.blog.modules.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 根据作者取文章列表
 *
 * @author wangxm
 *
 */
@Component
public class AuthorContentsDirective extends TemplateDirective {
    @Autowired
	private PostService postService;

	@Override
	public String getName() {
		return "author_contents";
	}

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        int pn = handler.getInteger("pn", 1);
        long uid = handler.getInteger("uid", 0);

        Page page = new Page(pn, 10);
        Page<PostVO> result = postService.pagingByAuthorId(page, uid);

        handler.put(RESULTS, result).render();
    }

}
