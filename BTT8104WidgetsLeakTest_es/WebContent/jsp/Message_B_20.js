window.Message_B_20 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'Message_B_20_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('Message_B_20_message', 'display', 'str');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(Message_B_20);
});