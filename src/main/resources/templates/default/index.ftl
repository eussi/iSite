<#include "/default/layout/ui.ftl"/>
<#assign topId = 1 />

<@layout>
<!-- top -->
<div>
    <@contents channelId=topId size=6>
        <#if  results.data?size gt 0>
            <div class="row banner">
                <#list results.data as row>
                    <div class="banner-item col-xs-12 col-sm-4 col-md-4">
                        <div class="index-banner-box"
                            <#if row.thumbnail?? && row.thumbnail?length gt 0>
                             style="background-image:url(<@resource src=row.thumbnail/>)"
                            <#else>
                             style="background-image:url(${base}/dist/images/spinner-overlay.jpg)"
                            </#if> >
                            <a class="top" href="${base}/view/${row.id}">
                                <div class="overlay"></div>
                                <div class="line"></div>
                                <div class="title">
                                    <h3>${row.title?html}</h3>
                                </div>
                            </a>
                        </div>
                    </div>
                </#list>
            </div>
        </#if>
    </@contents>
</div>
<#--<div class="row">-->
    <#--<@contents channelId=topId size=6>-->
        <#--<#list results.data as row>-->
            <#--<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">-->
                <#--<div class="block">-->
                    <#--<a class="block-thumbnail" href="${base}/view/${row.id}">-->
                        <#--<div class="thumbnail-overlay"></div>-->
                        <#--<span class="button-zoom">-->
                            <#--<img src="${base}/dist/images/image-overlay-view-icon.png">-->
                        <#--</span>-->

                        <#--<#if row.thumbnail?? && row.thumbnail?length gt 0>-->
                            <#--<img src="<@resource src=row.thumbnail/>">-->
                        <#--<#else>-->
                            <#--<img src="${base}/dist/images/image-overlay-view-icon.png">-->
                        <#--</#if>-->
                    <#--</a>-->

                    <#--<div class="block-contents">-->
                        <#--<p class="tit">${row.title?html}-->
                        <#--</p>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
        <#--</#list>-->
    <#--</@contents>-->
<#--</div>-->
<!-- top/end -->

<!-- latest contents -->
<div class="row">
    <div class="col-xs-12 col-md-9 side-left">
        <div class="panel panel-default list-panel home-topic-list">
            <div class="panel-heading">
                <h3 class="panel-title text-center">最近更新</h3>
            </div>

            <div class="panel-body ">
                <@contents size=20>
                    <ul class="list-group row topic-list">
                        <#list results.data as row>
                            <li class="list-group-item media col-md-12" style="margin-top: 0px;">
                                <a class="reply_last_time hidden-xs meta" href="${base}/view/${row.id}">
                                    <span class="stress">${row.views}</span> 浏览
                                    <span> ⋅ </span>${row.comments} 回复
                                    <span> ⋅ </span>${row.favors} 喜欢
                                    <span> ⋅ </span>${timeAgo(row.created)}
                                </a>

                                <div class="avatar pull-left">
                                    <a href="${base}/users/${row.author.id}">
                                        <img class="media-object img-thumbnail avatar avatar-middle"
                                             src="<@resource src=row.author.avatar + '?t=' + .now?time />">
                                    </a>
                                </div>

                                <div class="infos">
                                    <div class="media-heading">
                                        <@classify row/><a href="${base}/view/${row.id}">${row.title?html}</a>
                                    </div>
                                </div>
                            </li>
                        </#list>
                    </ul>
                </@contents>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-md-3 side-right">
        <#include "/default/inc/right.ftl"/>
    </div>
</div>

<!-- latest contents/end -->

</@layout>