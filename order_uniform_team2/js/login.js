/**
 * login(admin)用validation
 */


$(function(){
$('#loginForm').validate({
	// Validationルール
	rules: {
		adminid: {
			required: true,
		},
		password: {
			required: true,
		},
	},

	// エラーメッセージ
	messages: {

		adminid: {
			required: 'IDを入力してください。',
		},
		password: {
			required: 'パスワードを入力してください。',
		},
	},

	// エラーメッセージ出力箇所
	errorPlacement: function(error, element){
		var name = element.attr('name');
		error.appendTo($('.is-error-'+name));
	},

	errorElement: "span",
	errorClass: "is-error",

});
});