[{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'Multiselect_B_31_button01', e: 'onClick'}],
  onTrue: function(e, _rule) {
    this.runWF('Multiselect_B_31_multiSelect', 'focus');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'Multiselect_B_31_multiSelect', e: 'onFocus'}],
  onTrue: function(e, _rule) {
    this.setPW('Multiselect_B_31_multiSelect', 'disabled', true);
  }
}]