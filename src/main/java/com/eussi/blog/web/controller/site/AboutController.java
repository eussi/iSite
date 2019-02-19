package com.eussi.blog.web.controller.site;

import com.eussi.blog.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangxm
 *
 */
@Controller
public class AboutController extends BaseController {

    @RequestMapping("/disclaimer")
    public String disclaimer() {
        return view("/about/disclaimer");
    }

    @RequestMapping("/about")
	public String about() {
		return view("/about/about");
	}
	
	@RequestMapping("/joinus")
	public String joinus() {
		return view("/about/joinus");
	}
}
