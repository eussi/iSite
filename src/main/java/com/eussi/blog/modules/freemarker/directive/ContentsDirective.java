package com.eussi.blog.modules.freemarker.directive;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.freemarker.DirectiveHandler;
import com.eussi.blog.modules.freemarker.TemplateDirective;
import com.eussi.blog.modules.po.Channel;
import com.eussi.blog.modules.service.ChannelService;
import com.eussi.blog.modules.service.PostService;
import com.eussi.blog.modules.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 文章内容查询
 *
 * @author wangxm
 *
 */
@Component
public class ContentsDirective extends TemplateDirective {
	@Autowired
    private PostService postService;
    @Autowired
    private ChannelService channelService;

    @Override
    public String getName() {
        return "contents";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        Integer pn = handler.getInteger("pn", 1);
        Integer channelId = handler.getInteger("channelId", 0);
        String order = handler.getString("order", Consts.order.NEWEST);
        Integer size = handler.getInteger("size", 16);

        Set<Integer> excludeChannelIds = new HashSet<>();

        if (channelId <= 0) {
            List<Channel> channels = channelService.findAll(Consts.STATUS_CLOSED);
            if (channels != null) {
                channels.forEach((c) -> excludeChannelIds.add(c.getId()));
            }
        }

        Page page = new Page(pn - 1, size);
        Page<PostVO> result = postService.paging(page, channelId, excludeChannelIds, order);

        handler.put(RESULTS, result).render();
    }
}
