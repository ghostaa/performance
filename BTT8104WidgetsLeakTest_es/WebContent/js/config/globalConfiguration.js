dojo.provide("config.globalConfiguration");
dojo.require("com.ibm.btt.util.GlobalConfigurationUtil");
/**
 * This property indicates the selection list opens when the user clicks on the button at the right or anywhere on the widget.
 * Options : ["OnlyOnButton","OnWholeWidget"]
 * OnlyOnButton : If the value of widget property 'typeAhead' is 'true' the selection list opens when the user clicks on the button at the right.
 *                else if the value of widget property 'typeAhead' is 'false' the selection list opens when the user clicks anywhere on the widget.
 * OnWholeWidget : The selection list opens when the user clicks anywhere on the widget no matter what's the value of widget property 'typeAhead'.
 *                 and the value of the widget can't be edited.
 */
com.ibm.btt.util.GlobalConfigurationUtil.setValue("selectList.clickModeForDropDown", "OnlyOnButton");