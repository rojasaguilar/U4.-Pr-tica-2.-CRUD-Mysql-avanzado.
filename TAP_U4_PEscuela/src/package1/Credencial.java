package package1;
import java.util.Date;

public class Credencial {

    int numControl;
    String nombrealumno;
    String carrera;
    String fechaexpedicion;
    int semestre;

    public Credencial(int numControl, String nombrealumno, String carrera, String fechaexpedicion, int semestre) {
        this.numControl = numControl;
        this.nombrealumno = nombrealumno;
        this.carrera = carrera;
        this.fechaexpedicion = fechaexpedicion;
        this.semestre = semestre;
    }

    public Credencial(int numControl, String nombrealumno, String carrera, int semestre) {
        this.numControl = numControl;
        this.nombrealumno = nombrealumno;
        this.carrera = carrera;
        Date date = new Date();
        java.sql.Date sql;date = new java.sql.Date(date.getTime());
        this.fechaexpedicion =  date.toString();
        this.semestre = semestre;
    }

    @Override
    public String toString() {
        return "Credencial{" + "numControl=" + numControl + ", fechaexpedicion=" + fechaexpedicion + '}';
    }
    
    
}
