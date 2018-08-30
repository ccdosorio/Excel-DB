package leerexcel;

/**
 *
 * @author Christian
 */
public class Categoria {
    
    private int codigo_genero;
    private String descripcion;

    public Categoria() {
    }

    public Categoria(int codigo_genero, String descripcion) {
        this.codigo_genero = codigo_genero;
        this.descripcion = descripcion;
    }
    
    public int getCodigo_genero() {
        return codigo_genero;
    }

    public void setCodigo_genero(int codigo_genero) {
        this.codigo_genero = codigo_genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
