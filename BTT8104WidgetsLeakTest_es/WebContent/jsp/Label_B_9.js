window.Label_B_9 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'Label_B_9_button', e: 'onClick'}],
  onTrue: function(e) {
    this.setPW('Label_B_9_label', 'hint', 'str');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(Label_B_9);
});