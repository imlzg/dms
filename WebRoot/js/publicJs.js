$(function(){
	showtable();
})
//隔行变色
	function showtable(){ 
		var color1 = "#f0f0f0"; 
		var color2 = "rgb(255,255,255)"; 
		var bgColor = "rgb(255,255,193)"; 
		var trs = document.getElementById("tableId").getElementsByTagName("tr"); 
			for (var i=0;i<trs.length;i++){ 
				if (i%2==0) { 
					trs[i].style.backgroundColor=color1; 
					trs[i].onmouseover = function(){ 
					this.style.backgroundColor = bgColor; 
				} 
					trs[i].onmouseout = function(){ 
						this.style.backgroundColor = color1; 
					}
				}else { 
					trs[i].style.backgroundColor=color2; 
					trs[i].onmouseover = function(){ 
						this.style.backgroundColor = bgColor; 
					} 
					trs[i].onmouseout = function(){ 
						this.style.backgroundColor = color2; 
				}
			} 
		} 
	}