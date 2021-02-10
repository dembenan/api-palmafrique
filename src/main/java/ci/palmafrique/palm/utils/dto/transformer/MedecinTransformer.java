

/*
 * Java transformer for entity table medecin 
 * Created on 2021-01-24 ( Time 21:50:14 )
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
 * TRANSFORMER for table "medecin"
 * 
 * @author Geo
 *
 */
@Mapper
public interface MedecinTransformer {

	MedecinTransformer INSTANCE = Mappers.getMapper(MedecinTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
	})
	MedecinDto toDto(Medecin entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<MedecinDto> toDtos(List<Medecin> entities) throws ParseException;

    default MedecinDto toLiteDto(Medecin entity) {
		if (entity == null) {
			return null;
		}
		MedecinDto dto = new MedecinDto();
		dto.setId( entity.getId() );
		dto.setNom( entity.getNom() );
		dto.setPrenom( entity.getPrenom() );
		return dto;
    }

	default List<MedecinDto> toLiteDtos(List<Medecin> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<MedecinDto> dtos = new ArrayList<MedecinDto>();
		for (Medecin entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.nom", target="nom"),
		@Mapping(source="dto.prenom", target="prenom"),
		@Mapping(source="dto.telephone", target="telephone"),
		@Mapping(source="dto.ville", target="ville"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
	})
    Medecin toEntity(MedecinDto dto) throws ParseException;

    //List<Medecin> toEntities(List<MedecinDto> dtos) throws ParseException;

}
