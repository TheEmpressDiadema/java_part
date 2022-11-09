package resourses.java.by.grsu.makarevich.test.db.model;

public class Subject
{
    protected static Integer _id = 0;

    protected String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public static Integer getId() 
    {
        return _id;
    }

    @Override
    public String toString() 
    {
        return "Subject [name=" + name + "]";
    }

    
}
