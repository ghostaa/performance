window.TextArea_B_15 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'TextArea_B_15_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('TextArea_B_15_textArea', 'focus');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'TextArea_B_15_textArea', e: 'onFocus'}],
  onTrue: function(e) {
    this.setPW('TextArea_B_15_textArea', 'readOnly', true);
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(TextArea_B_15);
});