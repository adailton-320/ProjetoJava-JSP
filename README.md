<h3>
PROJETO JSP CRUD com Java utilizando o gerenciador de dependências Apache Maven </h3>

<li> 
 Java Web 
 </li>
 <li> 
 Maven 
 </li>
 <li>
 JSP
</li>
<li> 
 SQL
</li>
<li> 
 PostgreSql
</li>
<li> 
 JUnit
</li>
<li> 
 Jaspersoft Studio-6.20.5
</li>
<h3>Como usar:</h3> 

Windows, Linux e MacOS Primeiros passos Aquisição do IDE e do Servidor
<li>
Java 8
</li>
<li>
Eclipse IDE.
</li>
<li> 
Tomcat 10
</li>
<li> 
SQL PostgreSQL Configuração e instalação 
</li>

<h3>"Abrir projeto do sistema pela IDE"</h3>
<p>
Selecione o diretório que você baixou copie para pasta Workspace. 
</p>
<p>
Com eclipse aberto clique em file -> import -> Existing Project into Workspace. 
</p>
<p>
botão direito na pasta do projeto Vá até a pasta workspace clicando em browser (se encontra na parte superior da caixa na IDE. 
</p>
<p>
Selecione a pasta do projeto clique em “selecionar pasta” 
</p>
<p>
marque a caixa com endereço do projeto e em seguida em finish. 
</p>
<p>
Selecione "Tomcat v10 Server" adicione o projeto no servidor e espere o Maven concluir o download das dependências. 
</p>
<p>
Uso da aplicação Com o Eclipse rodando e o projeto selecionado, 
</p>
<p>
verifique se o SQL está rodando na porta 5432, user = "postgres", senha = "12345". 
</p>
<p>
Confira esses dados no pacote Connection e classe SingleConnection. 
</p>
</br>
<p>
<h3>Copie e cole o Script SQL Execute.</h3>
</p>
<div>

<p>
CREATE DATABASE "dbProjetoJsp" 
</p>
<p>
WITH OWNER = postgres 
</p>
<p>
ENCODING = 'UTF8' 
</p>
<p>
TABLESPACE = pg_default 
</p>
<p>
LC_COLLATE = 'Portuguese_Brazil.1252' 
</p>
<p>
LC_CTYPE = 'Portuguese_Brazil.1252' 
</p>
<p>
CONNECTION LIMIT = -1;
</p>
</div>

------------Cria Tabela--------------
<p>
CREATE TABLE public.loginmodel ( login character varying(255) NOT NULL, 
</p>
<p>
senha character varying(255) NOT NULL, 
</p>
nome character varying(300) NOT NULL, 
<p>
id integer NOT NULL, 
</p>
<p>
useradmin boolean NOT NULL DEFAULT false, 
</p>
<p>
usuario_id bigint NOT NULL DEFAULT 1, 
</p>
<p>
perfil character varying(200), 
</p>
<p>
userfoto text, 
</p>
<p>
extencaofoto character varying(10), 
</p>
<p>
datacadastro date, 
</p>
<p>
CONSTRAINT loginmodel_pkey PRIMARY KEY (id), 
</p>
<p>
CONSTRAINT usuario_fk FOREIGN KEY (usuario_id) REFERENCES public.loginmodel (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION, 
</p>
<p>
CONSTRAINT loginmodel_login_key UNIQUE (login) ) WITH ( OIDS=FALSE ); 
</p>
<p>
ALTER TABLE public.loginmodel OWNER TO postgres;
</p>


Endereço da aplicação 
http://localhost:8080/ProjetoJsp/

