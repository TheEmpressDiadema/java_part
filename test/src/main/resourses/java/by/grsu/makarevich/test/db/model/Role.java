package resourses.java.by.grsu.makarevich.test.db.model;

public class Role 
{
    protected static Integer _id = 0;

    protected String name;

    public Role(String name)
    {
        ++_id;

        this.name = name; 
    }

    public static Integer getId() 
    {
        return _id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    @Override
    public String toString() 
    {
        return "Role [name=" + name + "]";
    }

    
}
