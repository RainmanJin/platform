define(['admin/business/model/collegeListModel', 'jquery', 'layer', 'handlebars', 'ztree', 'ztreeCheck'], function (model, $, layer, Handlebars) {
    var zTree;
    var setting = {
        view: {
            dblClickExpand: false,//双击节点时，是否自动展开父节点的标识
            showLine: true,//是否显示节点之间的连线
            fontCss: {'color': 'black', 'font-weight': 'bold'},//字体样式函数
            selectedMulti: false //设置是否允许同时选中多个节点
        },
        check: {
            //chkboxType: { "Y": "ps", "N": "ps" },
            chkStyle: "checkbox",//复选框类型
            enable: false //每个节点上是否显示 CheckBox
        },
        data: {
            simpleData: {//简单数据模式
                enable: true,
                idKey: "code",
                pIdKey: "parentCode",
                rootPId: ""
            }
        },
        callback: {
            beforeClick: function (treeId, treeNode) {
                zTree = $.fn.zTree.getZTreeObj("user_tree");
                if (treeNode.isParent) {
                    zTree.expandNode(treeNode);//如果是父节点，则展开该节点
                } else {
                    zTree.checkNode(treeNode, !treeNode.checked, true, true);//单击勾选，再次单击取消勾选
                }
            },
            onClick: function (event, treeId, treeNode, clickFlag) {
                if (true) {
                    alert(" 节点code是：" + treeNode.code + ", 节点文本是：" + treeNode.name + ", 父节点code是：" + treeNode.parentCode);
                    _initCollege(treeNode.code);
                }

                //获取勾选node
                // var treeObj = $.fn.zTree.getZTreeObj("user_tree");
                // var nodes = treeObj.getCheckedNodes();
                // console.log(nodes);
            }
        }
    };

    var zNodes = [
        {code: 1, parentCode: 0, name: "test 1", index: 'S'},
        {code: 11, parentCode: 1, name: "test 1-1"},
        {code: 111, parentCode: 11, name: "test 1-1-1"},
        {code: 112, parentCode: 11, name: "test 1-1-2"},
        {code: 12, parentCode: 1, name: "test 1-2"}
    ];

    /**
     * 页面初始化
     */
    function init() {
        // $.fn.zTree.init($("#user_tree"), setting, zNodes);
        onLoadZTree();

        _initBind();
    }

    function _initCollege(code) {
        $.ajax({
            async: true,//是否异步
            cache: false,//是否使用缓存
            type: 'GET',//请求方式：post
            dataType: 'json',//数据传输格式：json
            url: window.__PLATFORM__.root_url + "admin/college/readlistbycode",//请求的action路径
            data: {code: code},
            error: function () {
                //请求失败处理函数
                alert('请求失败！');
            },
            success: function (data) {
                console.log(data.data);
                //请求成功后处理函数
                var templete = Handlebars.compile($('#college-templete').html());
                $('#college').append(templete(data.data));
            }
        });
    }

    function onCheck(e, treeId, treeNode) {
        var treeObj = $.fn.zTree.getZTreeObj("user_tree"),
            nodes = treeObj.getCheckedNodes(true),
            v = "";
        for (var i = 0; i < nodes.length; i++) {
            v += nodes[i].name + ",";
            alert(nodes[i].id); //获取选中节点的值
        }
    }

    /**
     * 加载树形结构数据
     */
    function onLoadZTree() {
        var treeNodes;
        $.ajax({
            async: false,//是否异步
            cache: false,//是否使用缓存
            type: 'GET',//请求方式：post
            dataType: 'json',//数据传输格式：json
            url: window.__PLATFORM__.root_url + "admin/base/readarealist",//请求的action路径
            error: function () {
                //请求失败处理函数
                alert('请求失败！');
            },
            success: function (data) {
                //请求成功后处理函数
                treeNodes = data.data;//把后台封装好的简单Json格式赋给treeNodes
            }
        });
        var t = $("#user_tree");
        t = $.fn.zTree.init(t, setting, treeNodes);
    }

    function _initBind() {
        $('#add').click(function () {
            layer.open({
                title: "新增",
                type: 2,
                area: ['600px', '500px'],
                content: [window.__PLATFORM__.root_url + 'admin/college/toaddoredit', 'no']
            });
        });
    }

    return {
        init: init
    }
});