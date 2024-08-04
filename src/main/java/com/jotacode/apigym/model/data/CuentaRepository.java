    package com.jotacode.apigym.model.data;

    import com.jotacode.apigym.model.entity.Cuenta;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.List;
    import java.util.Optional;

    @Repository
    public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

        Optional<Cuenta> findById(Long id);

        Optional<Cuenta> findByUsernameEqualsIgnoreCase(String username);

        Optional<List<Cuenta>> findByActivoTrue();

    }
