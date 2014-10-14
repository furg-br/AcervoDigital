
INSERT INTO community2community (id, parent_comm_id, child_comm_id)
(
		SELECT 
			nextval('community2community_seq'), /** Inserir número da comunidade "pai" **/, parent_comm_id 
		FROM 
			community2community 
		INNER JOIN 
			community ON community.community_id = parent_comm_id 
		WHERE 
			parent_comm_id NOT IN (SELECT child_comm_id FROM community2community)
);


INSERT INTO community2community (id, parent_comm_id, child_comm_id)
(
	SELECT 
		nextval('community2community_seq'), /** Inserir número da comunidade "pai" **/, community_id 
	FROM 
		community 
	WHERE 
		community_id NOT IN (SELECT parent_comm_id FROM community2community) AND community_id NOT IN (SELECT child_comm_id FROM community2community)
);
