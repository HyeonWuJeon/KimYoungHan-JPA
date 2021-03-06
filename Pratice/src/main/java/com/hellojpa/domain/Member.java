package com.hellojpa.domain;


import javax.persistence.*;


@Entity
public class Member  {
    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team teamId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Team getTeamId() {return teamId;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    public void setTeamId(Team teamId){this.teamId=teamId;}

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", teamId=" + teamId +
                '}';
    }
}


/*
@Entity
//@Table(name = "USER") //USER라는 테이블에 insert
public class Member {

    @Id
    private Long id;
    private String name;

    //기본생성자가 하나 있어야한다.
    public Member() {

    }

    //jpa는 내부적으로 리플렉션을 사용하기때문에 동적객체를 생성해야한다.
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
*/