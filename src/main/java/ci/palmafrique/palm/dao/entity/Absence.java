/*
 * Created on 2021-01-24 ( Time 21:50:12 )
 * Generated by Telosys Tools Generator ( version 3.1.2 )
 */
// This Bean has a basic Primary Key (not composite) 

package ci.palmafrique.palm.dao.entity;

import java.io.Serializable;

import lombok.*;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "absence"
 *
 * @author Telosys Tools Generator
 *
 */
@Data
@ToString
@Entity
@Table(name="absence" )
public class Absence implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Integer    id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Temporal(TemporalType.DATE)
    @Column(name="date_depart", nullable=false)
    private Date       dateDepart   ;

    @Temporal(TemporalType.DATE)
    @Column(name="date_retour", nullable=false)
    private Date       dateRetour   ;

    @Column(name="description", nullable=false, length=255)
    private String     description  ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date       createdAt    ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date       updatedAt    ;

    @Column(name="status", length=250)
    private String     status       ;

    @Column(name="created_by")
    private Integer    createdBy    ;

    @Column(name="updated_by")
    private Integer    updatedBy    ;

    @Column(name="is_deleted")
    private Boolean    isDeleted    ;

	// "agentId" (column "agent_id") is not defined by itself because used as FK in a link 
	// "typeId" (column "type_id") is not defined by itself because used as FK in a link 
	// "medecinId" (column "medecin_id") is not defined by itself because used as FK in a link 

    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="medecin_id", referencedColumnName="id")
    private Medecin medecin     ;
    @ManyToOne
    @JoinColumn(name="agent_id", referencedColumnName="id")
    private Agent agent       ;
    @ManyToOne
    @JoinColumn(name="type_id", referencedColumnName="id")
    private Typeabsence typeabsence ;

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Absence() {
		super();
    }
    
	//----------------------------------------------------------------------
    // clone METHOD
    //----------------------------------------------------------------------
	@Override
	public java.lang.Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
