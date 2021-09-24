package org.generation.blogPessoal.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "postagem")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "O atributo titulo é obrigatório!")
    @Size(min = 5, max = 100, message = "O atributo titulo deve ter no minimo 5 e no máximo 100 caracteres")
    private String titulo;

    @NotNull(message = "O atributo texto é obrigatório")
    @Size(min = 5, max = 500, message = "O atributo texto deve ter no minimo 5 e no máximo 500 caracteres")
    private String texto;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data = new java.sql.Date(System.currentTimeMillis());


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDate() {
        return data;
    }

    public void setDate(Date data) {
        this.data = data;
    }
}
