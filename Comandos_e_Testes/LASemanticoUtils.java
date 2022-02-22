package br.ufscar.dc.compiladores.lasemantico;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;

// Autor: Leticia Amaral da Cunha - 2021/2022

//Casos de análise semântica
//  1 - Variavel dependente igual a dependida
//  2 - Historia já atribuido valor
//  3 - Variável, etiqueta e declaração não registrada

public class LASemanticoUtils {
    public static List<String> errosSemanticos = new ArrayList<>();
    
    public static void adicionarErroSemantico(Token t, String mensagem) {
        int linha = t.getLine();
        errosSemanticos.add(String.format("Linha %d: %s" + System.lineSeparator(), linha, mensagem));
    }
    
    public static List<String> verificarDependentes(LASemanticoParser.RegistrodepContext ctx, List<String> nomes){
        
        if (ctx.registrodep() != null){
            for (var registroDep : ctx.registrodep()){
                nomes.addAll(verificarDependentes(registroDep, nomes));
            }
        }
        if (ctx.registro() != null){
            for (var registro : ctx.registro()){
                nomes.addAll(verificarRegistros(registro, nomes));
            }
        }
        
        nomes.add(ctx.dependente().variavel().CONSTANTE().getText());
        
        return nomes;
    }
    
    public static List<String> verificarRegistros(LASemanticoParser.RegistroContext ctx, List<String> nomes){
        
        if (ctx.registrodep() != null){
            for (var registroDep : ctx.registrodep()){
                nomes.addAll(verificarDependentes(registroDep, nomes));
            }
        }
        for(var atribuicao : ctx.atribuicao()){
            nomes.add(atribuicao.variavel().CONSTANTE().getText());
        }
        
        return nomes;
    }  
}
