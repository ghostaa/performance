window.GridDijit_B_18 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'GridDijit_B_18_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('GridDijit_B_18_table', 'hideColumn', 'columnId1');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'GridDijit_B_18_table', e: 'onBlur'}],
  cond: function(e) {
    return this.runWF('GridDijit_B_18_table', 'isFocusable');
  },
  onTrue: function(e) {
    this.runWF('GridDijit_B_18_table', 'showColumn', 'columnId1');
  },
  onFalse: function(e) {
    this.runWF('GridDijit_B_18_table', 'hideColumn', 'columnId1');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(GridDijit_B_18);
});