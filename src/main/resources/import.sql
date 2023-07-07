INSERT INTO `photos`(`description`, `title`, `visible`) VALUES ('Tramonto a Varese','Tramonto',true);
INSERT INTO `photos`(`description`, `title`, `visible`) VALUES ('Alba a Varese','Alba',false);

INSERT INTO `categories`(`name`) VALUES ('Alba');
INSERT INTO `categories`(`name`) VALUES ('Tramonto');

INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES ('1','2');
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES ('2','1');

INSERT INTO `users`(`id`, `email`, `first_name`, `last_name`, `password`) VALUES (1,'email1@email.com','Hadolph','Mercogliano','{noop}password1');
INSERT INTO `roles`(`id`, `name`) VALUES (1,'ADMIN');
INSERT INTO `users_roles`(`user_id`, `roles_id`) VALUES (1,1);

INSERT INTO `messages`( `email`, `message`) VALUES ('ciao@mail.it','ciao, come va?')