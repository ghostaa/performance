[{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'TitlePane_B_41_button01', e: 'onClick'}],
  onTrue: function(e, _rule) {
    this.runWF('TitlePane_B_41_titlePane', 'focus');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'TitlePane_B_41_titlePane', e: 'onFocus'}]
}]