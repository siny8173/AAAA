$(function() {
	// alert("aaa");
})

function onClickRow(index) {
	var row = $('#dg').datagrid('getSelected');
	var obj = document.getElementById("showpanel");

	var html = "<h1>" + row['title'] + "</h1>";
	html = html + "<h6>" + row['text'] + "</h6>";
	obj.innerHTML = html;
}