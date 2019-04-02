$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/userAddress/list',
        datatype: "json",
        colModel: [
            { label: '用户ID', name: 'userId', index: "user_id", width: 45},
            { label: '用户名', name: 'userName', index: 'user_name', width: 80 },
            { label: '收货人姓名', name: 'receiveName', index: 'receive_name', width: 80 },
            { label: '收货人电话', name: 'receiveMobile', index: 'receive_mobile', width: 80 },
            { label: '省', name: 'province', index: 'province', width: 80 },
            { label: '市', name: 'city', index: 'city', width: 80 },
            { label: '县', name: 'county', index: 'county', width: 80 },
            { label: '邮政编码', name: 'postalCode', index: 'postal_code', width: 80 },
            { label: '详细地址', name: 'address', index: 'address', width: 80 },
            { label: '是否默认', name: 'isDefault', width: 60, formatter: function(value, options, row){
                    return value == 0 ?
                        '<span class="label label-danger">是</span>' :
                        '<span class="label label-success">否</span>';
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
});

var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            userName: null
        },
        showList: true,
        title: null,
        userId:null,
        userName:null,
        userAddress: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.userAddress = {userName:null,userId:null};
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
            var url = vm.userAddress.id == null ? "sys/userAddress/save" : "sys/userAddress/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.userAddress),
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
                    url: baseURL + "sys/userAddress/delete",
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
            $.get(baseURL + "sys/userAddress/info/"+id, function(r){
                vm.userAddress = r.userAddress;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'userName': vm.q.userName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});