package com.eussi.blog.modules.repository;


import com.eussi.blog.base.modules.BaseRepository;
import com.eussi.blog.modules.po.User;

import java.util.List;
import java.util.Set;

/**
 * @author langhsu
 */
public interface UserRepository extends BaseRepository<User> {
    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAllByIdIn(Set<Long> ids);

}
