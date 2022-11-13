package by.grsu.makarevich.test.db.model;

import java.sql.Timestamp;

public class Test 
{
    private Integer id;
    private String creationDate;
    private String name;
    private Boolean status;
    private String updateDate;
    private Timestamp created;
    private Timestamp updated;

    public Integer getId() 
    {
        return id;
    }
    
    public String getCreationDate() 
    {
        return creationDate;
    }

    public String getName() 
    {
        return name;
    }

    public Boolean getStatus() 
    {
        return status;
    }

    public String getUpdateDate() 
    {
        return updateDate;
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

    public void setCreationDate(String creationDate) 
    {
        this.creationDate = creationDate;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setStatus(Boolean status) 
    {
        this.status = status;
    }

    public void setUpdateDate(String updateDate) 
    {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Test [id=" + id + ", creationDate=" + creationDate + ", name=" + name + ", status=" + status
                + ", updateDate=" + updateDate + "]";
    }


}
