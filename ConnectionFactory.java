/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Batalha;

import java.sql.*;

/**
 *
 * @author vitor
 */
public class ConnectionFactory {
    
    public static Connection obterConexao() throws Exception{
        String url = "jdbc:mysql://localhost:3306/dbmaupom";
        String usuario = "root";
        String senha = "Pipoca01";
        return DriverManager.getConnection(url, usuario, senha);
    }
    
}
