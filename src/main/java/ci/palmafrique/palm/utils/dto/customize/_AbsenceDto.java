
/*
 * Java dto for entity table absence 
 * Created on 2021-01-24 ( Time 21:50:12 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.palm.utils.dto.customize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * DTO customize for table "absence"
 * 
 * @author Geo
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class _AbsenceDto {

	private String libelleType;
	private String medecinNom;
	private String agentNom;
	private String status="en cours";
	
	private String nombreNote;
}
