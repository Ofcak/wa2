/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wa2.workercmd;

/**
 *
 * @author Mira
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        System.out.println("hello");
        
        Recv.init();

            Recv.run();
        while(true){
            Thread.sleep(1000);
        }
        //System.out.println("bye");
    }
    
}
