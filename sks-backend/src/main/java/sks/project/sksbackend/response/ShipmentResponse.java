package sks.project.sksbackend.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sks.project.sksbackend.entities.Company;
import sks.project.sksbackend.entities.Product;
import sks.project.sksbackend.entities.Ship;
import sks.project.sksbackend.entities.Shipment;
import sks.project.sksbackend.entities.Vehicle;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShipmentResponse {
	
	
		Long id;
	    String departurePoint;
	    String destinationPoint;
	    String customerPhone;
	    String price;
	    String comment;
	    Date shipmentDate;
	    String teslimatNo;
	    Company company;
	    Product product;
	    Ship ship;
	    List<Vehicle> vehicles;
	    
	    
	    public ShipmentResponse(Shipment shipment) {
			this.id = shipment.getId();
			this.departurePoint = shipment.getDeparturePoint();
			this.destinationPoint = shipment.getDestinationPoint();
			this.customerPhone = shipment.getCustomerPhone();
			this.price = shipment.getPrice();
			this.comment = shipment.getComment();
			this.shipmentDate = shipment.getShipmentDate();
		    this.teslimatNo = shipment.getTeslimatNo();
			this.company = shipment.getCompany();
			this.product = shipment.getProduct();
			this.ship = shipment.getShip();
			this.vehicles = new ArrayList<>(shipment.getVehicles());
		}


}
