window.ComboBox_B_3 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'ComboBox_B_3_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('ComboBox_B_3_combo', 'focus');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'ComboBox_B_3_combo', e: 'onFocus'}],
  onTrue: function(e) {
    this.runWF('ComboBox_B_3_combo', 'showErrorMessage', 'str');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(ComboBox_B_3);
});