window.CurrencyTextBox_B_4 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'CurrencyTextBox_B_4_text', e: 'onFocus'}],
  onTrue: function(e) {
    this.runWF('CurrencyTextBox_B_4_text', 'showErrorMessage', 'str');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'CurrencyTextBox_B_4_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('CurrencyTextBox_B_4_text', 'focus');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(CurrencyTextBox_B_4);
});