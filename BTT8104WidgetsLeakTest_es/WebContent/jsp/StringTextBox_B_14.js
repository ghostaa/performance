window.StringTextBox_B_14 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'StringTextBox_B_14_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('StringTextBox_B_14_text', 'focus');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'StringTextBox_B_14_text', e: 'onFocus'}],
  onTrue: function(e) {
    this.setPW('StringTextBox_B_14_text', 'readOnly', true);
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(StringTextBox_B_14);
});