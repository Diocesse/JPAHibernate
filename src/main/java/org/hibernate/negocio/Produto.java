/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.negocio;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "tbl_produtos")
@NamedQueries({ 
@NamedQuery (name = "Produtos.todosProdutos", query = "SELECT p FROM Produto p WHERE p.descricao LIKE :filtro"),
@NamedQuery(name = "Produtos.produtosAgrupadosPorDescricao",query = "SELECT p FROM Produto p WHERE p.descricao LIKE :filtro GROUP BY p.descricao")    
       
})
public class Produto implements Serializable{

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Produto;
    @Column(name = "descricao", unique = true, nullable = true)
    private String descricao ;
    @Column(name = "qtd")    
    private int qtd;
    @Column(name = "valor")
    private double valor;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    
    
    public long getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(long id_Produto) {
        long oldId_Produto = this.id_Produto;
        this.id_Produto = id_Produto;
        changeSupport.firePropertyChange("id_Produto", oldId_Produto, id_Produto);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        int oldQtd = this.qtd;
        this.qtd = qtd;
        changeSupport.firePropertyChange("qtd", oldQtd, qtd);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
    
    
    
}
