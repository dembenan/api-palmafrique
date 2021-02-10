

/*
 * Java transformer for entity table agent 
 * Created on 2021-01-24 ( Time 21:50:13 )
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
 * TRANSFORMER for table "agent"
 * 
 * @author Geo
 *
 */
@Mapper
public interface AgentTransformer {

	AgentTransformer INSTANCE = Mappers.getMapper(AgentTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.service.id", target="serviceId"),
		@Mapping(source="entity.service.nom", target="serviceNom"),
		@Mapping(source="entity.site.id", target="siteId"),
		@Mapping(source="entity.site.nom", target="siteNom"),
		@Mapping(source="entity.direction.id", target="directionId"),
	})
	AgentDto toDto(Agent entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<AgentDto> toDtos(List<Agent> entities) throws ParseException;

    default AgentDto toLiteDto(Agent entity) {
		if (entity == null) {
			return null;
		}
		AgentDto dto = new AgentDto();
		dto.setId( entity.getId() );
		dto.setNom( entity.getNom() );
		dto.setPrenom( entity.getPrenom() );
		return dto;
    }

	default List<AgentDto> toLiteDtos(List<Agent> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<AgentDto> dtos = new ArrayList<AgentDto>();
		for (Agent entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.nom", target="nom"),
		@Mapping(source="dto.prenom", target="prenom"),
		@Mapping(source="dto.sexe", target="sexe"),
		@Mapping(source="dto.nombreHeure", target="nombreHeure"),
		@Mapping(source="dto.telephone", target="telephone"),
		@Mapping(source="dto.age", target="age"),
		@Mapping(source="dto.username", target="username"),
		@Mapping(source="dto.password", target="password"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="service", target="service"),
		@Mapping(source="site", target="site"),
		@Mapping(source="direction", target="direction"),
	})
    Agent toEntity(AgentDto dto, Service service, Site site, Direction direction) throws ParseException;

    //List<Agent> toEntities(List<AgentDto> dtos) throws ParseException;

}
