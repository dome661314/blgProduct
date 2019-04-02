$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'blg/prod/list',
        datatype: "json",
        colModel: [
            { label: '商品编码', name: 'prodCode', index: 'prod_code', width: 80 },
            { label: '商品名称', name: 'prodName', index: 'prod_name', width: 80 },
            { label: '商品规格', name: 'prodSpec', index: 'prod_spec', width: 80 },
            { label: '商品价格', name: 'prodPrice', index: 'prod_price', width: 80 },
            { label: '商品数量', name: 'prodNum', index: 'prod_num', width: 80 },
            { label: '生产日期', name: 'produceDate', index: 'produce_date', width: 80 },
            { label: '商品单位', name: 'prodUnit', index: 'prod_unit', width: 80 },
            { label: '商品品牌', name: 'prodBrand', index: 'prod_brand', width: 80 },
            { label: '押金', name: 'deposit', index: 'deposit', width: 80 },
            { label: '商品产地', name: 'prodPlace', index: 'prod_place', width: 80 },
            { label: '商品成色', name: 'prodCondition', index: 'prod_condition', width: 80 },
            { label: '商品图片', name: 'imagePath', index: 'image_path', width: 80 },
            { label: '商品状态', name: 'prodStatus', width: 60, formatter: function(value, options, row){
                    return value === 0 ?
                        '<span class="label label-danger">下架</span>' :
                        '<span class="label label-success">上架</span>';
                }},
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });

    new AjaxUpload('#upload', {
        action: baseURL + "blg/prod/upload",
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete : function(file, r){
            if(r.code == 0){
                vm.prod.imagePath = r.url;
            }else{
                alert(r.msg);
            }
        }
    });
});

var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            prodCode: null
        },
        showList: true,
        title: null,
        prod: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.prod = {};
        },
        update: function (event) {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.prod.id == null ? "blg/prod/save" : "blg/prod/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.prod),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "blg/prod/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(index){
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function(id){
            $.get(baseURL + "blg/prod/info/"+id, function(r){
                vm.prod = r.prod;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'prodCode': vm.q.prodCode},
                page:page
            }).trigger("reloadGrid");
        },
        dateDefault: function(event){
            var d, s;
            var self = this;
            d = new Date();
            s = d.getFullYear() + "-";       //取年份
            s = s + (d.getMonth() + 1) + "-";   //取月份,date生成的月份为0-11
            s += d.getDate() + " ";        //取日期
            s += d.getHours() + ":";        //取小时
            s += d.getMinutes() + ":";       //取分
            s += d.getSeconds();          //取秒
            self.time = s;
            $('.form_datetime').datetimepicker({
                language: 'zh-CN',
                format: 'yyyy-mm-dd hh:ii:ss',
                //startDate: s,    默认开始时间
                weekStart: 0,    //一周从那一天开始，默认值为:0,范围：0-6
                todayBtn: 1,    //默认值：false，为true时，底部显示today，不选中，为linked时当天日期被选中
                autoclose: 1,    //选择一个日期后是否立即关闭此选择框
                todayHighlight: 1,  //高亮当前日期
                startView: 2,     // 日期时间选择器打开之后首先显示的视图，默认值为：2，0:hour,1:day,2:mouth,3:year,4:decade
                forceParse: 0,    //强制解析文本框的值
                showMeridian: 1
            });
            $('#form_datetime').datetimepicker()
                .on('hide', function (ev) {
                    var value = $("#form_datetime").val();
                    self.time = value;
                });

        },
        mounted: function(event) {
            //必须在组件渲染之后调用
            this.dateDefault();
        },
    }
});