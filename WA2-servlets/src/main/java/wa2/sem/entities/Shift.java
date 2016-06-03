/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wa2.sem.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mira
 */
@Entity
@Table
@XmlRootElement
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_shift;
    
    @Column
    private String note;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateTime", nullable = true)
    private Date shiftStart;
    
    @Column
    private int shiftLength;
    
    @ManyToOne
    @JoinColumn(nullable=true)
    private User user;

    @ManyToOne
    @JoinColumn(nullable=false)
    private Company company;
    
    @ManyToOne
    @JoinColumn(nullable=false)
    private Position position;

    public Integer getId_shift() {
        return id_shift;
    }

    public void setId_shift(Integer id_shift) {
        this.id_shift = id_shift;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(Date shiftStart) {
        this.shiftStart = shiftStart;
    }

    public int getShiftLength() {
        return shiftLength;
    }

    public void setShiftLength(int shiftLength) {
        this.shiftLength = shiftLength;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    
    
}
