package montador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;

public class Montador {
    private short memoria[] = new short[256];
    

    public void Montador(){

        
        int i=0;

        try {

            HashMap<String, String> lista = new HashMap<>();
            lista = passOne(lista);

            BufferedReader in = new BufferedReader(new FileReader("entrada_macro_processed.txt")); //Lê na raiz do projeto
            String str;

            //Criação do arquivo para gravar código objeto
            File arqObjeto = new File("entrada_montada.txt");
            arqObjeto.createNewFile();
            FileWriter escritor = new FileWriter(arqObjeto);
            BufferedWriter bw = new BufferedWriter(escritor); //variavel final para escrever no arquivo

            while (in.ready()) {
                i=0;   
                str = in.readLine().toUpperCase();
                str = str.replace(",", " ");
                str = str.replace("  ", " ");
                
                String[] tokens = str.split(" ");

                
                if(tokens.length >2)
                {
                    if(lista.containsKey(tokens[2]))
                    {
                        tokens[2]= lista.get(tokens[2]);
                    }
                }

                if(tokens.length>=1)
                {
                    switch(tokens[0]){
                        case "ADD":
                            System.out.println("ADD");
                            if(tokens[2].compareTo("DX") == 0) 
                            {
                                bw.write("03 C2");
                            }else if(tokens[2].compareTo("AX") == 0) 
                            {
                                bw.write("03 C0");
                            }else
                            {
                                bw.write("05 " + tokens[2]);
                            }
                            //i=i+2;
                            bw.newLine();
                            break;

                        case "DIV":
                            System.out.println("DIV");
                            if(tokens[1].compareTo("SI") == 0) 
                            {
                                bw.write("F7 F6");
                            }else 
                            {
                                bw.write("F7 F0");
                            }
                            //i=i+2;
                            bw.newLine();
                            break;

                        case "SUB":
                            System.out.println("SUB");

                            if(tokens[1].compareTo("AX") != 0)
                            {
                                bw.write("06 " + tokens[1]);
                                //i=i+2;
                                bw.newLine();
                                break;
                            }else if(tokens[2].compareTo("AX") == 0) 
                            {
                                bw.write("2B C0");
                            }else if(tokens[2].compareTo("DX") == 0) 
                            {
                                bw.write("2B C2");
                            }else
                            {
                                bw.write("2D " + tokens[2]);
                            }
                            //i=i+2;
                            bw.newLine();
                            break;
                        case "MUL":
                            System.out.println("MUL");
                            if(tokens[1].compareTo("SI") == 0) 
                            {
                                bw.write("F7 E6");
                            }else 
                            {
                                bw.write("F7 E0");
                            }
                            //i=i+2;
                            bw.newLine();
                            break;
                        
                        case "CMP":
                            System.out.println("CMP");
                            if(tokens[2].compareTo("DX") == 0) 
                            {
                                bw.write("3B C2");
                            }else 
                            {
                                bw.write("3D " + tokens[i+2]);
                            }

                            i=i+2;
                            bw.newLine();
                            break;

                        case "AND":
                            System.out.println("AND");
                            if(tokens[2].compareTo("DX") == 0) 
                            {
                                bw.write("23 C2");
                            }else if(tokens[2].compareTo("AX") == 0) 
                            {
                                bw.write("23 C0");
                            }else
                            {
                                bw.write("25 " + tokens[2]);
                            }
                            i=i+2;
                            bw.newLine();
                            break;

                        case "NOT":
                            System.out.println("NOT");
                            bw.write("F7 " +  "D0");
                            //i=i+2;
                            bw.newLine();
                            break;

                        case "OR":
                            System.out.println("OR");
                            if(tokens[2].compareTo("DX") == 0) 
                            {
                                bw.write("0B C2");
                            }else if(tokens[2].compareTo("AX") == 0) 
                            {
                                bw.write("0B C0");
                            }else
                            {
                                bw.write("0D " + tokens[2]);
                            }
                            //i=i+2;
                            bw.newLine();
                            break;

                        case "XOR":
                            System.out.println("XOR");
                            if(tokens[2].compareTo("DX") == 0) 
                            {
                                bw.write("33 C2");
                            }else if(tokens[2].compareTo("AX") == 0) 
                            {
                                bw.write("33 C0");
                            }else
                            {
                                bw.write("35 " + tokens[2]);
                            }
                            //i=i+2;
                            bw.newLine();
                            break;

                        case "JMP":
                            System.out.println("JMP");
                            bw.write("EB" + tokens[1]);
                            //i=i+2;
                            bw.newLine();
                            break;

                        case "JE":
                            System.out.println("JE");
                            bw.write("74 " + tokens[1]);
                            //i=i+2;
                            bw.newLine();
                            break;

                        case "JNZ":
                            System.out.println("JNZ");
                            bw.write("75 " + tokens[1]);
                            //i=i+2;
                            bw.newLine();
                            break;

                        case "JZ":
                            System.out.println("JZ");
                            bw.write("74 " + tokens[1]);
                            //i=i+2;
                            bw.newLine();
                            break;

                        case "JP":
                            System.out.println("JP");
                            bw.write("7A " + tokens[1]);
                            //i=i+2;
                            bw.newLine();
                            break;

                        case "call":
                            System.out.println("call");
                            bw.write("E8 " + tokens[1]);
                            //i=i+2;
                            bw.newLine();
                            break;

                        case "int":
                            System.out.println("int");
                            bw.write("CD " + tokens[1]);
                            //i=i+2;
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
                            bw.write("15 " + tokens[1]);
                            bw.newLine();
                            //i=i+2;;
                            break;
                        
                        case "COPY":
                            System.out.println("COPY");
                            bw.write("13 " + tokens[1] + " " + tokens[2]);
                            bw.newLine();
                            //i=i+3;;
                            break;

                        case "DIVIDE":
                            System.out.println("DIVIDE");
                            bw.write("10 " + tokens[1]);
                            bw.newLine();
                            //i=i+2;;
                            break;

                        case "LOAD":
                            System.out.println("LOAD");
                            bw.write("03 " + tokens[1]);
                            bw.newLine();
                            //i=i+2;;
                            break;

                        case "MULT":
                            System.out.println("MULT");
                            bw.write("14 " + tokens[1]);
                            bw.newLine();
                            //i=i+2;;
                            break;


                        case "READ":
                            System.out.println("READ");
                            bw.write("12 " + tokens[1]);
                            bw.newLine();
                            //i=i+2;;
                            break;


                        case "RET":
                            System.out.println("RET");
                            bw.write("16");
                            bw.newLine();
                            //i++;;
                            break;


                        case "STOP":
                            System.out.println("FIM DE EXECUÇÃO");
                            bw.write("11");
                            bw.newLine();
                            //i++;
                            break;
                        
                        case "STORE":
                            System.out.println("STORE");
                            bw.write("07 " + tokens [1]);
                            bw.newLine();
                            //i=i+2;;
                            break;


                        case "WRITE":
                            System.out.println("WRITE");
                            bw.write("08 " + tokens[1]);
                            bw.newLine();
                            //i=i+2;;
                            break;


                        default:
                            //i=i+2;
                            //bw.newLine();

                            break;
                    }//Fim switch

                }   

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

            BufferedReader in = new BufferedReader(new FileReader("entrada_macro_processed.txt")); //Lê na raiz do projeto
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
}