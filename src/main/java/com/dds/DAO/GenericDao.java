/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dds.DAO;

import com.dds.model.Site;
import com.dds.util.JPAUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.*;
import javax.swing.JList;

public abstract class GenericDao<T, I extends Serializable> {

    @Inject
    protected EntityManager entityManager;

    private Class<T> persistedClass;

    protected GenericDao() {
    }

    protected GenericDao(Class<T> persistedClass) {
        this();
        entityManager = JPAUtil.getEntityManager();
        this.persistedClass = persistedClass;
    }

    public T save(T entity) {
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        entityManager.merge(entity);
        entityManager.flush();
        t.commit();
        return entity;
    }

    public T update(T entity) {
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        entityManager.merge(entity);
        entityManager.flush();
        t.commit();
        return entity;
    }

    public void remove(Integer id) {
        T entity = find(id);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        T mergedEntity = entityManager.merge(entity);
        entityManager.remove(mergedEntity);
        entityManager.flush();
        tx.commit();
    }

    public List<T> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistedClass);
        query.from(persistedClass);
        return entityManager.createQuery(query).getResultList();
    }

    public T find(Integer id) {
        return entityManager.find(persistedClass, id);
    }
    
    public List<T> getSitesSelectedFromJList(JList<String> jList) {
        List<T> lista = new ArrayList<>();

        if (!jList.getSelectedValuesList().isEmpty()) {
            for(String item :  jList.getSelectedValuesList()){
                lista.add(find(Integer.parseInt(item.split(" - ")[0])));
            }
        }

        return lista;
    }
}
