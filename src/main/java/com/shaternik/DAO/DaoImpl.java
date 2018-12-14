package com.shaternik.DAO;

import com.shaternik.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.shaternik.model.*;

import java.io.Serializable;

public class DaoImpl <T> {

        private Class<T> persistentClass;

        public DaoImpl(Class<T> type) {
            this.persistentClass = type;
        }

        public Class<T> getPersistentClass() {
            return persistentClass;
        }


    private Session getSession() {
            return HibernateUtil.getInstance().getSession();
    }

    public T saveOrUpdate(T t) {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(t);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return t;
    }

    @NotNull
    public T load(Serializable id) {
        if (id == null) throw new IllegalArgumentException("Persistant instance id must not be null");
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        T t = null;
        try {
            t = (T)session.load(getPersistentClass(), id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
        }
        if (t == null) throw new IllegalStateException("Persistance instance doesn't exist");
        return t;
    }

    @Nullable
    public T find(Serializable id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        T t = null;
        try {
            t = (T)session.get(getPersistentClass(), id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
        }
        return t;
    }


    public void delete(Serializable id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            T t = session.get(getPersistentClass(), id);
            session.delete(t);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
        }
    }



}
