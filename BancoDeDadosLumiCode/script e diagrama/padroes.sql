use db_lumicode;
-- 
select * from tbl_cliente;
-- estabelecimento 
select * from tbl_estabelecimento;
select * from tbl_foto;

-- funcionario
select * from tbl_funcionario;
select * from tbl_agendamento;
select * from tbl_agendamento_servico;
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

SELECT f.id_funcionario, f.nome, f.id_salario, 
sa.salario, 
ts.tipo_salario,
a.data_horario_agendado,
s.id_servico, s.servico, s.preco;


SELECT 
SUM(s.preco) AS total_comissao
FROM tbl_funcionario AS f
INNER JOIN tbl_salario AS sa
ON f.id_salario = sa.id_salario
INNER JOIN tbl_tipo_salario AS ts
ON sa.id_tipo_salario = ts.id_tipo_salario
INNER JOIN tbl_agendamento AS a
ON f.id_funcionario = a.id_funcionario
INNER JOIN tbl_agendamento_servico AS ase
ON a.id_agendamento = ase.id_agendamento
INNER JOIN tbl_servico AS s
ON ase.id_servico = s.id_servico
WHERE f.id_funcionario = 1;

SELECT 
    sum(s.preco)
FROM
    tbl_agendamento AS a
        INNER JOIN
    tbl_agendamento_servico AS ase ON a.id_agendamento = ase.id_agendamento
        INNER JOIN
    tbl_servico AS s ON ase.id_servico = s.id_servico;

-- agendamento 
select * from tbl_agendamento;

-- endereco
select * from tbl_endereco;
select * from tbl_cidade;
select * from tbl_microrregiao;
select * from tbl_estado;
-- fim dereco

select * from tbl_fale_conosco;
select * from tbl_assunto;




-- INSERTS PADRAO


-- Cadastrando um cliente padrao
INSERT INTO tbl_endereco (id_endereco, logradouro, bairro, cep, id_cidade) VALUES (1, 'Rua Antônio gomes dos santos', 'Paque dos lagos', '06622-445', 3525003);

INSERT INTO tbl_cliente values (1,'David', 'Silva', '(11) 97709-9609', '435.423.668-03', 'M', '2002-12-06', 'david@gmail.com','123456789',  null, NOW(), NOW());

INSERT INTO tbl_endereco_cliente values (1, 1, 1);

-- Cadastrando Estabelecimento padrão
INSERT INTO tbl_endereco (id_endereco, logradouro, bairro, cep, id_cidade, numero) VALUES (2,'Ruinha legal', 'Teraza', '06622220', 3525003,  452);

INSERT INTO tbl_estabelecimento (id_estabelecimento, cnpj, razao_social, nome_estabelecimento, telefone, email, senha) values (1,'58.373.487/0001-38', 'agendaTeste', 'TesteAgenda s2', '11 46197048', 'teste@oul.com', '123456789');

INSERT INTO tbl_endereco_estabelecimento values (1,2,1);

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
INSERT INTO tbl_funcionario values (1, 'Ivanildo', null, 1,'ivan_fera', '789456123', 1, now(), now());

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
INSERT INTO tbl_servico values(1, 'BABY BANGS', 35.00 , 30,  1, 2, now(), now());
INSERT INTO tbl_servico values(2, 'SOCIAL', 20.00, 20, 1, 1, now(), now());

desc tbl_funcionario_servico;
select * from tbl_funcionario_servico;
insert into tbl_funcionario_servico values (0, 1, 1); 
insert into tbl_funcionario_servico values (0, 1, 2); 

desc tbl_categoria_servico;
    
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

INSERT INTO tbl_agendamento values (1, 1, 1, 1,  '2019-10-15 14:00:00',0, 'A', now(), now());
select * from tbl_agendamento;
select * from tbl_cliente;
select * from tbl_categoria_servico;
select * from tbl_servico;
select * from tbl_funcionario;
select * from tbl_estabelecimento;

