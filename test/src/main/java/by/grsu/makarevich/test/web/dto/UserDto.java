package by.grsu.makarevich.test.web.dto;

import java.sql.Timestamp;

public class UserDto 
{
    private Integer id;
    private String name;
    private String secondName;
    private String patronimyc;
    private Integer roleId;
    private String roleName;
    private Timestamp updated;
    private Timestamp created;

    public Integer getId() 
    {
        return id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
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

    public Integer getRoleId() 
    {
        return roleId;
    }

    public Timestamp getUpdated() 
    {
        return updated;
    }

    public Timestamp getCreated() 
    {
        return created;
    }

    public void setId(Integer id) 
    {
        this.id = id;
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

    public void setRoleId(Integer roleId) 
    {
        this.roleId = roleId;
    }

    public void setUpdated(Timestamp updated) 
    {
        this.updated = updated;
    }

    public void setCreated(Timestamp created) 
    {
        this.created = created;
    }

    @Override
    public String toString() 
    {
        return "User [id=" + id + ", name=" + name + ", secondName=" + secondName + ", patronimyc=" + patronimyc
                + ", role=" + roleId + ", updated=" + updated + ", created=" + created + "]";
    }
    
}
