window.GridComparer_B_26 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'GridComparer_B_26_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('GridComparer_B_26_table', 'hideColumn', 'columnId1');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'GridComparer_B_26_table', e: 'onBlur'}],
  onTrue: function(e) {
    this.runWF('GridComparer_B_26_table', 'showColumn', 'columnId1');
  },
  onFalse: function(e) {
    this.runWF('GridComparer_B_26_table', 'showColumn', 'columnId1');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(GridComparer_B_26);
});