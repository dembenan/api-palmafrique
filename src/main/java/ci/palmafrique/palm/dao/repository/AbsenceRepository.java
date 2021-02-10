package ci.palmafrique.palm.dao.repository;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ci.palmafrique.palm.utils.*;
import ci.palmafrique.palm.utils.dto.*;
import ci.palmafrique.palm.utils.contract.*;
import ci.palmafrique.palm.utils.contract.Request;
import ci.palmafrique.palm.utils.contract.Response;
import ci.palmafrique.palm.dao.entity.*;
import ci.palmafrique.palm.dao.repository.customize._AbsenceRepository;

/**
 * Repository : Absence.
 */
@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Integer>, _AbsenceRepository {
	/**
	 * Finds Absence by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Absence whose id is equals to the given id. If
	 *         no Absence is found, this method returns null.
	 */
	@Query("select e from Absence e where e.id = :id and e.isDeleted = :isDeleted")
	Absence findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Absence by using dateDepart as a search criteria.
	 * 
	 * @param dateDepart
	 * @return An Object Absence whose dateDepart is equals to the given dateDepart. If
	 *         no Absence is found, this method returns null.
	 */
	@Query("select e from Absence e where e.dateDepart = :dateDepart and e.isDeleted = :isDeleted")
	List<Absence> findByDateDepart(@Param("dateDepart")Date dateDepart, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Absence by using dateRetour as a search criteria.
	 * 
	 * @param dateRetour
	 * @return An Object Absence whose dateRetour is equals to the given dateRetour. If
	 *         no Absence is found, this method returns null.
	 */
	@Query("select e from Absence e where e.dateRetour = :dateRetour and e.isDeleted = :isDeleted")
	List<Absence> findByDateRetour(@Param("dateRetour")Date dateRetour, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Absence by using description as a search criteria.
	 * 
	 * @param description
	 * @return An Object Absence whose description is equals to the given description. If
	 *         no Absence is found, this method returns null.
	 */
	@Query("select e from Absence e where e.description = :description and e.isDeleted = :isDeleted")
	List<Absence> findByDescription(@Param("description")String description, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Absence by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Absence whose createdAt is equals to the given createdAt. If
	 *         no Absence is found, this method returns null.
	 */
	@Query("select e from Absence e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Absence> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Absence by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Absence whose updatedAt is equals to the given updatedAt. If
	 *         no Absence is found, this method returns null.
	 */
	@Query("select e from Absence e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Absence> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Absence by using status as a search criteria.
	 * 
	 * @param status
	 * @return An Object Absence whose status is equals to the given status. If
	 *         no Absence is found, this method returns null.
	 */
	@Query("select e from Absence e where e.status = :status and e.isDeleted = :isDeleted")
	List<Absence> findByStatus(@Param("status")String status, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Absence by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Absence whose createdBy is equals to the given createdBy. If
	 *         no Absence is found, this method returns null.
	 */
	@Query("select e from Absence e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Absence> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Absence by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Absence whose updatedBy is equals to the given updatedBy. If
	 *         no Absence is found, this method returns null.
	 */
	@Query("select e from Absence e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Absence> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Absence by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Absence whose isDeleted is equals to the given isDeleted. If
	 *         no Absence is found, this method returns null.
	 */
	@Query("select e from Absence e where e.isDeleted = :isDeleted")
	List<Absence> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Absence by using medecinId as a search criteria.
	 * 
	 * @param medecinId
	 * @return An Object Absence whose medecinId is equals to the given medecinId. If
	 *         no Absence is found, this method returns null.
	 */
	@Query("select e from Absence e where e.medecin.id = :medecinId and e.isDeleted = :isDeleted")
	List<Absence> findByMedecinId(@Param("medecinId")Integer medecinId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Absence by using agentId as a search criteria.
	 * 
	 * @param agentId
	 * @return An Object Absence whose agentId is equals to the given agentId. If
	 *         no Absence is found, this method returns null.
	 */
	@Query("select e from Absence e where e.agent.id = :agentId and e.isDeleted = :isDeleted")
	List<Absence> findByAgentId(@Param("agentId")Integer agentId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Absence by using typeId as a search criteria.
	 * 
	 * @param typeId
	 * @return An Object Absence whose typeId is equals to the given typeId. If
	 *         no Absence is found, this method returns null.
	 */
	@Query("select e from Absence e where e.typeabsence.id = :typeId and e.isDeleted = :isDeleted")
	List<Absence> findByTypeId(@Param("typeId")Integer typeId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Absence by using absenceDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Absence
	 * @throws DataAccessException,ParseException
	 */
	default List<Absence> getByCriteria(Request<AbsenceDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Absence e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Absence> query = em.createQuery(req, Absence.class);
		for (Map.Entry<String, java.lang.Object> entry : param.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		if (request.getIndex() != null && request.getSize() != null) {
			query.setFirstResult(request.getIndex() * request.getSize());
			query.setMaxResults(request.getSize());
		}
		return query.getResultList();
	}

	/**
	 * Finds count of Absence by using absenceDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Absence
	 * 
	 */
	default Long count(Request<AbsenceDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Absence e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by  e.id desc";
		javax.persistence.Query query = em.createQuery(req);
		for (Map.Entry<String, java.lang.Object> entry : param.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		Long count = (Long) query.getResultList().get(0);
		return count;
	}

	/**
	 * get where expression
	 * @param request
	 * @param param
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	default String getWhereExpression(Request<AbsenceDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		AbsenceDto dto = request.getData() != null ? request.getData() : new AbsenceDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (AbsenceDto elt : request.getDatas()) {
				elt.setIsDeleted(false);
				String eltReq = generateCriteria(elt, param, index, locale);
				if (request.getIsAnd() != null && request.getIsAnd()) {
					othersReq += "and (" + eltReq + ") ";
				} else {
					othersReq += "or (" + eltReq + ") ";
				}
				index++;
			}
		}
		String req = "";
		if (!mainReq.isEmpty()) {
			req += " and (" + mainReq + ") ";
		}
		req += othersReq;
		return req;
	}

	/**
	 * generate sql query for dto
	 * @param dto
	 * @param param
	 * @param index
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	default String generateCriteria(AbsenceDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (dto.getDateDepart()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("dateDepart", dto.getDateDepart(), "e.dateDepart", "Date", dto.getDateDepartParam(), param, index, locale));
			}
			if (dto.getDateRetour()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("dateRetour", dto.getDateRetour(), "e.dateRetour", "Date", dto.getDateRetourParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDescription())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("description", dto.getDescription(), "e.description", "String", dto.getDescriptionParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getStatus())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("status", dto.getStatus(), "e.status", "String", dto.getStatusParam(), param, index, locale));
			}
			if (dto.getCreatedBy()!= null && dto.getCreatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "Integer", dto.getCreatedByParam(), param, index, locale));
			}
			if (dto.getUpdatedBy()!= null && dto.getUpdatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (dto.getMedecinId()!= null && dto.getMedecinId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("medecinId", dto.getMedecinId(), "e.medecin.id", "Integer", dto.getMedecinIdParam(), param, index, locale));
			}
			if (dto.getAgentId()!= null && dto.getAgentId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("agentId", dto.getAgentId(), "e.agent.id", "Integer", dto.getAgentIdParam(), param, index, locale));
			}
			if (dto.getTypeId()!= null && dto.getTypeId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("typeId", dto.getTypeId(), "e.typeabsence.id", "Integer", dto.getTypeIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getMedecinNom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("medecinNom", dto.getMedecinNom(), "e.medecin.nom", "String", dto.getMedecinNomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getMedecinPrenom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("medecinPrenom", dto.getMedecinPrenom(), "e.medecin.prenom", "String", dto.getMedecinPrenomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getAgentNom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("agentNom", dto.getAgentNom(), "e.agent.nom", "String", dto.getAgentNomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getAgentPrenom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("agentPrenom", dto.getAgentPrenom(), "e.agent.prenom", "String", dto.getAgentPrenomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTypeabsenceLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("typeabsenceLibelle", dto.getTypeabsenceLibelle(), "e.typeabsence.libelle", "String", dto.getTypeabsenceLibelleParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
