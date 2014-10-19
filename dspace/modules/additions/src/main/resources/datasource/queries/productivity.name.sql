AND
	UPPER(TRIM(SUBSTRING(
	metadatavalue.text_value, 
			STRPOS(metadatavalue.text_value, ' by ') + 4, 
			STRPOS(
				(SUBSTRING(metadatavalue.text_value, STRPOS(metadatavalue.text_value, ' by ') + 4, 100)) , '('
			) - 1 ))) LIKE ?