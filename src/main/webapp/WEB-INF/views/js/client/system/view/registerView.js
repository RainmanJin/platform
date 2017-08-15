define(['client/system/model/registerModel', 'jquery', 'jquery.validate'], function (model, $) {
    function init() {
        $('#js-submit').click(function (event) {
            event.preventDefault();
            if (_formValidate()) {
                $.ajax({
                    url: window.__PLATFORM__.root_url + "client/user/save",
                    async: false,
                    type: 'post',
                    dataType: 'json',
                    serializable: false,
                    data: $('#js-form').serialize(),
                    success: function (data) {
                        if (data.status == "200") {
                            alert("添加成功！");
                            window.location.href = window.__PLATFORM__.root_url+"client/tologin";
                        } else {
                            if (data.subStatus == "1") {
                                alert("用户名已存在");
                            }else{
                                alert("添加失败！");
                            }
                        }
                    },
                    error:function (data) {
                        alert(data.status);
                    }
                });
            }
        })
    }

    function _formValidate() {
        return $('#js-form').validate({
            rules: {
                loginName: {
                    required: true,
                    maxlength: 20
                },
                password: {
                    required: true,
                    maxlength: 20
                },
                realName: {
                    required: true,
                    maxlength: 6
                }
            },
            messages: {
                loginName: {
                    required: "请输入用户名",
                    maxlength: "长度不能超过20"
                },
                password: {
                    required: "请输入密码",
                    maxlength: "长度不能超过20"
                },
                realName: {
                    required: "请输入真实姓名",
                    maxlength: "长度不能超过6"
                }
            }
        }).form()
    }

    return {
        init: init
    }
});