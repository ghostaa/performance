<div dojoType="com.ibm.btt.dijit.Button" id="testButton" type="button"  >
	Start
	<script type="dojo/method" data-dojo-event="onClick">
		this.set("disabled", true);
		window.clickCounter = dijit.byId("testInput").get('value');
		setInterval(function(){ 
			if(window.clickCounter) {
				window.clickCounter --;
				dijit.byId('Group_B_22_btn_reload')._onButtonClick({preventDefault:function(){}});
			} 
		}, 15000);
    </script>
    <script type="dojo/method" data-dojo-event="postCreate">
		if(typeof window.clickCounter == "undefined") window.clickCounter = 0;
		if(window.clickCounter != 0 ) {
			this.set("disabled", true);
		} else {
			this.set("disabled", false);
		}
    	dojo.byId('testLabel').innerHTML = window.clickCounter;
    </script>
</div>
<div id="testInput" dojoType="dijit.form.NumberTextBox" >
	<script type="dojo/method" data-dojo-event="postCreate">
		if(typeof window.clickCounter == "undefined") window.clickCounter = 0;
		if(window.clickCounter != 0 ) {
			dojo.style(this.domNode, "display", "none");
		} else {
			dojo.style(this.domNode, "display", "");
		}
	</script>
</div>

<label id="testLabel" >0</label>