package br.ufscar.dc.compiladores.lasemantico;

// Autor: Leticia Amaral da Cunha - 2021/2022

import br.ufscar.dc.compiladores.lasemantico.TabelaDeclaracoes.DeclaracaoGant;
import java.util.ArrayList;
import java.util.List;


//Casos de análise semântica
//  1 - Variavel dependente igual a dependida
//  2 - Historia já atribuido valor
//  3 - Variável, etiqueta e declaração não registrada
public class Gant extends LASemanticoBaseVisitor<Void>{
    TabeladeSimbolos tabelaS;
    TabelaDeclaracoes tabelaD;
    
    @Override
    public Void visitPrograma(LASemanticoParser.ProgramaContext ctx){
        tabelaS = new TabeladeSimbolos();
        tabelaD = new TabelaDeclaracoes();
        
        return super.visitPrograma(ctx);
    }
    
    @Override
    public Void visitTopicos(LASemanticoParser.TopicosContext ctx){
        
        for(var topico : ctx.variavel()){
            var nome = topico.CONSTANTE().getText();
        
            if (tabelaS.existe(nome)) {
                LASemanticoUtils.adicionarErroSemantico(topico.CONSTANTE().getSymbol(), "Variável " + nome + " já existe");
            } else {
                tabelaS.adicionar(nome, TabeladeSimbolos.TipoGant.VARIAVEL);
            }
        }
        
        return super.visitTopicos(ctx);
    }
    
    @Override
    public Void visitEtiquetas(LASemanticoParser.EtiquetasContext ctx){
        
        for(var etiqueta : ctx.etiqueta()){
            var nome = etiqueta.CONSTANTE().getText();
        
            if (tabelaS.existe(nome)) {
                LASemanticoUtils.adicionarErroSemantico(etiqueta.CONSTANTE().getSymbol(), "Etiqueta " + nome + " já existe");
            } else {
                tabelaS.adicionar(nome, TabeladeSimbolos.TipoGant.ETIQUETA);
            }
        }
        
        return super.visitEtiquetas(ctx);
    }
    
    @Override
    public Void visitDeclaracao(LASemanticoParser.DeclaracaoContext ctx){
        var nome = ctx.historia();
        var tipo = DeclaracaoGant.INVALIDO;
        
        switch(ctx.tipo().getText()){
            case "string":
                tipo = DeclaracaoGant.STRING;
                break;
            case "data":
                tipo = DeclaracaoGant.DATA;
        }
        
        for(var nomeVar : nome){
            var nomeAtual = nomeVar.variavel().CONSTANTE();
            if (tabelaS.existe(nomeAtual.getText())) {
                LASemanticoUtils.adicionarErroSemantico(nomeAtual.getSymbol(), "Variavel da declaracao " + nomeAtual.getText() + " já esta definida");
            } 
            else {
                tabelaS.adicionar(nomeAtual.getText(), TabeladeSimbolos.TipoGant.DECLARACAO);
            }
            if (tabelaD.existe(nomeAtual.getText())){
                     LASemanticoUtils.adicionarErroSemantico(nomeAtual.getSymbol(), "Declaracao " + nomeAtual.getText() + " já existe");
            }
            else{
                tabelaD.adicionar(nomeAtual.getText(), tipo);
            }
        }
        
        return super.visitDeclaracao(ctx);
    }
    
    @Override
    public Void visitRegistro(LASemanticoParser.RegistroContext ctx){
        List<String> dependentes = new ArrayList();
        
        for(var atribuicao:ctx.atribuicao()){
            var varNome = atribuicao.variavel().getText();
            
            if(ctx.registrodep() != null){
                for(var registro : ctx.registrodep()){
                    dependentes = LASemanticoUtils.verificarDependentes(
                            registro, 
                            dependentes);
                    if (dependentes.contains(varNome)){
                        LASemanticoUtils.adicionarErroSemantico(
                                atribuicao.variavel().CONSTANTE().getSymbol(), 
                                "Dependencia circular em " + varNome);
                    }
                }
            }
        }
        
        return super.visitRegistro(ctx);
    }
    
    
    
    
}
