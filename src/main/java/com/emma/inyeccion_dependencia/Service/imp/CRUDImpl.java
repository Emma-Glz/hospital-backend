package com.emma.inyeccion_dependencia.Service.imp;

import com.emma.inyeccion_dependencia.Repo.IGenericRepo;
import com.emma.inyeccion_dependencia.Service.ICRUD;

import java.util.List;
import java.util.Optional;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T save(T t) {
        return getRepo().save(t);
    }

    @Override
    public T update(T t) {
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) {

        return getRepo().findById(id).orElse(null);
        //return (Optional<T>) getRepo().findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) {
        getRepo().deleteById(id);
    }
}
