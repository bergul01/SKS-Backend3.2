package sks.project.sksbackend.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sks.project.sksbackend.entities.Shipment;
import sks.project.sksbackend.exception.ResourceNotFoundException;
import sks.project.sksbackend.request.ShipmentCreateRequest;
import sks.project.sksbackend.request.ShipmentUpdateRequest;
import sks.project.sksbackend.response.ShipmentResponse;
import sks.project.sksbackend.serviceBusiness.ShipmentService;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/shipments")
public class ShipmentController {
	

    private ShipmentService shipmentService;
    
    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody ShipmentCreateRequest request) {
        try {
            Shipment createdShipment = shipmentService.createShipment(request);
            return new ResponseEntity<>(createdShipment, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable("id") Long shipmentId){
    	
    	Shipment shipment = shipmentService.getShipmentById(shipmentId);
    		return ResponseEntity.ok(shipment);
    }
    
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipment(@PathVariable("id") Long shipmentId){
    	
    	try {
			shipmentService.deleteShipment(shipmentId);
			return ResponseEntity.ok("Sevkiyat silme işlemi başarılı");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
    	
    }
    
    @GetMapping
    public ResponseEntity<List<ShipmentResponse>> getAllShipments() {
        List<ShipmentResponse> shipments = shipmentService.getAllShipments();
        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<ShipmentResponse> updateShipment(@PathVariable("id") Long shipmentId, @RequestBody ShipmentUpdateRequest request) {
        try {
            ShipmentResponse updatedShipment = shipmentService.updateShipment(shipmentId, request);
            return new ResponseEntity<>(updatedShipment, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/teslimat/{teslimatNo}")
    public Shipment getShipmentByTeslimatNo(@PathVariable("teslimatNo") String teslimatNo) {
        return shipmentService.getShipmentByTeslimatNo(teslimatNo);
    }
    
    
    @PostMapping("/customerlogin")
    public ResponseEntity<Map<String, Object>> checkTeslimatNo(@RequestBody Map<String, String> request) {
        String teslimatNo = request.get("teslimatNo");
        boolean exists = shipmentService.checkTeslimatNo(teslimatNo);
        Map<String, Object> response = new HashMap<>();
        response.put("success", exists);
        return ResponseEntity.ok(response);
    }
    
    
    
    
    
    
    
}