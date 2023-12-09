package package1;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;

public class BaseDatos {

    Connection conexion;
    Statement transaccion;
    ResultSet cursor;

    String cadenaConexion = "jdbc:mysql://beawjbj2yyqnjaghl7mq-mysql.services.clever-cloud.com:3306/beawjbj2yyqnjaghl7mq?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String usuario = "u0cfrarvcxsasjio";
    String pass = "DwguZ7Y8WBkcaRIhtjMI";

    public BaseDatos() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(cadenaConexion, usuario, pass);
            transaccion = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println("no se conecto");
        } catch (ClassNotFoundException e) {
            System.out.println("no se encontró clase");
        }
    }

    public boolean insertar(Credencial c) {
        String SQL_Insertar = "INSERT INTO `credencial` (`numcontrol`, `nombrealumno`, `carrera`, `fechaexpedicion`, `semestre`) "
                + "VALUES ('%NC%', '%NA%', '%C%', '%FE%', '%S%')";
        
        SQL_Insertar = SQL_Insertar.replace("%NC%","" + c.numControl);
        SQL_Insertar = SQL_Insertar.replace("%NA%", c.nombrealumno);
        SQL_Insertar = SQL_Insertar.replace("%C%", c.carrera);
        SQL_Insertar = SQL_Insertar.replace("%FE%", ""+ c.fechaexpedicion);
        SQL_Insertar = SQL_Insertar.replace("%S%", ""+ c.semestre);
        try {
            transaccion.execute(SQL_Insertar);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public ArrayList<String[]> mostrarTodos() {
        ArrayList<String[]> credenciales = new ArrayList<String[]>();

        try {
            cursor = transaccion.executeQuery("SELECT * FROM `credencial`");
            if (cursor.next()) {
                do {

                    String[] temporal = {"" + cursor.getInt("numcontrol"),
                        cursor.getString("nombrealumno"),
                        cursor.getString("carrera"),
                        "" + cursor.getDate("fechaexpedicion"),
                        "" + cursor.getInt("semestre")};

                    credenciales.add(temporal);

                } while (cursor.next());
            }
        } catch (Exception e) {
            return null;
        }
        return credenciales;
    }

    public Credencial obtenerPorID(String numControl) {
        String SQL_Consulta = "SELECT * FROM `credencial` WHERE `credencial`.`numcontrol` =" + numControl;
        try {
            cursor = transaccion.executeQuery(SQL_Consulta);
            if (cursor.next()) {
                Credencial c = new Credencial(
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getDate(4).toString(),
                        cursor.getInt(5)
                );
                return c;
            }

        } catch (Exception e) {
        }
        return new Credencial(-1, "", "", null ,-1);
    }

    public boolean eliminar(String numControl) {
        try {
            String SQL = ("DELETE FROM `credencial` WHERE `credencial`.`numcontrol` =" + numControl);
            transaccion.execute(SQL);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean actualizar(Credencial c) {
        try {
            String SQL = "UPDATE `credencial` SET `nombrealumno` = 'c1', `carrera` = 'c2', `fechaexpedicion` = 'c3' , `semestre` = 'c4'"
                    + "WHERE `credencial`.`numcontrol` =" + c.numControl + ";";

            SQL = SQL.replace("c1", c.nombrealumno);
            SQL = SQL.replace("c2", c.carrera);
            SQL = SQL.replace("c3", "" + c.fechaexpedicion);
            SQL = SQL.replace("c4", "" + c.semestre);

            transaccion.execute(SQL);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
