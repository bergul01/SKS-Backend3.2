package sks.project.sksbackend.repositoryDataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sks.project.sksbackend.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

	 @Query("SELECT v FROM Vehicle v WHERE v.vehicleType = :vehicleType AND v.towPlate = :towPlate AND v.trailerPlate = :trailerPlate")
	    Vehicle findByVehicleTypeAndTowPlateAndTrailerPlate(
	        @Param("vehicleType") String vehicleType, 
	        @Param("towPlate") String towPlate, 
	        @Param("trailerPlate") String trailerPlate);
}
