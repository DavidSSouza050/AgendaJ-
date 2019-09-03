use db_lumicode;
select * from tbl_cliente;
select * from tbl_cliente_endereco;
select * from tbl_estabelecimento;
select * from tbl_estabelecimento_endereco;
select * from tbl_endereco;
select * from tbl_cidade;
select * from tbl_microregiao;
select * from tbl_cidade WHERE cidade = 'JANDIRA';
select * from tbl_estado;
select * from tbl_tipo_endereco;



-- tipo 
INSERT INTO tbl_tipo_endereco VALUES (0, 'Residencial');
INSERT INTO tbl_tipo_endereco VALUES (0, 'Comercial');

-- Cadastrando um cliente padrao
INSERT INTO tbl_cliente values (1,'David', 'Silva', '(11) 97709-9609', '435.423.668-03', 'M', '2002-12-06', 'david@gmail.com','123456789',  null, NOW(), NOW());

INSERT INTO tbl_endereco (id_endereco, logradouro, bairro, cep, id_tipo_endereco, id_cidade, criado_em, atualizado_em) VALUES (1, 'Rua Antônio gomes dos santos', 'Paque dos lagos', '06622-445', 1, 3525003, NOW(), NOW());

INSERT INTO tbl_cliente_endereco VALUES (1, 1, 1, NOW(), NOW());

-- Cadastrando Estabelecimento padrão
INSERT INTO tbl_estabelecimento (id_estabelecimento, cnpj, razao_social, nome_estabelecimento, telefone, email, senha, criado_em, atualizado_em) values (1,'100.000.000.000', 'agendaTeste', 'TesteAgenda s2', '11 46197048', 'teste@oul.com', '123456789',  NOW(), NOW());

INSERT INTO tbl_endereco (id_endereco, logradouro, bairro, cep, id_tipo_endereco, id_cidade, criado_em, atualizado_em) VALUES (2,'Ruinha legal', 'Teraza', '06622220', 2, 3525003,  NOW(), NOW());

INSERT INTO tbl_estabelecimento_endereco values (1, 1,  2,  NOW(), NOW());

delete FROM tbl_endereco where id_endereco = 3;


SELECT cliente.nome , cliente.cpf, te.tipo , cidade.cidade, endereco.logradouro
		from tbl_cliente as cliente INNER JOIN tbl_cliente_endereco as cliente_endereco
        ON cliente.id_cliente = cliente_endereco.id_cliente INNER JOIN tbl_endereco as endereco
        ON endereco.id_endereco= cliente_endereco.id_endereco INNER JOIN tbl_cidade as cidade
        ON endereco.id_cidade = cidade.id_cidade INNER JOIN tbl_tipo_endereco as te
        ON endereco.id_tipo_endereco = te.id_tipo_endereco;
        
SELECT e.nome_estabelecimento, te.tipo,c.cidade, m.microrregiao, estado.estado, estado.uf
		from tbl_estabelecimento as e INNER JOIN tbl_estabelecimento_endereco as estabelecimento_endereco
		ON e.id_estabelecimento = estabelecimento_endereco.id_estabelecimento INNER JOIN tbl_endereco as endereco
        ON estabelecimento_endereco.id_endereco = endereco.id_endereco INNER JOIN tbl_cidade as c 
        ON endereco.id_cidade = c.id_cidade INNER JOIN tbl_microrregiao as m 
        ON c.id_microrregiao = m.id_microrregiao INNER JOIN tbl_estado as estado
        ON m.id_estado = estado.id_estado INNER JOIN tbl_tipo_endereco as te
        ON endereco.id_tipo_endereco = te.id_tipo_endereco;
        
SELECT * from tbl_cidade WHERE id_cidade = 3525003;
