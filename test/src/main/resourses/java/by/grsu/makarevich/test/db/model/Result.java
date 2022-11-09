package resourses.java.by.grsu.makarevich.test.db.model;

public class Result 
{
    protected static Integer _id = 0;

    protected User user;
    protected Test test;
    protected String date;
    protected Float mark;


    public Result(User user, Test test, String date, Float mark)
    {
        ++_id;

        this.user = user;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }


    public static Integer getId() 
    {
        return _id;
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

    
}
