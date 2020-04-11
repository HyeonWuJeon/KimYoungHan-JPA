package com.hellojpa.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;
    private String name;


    //카테고리 트리형식
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM", joinColumns= @JoinColumn(name ="CATEGORY_ID"), //내가 조인
            inverseJoinColumns = @JoinColumn(name="Item_ID") //반대쪽이 조인해야 하는것
    )
    private List<Item> items = new ArrayList<>();
}
