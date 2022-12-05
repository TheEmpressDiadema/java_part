package by.grsu.makarevich.test.web.dto;

import java.sql.Timestamp;

public class RoleDto 
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
        return "Role [id=" + id + ", name=" + name + ", created=" + created + ", updated=" + updated + "]";
    }
}
