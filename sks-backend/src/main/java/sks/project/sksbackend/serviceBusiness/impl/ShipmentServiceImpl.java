package sks.project.sksbackend.serviceBusiness.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sks.project.sksbackend.entities.Company;
import sks.project.sksbackend.entities.Product;
import sks.project.sksbackend.entities.Ship;
import sks.project.sksbackend.entities.Shipment;
import sks.project.sksbackend.entities.Vehicle;
import sks.project.sksbackend.exception.ResourceNotFoundException;
import sks.project.sksbackend.repositoryDataAccess.ShipmentRepository;
import sks.project.sksbackend.request.ShipmentCreateRequest;
import sks.project.sksbackend.request.ShipmentUpdateRequest;
import sks.project.sksbackend.request.VehicleRequest;
import sks.project.sksbackend.response.ShipmentResponse;
import sks.project.sksbackend.serviceBusiness.CompanyService;
import sks.project.sksbackend.serviceBusiness.ProductService;
import sks.project.sksbackend.serviceBusiness.ShipService;
import sks.project.sksbackend.serviceBusiness.ShipmentService;
import sks.project.sksbackend.serviceBusiness.VehicleService;


@AllArgsConstructor
@Service
public class ShipmentServiceImpl implements ShipmentService {
	
	
	
    private ShipmentRepository shipmentRepository;
    private CompanyService companyService;
    private ProductService productService;
    private ShipService shipService;
    private VehicleService vehicleService;
    

  
	
    @Override
	public Shipment createShipment(ShipmentCreateRequest request) {
    	 Company company = companyService.getCompanyByName(request.getCompanyName());
    	    Product product = productService.getProductByName(request.getProductName());
    	    Ship ship = shipService.getShipByName(request.getShipName());
    	    List<Vehicle> vehicles = new ArrayList<>();

    	    for (VehicleRequest vehicleRequest : request.getVehicles()) {
    	        Vehicle vehicle = vehicleService.getVehicleByTypeAndPlates(
    	            vehicleRequest.getVehicleType(), vehicleRequest.getTowPlate(), vehicleRequest.getTrailerPlate());
    	        if (vehicle != null) {
    	            vehicles.add(vehicle);
    	        } else {
    	            throw new IllegalArgumentException("Vehicle not found: " + vehicleRequest);
    	        }
    	    }

    	    if (company != null && product != null && ship != null && !vehicles.isEmpty()) {
    	        Shipment shipmentToSave = new Shipment();
    	        shipmentToSave.setDeparturePoint(request.getDeparturePoint());
    	        shipmentToSave.setDestinationPoint(request.getDestinationPoint());
    	        shipmentToSave.setCustomerPhone(request.getCustomerPhone());
    	        shipmentToSave.setPrice(request.getPrice());
    	        shipmentToSave.setComment(request.getComment());
    	        shipmentToSave.setCompany(company);
    	        shipmentToSave.setProduct(product);
    	        shipmentToSave.setShip(ship);
    	        shipmentToSave.setVehicles(vehicles);
    	        shipmentToSave.setShipmentDate(request.getShipmentDate());
    	        shipmentToSave.setTeslimatNo(request.getTeslimatNo());

    	        return shipmentRepository.save(shipmentToSave);
    	    } else {
    	        throw new IllegalArgumentException("Company, Product, Ship, and Vehicles must not be null, and Vehicles list must not be empty");
    	    }

	}
    
    @Override
    public void deleteShipment(Long shipmentId) {
    	Shipment shipment = shipmentRepository.findById(shipmentId)
    			.orElseThrow(() -> new ResourceNotFoundException("Verilen kimliğe sahip sevkiyat mevcut değil" + shipmentId));
  
    	shipmentRepository.delete(shipment);
    }
    
