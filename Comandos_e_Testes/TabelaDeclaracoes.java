package br.ufscar.dc.compiladores.lasemantico;
import static br.ufscar.dc.compiladores.lasemantico.LASemanticoUtils.errosSemanticos;
import java.util.HashMap;
import java.util.List;

public class TabelaDeclaracoes {
    private HashMap<String, EntradaTabelaDeclaracoes> _tabelaDeclaracoes;
    
    public TabelaDeclaracoes(){
        _tabelaDeclaracoes = new HashMap<>();
    }
    
    public void adicionar(String nome, DeclaracaoGant tipo) {
        _tabelaDeclaracoes.put(nome, new EntradaTabelaDeclaracoes(nome, tipo));
    }
    
    public boolean existe(String nome) {
        return _tabelaDeclaracoes.containsKey(nome);
    }
    
    public DeclaracaoGant verificar(String nome) {
        return _tabelaDeclaracoes.get(nome)._tipo;
    }
    
    public enum DeclaracaoGant{
        STRING,
        DATA,
        INVALIDO
    }
    
    public class EntradaTabelaDeclaracoes{
        public String _nome;
        public DeclaracaoGant _tipo;
        
        private EntradaTabelaDeclaracoes(
                String nome,
                DeclaracaoGant tipo
        ){
            _nome = nome;
            _tipo = tipo;
        }
    }
}