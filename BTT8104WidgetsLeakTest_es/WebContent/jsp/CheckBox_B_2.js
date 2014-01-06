window.CheckBox_B_2 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'CheckBox_B_2_Button01', e: 'onClick'}],
  cond: function(e) {
    return this.getPW('CheckBox_B_2_checkBox', 'isChecked');
  },
  onTrue: function(e) {
    this.setPW('CheckBox_B_2_checkBox', 'isChecked', false);
  },
  onFalse: function(e) {
    this.setPW('CheckBox_B_2_checkBox', 'isChecked', true);
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'CheckBox_B_2_checkBox', e: 'onChange'}],
  cond: function(e) {
    return this.getPW('CheckBox_B_2_checkBox', 'isChecked');
  },
  onTrue: function(e) {
    this.setPW('CheckBox_B_2_checkBox', 'text', 'checkbox true');
  },
  onFalse: function(e) {
    this.setPW('CheckBox_B_2_checkBox', 'text', 'checkbox true');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(CheckBox_B_2);
});