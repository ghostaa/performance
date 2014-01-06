window.Pagination_B_28 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'Pagination_B_28_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('Pagination_B_28_table1', 'hideColumn', 'columnId1');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'Pagination_B_28_table1', e: 'onBlur'}],
  onTrue: function(e) {
    this.runWF('Pagination_B_28_table1', 'showColumn', 'columnId1');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(Pagination_B_28);
});