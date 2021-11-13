package z808;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;

public class Builder {
    private short memoria[] = new short[256];

    public void rotinaDeMontagem(){

        int contadorMemoria = 0;

        try {

            HashMap<String, String> lista = new HashMap<String, String>();
            lista = passOne(lista);

            BufferedReader in = new BufferedReader(new FileReader("file_montador.txt")); //Lê na raiz do projeto
            String str;

            //Criação do arquivo para gravar código objeto
            File arqObjeto = new File("file_retorno.txt");
            arqObjeto.createNewFile();
            FileWriter escritor = new FileWriter(arqObjeto);
            BufferedWriter bw = new BufferedWriter(escritor); //variavel final para escrever no arquivo

            while (in.ready()) {
                str = in.readLine().toUpperCase();

                String delims = " ";
                String[] tokens = str.split(delims);

                for(int i=0; i< tokens.length; i++){
                    switch(tokens[i]){
                        case "ADD":
                            System.out.println("ADD");
                            if(tokens[i+2].compareTo("DX") == 0)
                            {
                                bw.write("03 C2");
                            }else if(tokens[i+2].compareTo("AX") == 0)
                            {
                                bw.write("03 C0");
                            }else
                            {
                                bw.write("05 " + tokens[i+2]);
                            }

                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            //bw.write(" "+ detectaRegistrador(tokens[i+2]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "DIV":
                            System.out.println("DIV");
                            if(tokens[i+1].compareTo("SI") == 0)
                            {
                                bw.write("F7 F6");
                            }else
                            {
                                bw.write("F7 F0");
                            }

                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            //bw.write(" "+ detectaRegistrador(tokens[i+2]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "SUB":
                            System.out.println("SUB");
                            if(tokens[i+1].compareTo("AX,") != 0)
                            {
                                bw.write("06 " + tokens[i+1]);
                                i=i+2;
                                bw.newLine();
                                break;
                            }else if(tokens[i+2].compareTo("AX") == 0)
                            {
                                bw.write("2B C0");
                            }else if(tokens[i+2].compareTo("DX") == 0)
                            {
                                bw.write("2B C2");
                            }

                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            //bw.write(" "+ detectaRegistrador(tokens[i+2]));
                            i=i+2;
                            bw.newLine();
                            break;
                        case "MUL":
                            System.out.println("MUL");
                            if(tokens[i+1].compareTo("SI") == 0)
                            {
                                bw.write("F7 E6");
                            }else
                            {
                                bw.write("F7 E0");
                            }

                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            //bw.write(" "+ detectaRegistrador(tokens[i+2]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "CMP":
                            System.out.println("CMP");
                            if(tokens[i+2].compareTo("DX") == 0)
                            {
                                bw.write("3B C2");
                            }else
                            {
                                bw.write("3D " + tokens[i+2]);
                            }

                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            //bw.write(" "+ detectaRegistrador(tokens[i+2]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "AND":
                            System.out.println("AND");
                            if(tokens[i+2].compareTo("DX") == 0)
                            {
                                bw.write("23 C2");
                            }else if(tokens[i+2].compareTo("AX") == 0)
                            {
                                bw.write("23 C0");
                            }else
                            {
                                bw.write("25 " + tokens[i+2]);
                            }

                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            //bw.write(" "+ detectaRegistrador(tokens[i+2]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "NOT":
                            System.out.println("NOT");
                            bw.write("F7  " +  "D0");
                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "OR":
                            System.out.println("OR");
                            if(tokens[i+2].compareTo("DX") == 0)
                            {
                                bw.write("0B C2");
                            }else if(tokens[i+2].compareTo("AX") == 0)
                            {
                                bw.write("0B C0");
                            }else
                            {
                                bw.write("0D " + tokens[i+2]);
                            }

                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            //bw.write(" "+ detectaRegistrador(tokens[i+2]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "XOR":
                            System.out.println("XOR");
                            if(tokens[i+2].compareTo("DX") == 0)
                            {
                                bw.write("33 C2");
                            }else if(tokens[i+2].compareTo("AX") == 0)
                            {
                                bw.write("33 C0");
                            }else
                            {
                                bw.write("35 " + tokens[i+2]);
                            }

                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            //bw.write(" "+ detectaRegistrador(tokens[i+2]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "JMP":
                            System.out.println("JMP");
                            bw.write("EB" + tokens[i+1]);
                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "JE":
                            System.out.println("JE");
                            bw.write("74 " + tokens[i+1]);
                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "JNZ":
                            System.out.println("JNZ");
                            bw.write("75 " + tokens[i+1]);
                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "JZ":
                            System.out.println("JZ");
                            bw.write("74 " + tokens[i+1]);
                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "JP":
                            System.out.println("JP");
                            bw.write("7A " + tokens[i+1]);
                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "call":
                            System.out.println("call");
                            bw.write("E8 " + tokens[i+1]);
                            i=i+2;
                            bw.newLine();
                            break;

                        case "int":
                            System.out.println("int");
                            bw.write("CD " + tokens[i+1]);
                            //bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "ret":
                            System.out.println("ret");
                            bw.write("C3 ");
                            bw.newLine();
                            i++;
                            break;

                        case "CALL":
                            System.out.println("CALL");
                            bw.write("15 " + tokens[i+1]);
                            bw.newLine();
                            i=i+2;;
                            break;

                        case "COPY":
                            System.out.println("COPY");
                            bw.write("13 " + tokens[i+1] + " " + tokens[i+2]);
                            bw.newLine();
                            i=i+3;;
                            break;

                        case "DIVIDE":
                            System.out.println("DIVIDE");
                            bw.write("10 " + tokens[i+1]);
                            bw.newLine();
                            i=i+2;;
                            break;

                        case "LOAD":
                            System.out.println("LOAD");
                            bw.write("03 " + tokens[i+1]);
                            bw.newLine();
                            i=i+2;;
                            break;

                        case "MULT":
                            System.out.println("MULT");
                            bw.write("14 " + tokens[i+1]);
                            bw.newLine();
                            i=i+2;;
                            break;


                        case "READ":
                            System.out.println("READ");
                            bw.write("12 " + tokens[i+1]);
                            bw.newLine();
                            i=i+2;;
                            break;


                        case "RET":
                            System.out.println("RET");
                            bw.write("16");
                            bw.newLine();
                            i++;;
                            break;


                        case "STOP":
                            System.out.println("FIM DE EXECUÇÃO");
                            bw.write("11");
                            bw.newLine();
                            i++;
                            break;

                        case "STORE":
                            System.out.println("STORE");
                            bw.write("07 " + tokens [i+1]);
                            bw.newLine();
                            i=i+2;;
                            break;


                        case "WRITE":
                            System.out.println("WRITE");
                            bw.write("08 " + tokens[i+1]);
                            bw.newLine();
                            i=i+2;;
                            break;


                        default:
                            //System.out.println("dado ");
                            i=i+2;
                            //System.out.print(tokens[i]);
                            bw.write(tokens[i]);
                            bw.newLine();

                            break;
                    }//Fim switch

                }//Fim for

            }//Fim while leitura do arquivo
            in.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> passOne (HashMap<String, String> lista)
    {
        try {

            BufferedReader in = new BufferedReader(new FileReader("file_montador.txt")); //Lê na raiz do projeto
            String str;

            while (in.ready()) {
                str = in.readLine().toUpperCase();

                String delims = " ";
                String[] tokens = str.split(delims);
                for(int i=0; i< tokens.length; i++){
                    if(tokens[i].compareTo("DW") == 0)
                    {
                        lista.put(tokens[i-1], new String(tokens[i+1]));
                    }
                }
            }//Fim while leitura do arquivo
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String key : lista.keySet()) {
            System.out.println(key + " " + lista.get(key));
        }

        return lista;
    }

    private String detectaRegistrador(String entrada){

        String retorno = entrada;

        switch(entrada){
            case "AX":
                retorno = "-1";
                break;
            case "DX":
                retorno = "-2";
                break;
            case "SI":
                retorno = "-3";
                break;
        }
        return retorno;
    }
}