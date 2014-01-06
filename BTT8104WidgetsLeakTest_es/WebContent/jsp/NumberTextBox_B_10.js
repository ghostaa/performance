window.NumberTextBox_B_10 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'NumberTextBox_B_10_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('NumberTextBox_B_10_text', 'focus');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'NumberTextBox_B_10_text', e: 'onFocus'}],
  onTrue: function(e) {
    this.setPW('NumberTextBox_B_10_text', 'value', 1233);
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(NumberTextBox_B_10);
});