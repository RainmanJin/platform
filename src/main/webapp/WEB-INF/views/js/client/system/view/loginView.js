define(['client/system/model/loginModel', 'jquery', 'jquery.validate'], function (model, $) {
    function init() {

        $('#js-submit').click(function (event) {
            event.preventDefault();
            if (_formValidate()) {
                _submitForm();
            }
        });

        //更新图片验证码
        $('#kaptchaImage').click(function () {
            $(this).hide().attr('src', '/client/captcha/generatecode?' + Math.floor(Math.random() * 100)).fadeIn();
        });
        //更新图片验证码
        $('#change').click(function () {
            $('#kaptchaImage').hide().attr('src', '/client/captcha/generatecode?' + Math.floor(Math.random() * 100)).fadeIn();
        });
    }

    function _submitForm() {
        $.ajax({
            url: window.__PLATFORM__.root_url + "client/login",
            async: false,
            type: 'post',
            dataType: 'json',
            serializable: false,
            data: $('#js-form').serialize(),
            success: function (data) {
                if (data.data == true) {
                    window.location.href = window.__PLATFORM__.root_url + "admin/image/list";
                } else {
                    alert("登录失败！");
                }
            }
        });
    }

    function _formValidate() {
        return $('#js-form').validate({
            rules: {
                loginName: {
                    required: true
                },
                password: {
                    required: true
                },
                captcha: {
                    required: true,
                    remote: {
                        type: "GET",
                        async:false,
                        url: window.__PLATFORM__.root_url + "client/captcha/checkcode",
                        data: {
                            captcha: function () {
                                return $("#kaptcha").val();
                            }
                        }
                    }
                }
            },
            messages: {
                loginName: {
                    required: "请输入用户名"
                },
                password: {
                    required: "请输入密码"
                },
                captcha: {
                    required: "请输入验证码",
                    remote: "验证码输入错误"
                }
            }
            // submitHandler: function (form) {
            //     $.ajax({
            //         url: window.__PLATFORM__.root_url + "client/captcha/checkcode",
            //         async: true,
            //         type: 'get',
            //         dataType: 'json',
            //         serializable: false,
            //         data: $("#kaptcha").val(),
            //         success: function (data) {
            //             if (data.data == true) {
            //                 alert("success");
            //                 // _submitForm();
            //             } else {
            //                 alert("验证码输入错误！");
            //             }
            //         }
            //     });
            // }
        }).form();
    }


    return {
        init: init
    }
});
