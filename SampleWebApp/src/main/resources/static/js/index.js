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

/* */