SELECT c.nome, e.nome_estabelecimento, ag.finalizado, f.nome, s.servico, s.preco,cs.categoria_servico
	FROM tbl_cliente as c JOIN tbl_agendamento as ag 
    ON c.id_cliente = ag.id_cliente JOIN tbl_estabelecimento as e
    ON e.id_estabelecimento = ag.id_estabelecimento JOIN tbl_funcionario as f
    ON f.id_funcionario = ag.id_funcionario JOIN tbl_servico as s
    ON s.id_servico = ag.id_servico JOIN tbl_categoria_servico as cs
    ON s.id_categoria_servico = cs.id_categoria_servico;

desc tbl_horario_estabelecimento;
select * FROM tbl_horario_estabelecimento;
INSERT INTO tbl_horario_estabelecimento VALUES(1,  '20:00:00', '8:30:00', 1, 3, now(), now());
INSERT INTO tbl_horario_estabelecimento VALUES(2, '20:00:00','8:30:00',  1, 4, now(), now());
INSERT INTO tbl_horario_estabelecimento VALUES(3, '20:00:00','8:30:00',  1, 5, now(), now());
INSERT INTO tbl_horario_estabelecimento VALUES(4,  '20:00:00','8:30:00', 1, 6, now(), now());
INSERT INTO tbl_horario_estabelecimento VALUES(5, '17:00:00','9:00:00', 1, 7, now(), now());


insert into tbl_agendamento_servico values (1,1,1);
insert into tbl_agendamento_servico values (2,2,1);
select * from tbl_agendamento_servico;
select * from tbl_salario;
select * from tbl_agendamento;
desc tbl_endereco;
insert into tbl_salario values (2, 0, 50, 1, now(),now());

select * from tbl_cliente;
INSERT INTO tbl_funcionario values (2, 'David', null, 1, 'david@gmail.com', '123456789', 2, now(), now());

INSERT INTO tbl_funcionario_estabelecimento values(1,1,1);
INSERT INTO tbl_funcionario_estabelecimento values(2,2,1);


SELECT nome, cpf from tbl_cliente WHERE cpf like '435.423.668-03';

desc tbl_agendamento;
desc tbl_em_servico;


SELECT * FROM tbl_horario_estabelecimento;


select * from tbl_agendamento WHERE id_agendamento = 1;

SELECT * FROM tbl_agendamento_servico as a
	INNER JOIN tbl_agendamento as ag WHERE ag.id_agendamento = 1;
    
    
select * from tbl_em_servico;
DELETE FROM tbl_em_servico where id_em_servico > 0;



SELECT f.nome, em.ocupado_inicio, em.ocupado_fim FROM tbl_em_servico as em INNER JOIN tbl_funcionario as f 	ON f.id_funcionario = em.id_funcionario INNER JOIN tbl_estabelecimento as e  ON em.id_estabelecimento = e.id_estabelecimento where em.dia_mes = 20 and em.mes = 11 and em.ano = 2019 and em.id_estabelecimento = 1;


SELECT * FROM tbl_funcionario as f INNER JOIN tbl_funcionario_estabelecimento as fe
	ON f.id_funcionario = fe.id_funcionario INNER JOIN tbl_estabelecimento as e
    ON fe.id_estabelecimento = e.id_estabelecimento INNER JOIN  tbl_horario_funcionario as hf
    ON f.id_funcionario = hf.id_funcionario WHERE e.id_estabelecimento = 1 and hf.id_dia_semana = 7;

select * from tbl_horario_funcionario where id_funcionario = 2;

INSERT INTO tbl_horario_funcionario (hora_entrada, hora_saida, id_dia_semana, id_funcionario) values('8:00:00','16:00:00', 2, 2);

DESC tbl_horario_funcionario;

SELECT * from tbl_funcionario as f INNER JOIN tbl_horario_funcionario as hf
	ON f.id_funcionario = hf.id_funcionario WHERE hf.id_dia_semana = 6 AND hf.hora_entrada <> '16:00:00' AND hf.hora_saida <> '17:00:00'

