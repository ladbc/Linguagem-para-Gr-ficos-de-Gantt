# Linguagem geradora de Graficos de Gantt

## Esse projeto foi desenvolvido por Leticia A. da Cunha em 2021

## Contexto do Projeto
Esse projeto foi desenvolvido seguindo a proposta da disciplina de Contrução de Compiladores da UFSCar.
O trabalho apresentado tem como objetivo propor uma nova linguagem, conjutamente com um analisador léxico, sintático e semântico. Também são construídos casos de uso e um material de referência da linguagem gerada.

## Gráficos de Gantt 
Gráficos de Gannt são uma ferrameta muito útil para o desenvolvimento e acompanhamento de projetos. De forma simples, ele consinte em um gráfico de barras horizontais no qual cada uma das barras representa uma atividade. Seu eixo x é definido por uma medida de tempo (dias, semanas, sprints, etc...) enquanto o eixo y define uma sequencia de atividades. O inventor dessa técnica foi Henry Gantt entre 1910-1915, e seu segue corrente até hoje nos mais diferentes escopos de projetos.

## Proposta do Repositório
O repositório fornece material a respeito de linguagem de Gantt, uma ferramenta que permite a criação de gráficos de Gannt a partir de uma linguagem procedural e de alto nível. A linguagem de Gantt é bastante inovadora uma vez que praticamente não existem outras formas de gerar esse tipo de gráfico a não ser por ferramentas visuais, processo que pode ser bastante simplificado ao tomar apenas comandos como entrada.

## Características da Linguagem de Gantt
A linguagem de Gantt pode ser definida como procedural e de alto nível. Um programa se divide em três etapas, as definições estéticas e restriões de arquivo, declaração das legendas e titulos e por último, descrição das atividades contidas no gráfico.
A linguagem de Gantt permite a definição de colunas de predescessores, duração e relevãncia das atividades. Também permite a referência a atividades em outras linhas e a escolha de diferentes escalas 

## Modo de Execução

Para executar o projeto, presente na pasta Projeto_Codigo_Fonte,basta utilizar o seguinte comando em uma linha de comando
> java -jar ~/LALexico-1.0-SNAPSHOT-jar-with-dependencies.jar ./teste.txt ./saidateste.txt
sendo o primeiro path a rota do .jar presente na pasta, o segundo o arquivo txt contendo a programa de teste e o último um arquivo no qual se deseja criar receber os resultados da análise.

## Desenvolvimento futuro
Como projetos futuros estão o desenvolvimento de analisador semântico e um gerador de código, além de sugestões 
