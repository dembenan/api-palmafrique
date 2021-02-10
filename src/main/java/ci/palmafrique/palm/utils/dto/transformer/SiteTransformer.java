

/*
 * Java transformer for entity table site 
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
 * TRANSFORMER for table "site"
 * 
 * @author Geo
 *
 */
@Mapper
public interface SiteTransformer {

	SiteTransformer INSTANCE = Mappers.getMapper(SiteTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
	})
	SiteDto toDto(Site entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<SiteDto> toDtos(List<Site> entities) throws ParseException;

    default SiteDto toLiteDto(Site entity) {
		if (entity == null) {
			return null;
		}
		SiteDto dto = new SiteDto();
		dto.setId( entity.getId() );
		dto.setNom( entity.getNom() );
		return dto;
    }

	default List<SiteDto> toLiteDtos(List<Site> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<SiteDto> dtos = new ArrayList<SiteDto>();
		for (Site entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.nom", target="nom"),
		@Mapping(source="dto.superviseur", target="superviseur"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
	})
    Site toEntity(SiteDto dto) throws ParseException;

    //List<Site> toEntities(List<SiteDto> dtos) throws ParseException;

}
