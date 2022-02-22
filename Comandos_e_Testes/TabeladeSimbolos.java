package br.ufscar.dc.compiladores.lasemantico;
import static br.ufscar.dc.compiladores.lasemantico.LASemanticoUtils.errosSemanticos;
import java.util.HashMap;
import java.util.List;

public class TabeladeSimbolos {
    private HashMap<String, EntradaTabeladeSimbolos> _tabelaSimbolos;
    
    public TabeladeSimbolos(){
        _tabelaSimbolos = new HashMap<>();
    }
    
    public void adicionar(String nome, TipoGant tipo) {
        _tabelaSimbolos.put(nome, new EntradaTabeladeSimbolos(nome, tipo));
    }
    
    public boolean existe(String nome) {
        return _tabelaSimbolos.containsKey(nome);
    }
    
    public TipoGant verificar(String nome) {
        return _tabelaSimbolos.get(nome)._tipo;
    }
    
    public enum TipoGant {
        VARIAVEL,
        ETIQUETA,
        DECLARACAO
    }
    
    public class EntradaTabeladeSimbolos {
        public String _nome;
        public TipoGant _tipo;

        private EntradaTabeladeSimbolos(
                String nome, 
                TipoGant tipo) {
            _nome = nome;
            _tipo = tipo;
        }
    }
}
