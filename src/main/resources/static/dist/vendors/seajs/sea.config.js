seajs.config({
    alias: {
        /* jquery plugin */
        'plugins': 'dist/vendors/jquery/plugins',

            /* modules */
        'main': 'dist/js/modules/main',
        'authc': 'dist/js/modules/authc',
        'sidebox': 'dist/js/modules/sidebox',
        'post': 'dist/js/modules/post',
        'comment': 'dist/js/modules/comment',
        'phiz': 'dist/js/modules/phiz',
        'avatar': 'dist/js/modules/avatar',
        'editor': 'dist/js/modules/editor',
        'view': 'dist/js/modules/view',
        'webuploader': 'dist/js/modules/webuploader',

        /* vendors */
        'pace': 'vendors/pace/pace.min',
        /*'pjax': 'vendors/pjax/jquery.pjax',*/
        'validate': 'vendors/jquery/jquery-validate',
        'lazyload': 'vendors/jquery/jquery.lazyload',
        'share': 'vendors/share.js/js/social-share.min.js',

        'highlight':'vendors/highlight/highlight.pack',
        "tinymce": "vendors/tinymce/tinymce.min",
        "form": "vendors/jquery/jquery.form.min"
    },

    // 路径配置
    paths: {
        /*当目录比较深，或需要跨目录调用模块时，可以使用 paths 来简化书写。*/
        'vendors': _base_path + '/dist/vendors',
        'dist': _base_path + '/dist',
        'default': _base_path + '/theme/default'
    },

    // 变量配置
    vars: {
        'locale': 'zh-cn'
    },

    charset: 'utf-8',

    debug: false
});

// var __SEAJS_FILE_VERSION = '?v=1.3';
//
// seajs.on('fetch', function(data) {
// 	if (!data.uri) {
// 		return ;
// 	}
//
// 	if (data.uri.indexOf(app.mainScript) > 0) {
// 		return ;
// 	}
//
//    if (/\:\/\/.*?\/assets\/libs\/[^(common)]/.test(data.uri)) {
//        return ;
//    }
//
//    data.requestUri = data.uri + __SEAJS_FILE_VERSION;
//
// });
//
// seajs.on('define', function(data) {
// 	if (data.uri.lastIndexOf(__SEAJS_FILE_VERSION) > 0) {
// 	    data.uri = data.uri.replace(__SEAJS_FILE_VERSION, '');
// 	}
// });