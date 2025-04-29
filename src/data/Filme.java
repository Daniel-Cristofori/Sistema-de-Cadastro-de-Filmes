/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Date;


/**
 * Cria filme
 */


public class Filme {
    
    private String nome;
    private Date dataLancamento;
    private String categoria;

    
/**  retorna o nome do filme
     * @return  */
    public String getNome() {
        return nome;
    }

/**    adicionanome do filme
     * @param nome */
    public void setNome(String nome) {
        this.nome = nome;
    }

/**    retorna data de lançamento do filme
     * @return  */
    public Date getDataLancamento() {
        return dataLancamento;
    }

/**    adiciona data de lançamento do filme
     * @param dataLancamento */
    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

/**    retorna a categoria do filme
     * @return  */
    public String getCategoria() {
        return categoria;
    }

/**    adiciona a categoria do filme
     * @param categoria */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
  
}