    @Override
    public Shipment getShipmentById(Long shipmentId) {
    	return shipmentRepository.findById(shipmentId)
    			 .orElseThrow(() -> new ResourceNotFoundException("Verilen kimliğe sahip sevkiyat mevcut değil " + shipmentId));
    }
    
   @Override
   public List<ShipmentResponse> getAllShipments() {
            List<Shipment> shipments = shipmentRepository.findAll();
            return shipments.stream().map(shipment -> new ShipmentResponse(shipment)).collect(Collectors.toList());
    }
   
   @Override
   public ShipmentResponse updateShipment(Long shipmentId, ShipmentUpdateRequest request) {
       Optional<Shipment> shipmentOpt = shipmentRepository.findById(shipmentId);
       if (shipmentOpt.isPresent()) {
           Shipment shipmentToUpdate = shipmentOpt.get();

           // Güncelleme işlemleri
           shipmentToUpdate.setDeparturePoint(request.getDeparturePoint());
           shipmentToUpdate.setDestinationPoint(request.getDestinationPoint());
           shipmentToUpdate.setCustomerPhone(request.getCustomerPhone());
           shipmentToUpdate.setPrice(request.getPrice());
           shipmentToUpdate.setComment(request.getComment());
           shipmentToUpdate.setShipmentDate(request.getShipmentDate());
           shipmentToUpdate.setTeslimatNo(request.getTeslimatNo());

           // İlişkili nesneler için güncelleme işlemleri
           if (request.getCompanyName() != null) {
               Company company = companyService.getCompanyByName(request.getCompanyName());
               if (company != null) {
                   shipmentToUpdate.setCompany(company);
               } else {
                   throw new IllegalArgumentException("Company not found: " + request.getCompanyName());
               }
           }
           if (request.getProductName() != null) {
               Product product = productService.getProductByName(request.getProductName());
               if (product != null) {
                   shipmentToUpdate.setProduct(product);
               } else {
                   throw new IllegalArgumentException("Product not found: " + request.getProductName());
               }
           }
           if (request.getShipName() != null) {
               Ship ship = shipService.getShipByName(request.getShipName());
               if (ship != null) {
                   shipmentToUpdate.setShip(ship);
               } else {
                   throw new IllegalArgumentException("Ship not found: " + request.getShipName());
               }
           }
           if (request.getVehicles() != null && !request.getVehicles().isEmpty()) {
               List<Vehicle> vehicles = new ArrayList<>();
               for (VehicleRequest vehicleRequest : request.getVehicles()) {
                   Vehicle vehicle = vehicleService.getVehicleByTypeAndPlates(
                           vehicleRequest.getVehicleType(), vehicleRequest.getTowPlate(), vehicleRequest.getTrailerPlate());
                   if (vehicle != null) {
                       vehicles.add(vehicle);
                   } else {
                       throw new IllegalArgumentException("Vehicle not found: " + vehicleRequest);
                   }
               }
               shipmentToUpdate.setVehicles(vehicles);
           }

           // Güncellenmiş nesneyi kaydet
           Shipment updatedShipment = shipmentRepository.save(shipmentToUpdate);
           return new ShipmentResponse(updatedShipment);
       } else {
           throw new ResourceNotFoundException("Verilen kimliğe sahip sevkiyat mevcut değil: " + shipmentId);
       }
   }
   @Override
   public Shipment getShipmentByTeslimatNo(String teslimatNo) {
       if (teslimatNo != null) {
           Shipment shipment = shipmentRepository.findByTeslimatNo(teslimatNo);
           if (shipment != null) {
               return shipment;
           } else {
               throw new IllegalArgumentException("Teslimat numarası geçersiz.");
           }
       } else {
           throw new IllegalArgumentException("Teslimat numarası boş olamaz.");
       }
   }
   
   @Override
   public boolean checkTeslimatNo(String teslimatNo) {
       Shipment shipment = shipmentRepository.findByTeslimatNo(teslimatNo);
       return shipment != null;
   }
   
   
   
   
}


	 
	    




