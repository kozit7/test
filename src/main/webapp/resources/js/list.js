/**
 * 
 */

$(document).ready(function(){
	$("i").on("click",function(){
		$("input[name='pageNum']").val("1");
		$("#searchForm").submit();
	})
})