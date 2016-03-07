/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 *
 * @author gabri_000
 */
import java.sql.*;
public class DataBase {
    private Connection conexion = null;
    public void estableceConexion()
    {
        if (conexion != null)
            return;
        String url = "jdbc:postgresql://localhost:5432/";
        try
        {
           Class.forName("org.postgresql.Driver");
           //Establecemos el Usuario y la contraseña
           
           conexion = DriverManager.getConnection(url,"postgres","55555grg");
           if (conexion !=null){
               System.out.println("Conexión a base de datos ... Ok");
           }
        } catch (Exception e) {
            System.out.println("Problema al establecer la Conexión a la base de datos 1 ");
        }
    }
         public ResultSet dameNombre()
    {
        ResultSet rs = null;
        Statement s = null;
        try
        {
            s = conexion.createStatement();
            //seleccionamos la tabla de la base de datos la cual lleva por nombre EmployeeTable
            rs = s.executeQuery("SELECT *FROM  \"public\".\"EmployeeTable\"");
        }catch (Exception e)
        {
            System.out.println("Problema al consultar la base de datos 1 ");
        }
        return rs;
    }
public void cierraConexion()
    {
        try
        {
            conexion.close();
        }catch(Exception e)
        {
            System.out.println("Problema para cerrar la Conexión a la base de datos ");
        }
    }
    
}
