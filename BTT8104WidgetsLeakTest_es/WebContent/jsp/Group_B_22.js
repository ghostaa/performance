window.Group_B_22 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'Group_B_22_button', e: 'onClick'}],
  onTrue: function(e) {
    this.setPW('Group_B_22_group', 'class', 'str');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(Group_B_22);
});