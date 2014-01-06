window.Hidden_B_25 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'Hidden_B_25_button', e: 'onClick'}],
  onTrue: function(e) {
    this.setPW('Hidden_B_25_HiddenField01', 'value', 'str');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(Hidden_B_25);
});