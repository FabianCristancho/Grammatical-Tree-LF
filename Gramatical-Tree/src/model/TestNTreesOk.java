package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;


public class TestNTreesOk {
    private static int level = 0;
    
    private NTree <String> nTree;

    public void fill() throws FileNotFoundException, IOException{
        File file = new File ("data/piramide1.csv"); 
        System.out.println("path : "+file.getAbsolutePath());
        FileReader fr = new FileReader (file); 
        BufferedReader br = new BufferedReader(fr); 
        System.out.println(nTree.add("RAIZ")?"OK":"Error");
        String linea;
        while((linea=br.readLine())!=null){
            String[] strings = linea.split(",");
            System.out.println(nTree.add(strings[0],strings[1])?"OK":"Error");
        }
        br.close();
        fr.close();
     }
    
    
    public void query(){
        System.out.println("Querys .....");
        System.out.println(" los elementos del mismo nivel de ana son ..???"); 
    }
    
    public TestNTreesOk(){
       this.nTree = new NTree<>(new Comparator<String>() {
           @Override
           public int compare(String o1, String o2) {
               return o1.compareTo(o2);
           }
       });
    }
    
    public static void main(String[] args) throws IOException {
       TestNTreesOk testNTrees = new TestNTreesOk();
       testNTrees.fill();
       testNTrees.query();
    }
    
}
