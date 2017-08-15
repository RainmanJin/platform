define(['admin/image/model/editModel', 'jquery', 'layer', 'jquery.validate'], function (model, $, layer) {
    function init() {
        $('#js-submit').click(function () {
            if (!isPic()) {
                return;
            }
            if (_formValidate()) {
                $('#js-form').submit();
            }
        });

        $('#js-reset').click(function () {  
           $('#js-form')[0].reset();
        });
    }

    function isPic() {
        var pic = $('#imageUrl').val();
        var regx= /.(jpg|jpeg|png|gif)$/;
        if (!pic || !regx.test(pic)) {
            alert("请选择图片");
            return false;
        }
        return true;
    }

    function _formValidate() {
        return $('#js-form').validate({
            rules: {
                title: {
                    required: true,
                    maxlength: 20
                },
                type: {
                    required: true,
                    maxlength: 20
                },
                introduce: {
                    required: true,
                    maxlength: 200
                }
            },
            messages: {
                title: {
                    required: "请输入标题",
                    maxlength: "长度过长"
                },
                type: {
                    required: "请输入类型",
                    maxlength: "长度过长"
                },
                introduce: {
                    required: "请输入描述",
                    maxlength: "长度过长"
                }
            }
        }).form();
    }

    return {
        init: init
    }
});
