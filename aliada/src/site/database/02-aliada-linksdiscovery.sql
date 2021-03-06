CREATE TABLE `aliada`.`linksdiscovery_job_instances` (
`job_id` INT NOT NULL AUTO_INCREMENT,
`input_uri`  VARCHAR( 245 ) default NULL,
`input_login`  VARCHAR( 32 ) default NULL,
`input_password`  VARCHAR( 32 ) default NULL,
`input_graph`  VARCHAR( 245 ) default NULL,
`output_uri`  VARCHAR( 245 ) default NULL,
`output_login`  VARCHAR( 32 ) default NULL,
`output_password`  VARCHAR( 32 ) default NULL,
`output_graph`  VARCHAR( 245 ) default NULL,
`tmp_dir`  VARCHAR( 245 ) default NULL,
`client_app_bin_dir`  VARCHAR( 245 ) default NULL,
`client_app_user`  VARCHAR( 245 ) default NULL, 
`start_date` DATETIME default NULL,
`end_date` DATETIME default NULL ,
PRIMARY KEY ( `job_id` )
) ENGINE = InnoDB ;

CREATE TABLE `aliada`.`linksdiscovery_subjob_instances` (
`job_id` INT NOT NULL ,
`subjob_id` INT NOT NULL AUTO_INCREMENT,
`name`  VARCHAR( 245 ) default NULL,
`config_file`  VARCHAR( 245 ) default NULL,
`num_threads` INT default 1 ,
`reload_source` BOOLEAN default 0 ,
`reload_target` BOOLEAN default 0 ,
`output_uri`  VARCHAR( 245 ) default NULL,
`output_login`  VARCHAR( 32 ) default NULL,
`output_password`  VARCHAR( 32 ) default NULL,
`output_graph`  VARCHAR( 245 ) default NULL,
`tmp_dir`  VARCHAR( 245 ) default NULL,
`num_links` INT default 0 ,
`start_date` DATETIME default NULL,
`end_date` DATETIME default NULL ,
PRIMARY KEY (`subjob_id`)
) ENGINE = InnoDB ;

ALTER TABLE  `aliada`.`linksdiscovery_subjob_instances`
  ADD CONSTRAINT `linksdiscovery_subjob_instances_ibfk_1` FOREIGN KEY (`job_id`) REFERENCES `aliada`.`linksdiscovery_job_instances` (`job_id`) ON DELETE CASCADE ON UPDATE CASCADE;
