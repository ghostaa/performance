[{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'BorderContainer_B_38_button_ECA', e: 'onClick'}],
  onTrue: function(e, _rule) {
    this.runWF('BorderContainer_B_38_borderContainer_centerPanel', 'refresh');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'BorderContainer_B_38_borderContainer_centerPanel', e: 'onFocus'}],
  onTrue: function(e, _rule) {
    this.setPW('BorderContainer_B_38_borderContainer', 'gutters', true);
  }
}]