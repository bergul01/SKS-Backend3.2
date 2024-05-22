package sks.project.sksbackend.repositoryDataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sks.project.sksbackend.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

	 @Query("SELECT c FROM Company c WHERE c.companyName = :companyName")
	 Company findByCompanyName(String companyName);
	
}

