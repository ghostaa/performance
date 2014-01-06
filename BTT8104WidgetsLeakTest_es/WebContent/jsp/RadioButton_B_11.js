window.RadioButton_B_11 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'RadioButton_B_11_button', e: 'onClick'}],
  cond: function(e) {
    return this.getPW('RadioButton_B_11_radio', 'isChecked');
  },
  onTrue: function(e) {
    this.setPW('RadioButton_B_11_radio', 'isChecked', false);
  },
  onFalse: function(e) {
    this.setPW('RadioButton_B_11_radio', 'isChecked', true);
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'RadioButton_B_11_radio', e: 'onChange'}],
  cond: function(e) {
    return this.getPW('RadioButton_B_11_radio', 'isChecked');
  },
  onTrue: function(e) {
    this.setPW('RadioButton_B_11_radio', 'text', 'true');
  },
  onFalse: function(e) {
    this.setPW('RadioButton_B_11_radio', 'text', 'false');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(RadioButton_B_11);
});