package com.eussi.blog.web.controller.admin;

import com.eussi.blog.base.data.Data;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.po.Permission;
import com.eussi.blog.modules.po.Role;
import com.eussi.blog.modules.service.PermissionService;
import com.eussi.blog.modules.service.RoleService;
import com.eussi.blog.web.controller.BaseController;
import com.eussi.blog.web.controller.site.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;

/**
 * 角色管理
 * @author - wangxm on 2019/3/3
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {
	@Autowired
    private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	@GetMapping("/list")
	public String paging(String name, ModelMap model) {
		Page pageable = wrapPage();
		Page<Role> page = roleService.paging(pageable, name);
		model.put("name", name);
		model.put("page", page);
		return Views.ADMIN_ROLE_LIST;
	}

	@RequestMapping("/view")
	public String view(Long id, ModelMap model) {
		if (id != null && id > 0) {
			Role role = roleService.get(id);
			model.put("view", role);
		}

        model.put("permissions", permissionService.tree());
        return Views.ADMIN_ROLE_VIEW;
	}
	
	@RequestMapping("/update")
	public String update(Role role, @RequestParam(value = "perms", required=false) List<Long> perms, ModelMap model) {
		Data data;

		HashSet<Permission> permissions = new HashSet<>();
		if(perms != null && perms.size() > 0){

            for(Long pid: perms){
                Permission p = new Permission();
                p.setId(pid);
				permissions.add(p);
            }
        }
        
        if (Role.ADMIN_ID == role.getId()) {
			data = Data.failure("管理员角色不可编辑");
        } else {
            roleService.update(role, permissions);
            data = Data.success();
        }
        model.put("data", data);
        return Views.REDIRECT_ADMIN_ROLE_LIST;
	}
	
	@RequestMapping("/activate")
	@ResponseBody
	public Data activate(Long id, Boolean active) {
		Data ret = Data.failure("操作失败");
		if (id != null && id != Role.ADMIN_ID) {
			roleService.activate(id, active);
			ret = Data.success();
		}
		return ret;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Data delete(@RequestParam("id") Long id) {
		Data ret;
		if (Role.ADMIN_ID == id) {
			ret = Data.failure("管理员不能操作");
        }else if(roleService.delete(id)){
        	ret = Data.success();
        }else{
        	ret = Data.failure("该角色不能被操作");
        }
		return ret;
	}
}
