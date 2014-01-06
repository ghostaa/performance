window.FormValidationMixin_B_29 = [{
	name: 'FormValidationMixin_B_29_form01.xValidation',
	evts: [{id: 'FormValidationMixin_B_29_form01', e:'onLoaded' }],
	cond: function(e) { return true; },
	onTrue: function(e) {
		this.setPW('FormValidationMixin_B_29_form01', 'xValidations', [
		{
			name:'xValidation',
			cond:function(e){
				return !(this.getPW('FormValidationMixin_B_29_btn_reload', 'inValid'));
			},
			widgets: ['FormValidationMixin_B_29_btn_reload'],
			hint: 'FormXValidation promt'
		}]);
	} 
}];
dojo.addOnLoad(function(){
  engine.registerRules(FormValidationMixin_B_29);
});