/*Populate Table Articulos*/
INSERT INTO articulos (descripcion, componentes, contenido, gramaje, stock) VALUES ('prueba1', 'prueba1', 'prueba1', 2, 5);
INSERT INTO articulos (descripcion, componentes, contenido, gramaje, stock) VALUES ('prueba2', 'prueba2', 'prueba2', 2, 5);
INSERT INTO articulos (descripcion, componentes, contenido, gramaje, stock) VALUES ('prueba3', 'prueba3', 'prueba3', 2, 5);
INSERT INTO articulos (descripcion, componentes, contenido, gramaje, stock) VALUES ('prueba4', 'prueba4', 'prueba4', 2, 5);
INSERT INTO articulos (descripcion, componentes, contenido, gramaje, stock) VALUES ('prueba5', 'prueba5', 'prueba5', 2, 5);
INSERT INTO articulos (descripcion, componentes, contenido, gramaje, stock) VALUES ('prueba6', 'prueba6', 'prueba6', 2, 5);
INSERT INTO articulos (descripcion, componentes, contenido, gramaje, stock) VALUES ('prueba7', 'prueba7', 'prueba7', 2, 5);
INSERT INTO articulos (descripcion, componentes, contenido, gramaje, stock) VALUES ('prueba8', 'prueba8', 'prueba8', 2, 5);
INSERT INTO articulos (descripcion, componentes, contenido, gramaje, stock) VALUES ('prueba9', 'prueba9', 'prueba9', 2, 5);
INSERT INTO articulos (descripcion, componentes, contenido, gramaje, stock) VALUES ('prueba10', 'prueba10', 'prueba10', 2, 5);

INSERT INTO usuarios (username, nombre, apellidos, email, password, enabled) VALUES ('frank666', 'frank', 'mujica fuentealba', 'f.mujica1995@gmail.com', '$2a$10$1/FWMTqX/H4enA5s9k.ZGupTEudJhq0mk1QQMzYvRGjpdBw.nwQSu', 2);
INSERT INTO usuarios (username, nombre, apellidos, email, password, enabled) VALUES ('suicideboy', 'suicide', 'boys', 's.boys666@gmail.com', '$2a$10$ua67IMKby8nIhNyS5LxXZezHbxz0b1zfjH13dp2gsNCnA6jxbPKg6', 1);

INSERT INTO roles (nombre) VALUES ('ROLE_MEDICO');
INSERT INTO roles (nombre) VALUES ('ROLE_FARMACEUTICO');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);