package Parcial2_Web.util;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBaseServices {
    
    private static DataBaseServices instancia;
    private String URL = "jdbc:h2:tcp://localhost/~/BrayanMnz_Parcial2";
    private Server tcp;


    private DataBaseServices() {

    }

    public static DataBaseServices getInstancia() {
        if (instancia == null) {
            instancia = new DataBaseServices();
        }
        return instancia;
    }

    private void registerDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }

    public void testConn() {
        try {
            getConn().close();
            System.out.println("Conexión exitosa!");

            System.out.println("\n\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void startDB() throws SQLException {
        // Se crea el servidor
        tcp = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-tcpDaemon", "-ifNotExists").start();

        String status = Server.createWebServer("-trace", "-webPort", "0").start().getStatus();
        //
        System.out.println("\n\nStatus Web: "+status);
        
    }

    public void stopDB() {

        tcp.stop();
    }
}
