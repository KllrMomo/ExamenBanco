
package bake.breadbank;

public interface IListaSE <T> {
    public int Longitud();
    public T Obtener(int indice);
    public boolean EsVacia();
    public void Adiconar(T dato);
    public void Insertar(T dato, int indice);
    public void Eliminar(int indice);
    public int Buscar(T dato);
}
