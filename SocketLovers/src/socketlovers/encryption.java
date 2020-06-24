/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketlovers;

/**
 *
 * @author Nawoda Wijebandara
 */
public class encryption {
    
    public String encrypt(String msg){
    char[] arr=new char[msg.length()];
        int y=0;
        for(int i=msg.length()-1;i>=0;i--,y++){
            //System.out.println(message.charAt(i));
            arr[y]=msg.charAt(i);
            //System.out.println(arr[y]);
        }
        return String.valueOf(arr);
    }
    public String decrypt(String msg){
        char[] arr=new char[msg.length()];
        int y=msg.length()-1;
        for(int i=0;i<msg.length();i++,y--){
            arr[y]=msg.charAt(i);
        }
        return String.valueOf(arr);
    
    }
}
