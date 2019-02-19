<#include "/default/layout/ui.ftl"/>

<@layout "关于我们">

<div class="row main">
    <div class="col-xs-12 col-md-9 side-left topics-show">
        <!-- view show -->
        <div class="topic panel panel-default">
            <div class="infos panel-heading">

                <h1 class="panel-title topic-title">关于作者</h1>

                <div class="meta inline-block">
                    <a class="author" href="#">a young man struggling</a>
                </div>
                <div class="clearfix"></div>
            </div>

            <div class="content-body entry-content panel-body ">
                <div class="markdown-body" id="emojify">
                    <p><strong>关于网站</strong><br/></p>
                    <p>本网站是个人网站，平常会分享一些个人觉得有用的东西，希望能够帮到你。</p>
                    <p><strong>网站源码</strong></p>
                    <p>代码托管在<a href="https://github.com/eussi/BlogOfWangxm" target="_blank">https://github.com/eussi/BlogOfWangxm</a></p>
                    <p><strong>诚意邀请</strong></p>
                    <p>网站开发时间很短，存在很多功能不完善之处，需要继续开发，并解决一些技术难题，诚邀您的加入。</p>
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