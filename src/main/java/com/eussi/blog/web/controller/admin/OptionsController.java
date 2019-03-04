package com.eussi.blog.web.controller.admin;

import com.eussi.blog.base.data.Data;
import com.eussi.blog.config.ContextStartup;
import com.eussi.blog.modules.po.Options;
import com.eussi.blog.modules.service.OptionsService;
import com.eussi.blog.modules.service.PostSearchService;
import com.eussi.blog.web.controller.BaseController;
import com.eussi.blog.web.controller.site.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统配置
 *
 * @author wangxm 2019-03-04
 *
 */
@Controller
@RequestMapping("/admin/options")
public class OptionsController extends BaseController {
	@Autowired
	private OptionsService optionsService;
	@Autowired
	private PostSearchService postSearchService;
	@Autowired
	private ContextStartup contextStartup;

	@RequestMapping("/index")
	public String list(ModelMap model) {
		model.put("values", optionsService.findAll2Map());
		return Views.ADMIN_OPTIONS_INDEX;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap model) {
		Map<String, String[]> params = request.getParameterMap();

		List<Options> options = new ArrayList<>();

        for(Map.Entry<String, String[]> entry : params.entrySet()) {
            Options conf = new Options();
            conf.setKey(entry.getKey());
            conf.setValue(entry.getValue()[0]);

            options.add(conf);
        }

		optionsService.update(options);

		contextStartup.resetSetting(false);

		model.put("values", optionsService.findAll2Map());
		model.put("data", Data.success("操作成功", Data.NOOP));
		return Views.ADMIN_OPTIONS_INDEX;
	}

	/**
	 * 刷新系统变量
	 * @return
	 */
	@RequestMapping("/flush_conf")
	@ResponseBody
	public Data flushFiledia() {
		contextStartup.resetSetting(false);
		contextStartup.resetChannels();
		return Data.success("操作成功", Data.NOOP);
	}

	@RequestMapping("/flush_indexs")
	@ResponseBody
	public Data flushIndexs() {
		postSearchService.resetIndexs();
		return Data.success("操作成功", Data.NOOP);
	}
	
}
