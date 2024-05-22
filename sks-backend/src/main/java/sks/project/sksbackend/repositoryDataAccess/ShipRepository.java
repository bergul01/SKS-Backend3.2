package sks.project.sksbackend.repositoryDataAccess;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sks.project.sksbackend.entities.Ship;

public interface ShipRepository extends JpaRepository<Ship, Long>{
	@Query("SELECT s FROM Ship s WHERE s.shipName = :shipName")
	 Ship findByShipName(String shipName);
}
