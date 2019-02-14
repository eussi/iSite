package com.eussi.blog.modules.freemarker.directive;

import com.eussi.blog.modules.freemarker.DirectiveHandler;
import com.eussi.blog.modules.freemarker.TemplateDirective;
import org.springframework.stereotype.Component;

/**
 * 资源路径处理
 * @author wangxm
 *
 */
@Component
public class ResourceDirective extends TemplateDirective {
    @Override
    public String getName() {
        return "resource";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        String src = handler.getString("src", "#");
        if (src.startsWith("http")) {
            handler.renderString(src);
        } else {
            String base = handler.getContextPath();
            handler.renderString(base + src);
        }
    }

}
