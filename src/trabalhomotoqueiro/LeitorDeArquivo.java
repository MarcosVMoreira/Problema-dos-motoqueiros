package trabalhomotoqueiro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class LeitorDeArquivo {
    
    public static LinkedList<String> leitorString (String path) throws IOException { 
        BufferedReader buffRead = new BufferedReader(new FileReader(path)); 
        LinkedList<String> texto = new LinkedList<>();
        String linha = buffRead.readLine();
        while (linha != null) { 
            texto.add(linha);
            linha = buffRead.readLine(); 
        } 
        buffRead.close(); 
        return texto;
    }     
    
    public static void escritor (String path, String texto) throws IOException { 
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path, true)); 
        buffWrite.append(texto); 
        buffWrite.close();
    } 
    
}
