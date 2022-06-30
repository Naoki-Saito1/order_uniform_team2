$(function(){
$('#form').validate({
	// Validationルール
	rules: {
		name: {
			required: true,
		},
		email: {
			required: true,
			email: true,
		},
		post_code1: {
			required: true,
			rangelength:[3,3],
		},
		post_code2: {
			required: true,
			rangelength:[4,4],
		},
		address: {
			required: true,
		},
		quantity: {
			required: true,
		},
		adminid: {
			required: true,
		},
		password: {
			required: true,
		},
	},

	// エラーメッセージ
	messages: {
		name: {
			required: '名前を入力してください',
		},
		email: {
			required: 'メールアドレスを入力してください',
			email: 'メールアドレスの形式で入力してください',
		},
		post_code1: {
			required: '郵便番号が空です。',
			rangelength:'3桁を入力してください。',
		},
		post_code2: {
			required: '郵便番号が空です。',
			rangelength:'4桁を入力してください。',
		},
		address: {
			required: '住所を入力してください。',
		},
		quantity: {
			required: '枚数を入力してください。',
		},
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