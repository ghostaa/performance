window.Select_B_13 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'Select_B_13_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('Select_B_13_selectList', 'focus');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'Select_B_13_selectList', e: 'onFocus'}],
  onTrue: function(e) {
    this.runWF('Select_B_13_selectList', 'showErrorMessage', 'str');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(Select_B_13);
});