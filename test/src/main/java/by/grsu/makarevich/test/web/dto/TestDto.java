package by.grsu.makarevich.test.web.dto;

import java.sql.Timestamp;

public class TestDto {
    private Integer id;
    private String name;
    private Boolean status;
    private Integer subjectId;
    private String subjectName;
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

    public Boolean getStatus() 
    {
        return status;
    }

    public Timestamp getCreated() 
    {
        return created;
    }

    public Timestamp getUpdated() 
    {
        return updated;
    }

    public Integer getSubjectId()
    {
        return subjectId;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectId(Integer subjectId)
    {
        this.subjectId = subjectId;
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

    public void setStatus(Boolean status) 
    {
        this.status = status;
    }

    @Override
    public String toString() 
    {
        return "Test [id=" + id + ", created=" + created.toString() + ", name=" + name + ", status=" + status 
        + "subjectId" + subjectId + ", updated =" + updated.toString() + "]";
    }
}
