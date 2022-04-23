CREATE DATABASE IF NOT EXISTS projectmarduk;

USE projectmarduk;

# DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
                                     id INTEGER NOT NULL AUTO_INCREMENT,
                                     userName varChar(15),
                                     PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS folders (
                                       id INTEGER NOT NULL AUTO_INCREMENT,
                                       PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS users_folders(
                                            user_id INTEGER NOT NULL,
                                            folder_id INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS drawing_board (
                                             id INTEGER NOT NULL AUTO_INCREMENT,
                                             folder_id INTEGER NOT NULL,
                                             name varChar(13),
                                             x_size INTEGER NOT NULL,
                                             y_size INTEGER NOT NULL,
                                             PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS drawing_object (
                                              id INTEGER NOT NULL Auto_Increment,
                                              drawing_board_id INTEGER NOT NULL,
                                              shape_type VARCHAR(20) NOT NULL,
                                              x_cord INTEGER NOT NULL,
                                              y_cord INTEGER NOT NULL,
                                              param_one INTEGER NOT NULL,
                                              param_two INTEGER,
                                              color VARCHAR(13),
                                              style VARCHAR(13),
                                              fill VARCHAR(7),
                                              text_one VARCHAR(255),
                                              text_two VARCHAR(255),
                                              text_three VARCHAR(255),
                                              PRIMARY KEY(id)
);