INSERT INTO cliente (nome, email, senha, documento, data_cadastro) values ('Carlos Antonio', 'carlosantonio@gmail.com', 'essanaoehumaboasenha', '14.320.882-31', '2017-06-10');
INSERT INTO cliente (nome, email, senha, documento, data_cadastro) values ('João Antonio', 'joaoantonio@gmail.com', 'essanaoehumaboasenha', '66.420.812-54', '2017-06-10');
INSERT INTO cliente (nome, email, senha, documento, data_cadastro) values ('Carlos Marcos', 'carlosmarcos@gmail.com', 'essanaoehumaboasenha', '73.520.282-61', '2017-11-09');
INSERT INTO cliente (nome, email, senha, documento, data_cadastro) values ('Amanda Nunes', 'amandanunes@gmail.com', 'essanaoehumaboasenha', '34.320.782-31', '2017-12-13');
INSERT INTO cliente (nome, email, senha, documento, data_cadastro) values ('Carlos Prestes', 'carlosprestes@gmail.com', 'essanaoehumaboasenha', '76.387.562-91', '2017-06-19');
INSERT INTO cliente (nome, email, senha, documento, data_cadastro) values ('Giovanna Schmidt', 'giovannaschmidt@gmail.com', 'essanaoehumaboasenha', '48.333.883-41', '2017-04-22');
INSERT INTO cliente (nome, email, senha, documento, data_cadastro) values ('Carlos José Perez', 'carlosjoseperez@gmail.com', 'essanaoehumaboasenha', '98.389.652-31', '2017-02-20');
INSERT INTO cliente (nome, email, senha, documento, data_cadastro) values ('Gabriel Antonio', 'gabrielantonio@gmail.com', 'essanaoehumaboasenha', '14.120.272-71', '2017-03-12');
INSERT INTO cliente (nome, email, senha, documento, data_cadastro) values ('Luana Fiel', 'luanafiel@gmail.com', 'essanaoehumaboasenha', '12.320.132-21', '2017-04-11');
INSERT INTO cliente (nome, email, senha, documento, data_cadastro) values ('Carla Maciel', 'carlamaciel@gmail.com', 'essanaoehumaboasenha', '17.326.182-11', '2017-07-23');

INSERT INTO fornecedor (nome, cnpj) values ('Jose Escobar Ltda.', '548.762.335/5135-60');
INSERT INTO fornecedor (nome, cnpj) values ('Francisco Adnet S.A.', '912.442.315/5123-13');
INSERT INTO fornecedor (nome, cnpj) values ('Flea & Kiedis', '518.862.476/5555-34');
INSERT INTO fornecedor (nome, cnpj) values ('Carlos Poo', '543.764.565/1221-11');
INSERT INTO fornecedor (nome, cnpj) values ('KVC Madeiras', '448.762.565/1893-42');
INSERT INTO fornecedor (nome, cnpj) values ('Estofados Supimpas', '148.162.565/2731-43');
INSERT INTO fornecedor (nome, cnpj) values ('Decoração e Cia', '533.788.715/5553-30');

INSERT INTO produto (nome, valor, promocao, valor_promo, categoria, imagem, quantidade, fornecedor_id) values ('Produto X', 10.50, true, 9.50, 'eletronico', 'produto.jpg', 5, 1);
INSERT INTO produto (nome, valor, promocao, valor_promo, categoria, imagem, quantidade, fornecedor_id) values ('Produto Y', 99.99, false, 0, 'casa', 'produtoY.jpeg', 10, 1);
INSERT INTO produto (nome, valor, promocao, valor_promo, categoria, imagem, quantidade, fornecedor_id) values ('Produto Z', 42.42, false, 0, 'brinquedos', 'produtoz.raw', 33, 3);
INSERT INTO produto (nome, valor, promocao, valor_promo, categoria, imagem, quantidade, fornecedor_id) values ('Produto A', 33.31, false, 0, 'pet', 'produtoa.png', 12, 2);
INSERT INTO produto (nome, valor, promocao, valor_promo, categoria, imagem, quantidade, fornecedor_id) values ('Produto B', 29.99, true, 10.50, 'fitness', 'produtob.png', 15, 3);
INSERT INTO produto (nome, valor, promocao, valor_promo, categoria, imagem, quantidade, fornecedor_id) values ('Produto C', 19.99, true, 9.99, 'maquiagem', 'produtoc.jpeg', 301, 4);

INSERT INTO venda (data_compra, cliente_id, fornecedor_id) values ("2017-11-09", 1, 3);
INSERT INTO venda (data_compra, cliente_id, fornecedor_id) values ("2017-06-07", 7, 1);
INSERT INTO venda (data_compra, cliente_id, fornecedor_id) values ("2017-09-12", 5, 4);
INSERT INTO venda (data_compra, cliente_id, fornecedor_id) values ("2017-03-23", 4, 3);
INSERT INTO venda (data_compra, cliente_id, fornecedor_id) values ("2017-03-12", 3, 5);
INSERT INTO venda (data_compra, cliente_id, fornecedor_id) values ("2017-04-22", 2, 6);