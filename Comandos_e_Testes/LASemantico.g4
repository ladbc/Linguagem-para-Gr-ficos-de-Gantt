grammar LASemantico;

programa:
	legenda+ 'inicioGantt' topicos? etiquetas? conteudo? 'finalGantt';

topicos:
        'topicos' variavel+ (',' variavel)* 'fim_topicos';
etiquetas:
        'etiquetas' etiqueta+ (',' etiqueta)* 'fim_etiquetas';
conteudo:
	atividade+;
atividade:
	'inicioAtividades' comando* 'finalAtividades';

comando:
	registro |
	registrodep |
        declaracao;

legenda:
	'inicioLegenda' titulo+ (subtitulo | autor | descricao)* 'finalLegenda';

declaracao:
        'declare' tipo ':' historia+;
variavel:
        CONSTANTE;
etiqueta:
        CONSTANTE;
historia:
        variavel ATRIBUICAO dados;
registro: 
	'registro' atribuicao (atribuicao | registrodep)* (add_etiqueta)? 'fim-registro';
atribuicao:
        variavel ATRIBUICAO dados;
add_etiqueta:
	ATRIBUICAO '{' 'etiqueta' E CONSTANTE '}';
registrodep:
	'registrodep' dependente (registro | registrodep)* 'fim-registrodep';

dados:
	'{' TEXTO E datacompleta E datacompleta '}';
dado:
	'{' TEXTO E datacompleta '}';
dependente:
	ATRIBUICAO '{' 'dependente' variavel '}';

CONSTANTE: 
	LETRA (LETRA|DIGITO)*;
dia: 
	DIGITO DIGITO;
mes: 
	DIGITO DIGITO;
ano: 
	DIGITO DIGITO DIGITO DIGITO;
datacompleta: 
	dia '/' mes '/' ano;

ATRIBUICAO:
	'<-';
E:
	',';
tipo:
	'string'|
	'data';
titulo:
        TEXTO;
autor:
        TEXTO;
subtitulo:
        TEXTO;
descricao: 
        TEXTO;

LETRA:	
    ('a'..'z'|'A'..'Z'|'_');
TEXTO:     
    '"' ~('\t'|'\r'|'\n'|'"')* '"';
DIGITO:
    ('0'..'9');

COMENTARIO:
    '\\' ~('{'|'}')* '\\' {skip();};
ESPACOBRANCO:
    (' '|'\t'|'\r'|'\n') {skip();};