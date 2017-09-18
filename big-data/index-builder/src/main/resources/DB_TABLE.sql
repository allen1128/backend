CREATE TABLE `ad` (
  `adId` bigint(20) NOT NULL,
  `campaignId` bigint(20) NOT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `bidPrice` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `detail_url` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`adId`,`campaignId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
