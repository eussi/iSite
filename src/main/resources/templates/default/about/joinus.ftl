<#include "/default/layout/ui.ftl"/>

<@layout "联系作者">

<div class="row main">
    <div class="col-xs-12 col-md-9 side-left topics-show">
        <!-- view show -->
        <div class="topic panel panel-default">
            <div class="infos panel-heading">

                <h1 class="panel-title topic-title">联系作者</h1>

                <div class="meta inline-block">
                    <a class="author" href="#">a young man struggling</a>
                </div>
                <div class="clearfix"></div>
            </div>

            <div class="content-body entry-content panel-body ">
                <div class="markdown-body" id="emojify">
                    <p><strong>网站主页</strong></p>
                    <p>访问网站, 您可以 <a target="_blank" href="http://www.eussi.top" style="text-decoration:none;">www.eussi.top</a></p>
                    <p><strong>Github主页</strong></p>
                    <p>访问作者Github, 您可以 <a target="_blank" href="https://github.com/eussi" style="text-decoration:none;">github.com/eussi</a></p>
                    <p><strong>CSDN主页</strong></p>
                    <p>访问作者CSDN, 您可以 <a target="_blank" href="https://blog.csdn.net/eussi" style="text-decoration:none;">blog.csdn.net/eussi</a></p>
                    <p><strong>联系作者</strong></p>
                    <p>联系作者, 您可以 <a target="_blank" href="mailto:wangxuemingcn@yeah.net" style="text-decoration:none;">wangxuemingcn@yeah.net</a></p>
				</div>
            </div>
            <div class="panel-footer operate">
            </div>
            <div class="panel-footer operate">
                <div class="hidden-xs">
                    <div class="social-share" data-sites="weibo, wechat, facebook, twitter, google, qzone, qq"></div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <!-- /view show -->
    </div>
    <div class="col-xs-12 col-md-3 side-right hidden-xs hidden-sm">
		<#include "/default/inc/right.ftl"/>
    </div>
</div>

</@layout>