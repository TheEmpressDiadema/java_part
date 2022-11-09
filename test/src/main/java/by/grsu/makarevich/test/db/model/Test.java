package by.grsu.makarevich.test.db.model;

public class Test 
{
    private Integer id;
    private String creationDate;
    private String name;
    private Boolean status;
    private String updateDate;

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
