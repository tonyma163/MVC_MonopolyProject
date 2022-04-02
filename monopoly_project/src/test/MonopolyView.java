/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author Ma Man To Tony
 */
public class MonopolyView {
    
    private MonopolyController controller;
    
    public void setController(MonopolyController c) {
        this.controller = c;
    }
    
    public void processCommand() {
        System.out.println("Hello MVC");
    }
    
    public void showMessage(String s) {
        System.out.println(s);
    }
    
}
