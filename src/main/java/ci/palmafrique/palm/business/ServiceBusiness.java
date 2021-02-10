



                                        									

/*
 * Java transformer for entity table service 
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
import ci.palmafrique.palm.dao.entity.Service;
import ci.palmafrique.palm.dao.entity.Direction;
import ci.palmafrique.palm.dao.entity.*;
import ci.palmafrique.palm.dao.repository.*;

/**
BUSINESS for table "service"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class ServiceBusiness implements IBasicBusiness<Request<ServiceDto>, Response<ServiceDto>> {

	private Response<ServiceDto> response;
	@Autowired
	private ServiceRepository serviceRepository;
//	@Autowired
//	private UserRepository userRepository;
	@Autowired
	private DirectionRepository directionRepository;
	@Autowired
	private AgentRepository agentRepository;
//
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

	public ServiceBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	
	/**
	 * create Service by using ServiceDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<ServiceDto> create(Request<ServiceDto> request, Locale locale)  {
		log.info("----begin create Service-----");
		
		response = new Response<ServiceDto>();
		
		try {
//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.CREATE_SERVICE.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}
			
			List<Service> items = new ArrayList<Service>();
			
			for (ServiceDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("nom", dto.getNom());
				fieldsToVerify.put("directionId", dto.getDirectionId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if service to insert do not exist
				Service existingEntity = null;
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("service id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if direction exist
				Direction existingDirection = null;
				if (dto.getDirectionId() != null && dto.getDirectionId() > 0){
					existingDirection = directionRepository.findOne(dto.getDirectionId(), false);
					if (existingDirection == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("direction directionId -> " + dto.getDirectionId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				Service entityToSave = null;
				entityToSave = ServiceTransformer.INSTANCE.toEntity(dto, existingDirection);
				entityToSave.setCreatedAt(Utilities.getCurrentDate());
				entityToSave.setCreatedBy(request.getUser());
				entityToSave.setIsDeleted(false);
				items.add(entityToSave);
			}

			if (!items.isEmpty()) {
				List<Service> itemsSaved = null;
				// inserer les donnees en base de donnees
				itemsSaved = serviceRepository.saveAll((Iterable<Service>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("service", locale));
					response.setHasError(true);
					return response;
				}
				List<ServiceDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ServiceTransformer.INSTANCE.toLiteDtos(itemsSaved) : ServiceTransformer.INSTANCE.toDtos(itemsSaved);
				
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

			log.info("----end create Service-----");
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
	 * update Service by using ServiceDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<ServiceDto> update(Request<ServiceDto> request, Locale locale)  {
		log.info("----begin update Service-----");
		
		response = new Response<ServiceDto>();
		
		try {
//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.UPDATE_SERVICE.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Service> items = new ArrayList<Service>();
			
			for (ServiceDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la service existe
				Service entityToSave = null;
				entityToSave = serviceRepository.findOne(dto.getId(), false);
				if (entityToSave == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("service id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if direction exist
				if (dto.getDirectionId() != null && dto.getDirectionId() > 0){
					Direction existingDirection = directionRepository.findOne(dto.getDirectionId(), false);
					if (existingDirection == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("direction directionId -> " + dto.getDirectionId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setDirection(existingDirection);
				}
				if (Utilities.notBlank(dto.getNom())) {
					entityToSave.setNom(dto.getNom());
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
				List<Service> itemsSaved = null;
				// maj les donnees en base
				itemsSaved = serviceRepository.saveAll((Iterable<Service>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("service", locale));
					response.setHasError(true);
					return response;
				}
				List<ServiceDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ServiceTransformer.INSTANCE.toLiteDtos(itemsSaved) : ServiceTransformer.INSTANCE.toDtos(itemsSaved);

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

			log.info("----end update Service-----");
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
	 * delete Service by using ServiceDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<ServiceDto> delete(Request<ServiceDto> request, Locale locale)  {
		log.info("----begin delete Service-----");
		
		response = new Response<ServiceDto>();
		
		try {

//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.DELETE_SERVICE.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Service> items = new ArrayList<Service>();
			
			for (ServiceDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la service existe
				Service existingEntity = null;
				existingEntity = serviceRepository.findOne(dto.getId(), false);
				if (existingEntity == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("service -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// -----------------------------------------------------------------------
				// ----------- CHECK IF DATA IS USED
				// -----------------------------------------------------------------------

				// agent
				List<Agent> listOfAgent = agentRepository.findByServiceId(existingEntity.getId(), false);
				if (listOfAgent != null && !listOfAgent.isEmpty()){
					response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfAgent.size() + ")", locale));
					response.setHasError(true);
					return response;
				}


				existingEntity.setIsDeleted(true);
				items.add(existingEntity);
			}

			if (!items.isEmpty()) {
				// supprimer les donnees en base
				serviceRepository.saveAll((Iterable<Service>) items);

				response.setHasError(false);
			}

			log.info("----end delete Service-----");
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
	 * get Service by using ServiceDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public Response<ServiceDto> getByCriteria(Request<ServiceDto> request, Locale locale) {
		log.info("----begin get Service-----");
		
		response = new Response<ServiceDto>();
		
		try {
//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.VIEW_SERVICE.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Service> items = null;
			items = serviceRepository.getByCriteria(request, em, locale);
			if (items != null && !items.isEmpty()) {
				List<ServiceDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ServiceTransformer.INSTANCE.toLiteDtos(items) : ServiceTransformer.INSTANCE.toDtos(items);

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
				response.setCount(serviceRepository.count(request, em, locale));
				response.setHasError(false);
			} else {
				response.setStatus(functionalError.DATA_EMPTY("service", locale));
				response.setHasError(false);
				return response;
			}

			log.info("----end get Service-----");
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
	 * get full ServiceDto by using Service as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ServiceDto getFullInfos(ServiceDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
