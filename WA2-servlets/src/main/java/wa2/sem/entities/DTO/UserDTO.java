/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wa2.sem.entities.DTO;

import java.util.Set;

/**
 *
 * @author Mira
 */
public class UserDTO {
    private Integer id_user;
    
    private String name;
    
    private int company;

    private Set<Integer> positions;
    

    private Set<Integer> shifts;

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

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }

    public Set<Integer> getPositions() {
        return positions;
    }

    public void setPositions(Set<Integer> positions) {
        this.positions = positions;
    }

    public Set<Integer> getShifts() {
        return shifts;
    }

    public void setShifts(Set<Integer> shifts) {
        this.shifts = shifts;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "id_user=" + id_user + ", name=" + name + ", company=" + company + ", positions=" + positions + ", shifts=" + shifts + '}';
    }
    
    
}
