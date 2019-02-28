package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.dao.MessageMapper;
import com.eussi.blog.modules.po.Message;
import com.eussi.blog.modules.service.MessageService;
import com.eussi.blog.modules.service.PostService;
import com.eussi.blog.modules.service.UserService;
import com.eussi.blog.modules.utils.BeanMapUtils;
import com.eussi.blog.modules.vo.MessageVO;
import com.eussi.blog.modules.vo.PostVO;
import com.eussi.blog.modules.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by wangxueming on 2019/2/15.
 */
@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @Override
    public Page<MessageVO> findByOwnId(Page page, long ownId) {
        Message query = new Message();
        query.setOwnId(ownId);
        Long totalCount = messageMapper.getTotalCount(query);
        page.setTotalCount(totalCount);

        query.setOrderBy("  created desc");
        query.setLimit(page.getStartIndex() + "," + page.getPageSize());
        List<Message> list = messageMapper.findAllByQuery(query);

        List<MessageVO> rets = new ArrayList<>();
        Set<Long> postIds = new HashSet<>();
        Set<Long> fromUserIds = new HashSet<>();

        for (Message no : list) {
            rets.add(BeanMapUtils.copy(no));

            if (no.getPostId() > 0) {
                postIds.add(no.getPostId());
            }
            if (no.getFromId() > 0) {
                fromUserIds.add(no.getFromId());
            }

        }

        // 加载
        Map<Long, PostVO> posts = postService.findMapByIds(postIds);
        Map<Long, UserVO> fromUsers = userService.findMapByIds(fromUserIds);
        for(MessageVO n : rets) {
            if (n.getPostId() > 0) {
                n.setPost(posts.get(n.getPostId()));
            }
            if (n.getFromId() > 0) {
                n.setFrom(fromUsers.get(n.getFromId()));
            }
        }

        page.setData(rets);
        return page;
    }

    @Override
    public void send(MessageVO notify) {
        if (notify == null || notify.getOwnId() <=0 || notify.getFromId() <= 0) {
            return;
        }

        Message po = new Message();
        BeanUtils.copyProperties(notify, po);
        po.setCreated(new Date());
        po.setStatus(Consts.UNREAD);

        messageMapper.insert(po);
    }

    @Override
    public int unread4Me(long ownId) {
        Message message = new Message();
        message.setOwnId(ownId);
        message.setStatus(Consts.UNREAD);
        return messageMapper.countByOwnIdAndUnread(message);
    }

    @Override
    public void readed4Me(long ownId) {
        Message update = new Message();
        update.setOwnId(ownId);
        update.setStatus(Consts.READED);
        messageMapper.updateByOwnId(update);
    }
}
