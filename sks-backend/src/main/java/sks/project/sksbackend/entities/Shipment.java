package sks.project.sksbackend.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shipments")
public class Shipment {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "departure_point")
	private String departurePoint;
	
	@Column(name = "destination_point")
	private String destinationPoint;
	
	@Column(name = "customer_phone")
	private String customerPhone;
	
	@Column(name = "price")
	private String price;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "shipment_date")
	@Temporal(TemporalType.DATE)
	private Date shipmentDate;

	@Column(name = "teslimat_no")
	@Size(min = 9, max = 9, message = "Teslimat No 9 haneli olmalıdır.")
	private String teslimatNo;
	
	@ManyToOne
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	private Company company;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "ship_id", referencedColumnName = "id")
	private Ship ship;

	 @ManyToMany
	    @JoinTable(
	        name = "shipment_vehicle", 
	        joinColumns = @JoinColumn(name = "shipment_id", referencedColumnName = "id"), 
	        inverseJoinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
	    )
	    private List<Vehicle> vehicles;
	
	
	
	
}
