window.Button_B_7 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'Button_B_7_button01', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('Button_B_7_button', 'focus');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'Button_B_7_button', e: 'onFocus'}],
  onTrue: function(e) {
    this.setPW('Button_B_7_button', 'text', 'This is Button ECA');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(Button_B_7);
});