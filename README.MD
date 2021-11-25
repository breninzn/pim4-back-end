# Hotelaria
## _Projeto referente ao back-end do web-site._
Tecnologia utilizada: Java (SpringBoot)

Instruções para compilar o código:

Executar um mavem update para atualizar as dependencias do projeto.
Criar um database postgres com o nome "pim4"
Obs: Após iniciar a aplicação pela primeira vez está configurado para que sejam criadas
as tabelas do banco de dados, sendo assim não precisa se preocupar em criar as tabelas.

comandos que vão ajudar a popular as tabelas com quartos e reservas, eles devem ser 
executados na ordem que estão descritos abaixo:

INSERT INTO public.quarto (descricao,numero,preco,quantidade_lugares) VALUES
	 ('Quarto duplo',10,100.0,4),
	 ('Quarto casal',20,50.0,2),
	 ('Quarto triplo',30,125.0,6),
	 ('Quarto individual',109,250.0,1);
	
INSERT INTO public.reserva (celular,cpf,data_entrada,data_saida,email,nome,quarto_id) VALUES
	 ('17988126063','46736887836','2021-12-05','2021-12-10','breno.icpb@gmail.com','Breno Rodrigues',1),
	 ('17988126063','46736887687','2021-11-25','2021-12-31','mythalie@gmail.com','Mythalie Cruz',1),
	 ('17988126063','46736887890','2022-04-02','2022-05-05','bruno@gmail.com','Bruno',2),
	 ('17988126063','46736887836','2021-12-05','2021-12-10','miguel@gmail.com','Miguel Gustavo',3);
