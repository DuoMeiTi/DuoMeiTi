/**
 * 
 */



$("#envelope").click(function(){
	var list_box=$("#message-list-box");
	var content_box=$("#message-content-box");
	var contacts_box=$("#message-contacts-box");
	if(list_box.hasClass("hide")&&content_box.hasClass("hide")&&contacts_box.hasClass("hide")){
		list_box.removeClass("hide");
		start();
	}
	else{
		message_contacts_box_hide();
		message_list_box_hide();
		message_content_box_hide();
	}
})

$(".contacts-list-group").on("click","li",function(){
	message_list_box_hide();
	message_content_box_show();
	var contentBox=$("#message-content-box");
	contentBox.attr("iid",$(this).attr("id"));
	$(".message-content-top .title").html($(this).html());
});
	


function userlistCallback(data){
	var adminList=data.adminList;
	var studentList=data.studentList;
	var adminListBox=$("#admin-contacts ul");
	var teacherListBox=$("#teacher-contacts ul");
	var studentListBox=$("#student-contacts ul");
	$(adminList).each(function(i){
		adminListBox.append('<li class="list-group-item" id="'+adminList[i].id+'">'+adminList[i].username+'</li>');
	})
	$(studentList).each(function(i){
		studentListBox.append('<li class="list-group-item" id="'+studentList[i].id+'">'+studentList[i].username+'</li>');
	})
}

function message_list_box_show(){
	$("#message-list-box").removeClass("hide");
}
function message_list_box_hide(){
	$("#message-list-box").addClass("hide");
}
function message_contacts_box_show(){
	var box=$("#message-contacts-box");
	if(box.hasClass("hide")){
		box.removeClass("hide");
		$.ajax({
			url : '/message/userlist',
			type : 'post',
			dataType : 'json',
			data : {},
			success : userlistCallback
		});
	}
}
function message_contacts_box_hide(){
	$("#message-contacts-box").addClass("hide");
	$("#admin-contacts ul").html("");
	$("#teacher-contacts ul").html("");
	$("#student-contacts ul").html("");
}
function message_content_box_show(){
	$("#message-content-box").removeClass("hide");
}
function message_content_box_hide(){
	$("#message-content-box").addClass("hide");
}

$("#message-list-box .closed").click(function(){
	message_list_box_hide();
	message_contacts_box_hide();
})

$("#write-message").click(function(){
	message_content_box_show();
	message_list_box_hide();
	message_contacts_box_show();
})

$("#message-content-box .closed").click(function(){
	message_content_box_hide();
	message_contacts_box_hide();
})

$("#message-content-box .back").click(function(){
	message_content_box_hide();
	message_list_box_show();
})

$("#message-contacts-box .closed").click(function(){
	message_contacts_box_hide();
})

$(".users").click(function(){ 
	message_contacts_box_show();
})

$(".contacts-expand").click(function(){
	var s=$(this).children("span");
	if(s.hasClass("glyphicon-plus")){
		s.removeClass("glyphicon-plus");
		s.addClass("glyphicon-minus");
	}
	else{
		s.removeClass("glyphicon-minus");
		s.addClass("glyphicon-plus");
	}
	
})

//////////////////////////////////////////////////////////////////
var socket = null;

function init(){
	if("WebSocket" in window){
		socket = new WebSocket("ws://localhost:8080/websocket");
	}
	else{
		alert("该浏览器不支持WebSocket");
	}
	socket.onerror = function(event){
		handleError(event);
	}
	socket.onopen = function(event){
		handleOpen(event);
	}
	socket.onmessage = function(event){
		handleMessage(event);
	}
}

function handleError(event){
	//alert(event.data);
}

function handleOpen(event){
	//alert("ok");
}

function handleMessage(event){
	//alert(event.data);
}

function start(){
	init();
}