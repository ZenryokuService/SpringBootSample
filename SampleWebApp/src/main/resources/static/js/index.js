/* 登録処理 */
function insertData() {
	var data = $('#insertForm').serializeArray().reduce(function(obj, item) {
	    obj[item.name] = item.value;
	    return obj;
	}, {});
	// 通信処理
	$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/addData",
			data:JSON.stringify( data),
//			dataType: "json",
			catshe: false,
			timeout: 1000,
			success:  function(data) {
				alert("登録できました。: " + data);
				location.reload();
			},
			error: function(e) {
				alert("登録できませんでした。サーバー管理者に連絡してください。");
				console.log("ERROR: ", e );
			}
	});
}

/* 更新DOMに変更*/
function changeInputDom(id) {
	let typeArr = ["text", "text", "text", "select", "text"];
	let  rows = document.getElementById('row' + id).cells;
	let optA = document.getElementById('selectValueA').value;
	let optB = document.getElementById('selectValueB').value;

	let i = 0;
	for (let i = 0; i < rows.length; i++) {
		if (i < 2) {
			continue;
		}
		let type = typeArr[i - 2];
		if (type == 'text') {
			rows[i].innerHTML  = "<input type='" + type + "' value='" + rows[i].innerHTML + "' />";
		} else {
			let tag   = "<select name='isFinished'>"
			tag = tag + "<option  value='true'>" + optA + "</option>";
			tag  = tag  + "<option  value='false'>" + optB + "</option>";
			tag = tag + "</select>";
			rows[i].innerHTML = tag;
		}
		let btnDom = document.getElementById("btn" + id);
		btnDom.value = "GO";
		btnDom.setAttribute("onclick", "sendInput(" + id + ")");
	}
}

function sendInput(id) {
	alert(id);

	var data = $('#row' + id).
	console.log(data);
/*	$.get("/replace/update", {id: id}, function(fragment) {
		$('#' + id).replaceWith(fragment);
	}); */

}