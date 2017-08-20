CREATE TABLE `running_analysis` (
  `running_id` varchar(255) NOT NULL,
  `health_warning_level` varchar(255) DEFAULT NULL,
  `heart_rate` int(11) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `running_distance` double DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `total_running_time` double DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`running_id`),
  KEY `FK_agjrm5gv94f0n55osaj9gt3et` (`user_id`),
  CONSTRAINT `FK_agjrm5gv94f0n55osaj9gt3et` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_key` (
  `PK_NAME` varchar(45) NOT NULL,
  `PK_VALUE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`PK_NAME`),
  UNIQUE KEY `PK_VALUE_UNIQUE` (`PK_VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
