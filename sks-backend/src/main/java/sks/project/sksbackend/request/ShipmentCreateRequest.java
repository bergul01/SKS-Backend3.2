package sks.project.sksbackend.request;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShipmentCreateRequest {

	
	private String departurePoint;
	    private String destinationPoint;
	    private String customerPhone;
	    private String price;
	    private String comment;
	    private Date shipmentDate;
	    private String teslimatNo;
	    private String companyName;
	    private String productName;
	    private String shipName;
	    private List<VehicleRequest> vehicles;
}