
package bake.breadbank;

public class Cliente {
    private String identidad;
    private String nombre;
    private String apellidos;
    private char sexo;
    private int edad;
    private ListaSE<Cuenta> cuentas;

    public Cliente (String identidad, String nombre, String apellidos, char sexo, int edad) {
        this.identidad = identidad;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.edad = edad;
        this.cuentas = new ListaSE<Cuenta>();
    }

    //Getters
    public String getIdentidad() {return identidad;}
    public String getNombre() {return nombre;}
    public String getApellidos() {return apellidos; }
    public char getSexo() {return sexo;}
    public int getEdad() { return edad;}
    public ListaSE<Cuenta> getCuentas() { return cuentas;}

    //Setters
    public void setIdentidad(String identidad) { this.identidad = identidad; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos;}
    public void setSexo(char sexo) { this.sexo = sexo; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setCuentas(ListaSE<Cuenta> cuentas) { this.cuentas = cuentas; }
}
