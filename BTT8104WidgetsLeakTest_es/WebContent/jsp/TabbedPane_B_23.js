window.TabbedPane_B_23 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'TabbedPane_B_23_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('TabbedPane_B_23_tabbedPane', 'selectChildByIndex', 1);
  }
}, 
{ /* ecaRule02 */
  name: 'ecaRule02',
  evts: [{ id: 'TabbedPane_B_23_ContentPane01', e: 'onShow'}],
  onTrue: function(e) {
    this.setPW('TabbedPane_B_23_ContentPane01', 'title', 'str');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'TabbedPane_B_23_ContentPane02', e: 'onShow'}],
  onTrue: function(e) {
    this.setPW('TabbedPane_B_23_ContentPane01', 'title', 'str');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(TabbedPane_B_23);
});