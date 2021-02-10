

/*
 * Java transformer for entity table service 
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
 * TRANSFORMER for table "service"
 * 
 * @author Geo
 *
 */
@Mapper
public interface ServiceTransformer {

	ServiceTransformer INSTANCE = Mappers.getMapper(ServiceTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.direction.id", target="directionId"),
	})
	ServiceDto toDto(Service entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<ServiceDto> toDtos(List<Service> entities) throws ParseException;

    default ServiceDto toLiteDto(Service entity) {
		if (entity == null) {
			return null;
		}
		ServiceDto dto = new ServiceDto();
		dto.setId( entity.getId() );
		dto.setNom( entity.getNom() );
		return dto;
    }

	default List<ServiceDto> toLiteDtos(List<Service> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<ServiceDto> dtos = new ArrayList<ServiceDto>();
		for (Service entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.nom", target="nom"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="direction", target="direction"),
	})
    Service toEntity(ServiceDto dto, Direction direction) throws ParseException;

    //List<Service> toEntities(List<ServiceDto> dtos) throws ParseException;

}
