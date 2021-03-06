//树形只包含菜单，不包含按钮
function onlyGetMenuTree() {
    var root = {
        id : 0,
        name : "root",
        open : true,
    };

    $.ajax({
        type : 'get',
        url : '/permission/listAllPermission',
        contentType : "application/json; charset=utf-8",
        async : false,
        success : function(ret) {
            var data = ret.data
            var length = data.length;
            var children = [];
            for (var i = 0; i < length; i++) {
                var d = data[i];
                var node = createNode(d,true);
                children[i] = node;
            }
			console.log(children);
            root.children = children;
        }
    });

    return root;
}
//获取的树形包括菜单和按钮
function getMenuTree() {
	var root = {
		id : 0,
		name : "root",
		open : true,
	};

	$.ajax({
		type : 'get',
		url : '/permission/listAllPermission',
		contentType : "application/json; charset=utf-8",
		async : false,
		success : function(ret) {
			var data = ret.data
			var length = data.length;
			var children = [];
			for (var i = 0; i < length; i++) {
				var d = data[i];
				var node = createNode(d);
				children[i] = node;
			}

			root.children = children;
		}
	});

	return root;
}

function initMenuDatas(roleId){
    $.ajax({
        type : 'get',
        url : '/rolepermission/listAllPermissionByRoleId?roleid=' + roleId,
        success : function(ret) {
            var data = ret.datas;
            var length = data.length;
            var ids = [];
            for(var i=0; i<length; i++){
                ids.push(data[i]['permissionid']);
            }
            initMenuCheck(ids);
        }
    });
}

function initMenuCheck(ids) {
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var length = ids.length;
	if(length > 0){
		var node = treeObj.getNodeByParam("id", 0, null);
		treeObj.checkNode(node, true, false);
	}
	
	for(var i=0; i<length; i++){
		var node = treeObj.getNodeByParam("id", ids[i], null);
		treeObj.checkNode(node, true, false);
	}
	
}

function initRadioCheckTree(){
	//有接收传递过来的parentid放在input里
	var pid = $("#parentid").attr("value");
	if(pid != undefined && pid.length > 0){
		//把input里的parentid显示在treeDemo里
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        //节点属性和pid相同的节点
        var node = treeObj.getNodeByParam("id", pid, null);
        //该node节点改为cheak=true，即为选中
        treeObj.checkNode(node, true, false);
	}
}
function initSelectType(){
    var type = $("#selectType").attr("value");
    if(type != undefined && type.length > 0){
        $("#selectType").siblings("div.layui-form-select").find('dl').find('dd[lay-value='+type+']').click()
    }
}
function getCheckedMenuIds(){
	//ztree封装好的方法，获取nodes
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	
	var length = nodes.length;
	var ids = [];
	//循环遍历，一个node对应一个权限id
	for(var i=0; i<length; i++){
		var n = nodes[i];
		var id = n['id'];
		ids.push(id);
	}
	
	return ids;
}
//noShowBtn:树形列表中不显示按钮选择项，默认没值代表显示，true代表不显示
function createNode(d,noShowBtn) {

	var id = d['permissionid'];
	var pId = d['parentid'];
	var name = d['name'];
	var child = d['child'];

	var node = {
		open : true,
		id : id,
		name : name,
		pId : pId,
	};

	if (child != null) {
		var length = child.length;
		if (length > 0) {
			var children = [];
			var j = 0;
			for (var i = 0; i < length; i++) {
                if(!(noShowBtn && child[i].type == 2)){
                    children[j] = createNode(child[i],noShowBtn);
                    j++;
                }
			}
			if(children.length > 0){
                node.children = children;
			}

		}

	}
	return node;
}

function initParentMenuSelect(){
	$.ajax({
        type : 'get',
        url : '/permissions/parents',
        async : false,
        success : function(data) {
            var select = $("#parentId");
            select.append("<option value='0'>root</option>");
            for(var i=0; i<data.length; i++){
                var d = data[i];
                var id = d['id'];
                var name = d['name'];
                
                select.append("<option value='"+ id +"'>" +name+"</option>");
            }
        }
    });
}

function getSettting(isRadioType){
	var setting = {
		check : {
			enable : true,
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}
		},
		async : {
			enable : true,
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : 0
			}
		},
		callback : {
			onCheck : zTreeOnCheck
		}
	};
	if(isRadioType){
        setting.check =  {
            enable: true,
			chkStyle: "radio",
			radioType: "all"
        }
	}
	return setting;
}

function zTreeOnCheck(event, treeId, treeNode) {
//	console.log(treeNode.id + ", " + treeNode.name + "," + treeNode.checked
//			+ "," + treeNode.pId);
//	console.log(JSON.stringify(treeNode));
}