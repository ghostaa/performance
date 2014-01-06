window.Form_B_6 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'Form_B_6_cfrm', e: 'onLoaded'}],
  onTrue: function(e) {
    this.setPW('Form_B_6_btn_reload', 'hint', 'str');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'Form_B_6_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('Form_B_6_cfrm', 'validate');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(Form_B_6);
});