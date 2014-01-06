window.ValidationTextBox_B_17 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'ValidationTextBox_B_17_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('ValidationTextBox_B_17_wtxt', 'focus');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'ValidationTextBox_B_17_wtxt', e: 'onFocus'}],
  onTrue: function(e) {
    this.setPW('ValidationTextBox_B_17_wtxt', 'readOnly', true);
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(ValidationTextBox_B_17);
});