package resourses.java.by.grsu.makarevich.test.db.model;

public class Test 
{
    protected static Integer _id = 0;
    protected String _creationDate;

    protected String name;
    protected Boolean status;
    protected String updateDate;

    public Test(String creationDate, String name, Boolean status, String updateDate)
    {
        ++_id;

        this._creationDate = creationDate;
        this.name = name;
        this.status = status;
        this.updateDate = updateDate;
    }
    
    public Integer getId()
    {
        return _id;
    }

    public String getName()
    {
        return name;
    }

    public Boolean getStatus()
    {
        return status;
    }

    public String getCreationDate()
    {
        return _creationDate;
    }

    public String GetUpdateDate()
    {
        return updateDate;
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
        return "Test [name=" + name + "]";
    }
   
}
