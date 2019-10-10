use db_lumicode;
-- 
select * from tbl_cliente;
-- estabelecimento 
select * from tbl_estabelecimento;
select * from tbl_foto;

-- funcionario
select * from tbl_funcionario;
select * from tbl_funcionario_estabelecimento;
select * from tbl_funcionario_servico;
select * from tbl_categoria_servico;
select * from tbl_servico;
select * from tbl_salario;
select * from tbl_tipo_salario;
-- horario
select * from tbl_horario_funcionario;
select * from tbl_dia_semana;
-- 

-- agendamento 
select * from tbl_agendamento;

-- endereco
select * from tbl_endereco;
select * from tbl_tipo_endereco;
select * from tbl_cidade;
select * from tbl_microrregiao;
select * from tbl_estado;
-- fim dereco

select * from tbl_fale_conosco;
select * from tbl_assunto;




-- INSERTS PADRAO

-- tipo 
INSERT INTO tbl_tipo_endereco VALUES (1, 'Residencial');
INSERT INTO tbl_tipo_endereco VALUES (2, 'Comercial');

-- Cadastrando um cliente padrao
INSERT INTO tbl_endereco (id_endereco, logradouro, bairro, cep, id_tipo_endereco, id_cidade, criado_em, atualizado_em) VALUES (1, 'Rua Antônio gomes dos santos', 'Paque dos lagos', '06622-445', 1, 3525003, NOW(), NOW());

INSERT INTO tbl_cliente values (1,'David', 'Silva', '(11) 97709-9609', '435.423.668-03', 'M', '2002-12-06', 'david@gmail.com','123456789',  null, NOW(), NOW(), 1);


-- Cadastrando Estabelecimento padrão
INSERT INTO tbl_endereco (id_endereco, logradouro, bairro, cep, id_tipo_endereco, id_cidade, criado_em, atualizado_em) VALUES (2,'Ruinha legal', 'Teraza', '06622220', 2, 3525003,  NOW(), NOW());

INSERT INTO tbl_estabelecimento (id_estabelecimento, cnpj, razao_social, nome_estabelecimento, telefone, email, senha, criado_em, atualizado_em, id_endereco) values (1,'58.373.487/0001-38', 'agendaTeste', 'TesteAgenda s2', '11 46197048', 'teste@oul.com', '123456789',  NOW(), NOW(), 2);

-- cadastro de Assunto padrão 

INSERT INTO tbl_assunto values (1,'Comentário');
INSERT INTO tbl_assunto values (2,'Sugetão');
INSERT INTO tbl_assunto values (3,'Duvida');

-- cadastro de comentario padrão
select * from tbl_fale_conosco;
Insert into tbl_fale_conosco values (0, 'david', 'david@gmail.com', 'muito bom isso ai', now(), 1);

-- INSERT DE FUNCIONARIO
select * from tbl_dia_semana;
INSERT INTO tbl_dia_semana values(1, 'DOMINGO');
INSERT INTO tbl_dia_semana values(2, 'SEGUNDA-FEIRA');
INSERT INTO tbl_dia_semana values(3, 'TERÇA-FEIRA');
INSERT INTO tbl_dia_semana values(4, 'QUARTA-FEIRA');
INSERT INTO tbl_dia_semana values(5, 'QUINTA-FEIRA');
INSERT INTO tbl_dia_semana values(6, 'SEXTA-FEIRA');
INSERT INTO tbl_dia_semana values(7, 'SÁBADO');

select * from tbl_tipo_salario;

insert into tbl_tipo_salario values (1, 'Comissão');
insert into tbl_tipo_salario values (2, 'Valor Fixo');
insert into tbl_tipo_salario values (3, 'Comissão + Valor Fixo');

SELECT * FROM TBL_SALARIO;
INSERT INTO tbl_salario values (1, 500.00, NULL, 1, NOW(), NOW() );
desc tbl_funcionario;
select * from tbl_funcionario;
INSERT INTO tbl_funcionario values (1, 'Ivanildo', null, 'ivan_fera', '789456123', 1);

