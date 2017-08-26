/**
 * 
 */
$.extend($.fn.validatebox.defaults.rules, {
	specialCharFilte: {
		validator: function(value, param){
			var pattern = new RegExp("[';#$%&<>]");
			return (!pattern.test(value));
		},
		message: '不能包含以下特殊字符[\';#$%&<>]'
    }
});