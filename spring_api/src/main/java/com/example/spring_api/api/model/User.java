package com.example.spring_api.api.model;

import org.yaml.snakeyaml.events.Event.ID;

public class User 
{
    private int id, age;
    private String name, email;

    public User(int id, String name, int age, String email)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getID()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}