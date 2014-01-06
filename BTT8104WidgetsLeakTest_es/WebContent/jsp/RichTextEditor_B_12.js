window.RichTextEditor_B_12 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'RichTextEditor_B_12_richText', e: 'onFocus'}],
  onTrue: function(e) {
    this.setPW('RichTextEditor_B_12_richText', 'value', 'str');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'RichTextEditor_B_12_button', e: 'onClick'}],
  onTrue: function(e) {
    this.runWF('RichTextEditor_B_12_richText', 'focus');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(RichTextEditor_B_12);
});