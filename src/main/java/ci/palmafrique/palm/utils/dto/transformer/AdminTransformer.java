

/*
 * Java transformer for entity table admin 
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
 * TRANSFORMER for table "admin"
 * 
 * @author Geo
 *
 */
@Mapper
public interface AdminTransformer {

	AdminTransformer INSTANCE = Mappers.getMapper(AdminTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
	})
	AdminDto toDto(Admin entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<AdminDto> toDtos(List<Admin> entities) throws ParseException;

    default AdminDto toLiteDto(Admin entity) {
		if (entity == null) {
			return null;
		}
		AdminDto dto = new AdminDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<AdminDto> toLiteDtos(List<Admin> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<AdminDto> dtos = new ArrayList<AdminDto>();
		for (Admin entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.typeUser", target="typeUser"),
		@Mapping(source="dto.username", target="username"),
		@Mapping(source="dto.password", target="password"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
	})
    Admin toEntity(AdminDto dto) throws ParseException;

    //List<Admin> toEntities(List<AdminDto> dtos) throws ParseException;

}
