package uce.edu.web.api.matricula.infraestructure;

public class MateriaResumenDTO {
    public Integer id;
    public String nombre;
    public Integer creditos;

    public MateriaResumenDTO(Integer id, String nombre, Integer creditos) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
    }
}