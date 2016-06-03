/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wa2.sem.entities.DTO;

/**
 *
 * @author Mira
 */
public class MyMessage {
        private String note;
        private String num;
        public MyMessage() {
        }

        public MyMessage(String note, String num) {
            this.note = note;
            this.num = num;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
        
        
    }
