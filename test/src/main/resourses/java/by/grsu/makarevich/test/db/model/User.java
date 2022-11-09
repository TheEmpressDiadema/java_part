package resourses.java.by.grsu.makarevich.test.db.model;

public class User 
{
    protected static Integer _id = 0;

    protected String name;
    protected String secondName;
    protected String patronimyc;
    protected Role role;

    public User(String name, String secondName, String patronimyc, Role role)
    {
        ++_id;

        this.name = name;
        this.secondName = secondName;
        this.patronimyc = patronimyc;
        this.role = role;
    }

    public static Integer getId() 
    {
        return _id;
    }

    public String getName() 
    {
        return name;
    }

    public String getSecondName() 
    {
        return secondName;
    }

    public String getPatronimyc() 
    {
        return patronimyc;
    }

    public Role getRole() 
    {
        return role;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setSecondName(String secondName) 
    {
        this.secondName = secondName;
    }

    public void setPatronimyc(String patronimyc) 
    {
        this.patronimyc = patronimyc;
    }

    public void setRole(Role role) 
    {
        this.role = role;
    }

    @Override
    public String toString()
    {
        return "User [name=" + name + ", secondName=" + secondName + ", patronimyc=" + patronimyc + "]";
    }
    
}
