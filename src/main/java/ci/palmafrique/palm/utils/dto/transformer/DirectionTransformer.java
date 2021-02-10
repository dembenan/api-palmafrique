

/*
 * Java transformer for entity table direction 
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
 * TRANSFORMER for table "direction"
 * 
 * @author Geo
 *
 */
@Mapper
public interface DirectionTransformer {

	DirectionTransformer INSTANCE = Mappers.getMapper(DirectionTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
	})
	DirectionDto toDto(Direction entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<DirectionDto> toDtos(List<Direction> entities) throws ParseException;

    default DirectionDto toLiteDto(Direction entity) {
		if (entity == null) {
			return null;
		}
		DirectionDto dto = new DirectionDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<DirectionDto> toLiteDtos(List<Direction> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<DirectionDto> dtos = new ArrayList<DirectionDto>();
		for (Direction entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.nomDirection", target="nomDirection"),
		@Mapping(source="dto.directeur", target="directeur"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
	})
    Direction toEntity(DirectionDto dto) throws ParseException;

    //List<Direction> toEntities(List<DirectionDto> dtos) throws ParseException;

}
