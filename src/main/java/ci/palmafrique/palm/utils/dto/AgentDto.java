
/*
 * Java dto for entity table agent 
 * Created on 2021-01-24 ( Time 21:50:13 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.palm.utils.dto;

import java.util.Date;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

import ci.palmafrique.palm.utils.contract.*;
import ci.palmafrique.palm.utils.dto.customize._AgentDto;

/**
 * DTO for table "agent"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class AgentDto extends _AgentDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     nom                  ;
    private String     prenom               ;
    private String     sexe                 ;
    private String     nombreHeure          ;
    private String     telephone            ;
    private String     age                  ;
    private String     username             ;
    private String     password             ;
    private Integer    siteId               ;
    private Integer    directionId          ;
    private Integer    serviceId            ;
	private String     createdAt            ;
	private String     updatedAt            ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Boolean    isDeleted            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String serviceNom;
	private String siteNom;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   nomParam              ;                     
	private SearchParam<String>   prenomParam           ;                     
	private SearchParam<String>   sexeParam             ;                     
	private SearchParam<String>   nombreHeureParam      ;                     
	private SearchParam<String>   telephoneParam        ;                     
	private SearchParam<String>   ageParam              ;                     
	private SearchParam<String>   usernameParam         ;                     
	private SearchParam<String>   passwordParam         ;                     
	private SearchParam<Integer>  siteIdParam           ;                     
	private SearchParam<Integer>  directionIdParam      ;                     
	private SearchParam<Integer>  serviceIdParam        ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   serviceNomParam       ;                     
	private SearchParam<String>   siteNomParam          ;                     
    /**
     * Default constructor
     */
    public AgentDto()
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
