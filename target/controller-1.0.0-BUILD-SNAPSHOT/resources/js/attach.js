/**
 * 첨부파일 관련 js
 */

 $(document).ready(function(){

	var bno = $("input[name='bno']").val();
	
	$.getJSON("/attachlist",{bno:bno},function(attachlist){
		
		console.log(attachlist);
		
		var str_img="";
		var str="";
		
		$(attachlist).each(function(i,attach){
							
			if(attach.img){
				var filePath = encodeURIComponent(attach.uploadPath + "/s_" + attach.uuid + "_" + attach.fileName);			
				str_img+="<li><img src='/display?fileName="+filePath+"'></li>";
			}
			else{ 
				var filePath = encodeURIComponent(attach.uploadPath + "/" + attach.uuid + "_" + attach.fileName);
				
				str+="<li>"
				str+="<a href='/download?fileName="+filePath+"'>"+attach.fileName+"</a>";
				str+="</li>"

			}
			
		})
		
		$("#uploadimg").html(str_img);
		$("#uploadResult").html(str);
	
	})	
})