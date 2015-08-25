Desafio Java para a S2IT (Entregando mercadorias)

A estratégia adotada para solucionar o problema, consiste em 3 passos: 1) elaborar a solução do algoritmo para busca do melhor caminho (projeto S2IT); 2) montar um web service básico para executar o algoritmo; 3) incluir a persistência do mapa a ser inserido.

A solução final está no projeto EntregandoMercadorias. Foi implementado o servico Rest, sendo que melhor caminho é retornado em JSON e o input dos dados é feito com XML.

A web service implementaod utiliza o servidor de aplicação apache-tomcat-7.0.63 e persiste os dados no mongoDB-3.0. Para executar a aplicação deve-se gerar o pacote war (mvn clean install), realizar o deploy no tomcat e iniciar o mongoDB (com suas configurações default).

Para inserir um mapa na base de dados: curl -i -X POST -H 'Content-Type:text/xml' -d 'AB10BD15AC20CD30BE50DE30' http://localhost:8080/EntregandoMercadorias/webservice/basedados/inserir

Para listar os dados inseridos no mapa: curl -i -X GET http://localhost:8080/EntregandoMercadorias/webservice/basedados/listar

Para fazer a pesquisa do melhor caminho: curl -i -X GET http://localhost:8080/EntregandoMercadorias/webservice/melhorcaminho/A/D/10/2.5 (uri=melhorcaminho/origem/destino/autonomia/preco_combustivel)

Para apagar todos os registros da base de dados: curl -i -X DELETE http://localhost:8080/EntregandoMercadorias/webservice/basedados/limpar
