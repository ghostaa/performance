window.Anchor_B_1 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'Anchor_B_1_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('Anchor_B_1_link', 'focus');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'Anchor_B_1_link', e: 'onFocus'}],
  cond: function(e) {
    return BTTUtil.equals('1', '1') ;
  },
  onTrue: function(e) {
    this.setPW('Anchor_B_1_link', 'text', 'this is link ECA');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(Anchor_B_1);
});