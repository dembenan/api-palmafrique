



                                        									

/*
 * Java transformer for entity table direction 
 * Created on 2021-01-24 ( Time 21:50:14 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.palm.business;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ci.palmafrique.palm.utils.*;
import ci.palmafrique.palm.utils.dto.*;
import ci.palmafrique.palm.utils.enums.*;
import ci.palmafrique.palm.utils.contract.*;
import ci.palmafrique.palm.utils.contract.IBasicBusiness;
import ci.palmafrique.palm.utils.contract.Request;
import ci.palmafrique.palm.utils.contract.Response;
import ci.palmafrique.palm.utils.dto.transformer.*;
import ci.palmafrique.palm.dao.entity.Direction;
import ci.palmafrique.palm.dao.entity.*;
import ci.palmafrique.palm.dao.repository.*;

/**
BUSINESS for table "direction"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class DirectionBusiness implements IBasicBusiness<Request<DirectionDto>, Response<DirectionDto>> {

	private Response<DirectionDto> response;
	@Autowired
	private DirectionRepository directionRepository;
//	@Autowired
//	private UserRepository userRepository;
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private ServiceRepository serviceRepository;

//    @Autowired
//    private UserBusiness userBusiness;

	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public DirectionBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	
	/**
	 * create Direction by using DirectionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<DirectionDto> create(Request<DirectionDto> request, Locale locale)  {
		log.info("----begin create Direction-----");
		
		response = new Response<DirectionDto>();
		
		try {
//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.CREATE_DIRECTION.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}
			
			List<Direction> items = new ArrayList<Direction>();
			
			for (DirectionDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("nomDirection", dto.getNomDirection());
				fieldsToVerify.put("directeur", dto.getDirecteur());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if direction to insert do not exist
				Direction existingEntity = null;
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("direction id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				Direction entityToSave = null;
				entityToSave = DirectionTransformer.INSTANCE.toEntity(dto);
				entityToSave.setCreatedAt(Utilities.getCurrentDate());
				entityToSave.setCreatedBy(request.getUser());
				entityToSave.setIsDeleted(false);
				items.add(entityToSave);
			}

			if (!items.isEmpty()) {
				List<Direction> itemsSaved = null;
				// inserer les donnees en base de donnees
				itemsSaved = directionRepository.saveAll((Iterable<Direction>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("direction", locale));
					response.setHasError(true);
					return response;
				}
				List<DirectionDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? DirectionTransformer.INSTANCE.toLiteDtos(itemsSaved) : DirectionTransformer.INSTANCE.toDtos(itemsSaved);
				
				final int size = itemsSaved.size();
				List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
				itemsDto.parallelStream().forEach(dto -> {
					try {
						dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
					} catch (Exception e) {
						listOfError.add(e.getMessage());
						e.printStackTrace();
					}
				});
				if (Utilities.isNotEmpty(listOfError)) {
					Object[] objArray = listOfError.stream().distinct().toArray();
					throw new RuntimeException(StringUtils.join(objArray, ", "));
				}
				response.setItems(itemsDto);
				response.setHasError(false);
			}

			log.info("----end create Direction-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * update Direction by using DirectionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<DirectionDto> update(Request<DirectionDto> request, Locale locale)  {
		log.info("----begin update Direction-----");
		
		response = new Response<DirectionDto>();
		
		try {
//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.UPDATE_DIRECTION.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Direction> items = new ArrayList<Direction>();
			
			for (DirectionDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la direction existe
				Direction entityToSave = null;
				entityToSave = directionRepository.findOne(dto.getId(), false);
				if (entityToSave == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("direction id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				if (Utilities.notBlank(dto.getNomDirection())) {
					entityToSave.setNomDirection(dto.getNomDirection());
				}
				if (Utilities.notBlank(dto.getDirecteur())) {
					entityToSave.setDirecteur(dto.getDirecteur());
				}
				if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
					entityToSave.setCreatedBy(dto.getCreatedBy());
				}
				if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
					entityToSave.setUpdatedBy(dto.getUpdatedBy());
				}
				entityToSave.setUpdatedAt(Utilities.getCurrentDate());
				entityToSave.setUpdatedBy(request.getUser());
				items.add(entityToSave);
			}

			if (!items.isEmpty()) {
				List<Direction> itemsSaved = null;
				// maj les donnees en base
				itemsSaved = directionRepository.saveAll((Iterable<Direction>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("direction", locale));
					response.setHasError(true);
					return response;
				}
				List<DirectionDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? DirectionTransformer.INSTANCE.toLiteDtos(itemsSaved) : DirectionTransformer.INSTANCE.toDtos(itemsSaved);

				final int size = itemsSaved.size();
				List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
				itemsDto.parallelStream().forEach(dto -> {
					try {
						dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
					} catch (Exception e) {
						listOfError.add(e.getMessage());
						e.printStackTrace();
					}
				});
				if (Utilities.isNotEmpty(listOfError)) {
					Object[] objArray = listOfError.stream().distinct().toArray();
					throw new RuntimeException(StringUtils.join(objArray, ", "));
				}
				response.setItems(itemsDto);
				response.setHasError(false);
			}

			log.info("----end update Direction-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * delete Direction by using DirectionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<DirectionDto> delete(Request<DirectionDto> request, Locale locale)  {
		log.info("----begin delete Direction-----");
		
		response = new Response<DirectionDto>();
		
		try {

//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.DELETE_DIRECTION.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Direction> items = new ArrayList<Direction>();
			
			for (DirectionDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la direction existe
				Direction existingEntity = null;
				existingEntity = directionRepository.findOne(dto.getId(), false);
				if (existingEntity == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("direction -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// -----------------------------------------------------------------------
				// ----------- CHECK IF DATA IS USED
				// -----------------------------------------------------------------------

				// agent
				List<Agent> listOfAgent = agentRepository.findByDirectionId(existingEntity.getId(), false);
				if (listOfAgent != null && !listOfAgent.isEmpty()){
					response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfAgent.size() + ")", locale));
					response.setHasError(true);
					return response;
				}
				// service
				List<Service> listOfService = serviceRepository.findByDirectionId(existingEntity.getId(), false);
				if (listOfService != null && !listOfService.isEmpty()){
					response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfService.size() + ")", locale));
					response.setHasError(true);
					return response;
				}


				existingEntity.setIsDeleted(true);
				items.add(existingEntity);
			}

			if (!items.isEmpty()) {
				// supprimer les donnees en base
				directionRepository.saveAll((Iterable<Direction>) items);

				response.setHasError(false);
			}

			log.info("----end delete Direction-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * get Direction by using DirectionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public Response<DirectionDto> getByCriteria(Request<DirectionDto> request, Locale locale) {
		log.info("----begin get Direction-----");
		
		response = new Response<DirectionDto>();
		
		try {
//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.VIEW_DIRECTION.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Direction> items = null;
			items = directionRepository.getByCriteria(request, em, locale);
			if (items != null && !items.isEmpty()) {
				List<DirectionDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? DirectionTransformer.INSTANCE.toLiteDtos(items) : DirectionTransformer.INSTANCE.toDtos(items);

				final int size = items.size();
				List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
				itemsDto.parallelStream().forEach(dto -> {
					try {
						dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
					} catch (Exception e) {
						listOfError.add(e.getMessage());
						e.printStackTrace();
					}
				});
				if (Utilities.isNotEmpty(listOfError)) {
					Object[] objArray = listOfError.stream().distinct().toArray();
					throw new RuntimeException(StringUtils.join(objArray, ", "));
				}
				response.setItems(itemsDto);
				response.setCount(directionRepository.count(request, em, locale));
				response.setHasError(false);
			} else {
				response.setStatus(functionalError.DATA_EMPTY("direction", locale));
				response.setHasError(false);
				return response;
			}

			log.info("----end get Direction-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * get full DirectionDto by using Direction as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private DirectionDto getFullInfos(DirectionDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}

}
