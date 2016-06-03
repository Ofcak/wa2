/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wa2.sem.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mira
 */
@Entity
@Table
@XmlRootElement
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_position;
    
    @Column
    private String name;
    
    @ManyToOne()
    @JoinColumn(name = "id_company")
    private Company company;
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "positions")
    private Set<User> users;
    
    @OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
    private Set<Shift> shifts;

    public Integer getId_position() {
        return id_position;
    }

    public void setId_position(Integer id_position) {
        this.id_position = id_position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @XmlTransient
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @XmlTransient
    public Set<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }
    
    
}
