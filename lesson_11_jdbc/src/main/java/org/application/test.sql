CREATE DATABASE test1;

CREATE TABLE students
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(256) NOT NULL,
    phone_number VARCHAR(10)
);


DROP TABLE students;


ALTER TABLE students
    ADD description VARCHAR(512);

INSERT INTO students (name, phone_number, description)
VALUES ('Timur', '2343242', 'student1');

INSERT INTO students (name, phone_number, description)
VALUES ('Vlad', '3234', 'student2');

INSERT INTO students (name, phone_number, description)
VALUES ('Marina', '1111', 'student3');

SELECT * FROM students ORDER BY name DESC ;

UPDATE students SET phone_number=333333 WHERE id=1;

DELETE FROM students WHERE name='Vlad';


CREATE TABLE hillel_groups (
                               id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                               name VARCHAR(256) NOT NULL
);


ALTER TABLE students
    ADD hillel_group_id INT;

ALTER TABLE students
    ADD FOREIGN KEY (hillel_group_id) REFERENCES hillel_groups(id);

SELECT * FROM students;


INSERT INTO hillel_groups(name)
VALUES ('JAVA_PRO');

SELECT * FROM hillel_groups;
SELECT * FROM students;

UPDATE students SET hillel_group_id=1;

SELECT name, COUNT(id) AS count FROM students
GROUP BY name;


SELECT name, hillel_group_id,  COUNT(id) AS count FROM students
GROUP BY name, hillel_group_id
HAVING students.hillel_group_id is NULL;


SELECT * FROM students AS s
                  JOIN hillel_groups AS hg ON s.hillel_group_id = hg.id;


SELECT * FROM students AS s
                  LEFT JOIN hillel_groups AS hg ON s.hillel_group_id = hg.id;

SELECT * FROM students AS s
                  RIGHT JOIN hillel_groups AS hg ON s.hillel_group_id = hg.id;


SELECT * FROM students AS s
                  CROSS JOIN hillel_groups;

SELECT * FROM students AS s
                  LEFT JOIN  hillel_groups hg ON s.hillel_group_id = hg.id
UNION
SELECT * FROM students AS s
                  RIGHT JOIN  hillel_groups hg ON s.hillel_group_id = hg.id;