use db_lumicode;
select * from tbl_cliente;
select * from tbl_estabelecimento;
select * from tbl_endereco;
select * from tbl_cidade;
select * from tbl_microregiao;
select * from tbl_cidade WHERE cidade = 'JANDIRA';
select * from tbl_estado;
select * from tbl_tipo_endereco;



-- tipo 
INSERT INTO tbl_tipo_endereco VALUES (1, 'Residencial');
INSERT INTO tbl_tipo_endereco VALUES (2, 'Comercial');

-- Cadastrando um cliente padrao
INSERT INTO tbl_endereco (id_endereco, logradouro, bairro, cep, id_tipo_endereco, id_cidade, criado_em, atualizado_em) VALUES (1, 'Rua Antônio gomes dos santos', 'Paque dos lagos', '06622445', 1, 3525003, NOW(), NOW());

INSERT INTO tbl_cliente values (1,'David', 'Silva', '(11) 97709-9609', '435.423.668-03', 'M', '2002-12-06', 'david@gmail.com','123456789',  null, NOW(), NOW(), 1);


-- Cadastrando Estabelecimento padrão
INSERT INTO tbl_endereco (id_endereco, logradouro, bairro, cep, id_tipo_endereco, id_cidade, criado_em, atualizado_em) VALUES (2,'Ruinha legal', 'Teraza', '06622220', 2, 3525003,  NOW(), NOW());

INSERT INTO tbl_estabelecimento (id_estabelecimento, cnpj, razao_social, nome_estabelecimento, telefone, email, senha, criado_em, atualizado_em, id_endereco) values (1,'100.000.000.000', 'agendaTeste', 'TesteAgenda s2', '11 46197048', 'teste@oul.com', '123456789',  NOW(), NOW(), 2);




SELECT cliente.id_cliente, cliente.nome, cidade.cidade, endereco.cep, endereco.id_tipo_endereco, microrregiao.microrregiao, estado.estado, estado.uf
	FROM tbl_cliente as cliente inner join tbl_endereco as endereco
    ON cliente.id_endereco = endereco.id_endereco INNER JOIN tbl_cidade as cidade
    On endereco.id_cidade = cidade.id_cidade INNER JOIN tbl_microrregiao as microrregiao
    ON cidade.id_microrregiao = microrregiao.id_microrregiao INNER JOIN tbl_estado as estado
    on microrregiao.id_estado = estado.id_estado;


    

