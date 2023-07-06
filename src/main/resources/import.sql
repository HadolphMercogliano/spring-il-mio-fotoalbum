INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('Tramonto a Varese','Tramonto','',true);
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('Alba a Varese','Alba','',false);

INSERT INTO `categories`(`name`) VALUES ('Alba')
INSERT INTO `categories`(`name`) VALUES ('Tramonto')

INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES ('1','2')
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES ('2','1')
