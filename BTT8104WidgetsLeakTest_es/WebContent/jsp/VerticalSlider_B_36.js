[{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'VerticalSlider_B_36_button_ECA', e: 'onClick'}],
  onTrue: function(e, _rule) {
    this.runWF('VerticalSlider_B_36_verticalSlider', 'focus');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'VerticalSlider_B_36_verticalSlider', e: 'onFocus'}],
  onTrue: function(e, _rule) {
    this.setPW('VerticalSlider_B_36_verticalSlider', 'disabled', true);
  }
}]