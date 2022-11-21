package by.grsu.makarevich.test.db.model;

import java.sql.Timestamp;

public class Result 
{
    private Integer id;
    private Integer userId;
    private Integer testId;
    private Timestamp date;
    private Double mark;
    private Timestamp created;
    private Timestamp updated;

    public Integer getId() 
    {
        return id;
    }

    public Integer getUserId() 
    {
        return userId;
    }

    public Integer getTestId() 
    {
        return testId;
    }

    public Timestamp getDate() 
    {
        return date;
    }

    public Double getMark() 
    {
        return mark;
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

    public void setUserId(Integer userId) 
    {
        this.userId = userId;
    }

    public void setTestId(Integer testId) 
    {
        this.testId = testId;
    }

    public void setDate(Timestamp date) 
    {
        this.date = date;
    }

    public void setMark(Double mark) 
    {
        this.mark = mark;
    }

    @Override
    public String toString() 
    {
        return "Result [id=" + id + ", user=" + userId + ", test=" + testId + ", date=" + date + ", mark=" + mark
                + ", created=" + created + ", updated=" + updated + "]";
    }
    
}
