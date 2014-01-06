window.Image_B_8 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'Image_B_8_button', e: 'onClick'}],
  onTrue: function(e) {
    this.setPW('Image_B_8_image', 'hint', 'str');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(Image_B_8);
});