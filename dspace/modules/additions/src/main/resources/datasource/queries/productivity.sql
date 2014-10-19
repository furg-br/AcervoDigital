SELECT

			initcap(TRIM(SUBSTRING(
					metadatavalue.text_value, 
						STRPOS(metadatavalue.text_value, '' by '') + 4, 
						STRPOS(
							(SUBSTRING(metadatavalue.text_value, STRPOS(metadatavalue.text_value, '' by '') + 4, 100)) , ''(''
						) - 1 ))) as name, 
			SUBSTRING(metadatavalue.text_value, 1,4) AS action,
			SUBSTRING(metadatavalue.text_value, STRPOS(metadatavalue.text_value, ''on 2'') + 3,4) as year, 
			SUBSTRING(metadatavalue.text_value, STRPOS(metadatavalue.text_value, ''on 2'') + 8,2) as month,
			COUNT(*) as actionCount
		FROM
			metadatavalue
		WHERE
			metadata_field_id = 28
		AND
			SUBSTRING(metadatavalue.text_value, 1,4) <> ''Made''
			
		{0}
		
		{1}
			
		GROUP BY name, action, year, month
		LIMIT 5000; 