<footer class="footer">
    <div class="container">
        <div class="footer-col footer-col-logo hidden-xs hidden-sm">
            <img src="${base}/dist/images/logo.png" alt="BlogOfWangxm"/>
        </div>
        <div class="footer-col footer-col-copy">
            <ul class="footer-nav hidden-xs">
                <li class="menu-item"><a href="${base}/about">关于作者</a></li>
                <li class="menu-item"><a href="${base}/joinus">联系作者</a></li>
                <li class="menu-item"><a href="${base}/disclaimer">免责声明</a></li>
            <#--<li class="menu-item"><a href="${base}/faqs">常见问题</a></li>-->
                <li>
                    <script>
                        var _hmt = _hmt || [];
                        (function() {
                            var hm = document.createElement("script");
                            hm.src = "";
                            var s = document.getElementsByTagName("script")[0];
                            s.parentNode.insertBefore(hm, s);
                        })();
                    </script>
                </li>
            </ul>
            <div class="copyright">
				<span>${options['site_copyright']}. ${options['site_icp']}</span>
			</div>
        </div>
        <div class="footer-col footer-col-sns hidden-xs hidden-sm">
            <div class="footer-sns">
                <span>Powered By <a href="http://eussi.top/?copyright" target="_blank">Wangxueming's Site</a></span>
            </div>
        </div>
    </div>
</footer>

<a href="#" class="site-scroll-top"></a>

<script type="text/javascript">
    seajs.use('main', function (main) {
        main.init();
    });
</script>