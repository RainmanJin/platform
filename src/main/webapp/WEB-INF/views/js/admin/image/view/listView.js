define(['admin/image/model/listModel', 'jquery'], function (model, $) {
    function init() {
        $('.js-delete').click(function () {
            var id = $(this).data('id');
            $.ajax({
                url: window.__PLATFORM__.root_url + "admin/image/delete/" + id,
                async: true,
                type: 'post',
                dataType: 'json',
                serializable: false,
                success: function (data) {
                    window.location.href = window.__PLATFORM__.root_url + "admin/image/list";
                }
            });
        })
    }

    return {
        init: init
    }
});