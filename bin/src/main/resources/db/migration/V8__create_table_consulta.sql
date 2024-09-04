create table tb_consulta(

	id bigint not null auto_increment,
	cd_consulta varchar(10) not null,
	medico_id bigint not null,
	paciente_id bigint not null,
	dt_consulta datetime not null,
	
	primary key(id),
	constraint fk_consultas_medico_id foreign key (medico_id)references tb_medicos(id),
	constraint fk_consultas_paciente_id foreign key (paciente_id)references tb_paciente(id)
	
);