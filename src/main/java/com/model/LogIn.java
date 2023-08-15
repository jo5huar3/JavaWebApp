package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LogIn
{
    PreparedStatement pStatement = null;
    Connection connection = null;
    public String validate(String email, String password)
    {
        if(verifyEmail(email) && password.length() < 17 && !password.isEmpty())
        {   
            try
            {
                if(connection == null || connection.isClosed())
                    connection = DatabaseConnection.getConnection();
                pStatement = connection.prepareStatement(
                    "SELECT firstName, lastName FROM credentials WHERE " +
                    "email = ? AND passCode = ?"
                );
                pStatement.setString(1, email);
                pStatement.setString(2, password);
                ResultSet result = pStatement.executeQuery();
                if(result.next())
                    return result.getString(1) + " " + result.getString(2);
            } 
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
       return "";
    }
    private boolean verifyEmail(String email)
    {
        boolean isValid =
            (email.endsWith(".com") || email.endsWith(".org"));
        for(int i = 0; i < email.length() && isValid; ++i)
        {
            if(email.charAt(i) != '@')
            {
                if((int) email.charAt(i) < 48 || (int) email.charAt(i) > 122 ||
                    ((int) email.charAt(i) > 57 && (int) email.charAt(i) < 97))
                    isValid = false;
            }
            else
                break;
        }
        return isValid;
    }
}
