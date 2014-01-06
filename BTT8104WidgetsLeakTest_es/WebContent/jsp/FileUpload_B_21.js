window.FileUpload_B_21 = [{ /* ecaRule */
  name: 'ecaRule',
  evts: [{ id: 'FileUpload_B_21_Button01', e: 'onClick'}],
  onTrue: function(e) {
    this.setPW('FileUpload_B_21_fileUpload', 'hint', 'str');
  }
}];
dojo.addOnLoad(function(){
  engine.registerRules(FileUpload_B_21);
});