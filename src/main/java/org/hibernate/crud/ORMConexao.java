/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.crud;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author leandro
 */
public class ORMConexao {

    private static  EntityManagerFactory EMF; 
   
    private ORMConexao() {
             EMF =  Persistence.createEntityManagerFactory("hiberte_jpa");
    }
    
    public  EntityManagerFactory openConnect() {
        return EMF;
    }
    
    public static ORMConexao conexaoSegura(){
        return new ORMConexao();
    }

    
}
