package com.example.apirest2.model;

public class Libro {
    private int idlibro;
    private String titulo;
    private String autor;
    private int paginas;
    private double edicion;
    private int ideditorial;
    private String editorial;

    public int getIdlibro() {
        return idlibro;
    }
    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setPaginas(String paginas) {
        this.autor = paginas;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public double getEdicion() {
        return edicion;
    }

    public void setEdicion(double edicion) {
        this.edicion = edicion;
    }

    public int getIdeditorial() {
        return ideditorial;
    }

    public void setIdeditorial(int ideditorial) {
        this.ideditorial = ideditorial;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + ideditorial +
                ", titulo='" + titulo +
                ", autor='" + autor +
                ", paginas='" + paginas +
                ", precio='" + edicion +
                '}';
    }
}
