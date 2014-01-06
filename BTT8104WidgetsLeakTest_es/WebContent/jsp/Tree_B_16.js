window.Tree_B_16 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'Tree_B_16_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('Tree_B_16_tree', 'getSelectedLabel');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(Tree_B_16);
});