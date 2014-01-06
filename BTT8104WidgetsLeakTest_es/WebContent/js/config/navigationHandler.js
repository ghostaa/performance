[{
	name : 'forward',				
	handler : function(evt){			 
		console.log("forward event");
		return false;

	}
},
{
	name : 'backward',				
	handler : function(evt){			 
		console.log("backward event");
		return false;

	}
},
{
	name : 'refresh',				
	handler : function(evt){			 
		console.log("refresh event");
		return false;

	}
},
{
	name : 'leave',				
	handler : function(evt){			 
      console.log("leave event");
	  evt = evt || window.event;

	  //Please use an I18N string if necessary
	  var message = 'It is not recommended that do navigation beyond of Application Logic';

	  
	  //For IE<8 and FF4
	  if (evt) 
	    evt.returnValue = message;
	  		 
	  // Chrome, Safari, Firefox 4+, Opera 12+ , IE 9+
	  return message;
 
	}
}]
