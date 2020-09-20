package com.hellojpa.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    private Long Team_id;

    private String name;

    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();


    public Long getId() {
        return Team_id;
    }

    public void setId(Long id) {
        this.Team_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member>  getMember() {
        return members;
    }

    public void setMember(List<Member> members ) {
        this.members = members;
    }


}
