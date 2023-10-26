PROJETO JSP
CRUD com Java utilizando o gerenciador de dependências Apache Maven
•	Java Web
•	Maven
•	JSP
•	SQL
•	PostgreSql
•	JUnit
•	Jaspersoft Studio-6.20.5

Como usar:
Windows, Linux e MacOS
Primeiros passos
Aquisição do IDE e do Servidor
•	Java 8.
•	Eclipse IDE.
•	Tomcat 10.
•	SQL PostgreSQL
Configuração e instalação
"Abrir projeto do sistema pela IDE"
Selecione o diretório que você baixou copie para pasta Workspace.
Com eclipse aberto clique em file -> import -> Existing Project into Workspace.  botão direito na pasta do projeto
Vá até a pasta workspace clicando em browser (se encontra na parte superior da caixa na IDE.
Selecione a pasta do projeto clique em “selecionar pasta” marque a caixa com endereço do projeto e em seguida em finish.
Selecione "Tomcat v10 Server" adicione o projeto no servidor e espere o Maven concluir o download das dependências.
Uso da aplicação
Com o Eclipse rodando e o projeto selecionado, verifique se o SQL está rodando na porta 5432, user = "postgres", senha = "12345".
 Confira esses dados no pacote Connection e classe SingleConnection.
Execute o Script SQL 

CREATE DATABASE "dbProjetoJsp"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;
	   
------------Cria Tabela--------------

CREATE TABLE public.loginmodel
(
  login character varying(255) NOT NULL,
  senha character varying(255) NOT NULL,
  nome character varying(300) NOT NULL,
  id integer NOT NULL,
  useradmin boolean NOT NULL DEFAULT false,
  usuario_id bigint NOT NULL DEFAULT 1,
  perfil character varying(200),
  userfoto text,
  extencaofoto character varying(10),
  datacadastro date,
  CONSTRAINT loginmodel_pkey PRIMARY KEY (id),
  CONSTRAINT usuario_fk FOREIGN KEY (usuario_id)
      REFERENCES public.loginmodel (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT loginmodel_login_key UNIQUE (login)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.loginmodel
OWNER TO postgres;


Endereço da aplicação 
http://localhost:8080/ProjetoJsp/
 
