INSERT INTO file (file_name, file_path, file_type) VALUES ('First file', 'path1', 'mp4');
INSERT INTO file (file_name, file_path, file_type) VALUES ('Second file', 'path2', 'png');
INSERT INTO file (file_name, file_path, file_type) VALUES ('Third file', 'path3', 'pdf');

INSERT INTO file_on_channel (channel_id, fk_file_id) VALUES (1, 1);
INSERT INTO file_on_channel (channel_id, fk_file_id) VALUES (1, 2);
INSERT INTO file_on_channel (channel_id, fk_file_id) VALUES (1, 3);
INSERT INTO file_on_channel (channel_id, fk_file_id) VALUES (2, 1);
INSERT INTO file_on_channel (channel_id, fk_file_id) VALUES (2, 3);

INSERT INTO file_owner (user_id, fk_file_id) VALUES (1, 1);
INSERT INTO file_owner (user_id, fk_file_id) VALUES (1, 2);
INSERT INTO file_owner (user_id, fk_file_id) VALUES (2, 3);