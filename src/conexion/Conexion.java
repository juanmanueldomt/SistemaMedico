/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import org.cs3.prolog.connector.process.PrologProcess;
import org.cs3.prolog.connector.Connector;
import org.cs3.prolog.connector.process.PrologProcessException;

/**
 *
 * @author kronoz
 */
public class Conexion {

    PrologProcess prolog_terminal;
    

    public Conexion() throws IOException, PrologProcessException {
        this.prolog_terminal = Connector.newPrologProcess();
        File file = new File("/home/kronoz/Workspaces/Prolog/SistemaMedico.pl");
        prolog_terminal.consult(file);
    }

    public ArrayList<String> getEnfermedad() throws PrologProcessException {
        
        ArrayList<String> Enfermedades=new ArrayList<String>();
        List<Map<String,Object>> lista;
        lista=prolog_terminal.queryAll("enfermedad(X).");
        
        for(Map<String,Object> i:lista){
            Enfermedades.add(i.get("X").toString());
        }
       return Enfermedades;        
    }
    
     public ArrayList<String> getCondicion() throws PrologProcessException {
        
        ArrayList<String> Enfermedades=new ArrayList<String>();
        List<Map<String,Object>> lista;
        lista=prolog_terminal.queryAll("condicion(X).");
        
        for(Map<String,Object> i:lista){
            Enfermedades.add(i.get("X").toString());
        }
       return Enfermedades;        
    }
    
    public void addCondicion(String condicion,String parte,String intensidad) throws PrologProcessException{
        prolog_terminal.queryOnce("assert(sintoma("+condicion+","+parte+","+intensidad+")).");
        System.out.println("assert(sintoma("+condicion+","+parte+","+intensidad+")).");
    }
    
    public void addCondicion(ArrayList<String[]> lista) throws PrologProcessException{
        for(int i=0;i<lista.size();i++){
           addCondicion(lista.get(i)[0],lista.get(i)[1],lista.get(i)[2]);
          
        }
     
    }
/*

    */

    
     public void addAnalisis(String para,String valor) throws PrologProcessException{
          System.out.println("assert("+para+"("+valor+")).");
        prolog_terminal.queryOnce("assert("+para+"("+valor+")).");
        

    }
    public void addAnalisis(ArrayList<String[]> Analisis) throws PrologProcessException {
         for(int i=0;i<Analisis.size();i++){
           addAnalisis(Analisis.get(i)[0],Analisis.get(i)[1]);
          
        }
    }
}
