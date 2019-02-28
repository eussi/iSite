package com.eussi.blog.web.controller.site.user;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.utils.FileOperUtils;
import com.eussi.blog.base.utils.FilePathUtils;
import com.eussi.blog.base.utils.ImageUtils;
import com.eussi.blog.modules.service.UserService;
import com.eussi.blog.modules.vo.AccountProfile;
import com.eussi.blog.web.controller.BaseController;
import com.eussi.blog.web.controller.site.Views;
import com.eussi.blog.web.controller.site.posts.UploadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author wangxm
 *
 */
@Controller
@RequestMapping("/user")
public class AvatarController extends BaseController {
	@Autowired
	private UserService userService;

	@Value("${site.store.size:2}")
	private String storeSize;

	@RequestMapping(value = "/avatar", method = RequestMethod.GET)
	public String view() {
		return view(Views.USER_AVATAR);
	}
	
	@PostMapping("/avatar")
	@ResponseBody
	public UploadController.UploadResult upload(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
		UploadController.UploadResult result = new UploadController.UploadResult();
		AccountProfile profile = getProfile();

		// 检查空
		if (null == file || file.isEmpty()) {
			return result.error(UploadController.errorInfo.get("NOFILE"));
		}

		String fileName = file.getOriginalFilename();

		// 检查类型
		if (!FileOperUtils.checkFileType(fileName)) {
			return result.error(UploadController.errorInfo.get("TYPE"));
		}

		// 检查大小
		if (file.getSize() > (Long.parseLong(storeSize) * 1024 * 1024)) {
			return result.error(UploadController.errorInfo.get("SIZE"));
		}

		// 保存图片
		try {
			String ava100 = Consts.avatarPath + getAvaPath(profile.getId(), 240);
			byte[] bytes = ImageUtils.screenshot(file, 240, 240);

			AccountProfile user = userService.updateAvatar(profile.getId(), ava100);
			putProfile(user);

			String path = storageFactory.get().writeToStore(bytes, ava100);
			result.ok(UploadController.errorInfo.get("SUCCESS"));
			result.setName(fileName);
			result.setType(getSuffix(fileName));
			result.setPath(path.concat("?time=").concat("" + System.currentTimeMillis()));//防止缓存静态资源
			result.setSize(file.getSize());
		} catch (Exception e) {
			result.error(UploadController.errorInfo.get("UNKNOWN"));
		}
		return result;
	}

	public String getAvaPath(long uid, int size) {
		String base = FilePathUtils.getAvatar(uid);
		return String.format("/%s_%d.jpg", base, size);
	}
}
