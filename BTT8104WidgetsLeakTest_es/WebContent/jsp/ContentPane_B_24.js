window.ContentPane_B_24 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'ContentPane_B_24_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('ContentPane_B_24_tabbedPane', 'selectChildByIndex', 2);
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'ContentPane_B_24_ContentPane03', e: 'onShow'}],
  onTrue: function(e) {
    this.setPW('ContentPane_B_24_ContentPane01', 'title', 'str');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(ContentPane_B_24);
});