package mypackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{
	
}