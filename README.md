create table users(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username varchar(50) not null ,
	password varchar(50) not null,
	enabled boolean not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table authorities (
	user_id BIGINT UNSIGNED NOT NULL,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(user_id) references users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create unique index ix_auth_id on authorities (user_id,authority);

create table groups (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	group_name varchar(50) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table group_authorities (
	group_id BIGINT UNSIGNED NOT NULL,
	authority varchar(50) not null,
	constraint fk_group_authorities_groups foreign key(group_id) references groups(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table group_members (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id BIGINT UNSIGNED NOT NULL,
	group_id bigint  UNSIGNED not null,
	constraint fk_group_members_groups foreign key(group_id) references groups(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;