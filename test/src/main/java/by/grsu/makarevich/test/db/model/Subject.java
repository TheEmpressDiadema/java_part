package by.grsu.makarevich.test.db.model;

import java.sql.Timestamp;

public class Subject
{
    private Integer id;
    private String name;
    private Timestamp created;
    private Timestamp updated;
    
    public Integer getId() 
    {
        return id;
    }

    public String getName() 
    {
        return name;
    }

    public Timestamp getCreated() 
    {
        return created;
    }

    public Timestamp getUpdated() 
    {
        return updated;
    }

    public void setCreated(Timestamp created) 
    {
        this.created = created;
    }

    public void setUpdated(Timestamp updated) 
    {
        this.updated = updated;
    }

    public void setId(Integer id) 
    {
        this.id = id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    @Override
    public String toString() 
    {
        return "Subject [id=" + id + ", name=" + name + ", created=" + created + ", updated=" + updated + "]";
    }
}
