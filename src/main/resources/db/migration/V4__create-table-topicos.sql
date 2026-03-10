create table topicos (

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(500) not null,
    fecha_creacion datetime not null,
    status varchar(50) not null,

    autor_id bigint not null,
    curso_id bigint not null,

    primary key(id),

    constraint fk_topico_autor foreign key (autor_id) references usuarios(id),
    constraint fk_topico_curso foreign key (curso_id) references cursos(id)

);





