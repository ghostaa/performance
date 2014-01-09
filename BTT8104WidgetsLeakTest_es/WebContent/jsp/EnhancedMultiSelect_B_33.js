[{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'EnhancedMultiSelect_B_33_button_ECA', e: 'onClick'}],
  onTrue: function(e, _rule) {
    this.runWF('EnhancedMultiSelect_B_33_checkedMultiSelectExt', 'focus');
    this.setPW('EnhancedMultiSelect_B_33_button_ECA', 'text', 'ab');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'EnhancedMultiSelect_B_33_checkedMultiSelectExt', e: 'onFocus'}],
  onTrue: function(e, _rule) {
    this.setPW('EnhancedMultiSelect_B_33_checkedMultiSelectExt', 'visibility', 'hidden');
  }
}]