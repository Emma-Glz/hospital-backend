package com.emma.inyeccion_dependencia.Service;

import com.emma.inyeccion_dependencia.Model.Patient;

import java.util.List;
import java.util.Optional;

public interface ICRUD<T,ID> {
    T save(T t);
    T update(T t, ID id);
    List<T> findAll();
    T findById(ID id);

    void delete(ID id);
}
