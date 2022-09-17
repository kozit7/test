/**
 * 
 */

 $(document).ready(function(){

	var bnoVal=$("input[name='bno']").val();
	
	// 2. 댓글 목록 리스트
	list(bnoVal);  
	
	// 1. 댓글 쓰기 버튼을 클릭하면 
	$("#add").on("click",function(){
		var replyVal=$("#reply").val();
		var idVal=$("input[name='loginId']").val();
		
		add({bno:bnoVal,reply:replyVal,id:idVal});
	})
	
	
	// 3. 수정 버튼 클릭
	$("#chat").on("click",".modify",function(){
		var rno=$(this).data("rno");
		var reply=$("#replycontent"+rno).val();
		var id=$("input[name='loginId']").val();

		modifylist({bno:bnoVal,rno:rno,reply:reply,id:id});
	})
		
	// 4. 댓글 수정완료을 클릭하면
	$("#chat").on("click",".update",function(){
		var rno=$(this).data("rno");
		var reply=$("#replycontent"+rno).val();

		modify({rno:rno,reply:reply});
	})
	
	// 5. 댓글 삭제버튼 클릭하면
	$("#chat").on("click",".remove",function(){		
		var rno=$(this).data("rno");

		remove(rno); 
	})
	
})


// 1. add 함수 선언 시작
function add(reply){   

	$.ajax({  
		type:"post", 
		url:"/replies/new",
		data:JSON.stringify(reply),
		contentType:"application/json; charset=utf-8",
		success:function(result){
			if(result=="success"){
				alert("댓글이 등록되었습니다.");
				location.reload();
			}
		}
	})
}   
// add 함수 선언 끝

// 2. list 함수 선언 시작
function list(bno){
	
	$.getJSON("/replies/"+bno+".json",function(data){
		
		var str="";
		var id=$("input[name='loginId']").val();
		
		for(var i=0;i<data.length;i++){
			str+="<li>"
			str+="ㄴ"+data[i].id
			if(id==data[i].id){
			str+="<input class='modify' type='button' id='remo' value='수정' data-rno="+data[i].rno+">"			
			str+="<input class='remove' type='button' id='rere' value='삭제' data-rno="+data[i].rno+">"
			}
			str+="</li>"
			str+="<li><textarea readonly rows='5' cols='128' class='rct' id='replycontent"+data[i].rno+"'>"+data[i].reply+"</textarea></li>"
		}
		
		$("#replyUL").html(str);
	})
}
// list 함수 선언 끝

// 3. modifylist 함수 선언 시작
function modifylist(bno){

	$.getJSON("/replies/"+bno.bno+".json",function(data){
		var str="";
		
		for(var i=0;i<data.length;i++){
			str+="<li>"
			str+="ㄴ"+data[i].id
			if(bno.rno==data[i].rno){
				str+="<input class='update' type='button' id='remo' value='수정완료' data-rno="+data[i].rno+">"
				str+="<input class='back' type='button' id='rere' value='취소' onclick='location.reload()'>"
					str+="</li>"                                                                       
						str+="<li><textarea rows='5' cols='128' class='rc' id='replycontent"+data[i].rno+"'>"+data[i].reply+"</textarea></li>"
			}
			else{
				if(bno.id==data[i].id){
					str+="<input class='modify' type='button' id='remo' value='수정' data-rno="+data[i].rno+">"
					str+="<input class='remove' type='button' id='rere' value='삭제' data-rno="+data[i].rno+">"
				}
			str+="</li>"                                                                       
			str+="<li><textarea readonly rows='5' cols='128' class='rct' id='replycontent"+data[i].rno+"'>"+data[i].reply+"</textarea></li>"
			}
		}
		
		$("#replyUL").html(str);
	})
}
// 3. modifylist 함수 선언 끝

// 4. modify 함수 선언 시작
function modify(reply){
	console.log(reply);
	$.ajax({  
		type:"put",
		url:"/replies/modify",
		data:JSON.stringify(reply),
		contentType:"application/json; charset=utf-8",
		success:function(result){
			if(result=="success"){
				alert("댓글이 수정되었습니다.");
				location.reload();
			}
		}
	})
}
// modify 함수 선언 끝

// 5. remove 함수 선언 시작
function remove(rno){
	console.log(rno);
	$.ajax({ 
		type:"delete",  
		url:"/replies/remove/"+rno,

		success:function(result){
			if(result=="success"){
				alert("댓글이 삭제되었습니다.");
				location.reload();
			}
		}
	})
}
// remove 함수 선언 끝