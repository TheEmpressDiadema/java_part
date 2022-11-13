package by.grsu.makarevich.test;

import by.grsu.makarevich.test.db.model.*;

public class Main 
{
    public static void main(String[] args)
    {
        User user = new User();

        user.setId(1);
        user.setName("Victor");
        user.setSecondName("Makarevich");
        user.setPatronimyc("Anatolich");
        user.setRole(new Role());

        System.out.println(user.toString());
    }    
}
