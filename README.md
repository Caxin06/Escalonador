# Escalonador de Processos (Simulação em Java)  

Informações do Trabalho:  
Disciplina: Algoritmos e Estrutura de Dados  
Professor: DIMMY MAGALHÃES  
Aluno: ABSALÃO RESPLANDES DA SILVA NETO  

link do repositório: https://github.com/Caxin06/Escalonador.git  


Descrição do projeto:  
Este projeto implementa um escalonador de processos em Java, utilizando listas encadeadas para organizar processos em diferentes 
filas de prioridade (alta, média e baixa),além de uma fila de bloqueados.  

A simulação é alimentada por um **arquivo CSV**, que contém os processos com suas características.

---

Estrutura do Projeto:

src/
Main.java
Schedule.java
ListaDeProcessos.java
Node.java
Processo.java

---

Funcionalidades:

- Leitura de processos a partir de um arquivo `.csv`
  
- Organização em filas de prioridade:
  - Alta prioridade (1)  
  - Média prioridade (2)  
  - Baixa prioridade (3)  
    
- Prevenção de inanição (processos de prioridade menor também são executados após certo tempo)

- Bloqueio e desbloqueio de processos que necessitam de recursos (`DISCO`)
  
- Execução ciclo a ciclo, mostrando o estado atual das filas

---

Como compilar e executar o projeto?

no terminal ( prompt, Powershell, bash, etc...) 

-------------------------------------------

navegar até a pasta até onde a pasta src está salva, exemplo de comando:

cd C:Users\SeuUsuario\downloads\src ---> caso sua pasta src que você baixou no repositório esteja nos downloads

-------------------------------------------

após isso deve compilar os arquivos presentes na pasta, exemplo de comando:

javac *.java

-------------------------------------------

após isso execute um arquivo.csv como entrada na função Main, exemplo de comando:

java Main Processos.csv ---> caso seu arquivo esteja nomeado como Processos.csv

-------------------------------------------

Exemplo de saida esperada:  

CICLO 1  
Estado atual do Scheduler:  
Alta prioridade: P1, P4, P7, P10, P13, P16, P19  
Média prioridade: P2, P5, P8, P11, P14, P17, P20  
Baixa prioridade: P3, P6, P9, P12, P15, P18  
Bloqueados: Nenhum processo  
Contador ciclos alta prioridade: 0  

--------------------------------------------------


## 📄 Exemplo de arquivo CSV (`Processos.csv`)

```csv
# id, nome, prioridade, ciclos, recurso
1,ProcessoA,3,4,
2,ProcessoB,2,3,DISCO
3,ProcessoC,1,5,
4,ProcessoD,3,2,
5,ProcessoE,2,1,
6,ProcessoF,1,2,DISCO
7,ProcessoG,3,3,
```

Linhas que começam com # são ignoradas.
O campo recurso pode ser vazio ou DISC.

tambem foram disponibilizados 2 arquivos .csv como exemplo no repositório que foram utilizados para testar o escalonador.


