package sks.project.sksbackend.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ships")
public class Ship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ship_name")
	private String shipName;
	
	@Column(name = "exporters")
	private String exporter;
	
	@Column(name = "departure_port")
	private String departurePort;
	
	@Column(name = "destination_port")
	private String destinationPort;
	
	@Column(name = "ship_load")
	private String load;
	
	@Column(name = "Upload Date")
	@Temporal(TemporalType.DATE)
	private Date uploadDate;
}

