
package bake.examenbanco;

public class Cliente {
    private String id;
    private String nombre;
    private String apellidos;
    private char sexo;
    private int edad;
    private ListaSE<Cuenta> cuentas;

    public Cliente(String id, String nombre, String apellidos, char sexo, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.edad = edad;
        this.cuentas = new ListaSE<>();
    }
    
    public void agregarCuenta (Cuenta cuenta){
        cuentas.Adiconar(cuenta);
    }
    
    public void eliminarCuenta (String numeroCuenta) {
        int indiceCuenta = -1;
        for (int i = 0; i < cuentas.Longitud(); i++) {
            if (cuentas.Obtener(i).getNumeroCuenta() == numeroCuenta) {
                indiceCuenta = i;
                break;
            }
        }
        if (indiceCuenta != -1) {
            cuentas.Eliminar(indiceCuenta);
        }
    }

    // Getters y setters

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public char getSexo() {
        return sexo;
    }

    public int getEdad() {
        return edad;
    }

    public ListaSE<Cuenta> getCuentas() {
        ListaSE<Cuenta> listaCuentas = new ListaSE<>();
        for (int i = 0; i < cuentas.Longitud(); i++) {
            listaCuentas.Adiconar(cuentas.Obtener(i));
        }
        return listaCuentas;
    }
}
