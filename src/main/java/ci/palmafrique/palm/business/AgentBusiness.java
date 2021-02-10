



                                                                            																		

/*
 * Java transformer for entity table agent 
 * Created on 2021-01-24 ( Time 21:50:13 )
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
import ci.palmafrique.palm.dao.entity.Agent;
import ci.palmafrique.palm.dao.entity.Service;
import ci.palmafrique.palm.dao.entity.Site;
import ci.palmafrique.palm.dao.entity.Direction;
import ci.palmafrique.palm.dao.entity.*;
import ci.palmafrique.palm.dao.repository.*;

/**
BUSINESS for table "agent"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class AgentBusiness implements IBasicBusiness<Request<AgentDto>, Response<AgentDto>> {

	private Response<AgentDto> response;
	@Autowired
	private AgentRepository agentRepository;
//	@Autowired
//	private UserRepository userRepository;
	@Autowired
	private AbsenceRepository absenceRepository;
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private SiteRepository siteRepository;
	@Autowired
	private DirectionRepository directionRepository;
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

	public AgentBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	
	/**
	 * create Agent by using AgentDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<AgentDto> create(Request<AgentDto> request, Locale locale)  {
		log.info("----begin create Agent-----");
		
		response = new Response<AgentDto>();
		
		try {
//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.CREATE_AGENT.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}
			
			List<Agent> items = new ArrayList<Agent>();
			
			for (AgentDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("nom", dto.getNom());
				fieldsToVerify.put("prenom", dto.getPrenom());
				fieldsToVerify.put("sexe", dto.getSexe());
				fieldsToVerify.put("nombreHeure", dto.getNombreHeure());
				fieldsToVerify.put("telephone", dto.getTelephone());
				fieldsToVerify.put("age", dto.getAge());
				fieldsToVerify.put("username", dto.getUsername());
				fieldsToVerify.put("password", dto.getPassword());
				fieldsToVerify.put("siteId", dto.getSiteId());
				fieldsToVerify.put("directionId", dto.getDirectionId());
				fieldsToVerify.put("serviceId", dto.getServiceId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if agent to insert do not exist
				Agent existingEntity = null;
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("agent id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if service exist
				Service existingService = null;
				if (dto.getServiceId() != null && dto.getServiceId() > 0){
					existingService = serviceRepository.findOne(dto.getServiceId(), false);
					if (existingService == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("service serviceId -> " + dto.getServiceId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if site exist
				Site existingSite = null;
				if (dto.getSiteId() != null && dto.getSiteId() > 0){
					existingSite = siteRepository.findOne(dto.getSiteId(), false);
					if (existingSite == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("site siteId -> " + dto.getSiteId(), locale));
						response.setHasError(true);
						return response;
					}
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
				Agent entityToSave = null;
				entityToSave = AgentTransformer.INSTANCE.toEntity(dto, existingService, existingSite, existingDirection);
				entityToSave.setCreatedAt(Utilities.getCurrentDate());
				entityToSave.setCreatedBy(request.getUser());
				entityToSave.setIsDeleted(false);
				items.add(entityToSave);
			}

			if (!items.isEmpty()) {
				List<Agent> itemsSaved = null;
				// inserer les donnees en base de donnees
				itemsSaved = agentRepository.saveAll((Iterable<Agent>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("agent", locale));
					response.setHasError(true);
					return response;
				}
				List<AgentDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AgentTransformer.INSTANCE.toLiteDtos(itemsSaved) : AgentTransformer.INSTANCE.toDtos(itemsSaved);
				
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

			log.info("----end create Agent-----");
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
	 * update Agent by using AgentDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<AgentDto> update(Request<AgentDto> request, Locale locale)  {
		log.info("----begin update Agent-----");
		
		response = new Response<AgentDto>();
		
		try {
//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.UPDATE_AGENT.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Agent> items = new ArrayList<Agent>();
			
			for (AgentDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la agent existe
				Agent entityToSave = null;
				entityToSave = agentRepository.findOne(dto.getId(), false);
				if (entityToSave == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("agent id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if service exist
				if (dto.getServiceId() != null && dto.getServiceId() > 0){
					Service existingService = serviceRepository.findOne(dto.getServiceId(), false);
					if (existingService == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("service serviceId -> " + dto.getServiceId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setService(existingService);
				}
				// Verify if site exist
				if (dto.getSiteId() != null && dto.getSiteId() > 0){
					Site existingSite = siteRepository.findOne(dto.getSiteId(), false);
					if (existingSite == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("site siteId -> " + dto.getSiteId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setSite(existingSite);
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
				if (Utilities.notBlank(dto.getPrenom())) {
					entityToSave.setPrenom(dto.getPrenom());
				}
				if (Utilities.notBlank(dto.getSexe())) {
					entityToSave.setSexe(dto.getSexe());
				}
				if (Utilities.notBlank(dto.getNombreHeure())) {
					entityToSave.setNombreHeure(dto.getNombreHeure());
				}
				if (Utilities.notBlank(dto.getTelephone())) {
					entityToSave.setTelephone(dto.getTelephone());
				}
				if (Utilities.notBlank(dto.getAge())) {
					entityToSave.setAge(dto.getAge());
				}
				if (Utilities.notBlank(dto.getUsername())) {
					entityToSave.setUsername(dto.getUsername());
				}
				if (Utilities.notBlank(dto.getPassword())) {
					entityToSave.setPassword(dto.getPassword());
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
				List<Agent> itemsSaved = null;
				// maj les donnees en base
				itemsSaved = agentRepository.saveAll((Iterable<Agent>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("agent", locale));
					response.setHasError(true);
					return response;
				}
				List<AgentDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AgentTransformer.INSTANCE.toLiteDtos(itemsSaved) : AgentTransformer.INSTANCE.toDtos(itemsSaved);

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

			log.info("----end update Agent-----");
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
	 * delete Agent by using AgentDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<AgentDto> delete(Request<AgentDto> request, Locale locale)  {
		log.info("----begin delete Agent-----");
		
		response = new Response<AgentDto>();
		
		try {

//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.DELETE_AGENT.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Agent> items = new ArrayList<Agent>();
			
			for (AgentDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la agent existe
				Agent existingEntity = null;
				existingEntity = agentRepository.findOne(dto.getId(), false);
				if (existingEntity == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("agent -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// -----------------------------------------------------------------------
				// ----------- CHECK IF DATA IS USED
				// -----------------------------------------------------------------------

				// absence
				List<Absence> listOfAbsence = absenceRepository.findByAgentId(existingEntity.getId(), false);
				if (listOfAbsence != null && !listOfAbsence.isEmpty()){
					response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfAbsence.size() + ")", locale));
					response.setHasError(true);
					return response;
				}


				existingEntity.setIsDeleted(true);
				items.add(existingEntity);
			}

			if (!items.isEmpty()) {
				// supprimer les donnees en base
				agentRepository.saveAll((Iterable<Agent>) items);

				response.setHasError(false);
			}

			log.info("----end delete Agent-----");
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
	 * get Agent by using AgentDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public Response<AgentDto> getByCriteria(Request<AgentDto> request, Locale locale) {
		log.info("----begin get Agent-----");
		
		response = new Response<AgentDto>();
		
		try {
//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.VIEW_AGENT.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Agent> items = null;
			items = agentRepository.getByCriteria(request, em, locale);
			if (items != null && !items.isEmpty()) {
				List<AgentDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AgentTransformer.INSTANCE.toLiteDtos(items) : AgentTransformer.INSTANCE.toDtos(items);

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
				response.setCount(agentRepository.count(request, em, locale));
				response.setHasError(false);
			} else {
				response.setStatus(functionalError.DATA_EMPTY("agent", locale));
				response.setHasError(false);
				return response;
			}

			log.info("----end get Agent-----");
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
	 * get full AgentDto by using Agent as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private AgentDto getFullInfos(AgentDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
