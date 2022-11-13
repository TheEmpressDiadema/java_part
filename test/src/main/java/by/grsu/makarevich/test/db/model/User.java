package by.grsu.makarevich.test.db.model;

import java.sql.Timestamp;

public class User 
{
    private Integer id;

    private String name;
    private String secondName;
    private String patronimyc;
    private Role role;
    private Timestamp updated;
    private Timestamp created;

    public Integer getId() 
    {
        return id;
    }

    public String getName() 
    {
        return name;
    }

    public String getSecondName() 
    {
        return secondName;
    }

    public String getPatronimyc() 
    {
        return patronimyc;
    }

    public Role getRole() 
    {
        return role;
    }

    public Timestamp getUpdated() 
    {
        return updated;
    }

    public Timestamp getCreated() 
    {
        return created;
    }

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setSecondName(String secondName) 
    {
        this.secondName = secondName;
    }

    public void setPatronimyc(String patronimyc) 
    {
        this.patronimyc = patronimyc;
    }

    public void setRole(Role role) 
    {
        this.role = role;
    }

    public void setUpdated(Timestamp updated) 
    {
        this.updated = updated;
    }

    public void setCreated(Timestamp created) 
    {
        this.created = created;
    }

    @Override
    public String toString() 
    {
        return "User [id=" + id + ", name=" + name + ", secondName=" + secondName + ", patronimyc=" + patronimyc
                + ", role=" + role + "]";
    }
}
