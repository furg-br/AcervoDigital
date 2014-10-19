AND
	TO_DATE(SUBSTRING(metadatavalue.text_value, STRPOS(metadatavalue.text_value, 'on 2') + 3,10), 'YYYY-MM-DD') BETWEEN ? AND ?