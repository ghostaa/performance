[{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'checkedMultiSelect_B_32_button_ECA', e: 'onClick'}],
  onTrue: function(e, _rule) {
    this.runWF('checkedMultiSelect_B_32_checkedMultiSelect', 'focus');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'checkedMultiSelect_B_32_checkedMultiSelect', e: 'onFocus'}],
  onTrue: function(e, _rule) {
    this.setPW('checkedMultiSelect_B_32_checkedMultiSelect', 'visibility', 'hidden');
  }
}]