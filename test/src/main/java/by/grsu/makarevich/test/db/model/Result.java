package by.grsu.makarevich.test.db.model;

import java.sql.Timestamp;;

public class Result 
{
    private Integer id;
    private Integer userId;
    private Integer testId;
    private String date;
    private Float mark;
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

    public String getDate() 
    {
        return date;
    }

    public Float getMark() 
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

    public void setDate(String date) 
    {
        this.date = date;
    }

    public void setMark(Float mark) 
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
