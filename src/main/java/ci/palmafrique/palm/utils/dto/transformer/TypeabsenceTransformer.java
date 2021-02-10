

/*
 * Java transformer for entity table typeabsence 
 * Created on 2021-01-24 ( Time 21:50:15 )
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
 * TRANSFORMER for table "typeabsence"
 * 
 * @author Geo
 *
 */
@Mapper
public interface TypeabsenceTransformer {

	TypeabsenceTransformer INSTANCE = Mappers.getMapper(TypeabsenceTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
	})
	TypeabsenceDto toDto(Typeabsence entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<TypeabsenceDto> toDtos(List<Typeabsence> entities) throws ParseException;

    default TypeabsenceDto toLiteDto(Typeabsence entity) {
		if (entity == null) {
			return null;
		}
		TypeabsenceDto dto = new TypeabsenceDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<TypeabsenceDto> toLiteDtos(List<Typeabsence> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<TypeabsenceDto> dtos = new ArrayList<TypeabsenceDto>();
		for (Typeabsence entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
	})
    Typeabsence toEntity(TypeabsenceDto dto) throws ParseException;

    //List<Typeabsence> toEntities(List<TypeabsenceDto> dtos) throws ParseException;

}
