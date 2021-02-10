

/*
 * Java transformer for entity table absence 
 * Created on 2021-01-24 ( Time 21:50:12 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.palm.utils.dto.transformer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import ci.palmafrique.palm.utils.contract.*;
import ci.palmafrique.palm.utils.dto.*;
import ci.palmafrique.palm.dao.entity.*;


/**
 * TRANSFORMER for table "absence"
 * 
 * @author Geo
 *
 */
@Mapper
public interface AbsenceTransformer {

	AbsenceTransformer INSTANCE = Mappers.getMapper(AbsenceTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.medecin.id", target="medecinId"),
		@Mapping(source="entity.medecin.nom", target="medecinNom"),
		@Mapping(source="entity.medecin.prenom", target="medecinPrenom"),
		@Mapping(source="entity.agent.id", target="agentId"),
		@Mapping(source="entity.agent.nom", target="agentNom"),
		@Mapping(source="entity.agent.prenom", target="agentPrenom"),
		@Mapping(source="entity.typeabsence.id", target="typeId"),
		@Mapping(source="entity.typeabsence.libelle", target="typeabsenceLibelle"),
	})
	AbsenceDto toDto(Absence entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<AbsenceDto> toDtos(List<Absence> entities) throws ParseException;

    default AbsenceDto toLiteDto(Absence entity) {
		if (entity == null) {
			return null;
		}
		AbsenceDto dto = new AbsenceDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<AbsenceDto> toLiteDtos(List<Absence> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<AbsenceDto> dtos = new ArrayList<AbsenceDto>();
		for (Absence entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.dateDepart", target="dateDepart"),
		@Mapping(source="dto.dateRetour", target="dateRetour"),
		@Mapping(source="dto.description", target="description"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="medecin", target="medecin"),
		@Mapping(source="agent", target="agent"),
		@Mapping(source="typeabsence", target="typeabsence"),
	})
    Absence toEntity(AbsenceDto dto, Medecin medecin, Agent agent, Typeabsence typeabsence) throws ParseException;

    //List<Absence> toEntities(List<AbsenceDto> dtos) throws ParseException;

}
