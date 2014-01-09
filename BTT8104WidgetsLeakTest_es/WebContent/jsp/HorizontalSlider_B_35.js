[{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'HorizontalSlider_B_35_button_ECA', e: 'onClick'}],
  onTrue: function(e, _rule) {
    this.runWF('HorizontalSlider_B_35_horizontalSlider', 'focus');
  }
}, 
{ /* ecaRule01 */
  name: 'ecaRule01',
  evts: [{ id: 'HorizontalSlider_B_35_horizontalSlider', e: 'onFocus'}],
  onTrue: function(e, _rule) {
    this.setPW('HorizontalSlider_B_35_horizontalSlider', 'disabled', true);
  }
}]