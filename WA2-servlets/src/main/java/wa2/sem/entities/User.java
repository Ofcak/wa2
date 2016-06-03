/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wa2.sem.entities;

import cz.cvut.fel.glassfishjspmysql.SelectedDatabase;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.Session;
import org.hibernate.annotations.Cascade;
import wa2.sem.entities.DTO.UserDTO;

/**
 *
 * @author Mira
 */
@Entity
@Table
@XmlRootElement
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_user;
    
    @Column
    private String name;
    
    @ManyToOne()
    @JoinColumn(name = "id_company")
    private Company company;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade(value=org.hibernate.annotations.CascadeType.DELETE)
    @JoinTable(name = "user_position", 
            joinColumns = {@JoinColumn(name = "id_user", nullable = false, 
                    updatable = false) }, 
            inverseJoinColumns = { @JoinColumn(name = "id_position", 
                    nullable = false, updatable = false) })
    private Set<Position> positions;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Shift> shifts;

    public User() {
    }

    
    
    public User(UserDTO dto){
        if(dto.getId_user() > 0){
            this.id_user = dto.getId_user();
        }
        this.name = dto.getName();
        
        Session sess = SelectedDatabase.getHibernateSession();
        if(dto.getPositions() != null && !dto.getPositions().isEmpty()){
            this.positions = new HashSet<>();
            for(int x : dto.getPositions()){
                positions.add((Position) sess.load(Position.class, x));
            }
        } else {
            //this.positions = null;
        }
        
        if(dto.getShifts() != null && !dto.getShifts().isEmpty()){
            this.shifts = new HashSet<>();
            for(int x : dto.getShifts()){
                shifts.add((Shift) sess.load(Shift.class, x));
            }
        }
        sess.flush();
        sess.close();
    }
    
    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
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
    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    @XmlTransient
    public Set<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }
    
    
}
