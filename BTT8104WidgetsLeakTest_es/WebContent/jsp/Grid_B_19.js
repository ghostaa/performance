window.Grid_B_19 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'Grid_B_19_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('Grid_B_19_table', 'hideColumn', 'columnId1');
  }
}, 
{ /* ecaRule03 */
  name: 'ecaRule03',
  evts: [{ id: 'Grid_B_19_table', e: 'onBlur'}],
  onTrue: function(e) {
    this.runWF('Grid_B_19_table', 'showColumn', 'columnId1');
  },
  onFalse: function(e) {
    this.runWF('Grid_B_19_table', 'showColumn', 'columnId1');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(Grid_B_19);
});