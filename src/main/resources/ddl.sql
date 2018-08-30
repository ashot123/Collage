DROP TABLE IF EXISTS `collage`.`courses_members`;
DROP TABLE IF EXISTS `collage`.`courses_details`;
DROP TABLE IF EXISTS `collage`.`teachers`;
DROP TABLE IF EXISTS `collage`.`rooms`;
DROP TABLE IF EXISTS `collage`.`students`;
DROP TABLE IF EXISTS `collage`.`courses`;

CREATE TABLE `courses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `teachers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `courses_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courses_id` int(11) NOT NULL,
  `teachers_id` int(11) NOT NULL,
  `rooms_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_courses_idx_1_idx` (`courses_id`),
  KEY `fk_teachers_idx_2_idx` (`teachers_id`),
  KEY `fk_rooms_idx_3_idx` (`rooms_id`),
  CONSTRAINT `fk_courses_idx_1` FOREIGN KEY (`courses_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `fk_rooms_idx_3` FOREIGN KEY (`rooms_id`) REFERENCES `rooms` (`id`),
  CONSTRAINT `fk_teachers_idx_2` FOREIGN KEY (`teachers_id`) REFERENCES `teachers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `courses_members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_membercol` varchar(45) NOT NULL,
  `courses_details_id` int(11) NOT NULL,
  `students_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_courses_details_idx_1_idx` (`courses_details_id`),
  KEY `fk_students_idx_2_idx` (`students_id`),
  CONSTRAINT `fk_courses_details_idx_1` FOREIGN KEY (`courses_details_id`) REFERENCES `courses_details` (`id`),
  CONSTRAINT `fk_students_idx_2` FOREIGN KEY (`students_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

