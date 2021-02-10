



                                                            														

/*
 * Java transformer for entity table absence 
 * Created on 2021-01-24 ( Time 21:50:12 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.palm.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ci.palmafrique.palm.dao.entity.Absence;
import ci.palmafrique.palm.dao.entity.*;
import ci.palmafrique.palm.dao.repository.AbsenceRepository;
import ci.palmafrique.palm.dao.repository.AgentRepository;
import ci.palmafrique.palm.dao.repository.MedecinRepository;
import ci.palmafrique.palm.dao.repository.TypeabsenceRepository;
import ci.palmafrique.palm.utils.*;
import ci.palmafrique.palm.utils.contract.IBasicBusiness;
import ci.palmafrique.palm.utils.contract.*;
import ci.palmafrique.palm.utils.dto.*;
import ci.palmafrique.palm.utils.dto.transformer.*;
import lombok.extern.java.Log;

/**
BUSINESS for table "absence"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class AbsenceBusiness implements IBasicBusiness<Request<AbsenceDto>, Response<AbsenceDto>> {

	private Response<AbsenceDto> response;
	@Autowired
	private AbsenceRepository absenceRepository;
//	@Autowired
//	private UserRepository userRepository;
	@Autowired
	private MedecinRepository medecinRepository;
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private TypeabsenceRepository typeabsenceRepository;

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
	

    @Autowired
	@Qualifier("mysqlJdbcTemplate")
	private JdbcTemplate mysqlTemplate;

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public AbsenceBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	
	/**
	 * create Absence by using AbsenceDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<AbsenceDto> create(Request<AbsenceDto> request, Locale locale)  {
		log.info("----begin create Absence-----");
		
		response = new Response<AbsenceDto>();
		
		try {
//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.CREATE_ABSENCE.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}
			
			List<Absence> items = new ArrayList<Absence>();
			
			for (AbsenceDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("dateDepart", dto.getDateDepart());
				fieldsToVerify.put("dateRetour", dto.getDateRetour());
				fieldsToVerify.put("description", dto.getDescription());
				fieldsToVerify.put("agentId", dto.getAgentId());
				fieldsToVerify.put("typeId", dto.getTypeId());
				fieldsToVerify.put("medecinId", dto.getMedecinId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if absence to insert do not exist
				Absence existingEntity = null;
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("absence id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if medecin exist
				Medecin existingMedecin = null;
				if (dto.getMedecinId() != null && dto.getMedecinId() > 0){
					existingMedecin = medecinRepository.findOne(dto.getMedecinId(), false);
					if (existingMedecin == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("medecin medecinId -> " + dto.getMedecinId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if agent exist
				Agent existingAgent = null;
				if (dto.getAgentId() != null && dto.getAgentId() > 0){
					existingAgent = agentRepository.findOne(dto.getAgentId(), false);
					if (existingAgent == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("agent agentId -> " + dto.getAgentId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if typeabsence exist
				Typeabsence existingTypeabsence = null;
				if (dto.getTypeId() != null && dto.getTypeId() > 0){
					existingTypeabsence = typeabsenceRepository.findOne(dto.getTypeId(), false);
					if (existingTypeabsence == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("typeabsence typeId -> " + dto.getTypeId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				Absence entityToSave = null;
				entityToSave = AbsenceTransformer.INSTANCE.toEntity(dto, existingMedecin, existingAgent, existingTypeabsence);
				entityToSave.setCreatedAt(Utilities.getCurrentDate());
				entityToSave.setCreatedBy(request.getUser());
				entityToSave.setIsDeleted(false);
				items.add(entityToSave);
			}

			if (!items.isEmpty()) {
				List<Absence> itemsSaved = null;
				// inserer les donnees en base de donnees
				itemsSaved = absenceRepository.saveAll((Iterable<Absence>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("absence", locale));
					response.setHasError(true);
					return response;
				}
				List<AbsenceDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AbsenceTransformer.INSTANCE.toLiteDtos(itemsSaved) : AbsenceTransformer.INSTANCE.toDtos(itemsSaved);
				
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

			log.info("----end create Absence-----");
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
	 * update Absence by using AbsenceDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<AbsenceDto> update(Request<AbsenceDto> request, Locale locale)  {
		log.info("----begin update Absence-----");
		
		response = new Response<AbsenceDto>();
		
		try {
//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.UPDATE_ABSENCE.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Absence> items = new ArrayList<Absence>();
			
			for (AbsenceDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la absence existe
				Absence entityToSave = null;
				entityToSave = absenceRepository.findOne(dto.getId(), false);
				if (entityToSave == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("absence id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if medecin exist
				if (dto.getMedecinId() != null && dto.getMedecinId() > 0){
					Medecin existingMedecin = medecinRepository.findOne(dto.getMedecinId(), false);
					if (existingMedecin == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("medecin medecinId -> " + dto.getMedecinId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setMedecin(existingMedecin);
				}
				// Verify if agent exist
				if (dto.getAgentId() != null && dto.getAgentId() > 0){
					Agent existingAgent = agentRepository.findOne(dto.getAgentId(), false);
					if (existingAgent == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("agent agentId -> " + dto.getAgentId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setAgent(existingAgent);
				}
				// Verify if typeabsence exist
				if (dto.getTypeId() != null && dto.getTypeId() > 0){
					Typeabsence existingTypeabsence = typeabsenceRepository.findOne(dto.getTypeId(), false);
					if (existingTypeabsence == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("typeabsence typeId -> " + dto.getTypeId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setTypeabsence(existingTypeabsence);
				}
				if (dto.getDateDepart() != null) {
					entityToSave.setDateDepart(dto.getDateDepart());
				}
				if (dto.getDateRetour() != null) {
					entityToSave.setDateRetour(dto.getDateRetour());
				}
				if (Utilities.notBlank(dto.getDescription())) {
					entityToSave.setDescription(dto.getDescription());
				}
				if (Utilities.notBlank(dto.getStatus())) {
					entityToSave.setStatus(dto.getStatus());
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
				List<Absence> itemsSaved = null;
				// maj les donnees en base
				itemsSaved = absenceRepository.saveAll((Iterable<Absence>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("absence", locale));
					response.setHasError(true);
					return response;
				}
				List<AbsenceDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AbsenceTransformer.INSTANCE.toLiteDtos(itemsSaved) : AbsenceTransformer.INSTANCE.toDtos(itemsSaved);

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

			log.info("----end update Absence-----");
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
	 * delete Absence by using AbsenceDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<AbsenceDto> delete(Request<AbsenceDto> request, Locale locale)  {
		log.info("----begin delete Absence-----");
		
		response = new Response<AbsenceDto>();
		
		try {

//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.DELETE_ABSENCE.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Absence> items = new ArrayList<Absence>();
			
			for (AbsenceDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la absence existe
				Absence existingEntity = null;
				existingEntity = absenceRepository.findOne(dto.getId(), false);
				if (existingEntity == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("absence -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// -----------------------------------------------------------------------
				// ----------- CHECK IF DATA IS USED
				// -----------------------------------------------------------------------



				existingEntity.setIsDeleted(true);
				items.add(existingEntity);
			}

			if (!items.isEmpty()) {
				// supprimer les donnees en base
				absenceRepository.saveAll((Iterable<Absence>) items);

				response.setHasError(false);
			}

			log.info("----end delete Absence-----");
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
	 * get Absence by using AbsenceDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public Response<AbsenceDto> getByCriteria(Request<AbsenceDto> request, Locale locale) {
		log.info("----begin get Absence-----");
		
		response = new Response<AbsenceDto>();
		
		try {
//			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
//			fieldsToVerifyUser.put("user", request.getUser());
//			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			Response<UserDto> userResponse = userBusiness.isGranted(request.getUser(), FunctionalityEnum.VIEW_ABSENCE.getValue(), locale);
//			if (userResponse.isHasError()) {
//				response.setHasError(true);
//				response.setStatus(userResponse.getStatus());
//				return response;
//			}

			List<Absence> items = null;
			items = absenceRepository.getByCriteria(request, em, locale);
			if (items != null && !items.isEmpty()) {
				List<AbsenceDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AbsenceTransformer.INSTANCE.toLiteDtos(items) : AbsenceTransformer.INSTANCE.toDtos(items);

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
				response.setCount(absenceRepository.count(request, em, locale));
				response.setHasError(false);
			} else {
				response.setStatus(functionalError.DATA_EMPTY("absence", locale));
				response.setHasError(false);
				return response;
			}

			log.info("----end get Absence-----");
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
	
//	/**Calcul de absence
//	  *  Classe et pour un
//	  *  Eleve specifique*/
//	 @SuppressWarnings("unused")
//	 @Transactional(rollbackFor = { RuntimeException.class, Exception.class })
//	 public Response<AbsenceDto> absenceListUtil(Request<AbsenceDto> request, Locale locale) {
//	  log.info("----begin absenceListUtil-----");
//
//	  response = new Response<AbsenceDto>();
//	  try {
//	   List<Map<String, Object>> map = new ArrayList<>();
//	   List<AbsenceDto> items = new ArrayList<AbsenceDto>();
//
//	   for (AbsenceDto dto : request.getDatas()) {
//	
//
////	    String query =" SELECT absence.date_depart AS depart ,absence.date_retour AS retour ,absence.description AS description ,absence.agent_id,agent.nom AS agentNom ,absence.type_id,typeabsence.libelle AS type ,absence.medecin_id,medecin.nom AS medecinNom FROM absence,agent,typeabsence,medecin "
////	    		+ " WHERE agent_id = agent.id "
////	    		+ " AND type_id = typeabsence.id "
////	    		+ " AND medecin_id = medecin.id " ;
//		   
//		   String query ="SELECT COUNT(*) AS nombreNote FROM absence ";
//
//
//		  
//		     
//
//	    map = mysqlTemplate.queryForList(query);
//
//	    for (Map<String, Object> m : map) {
//	    AbsenceDto AbsenceDto = new AbsenceDto();
//	    
//	     AbsenceDto.setNombreNote(m.get("nombreNote").toString());
//
////	    AbsenceDto.setDateDepart((Date) m.get("depart"));
////	    AbsenceDto.setDateRetour((Date) m.get("retour"));
////	    AbsenceDto.setDescription(m.get("description").toString());
////	    AbsenceDto.setAgentNom(m.get("agentNom").toString());
////	    AbsenceDto.setLibelleType(m.get("type").toString());
////	    AbsenceDto.setMedecinNom(m.get("medecinNom").toString());
////	    
//	   
//	     items.add(AbsenceDto);
//	    }
//	    response.setStatus(functionalError.SUCCESS("list absence par infos site direction service", locale));
//	    response.setItems(items);
//	    response.setHasError(false);
//
//	    
//	   }
//
//	   log.info("----end list util----");
//	  } catch (PermissionDeniedDataAccessException e) {
//	   exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
//	  } catch (DataAccessResourceFailureException e) {
//	   exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
//	  } catch (DataAccessException e) {
//	   exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
//	  } catch (RuntimeException e) {
//	   exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
//	  } catch (Exception e) {
//	   exceptionUtils.EXCEPTION(response, locale, e);
//	  } finally {
//	   if (response.isHasError() && response.getStatus() != null) {
//	    log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
//	      response.getStatus().getMessage()));
//	    throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
//	   }
//	  }
//	  return response;
//
//	 }
//	
	

	/**
	 * get full AbsenceDto by using Absence as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private AbsenceDto getFullInfos(AbsenceDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
