package sks.project.sksbackend.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest {
		private String vehicleType;
	    private String towPlate;
	    private String trailerPlate;

}
