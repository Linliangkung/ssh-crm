	//使用ajax动态加载数据字典，生成select
	//typecode:数据字典类型
	//positionId:下拉选放入的位置
	//name:select的name属性值
	//selectedId:需要回显时选中的option
	function loadSelect(typecode,positionId,name,selectedId){
		//1创建select对象,将name属性指定
		var $select=$("<select name='"+name+"'></select>");
		//2添加提示选项
		 $select.append($("<option value=''>---请选择---</option>"));
		//3通过jquery的ajax方法访问后台获得数据字典json
		$.post("${pageContext.request.contextPath}/BaseDictAction",{dict_type_code:typecode},
				function(data){
			$.each(data,function(i,json){
				//4返回json数据，遍历
					//5创建option对象,判断是否需要回显,添加给select对象
					var dict_item_name =json["dict_item_name"];
					var dict_id=json["dict_id"];
					var $option=$("<option value='"+dict_id+"'>"+dict_item_name+"</option>");
					if(dict_id==selectedId){
						$option.attr("selected","selected");
					}
 					$select.append($option);
					
			});
		}
				,"json");
		
		//6将select放入指定位置
		$("#"+positionId).append($select);
	}