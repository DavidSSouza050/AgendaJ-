use db_lumicode;
select * from tbl_cliente;
select * from tbl_cliente_endereco;
select * from tbl_estabelecimento;
select * from tbl_estabelecimento_endereco;
select * from tbl_endereco;
select * from tbl_cidade;
select * from tbl_microregiao;
select * from tbl_microregiao;
select * from tbl_cidade WHERE cidade = 'JANDIRA';
select * from tbl_estado;
select * from tbl_tipo_endereco;

-- tipo 
INSERT INTO tbl_tipo_endereco VALUES (0, 'Residencial');
INSERT INTO tbl_tipo_endereco VALUES (0, 'Comercial');

-- Cadastrando um cliente padrao
INSERT INTO tbl_cliente values (0,'David', 'Silva', '(11) 97709-9609', '435.423.668-03', 'M', '2002-12-06', 'david@gmail.com', '123456789');

INSERT INTO tbl_endereco (logradouro, bairro, cep, id_tipo_endereco, id_cidade) VALUES ('Rua Antônio gomes dos santos', 'Paque dos lagos', '06622-445', 1, 3525003);

INSERT INTO tbl_cliente_endereco VALUES (0, 1, 1);




SELECT cliente.nome , cidade.cidade, endereco.logradouro
		from tbl_cliente as cliente INNER JOIN tbl_cliente_endereco as cliente_endereco
        ON cliente.id_cliente = cliente_endereco.id_cliente INNER JOIN tbl_endereco as endereco
        ON endereco.id_endereco= cliente_endereco.id_endereco INNER JOIN tbl_cidade as cidade
        ON endereco.id_cidade = cidade.id_cidade;
        
        