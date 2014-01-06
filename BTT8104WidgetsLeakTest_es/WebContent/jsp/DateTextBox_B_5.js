window.DateTextBox_B_5 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'DateTextBox_B_5_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('DateTextBox_B_5_text', 'focus');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'DateTextBox_B_5_text', e: 'onFocus'}],
  onTrue: function(e) {
    this.setPW('DateTextBox_B_5_text', 'readOnly', true);
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(DateTextBox_B_5);
});