desc tbl_horario;
select * from tbl_horario;
INSERT INTO tbl_horario_funcionario values(1, '8:30:00' , '17:00:00', '12:00:00', '13:00:00', 7, 1, now(), now());
INSERT INTO tbl_horario_funcionario values(2, '8:30:00' , '17:00:00', '12:00:00', '13:00:00', 3, 1, now(), now());
INSERT INTO tbl_horario_funcionario values(3, '8:30:00' , '17:00:00', '12:00:00', '13:00:00', 4, 1, now(), now());
INSERT INTO tbl_horario_funcionario values(4, '8:30:00' , '17:00:00', '12:00:00', '13:00:00', 5, 1, now(), now());
INSERT INTO tbl_horario_funcionario values(5, '8:30:00' , '17:00:00', '12:00:00', '13:00:00', 6, 1, now(), now());

-- serviços
desc tbl_categoria_servico;
INSERT INTO tbl_categoria_servico values (1, 'Corte Masculino');
INSERT INTO tbl_categoria_servico values (2, 'Corte Feminino');

desc tbl_servico;
SELECT * FROM TBL_SERVICO;
INSERT INTO tbl_servico values(1, 35.00 ,'BABY BANGS', 1, 1, now(), now());
INSERT INTO tbl_servico values(2, 20.00, 'SOCIAL', 1, 1, now(), now());

desc tbl_funcionario_servico;
select * from tbl_funcionario_servico;
insert into tbl_funcionario_servico values (0, 1, 1); 
insert into tbl_funcionario_servico values (0, 1, 2); 

desc tbl_categoria_servico;

SELECT f.nome, s.servico, cs.categoria_servico, sa.salario, tipoSa.tipo_salario, h.hora_entrada, ds.dia_semana
	FROM tbl_funcionario as f INNER JOIN tbl_funcionario_servico as fs
    ON f.id_funcionario = fs.id_funcionario INNER JOIN tbl_servico AS s
    ON s.id_servico = fs.id_servico INNER JOIN tbl_categoria_servico as cs
    ON cs.id_categoria_servico = s.id_categoria_servico INNER JOIN tbl_salario as sa
    ON sa.id_salario = f.id_salario INNER JOIN tbl_tipo_salario as tipoSa
    ON sa.id_tipo_salario = tipoSa.id_tipo_salario INNER JOIN tbl_horario_funcionario as h
    ON f.id_funcionario = h.id_funcionario INNER JOIN tbl_dia_semana as ds
    ON h.id_dia_semana = ds.id_dia_semana; 
    
SELECT f.nome ,h.hora_entrada, h.hora_saida, h.hora_pausa, h.duracao_pausa, h.id_dia_semana
	FROM tbl_horario_funcionario as h INNER JOIN tbl_funcionario as f
    ON h.id_funcionario = f.id_funcionario ;
    
SELECT * from tbl_funcionario_estabelecimento;

INSERT INTO tbl_funcionario_estabelecimento values (1, 1, 1);

SELECT e.nome_estabelecimento , s.servico, f.nome, s.preco, ds.dia_semana
	FROM tbl_funcionario as f JOIN tbl_funcionario_servico as fs
    ON f.id_funcionario = fs.id_funcionario JOIN tbl_servico as s
    ON fs.id_servico = s.id_servico JOIN tbl_horario_funcionario as hf
    ON f.id_funcionario = hf.id_funcionario JOIN tbl_dia_semana as ds
    ON hf.id_dia_semana = ds.id_dia_semana JOIN tbl_funcionario_estabelecimento as fe
    ON fe.id_funcionario = f.id_funcionario JOIN tbl_estabelecimento as e
    ON fe.id_estabelecimento = e.id_estabelecimento; 
    

select * from  tbl_agendamento;
 desc tbl_agendamento;

INSERT INTO tbl_agendamento values (1, 1, 1, 1, 1, 0, now(), now(), now());
