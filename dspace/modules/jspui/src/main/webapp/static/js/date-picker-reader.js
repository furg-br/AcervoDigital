(function() {
	
	jQuery.noConflict();
	jQuery.datepicker.setDefaults( jQuery.datepicker.regional[ "pt_BR" ] );
	jQuery(document).ready(function($) {
       
		$(".date-picker-pt_BR").datepicker({
        	dateFormat: "dd/mm/yy",
        	 changeMonth: true,
        	 changeYear: true
        });
        
    });
	

    
})();