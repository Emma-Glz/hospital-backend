package com.emma.inyeccion_dependencia.Repo;

import com.emma.inyeccion_dependencia.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface IGenericRepo<T,ID> extends JpaRepository<T,ID> {
}
