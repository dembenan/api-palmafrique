
/*
 * Java dto for entity table absence 
 * Created on 2021-01-24 ( Time 21:50:12 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.palm.utils.dto;

import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

import ci.palmafrique.palm.utils.contract.*;
import ci.palmafrique.palm.utils.dto.customize._AbsenceDto;

/**
 * DTO for table "absence"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class AbsenceDto extends _AbsenceDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Date       dateDepart           ;
    private Date       dateRetour           ;
    private String     description          ;
    private Integer    agentId              ;
    private Integer    typeId               ;
    private Integer    medecinId            ;
	private String     createdAt            ;
	private String     updatedAt            ;
    private String     status               ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Boolean    isDeleted            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String medecinNom;
	private String medecinPrenom;
	private String agentNom;
	private String agentPrenom;
	private String typeabsenceLibelle;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Date>     dateDepartParam       ;                     
	private SearchParam<Date>     dateRetourParam       ;                     
	private SearchParam<String>   descriptionParam      ;                     
	private SearchParam<Integer>  agentIdParam          ;                     
	private SearchParam<Integer>  typeIdParam           ;                     
	private SearchParam<Integer>  medecinIdParam        ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   statusParam           ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   medecinNomParam       ;                     
	private SearchParam<String>   medecinPrenomParam    ;                     
	private SearchParam<String>   agentNomParam         ;                     
	private SearchParam<String>   agentPrenomParam      ;                     
	private SearchParam<String>   typeabsenceLibelleParam;                     
    /**
     * Default constructor
     */
    public AbsenceDto()
    {
        super();
    }
    
	//----------------------------------------------------------------------
    // clone METHOD
    //----------------------------------------------------------------------
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
