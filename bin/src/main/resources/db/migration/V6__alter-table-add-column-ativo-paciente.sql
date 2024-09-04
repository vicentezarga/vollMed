alter table tb_paciente add column st_ativo boolean not null;

UPDATE tb_paciente set tb_paciente.st_ativo = 1;