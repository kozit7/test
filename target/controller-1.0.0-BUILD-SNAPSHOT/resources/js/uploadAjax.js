/**
 * 
 */

$(document).ready(function(){
	$("#uploadBtn").on("click",function(){
		alert("수정이 완료되었습니다.");
})// #uploadBtn 끝

		// 정규식을 이용하여 확장자 제한
		var reg = new RegExp("(.*?)\.(exe|zip|alz)$")
		var maxSize = 5242880; // 5MB

		function checkExtension(fileName, fileSize){

			if(fileSize >= maxSize){
				alert("파일 사이즈 초과");
				return false;
			} 
			if(reg.test(fileName)){
				alert("해당종류의 파일은 업로드 할 수 없습니다.");
				return false;
			} 
			return true;
		} // checkExtension 끝
	
		$("#upFileBtn").on("click",function(e){
			e.preventDefault();

			var formData = new FormData();			
			var inputFile = $("input[name='uploadFile']");
			
			var files = inputFile[0].files;
			
			console.log(files);
			
			for(var i=0; i<files.length; i++){
				
				// 함수 호출(checkExtension)
				if(!checkExtension(files[i].name, files[i].size)){
					return false;
				}

				formData.append("uploadFile",files[i]);
				
			} // for 끝

			$.ajax({
				type : "post",
				url : "/uploadAjaxAction3",
				data : formData,
				contentType : false,
				processData : false,
				dataType:"json"
			}) // ajax 끝
		}) // upFileBtn 끝
})