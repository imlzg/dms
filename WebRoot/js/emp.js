$(document).ready(function(){
	$("#empname").blur(function(){
	    var $empname = $("#empname").val();
	    if($empname == ''){
//	        $("#empnameTip").html("员工姓名不能为空");
	        return false;
	    }
	    $("#empnameTip").html("");
	});
	
	$("#address").blur(function(){
		var $address = $("#address").val();
	    if($address == ''){
//	        $("#addressTip").html("住址不能为空");
	        return false;
	    }
	    $("#addressTip").html("");
	});
	
	$("#phone").blur(function(){
		var $phone = $("#phone").val();
	    if($phone == ''){
//	        $("#phoneTip").html("电话不能为空");
	        return false;
	    }
	    if ($phone != '' && !/^1\d{10}$/.exec($phone)) {
	    	$("#phoneTip").html("请正确填写手机号码！如：13812344321");
	    	return false;
     	}
	    $("#phoneTip").html("");
	});
	
	$("#email").blur(function(){
		var $email = $("#email").val();
	    if($email == ''){
//	        $("#emailTip").html("邮箱不能为空");
	        return false;
	    }
	    if ('' != $email && ! /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/.exec($email)) {
	    	$("#emailTip").html("请正确填写邮箱地址！如：andy@122.com");
	    	return false;
     	}
	    $("#emailTip").html("");
	});
	
	$("#dept").change(function(){
		var $dept = $("#dept").val();
	    if($dept == ''){
	        $("#deptTip").html("请选择部门");
	        return false;
	    }
	    $("#deptTip").html("");
	});
	
	$("#role").change(function(){
		var $role = $("#role").val();
	    if($role == ''){
	        $("#roleTip").html("请选择角色");
	        return false;
	    }
	    $("#roleTip").html("");
	});
	
	$("#sub").click(function(){
		var $empname = $("#empname").val();
	    if($empname == ''){
//	        $("#empnameTip").html("员工姓名不能为空");
	        return false;
	    }
	    $("#empnameTip").html("");
	
	    var $address = $("#address").val();
	    if($address == ''){
//	        $("#addressTip").html("住址不能为空");
	        return false;
	    }
	    $("#addressTip").html("");
	
	    var $phone = $("#phone").val();
	    if($phone == ''){
//	        $("#phoneTip").html("电话不能为空");
	        return false;
	    }
	    if ($phone != '' && !/^1\d{10}$/.exec($phone)) {
	    	$("#phoneTip").html("请正确填写手机号码！如：13812344321");
	    	return false;
     	}
	    $("#phoneTip").html("");
	
	    var $email = $("#email").val();
	    if($email == ''){
//	        $("#emailTip").html("邮箱不能为空");
	        return false;
	    }
	    if ('' != $email && ! /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/.exec($email)) {
	    	$("#emailTip").html("请正确填写邮箱地址！如：andy@122.com");
	    	return false;
     	}
	    $("#emailTip").html("");
	
	    var $dept = $("#dept").val();
	    if($dept == ''){
	        $("#deptTip").html("请选择部门");
	        return false;
	    }
	    $("#deptTip").html("");
	
	    var $role = $("#role").val();
	    if($role == ''){
	        $("#roleTip").html("请选择角色");
	        return false;
	    }
	    $("#roleTip").html("");
	    alert("操作成功");
	});
});

/*
function depts(){
	var url = '${pageContext.request.contextPath }/jsonaction_allDept.action?d='+Math.random();
    $.ajax( {
        type : 'post',
        url : url,
        dataType : 'json',
        success : function(data) {
            $.each(data, function(i, list) {
                var $option = $("<option></option>");
                $option.attr("value", list[0]);
                $option.text(list[1]);
                $("#dept").append($option);
            });
            $("#dept").find("option[value='${emp.department.id}']").attr("selected",true);
        }
    });
}

function roles(){
    var url = '${pageContext.request.contextPath }/jsonaction_allRoel.action?d='+Math.random();
    $.ajax( {
        type : 'post',
        url : url,
        dataType : 'json',
        success : function(data) {
            $.each(data, function(i, list) {
                var $option = $("<option></option>");
                $option.attr("value", list[0]);
                $option.text(list[1]);
                $("#role").append($option);
            });
            $("#role").find("option[value='${emp.role.id}']").attr("selected",true);
        }
    });
}*/