<#include "/default/layout/ui.ftl"/>

<@layout "注册">
<div class="row">
    <div class="col-md-4 col-md-offset-4 floating-box">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">注册</h3>
            </div>
            <div class="panel-body">
                <#include "/default/inc/action_message.ftl"/>
                <div id="message">
                </div>
                <form method="POST" action="register" accept-charset="UTF-8">
                    <div class="form-group ">
                        <label class="control-label" for="username">用户名</label>
                        <input class="form-control" name="username" type="text" data-required data-conditional="username" data-description="username" data-describedby="message">
                    </div>
                    <div class="form-group ">
                        <label class="control-label" for="username">密码</label>
                        <input class="form-control" id="password" name="password" type="password" maxlength="18" placeholder="新密码" data-required>
                    </div>
                    <div class="form-group ">
                        <label class="control-label" for="username">确认密码</label>
                        <input class="form-control" name="password2" type="password" maxlength="18" placeholder="请再输入一次密码" data-required data-conditional="confirm" data-describedby="message" data-description="confirm">
                    </div>
                    <div class="form-group ">
                        <label class="control-label" style="display:block" for="code">验证码</label>
                        <input class="form-control" name="imagecode" type="text" placeholder="请输入验证码" style="display:inline;width: 200px;"/>
                        <span class="input-group-addon" style="float:right;width: 125px;padding: 0px 0px;">
                            <img alt="验证码" src="${base}/register/validate" onclick="change(this)">
                        </span>
                    </div>
                    <button type="submit" class="btn btn-success btn-block">
                        提交
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        $('form').validate({
            onKeyup : true,
            onChange : true,
            eachValidField : function() {
                $(this).closest('div').removeClass('has-error').addClass('has-success');
            },
            eachInvalidField : function() {
                $(this).closest('div').removeClass('has-success').addClass('has-error');
            },
            conditional : {
                confirm : function() {
                    return $(this).val() == $('#password').val();
                },
                username : function() {
                    return /^[a-z][a-z_0-9]{4,18}$/i.test($(this).val());
                }
            },
            description : {
                confirm : {
                    conditional : '<div class="alert alert-danger">两次输入的密码不一致</div>'
                },
                username : {
                    conditional : '<div class="alert alert-danger">只能是字母/字母+数字的组合,不少于5位</div>'
                }
            }
        });
    })
</script>

</@layout>