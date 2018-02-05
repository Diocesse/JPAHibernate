/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.crud;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.negocio.Produto;

/**
 *
 * @author leandro
 */
public class RepositorioProduto {

    ORMConexao conexao = ORMConexao.conexaoSegura();

    public String[][] produtosParaCompra(String busca) {
        EntityManagerFactory emf = conexao.openConnect();
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Produtos.todosProdutos");
        q.setParameter("filtro", "%" + busca + "%");
        Collection<Produto> listaTodos = q.getResultList();
        String[][] lista = new String[listaTodos.size()][0];
        int count = 0;
        for (Produto p : listaTodos) {

            String[] linha = {String.valueOf(p.getId_Produto()), p.getDescricao(), String.valueOf(p.getQtd()), String.valueOf("20,00")};
            lista[count++] = linha;

        }
        emf.close();
        return lista;

    }

    public String[][] agrudosPorDescricao(String filtro) {
        EntityManagerFactory emf = conexao.openConnect();
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Produtos.produtosAgrupadosPorDescricao");
        q.setParameter("filtro", "%" + filtro + "%");
        Collection<Produto> listaTodos = q.getResultList();
        String[][] lista = new String[listaTodos.size()][0];
        int count = 0;
        for (Produto p : listaTodos) {

            String[] linha = {String.valueOf(p.getId_Produto()), p.getDescricao(), String.valueOf(p.getQtd()), String.valueOf(p.getValor())};
            lista[count++] = linha;

        }
        emf.close();
        return lista;

    }

    public void save(Produto p) {
        try {
            EntityManagerFactory emf = conexao.openConnect();
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();

        } catch (Exception e) {
        }
    }
}
