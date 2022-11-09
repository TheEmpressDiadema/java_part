package by.grsu.makarevich.test.db.model;

public class Result 
{
    private Integer id;
    private User user;
    private Test test;
    private String date;
    private Float mark;

    public Integer getId() 
    {
        return id;
    }

    public User getUser() 
    {
        return user;
    }

    public Test getTest() 
    {
        return test;
    }

    public String getDate() 
    {
        return date;
    }

    public Float getMark() 
    {
        return mark;
    }

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public void setUser(User user) 
    {
        this.user = user;
    }

    public void setTest(Test test) 
    {
        this.test = test;
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
        return "Result [id=" + id + ", user=" + user + ", test=" + test + ", date=" + date + ", mark=" + mark + "]";
    }

    
}
