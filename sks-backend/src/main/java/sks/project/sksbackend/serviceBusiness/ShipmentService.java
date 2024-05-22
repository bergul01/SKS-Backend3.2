package sks.project.sksbackend.serviceBusiness;

import java.util.List;

import sks.project.sksbackend.entities.Shipment;
import sks.project.sksbackend.request.ShipmentCreateRequest;
import sks.project.sksbackend.request.ShipmentUpdateRequest;
import sks.project.sksbackend.response.ShipmentResponse;

public interface ShipmentService {

	Shipment createShipment(ShipmentCreateRequest request);

	void deleteShipment(Long shipmentId);

	Shipment getShipmentById(Long shipmentId);

	List<ShipmentResponse> getAllShipments();

	ShipmentResponse updateShipment(Long shipmentId, ShipmentUpdateRequest request);

	Shipment getShipmentByTeslimatNo(String teslimatNo);

	boolean checkTeslimatNo(String teslimatNo);


	
}
