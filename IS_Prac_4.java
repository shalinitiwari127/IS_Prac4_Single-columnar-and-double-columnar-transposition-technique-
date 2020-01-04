package ispracs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
public class Singledoublecolumnar {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String[] keyset={"SYSTEMS","ENGINEERING","UNIVERSITY","ESSEN","DUISBURG","CRYPTOGRAPHY"};
        String check="SSDTTRRNNRICNAWCOILOATHKIUSGYITATOAAECN";
        String temp; 
        for(int i=0;i<keyset.length;i++){
            temp=decryption(check,keyset[i]);
            for(int j=0;j<keyset.length;j++){
                if(j!=i){
                  temp=decryption(temp,keyset[j]);
                    System.out.println("key1:-"+keyset[i]);
                    System.out.println("key2:-"+keyset[j]);
                    System.out.println("Decrypted text:-"+temp);
                    System.out.println("------------------");
                }                
            }
        }  
    }
   static String encryption(String key2,String cipher) {
        int key2arr[]=new int[key2.length()];
        kcal(key2,key2arr);
        System.out.println("Key for transposition:-");  
        for(int i=0;i<key2.length();i++)
            System.out.print(key2arr[i]+" ");
        System.out.println("");
        int rows=(int) Math.ceil((float)cipher.length()/(float)key2.length());
        int size= rows*key2.length();
        char transarr[][]=new char[rows+1][key2.length()]; 
        matrix(size,transarr,cipher,key2.length(),rows,key2arr);
        String transcipher=displaytrans(transarr,rows,key2.length(),size);
        System.out.println("\nTransposition cipher:-"+transcipher);
        return transcipher;
    }
   static void kcal(String key2,int[] keyarr){
        key2=key2.toUpperCase();
        char[] key2arr=new char[key2.length()];
        key2arr=key2.toCharArray();
        Arrays.sort(key2arr);
        ArrayList<Character>al=new ArrayList<>();
        for(char c:key2arr){
            al.add(c);
        }
        for(int i=0;i<key2.length();i++){
           char c=key2.charAt(i);
               int index=al.indexOf(c);
               al.remove(index);
               al.add(index,'#');
               keyarr[i]=index+1;
        }
    }    
static void matrix(int size, char[][] transarr, String cipher,int n,int rows,int[] key2arr) {
        for(int i=cipher.length();i<size;i++)
            cipher=cipher+"$";
        for(int i=0;i<n;i++)
         transarr[0][i]=(char)(key2arr[i]+'0');
        int k=0;
        for(int i=1;i<=rows;i++){
            for(int j=0;j<n;j++)
            { transarr[i][j]=cipher.charAt(k);
                k++;}
        }    
    }    
static String displaytrans(char[][] transarr,int rows,int col,int size ) {
        System.out.println("Matrix formed:-");
        for(int i=0;i<=rows;i++){
           System.out.println("");
           for(int j=0;j<col;j++)
               System.out.print(transarr[i][j]+" ");
       }        
       int k=1;
       int colindex=0;
       String transcipher="";
      while(true){ 
       for(int i=0;i<col;i++){
          if((char)(k+'0')==transarr[0][i]){
              colindex=i;
              k++;
              break;
          }    
       }
       for(int j=1;j<=rows;j++){
           transcipher+=String.valueOf(transarr[j][colindex]);
       }
       if(size==transcipher.length())
           break;
      }   
     return transcipher;
    }  
    static String decryption(String cipher2,String k) {
        int row= cipher2.length()/k.length();
        int col=k.length();
        char[][] matd=new char[row][col];
        int[] indexes=new int[k.length()];
        kcal(k,indexes);
        ArrayList<Integer>al=new ArrayList<>();
        for(int i:indexes){
            al.add(i);
        }
        int f=1,p=0;
        for(int i=0;i<col;i++){
            for(int j=0;j<row;j++){
              int f2=al.indexOf(f);
              char c=cipher2.charAt(p);
              p++;
              matd[j][f2]=c;
            }
            f++;
        }
        String temp=output(matd,row,col);
        return temp;  }
    private static String output(char[][] matd,int row,int col) {
        String temp="";
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++)
                temp+=(matd[i][j]); 
} return temp;  }}
