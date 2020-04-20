package ru.vrn.medsys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vrn.medsys.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String s);
}
