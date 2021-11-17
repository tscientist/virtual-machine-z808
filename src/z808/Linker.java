package z808.z808;
import java.util.ArrayList;
import java.util.HashMap;

public class Linker {

    public Linker(){
    }

    public ArrayList<ArrayList<String>> liga(ArrayList<ArrayList<ArrayList<String>>> nmodulos, Registradores registradores, TabelaDeOperacoes tabelaOp, TabelaDeSimbolos tsg){
        ArrayList<ArrayList<String>> modulos, codigoObjeto;
        int i = 0;

        modulos = new ArrayList<>();
        codigoObjeto = new ArrayList<>();

        //PRIMEIRO PASSO
        registradores.setLC(0);

        System.out.println("MODULOS LIGADOS");

        //JUNTA TODOS OS MODULOS EM UM UNICO MODULO ORDENADO
        for (ArrayList<ArrayList<String>> modulo : nmodulos) {
            for (ArrayList<String> linha : modulo) {
                modulos.add(linha);
                System.out.println(linha);
            }
        }

        //RECALCULA POSICOES REFERENTES A SIMBOLOS
        for (ArrayList<String> linha : modulos) {
            for (String simbolo : linha) {
                //System.out.println(simbolo);
                if (!tabelaOp.getTabela().containsKey(simbolo)) {
                    //ATUALIZA OU ADICIONA SIMBOLO NA TABELA DE SIMBOLOS GLOBAIS
                    if (tsg.getTabela().containsKey(simbolo) && tsg.getTabela().get(simbolo)[1].equals("r"))
                        tsg.getTabela().get(simbolo)[0] = Integer.toBinaryString(registradores.getLC());
                    else {
                        tsg.getTabela().put(simbolo, new String[2]);
                        tsg.getTabela().get(simbolo)[0] = Integer.toBinaryString(registradores.getLC());

                        if(linha.indexOf(simbolo) == 0){
                            tsg.getTabela().get(simbolo)[1] = "a";
                            registradores.setLC(registradores.getLC() - 1);
                        }
                        else
                            tsg.getTabela().get(simbolo)[1] = "r";
                    }
                }

                registradores.addLC();
            }
        }

//        System.out.println("MODULOS EM BINARIO");

        //SEGUNDO PASSO
        for(ArrayList<String> linha : modulos){
            codigoObjeto.add(new ArrayList<String>());

            for(String simbolo : linha){
                if(tabelaOp.getTabela().containsKey(simbolo))
                    codigoObjeto.get(i).add(tabelaOp.getTabela().get(simbolo));
                else
                    codigoObjeto.get(i).add(tsg.getTabela().get(simbolo)[0]);
            }
            i++;
        }

        return codigoObjeto;
    }
}