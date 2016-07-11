Bloqueio Bifásio

O escalondador de agendas utiliza o protocolo de bloqueio bifásico para serializar uma sequência de transações.

### Passo a passo de como usar o escalonador ###

1 - Executar o .jar que se encontra na pasta do /dist do projeto usando o comando (java -jar BloqueioBifasico.jar).
2 - Na tela do terminal aparecerá 5 opções: 1 - Carregar um arquivo de agenda não escalonado
                    						2 - Escalonar agenda 
                    						3 - Imprimir transações
                    						4 - Gerar arquivo com transações escalonadas
                    						5 - Sair.
3 - Selecione a opção 1, será pedido o caminho com o nome do arquivo que deseja carregar, após digitar tecle enter.
4 - Caso não receba uma mensagem de OK, tente novamente a opção 1.
5 - Selecione a opção 2.
6 - Selecione a opção 4, digite o nome do arquivo para salvar as transações escalonadas (o arquivo será criado na pasta raiz do projeto).
7 - A opção 3 é opcional, ela imprime as transações.
8 - Para finalizar tecle 5.

### Fim do passo a passo ###

### Funcionalidades implementadas ###
Classe Arquivo
	- O método readAgenda da classe Arquivo simplesmente faz a leitura do arquivo onde se encontram as transações não serializadas, a cada operação lida, o método instancia uma nova operação e adiciona em uma lista de operações.
	- O método getVariaveis varre o arquivo e pega todas as variáveis encontradas retornando uma lista de variáveis.
	- O método getVariavel retorna a vairável contida na string que está no processamento de operação.
	- O método writeAgenda apenas grava a agenda serializa em um arquivo de texto.
Classe Operacao
	- A classe Operacao contém os atributos que uma operação necessita como a variável, tipo de operação, um id, e um booleano wait. Para o acesso a estes atributos criamos também métodos getter e setter.
Classe Variavel
	- A classe Varaivel contém os atributos de cada variável como uma string dado e uma string tipo de lock que define se a variável é do tipo U, S ou X. Nela também foram criados os métodos de acesso.
Classe Escalonador
	- É a classe principal do sistema, ela é quem recebe uma agenda não escalonada e faz o processamento desta transformando em uma agenda serializavél.
	- O método contrutor recebe uma agenda e uma lista de variáveis como paramento e atribui esta agenda e a lista de variávies para uma array local.
	- O metodo escalonar percorre a lista de operaçoes, verifica qual é o tipo de operação requisitada (R/W/C), e verifica se é possivel aplicar o lock nessa variável seje ele um lock de leitura ou de escrita, caso o lock seja permintido a operação é colocada em uma nova agenda, caso o lock esteja bloqueado a operação é colocada em espera, se uma operação for de commit a operação é adiciona na nova agenda, suas variáveis são desbloqueadas e o escalonadorWait é chamado para tratar o primeira operação em espera.
	- O método escalonadorWait procura a primeira operação em espera e faz a verificação para descobrir se a operação pode ser retirada da fila de espera e colocada na nova agenda.
	- Os métodos lockS e lockX fazem o bloqueio de uma variável para leitura e para escrita respecitivamente.
	- O método unlock faz o desbloqueio de uma variável
	- O método unlokAll faz o desbloqueio de todas as variáveis da transação.
