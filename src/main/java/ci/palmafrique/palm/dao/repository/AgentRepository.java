package ci.palmafrique.palm.dao.repository;

import java.util.Date;
import java.util.List;
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
import ci.palmafrique.palm.dao.repository.customize._AgentRepository;

/**
 * Repository : Agent.
 */
@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer>, _AgentRepository {
	/**
	 * Finds Agent by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Agent whose id is equals to the given id. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.id = :id and e.isDeleted = :isDeleted")
	Agent findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Agent by using nom as a search criteria.
	 * 
	 * @param nom
	 * @return An Object Agent whose nom is equals to the given nom. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.nom = :nom and e.isDeleted = :isDeleted")
	List<Agent> findByNom(@Param("nom")String nom, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Agent by using prenom as a search criteria.
	 * 
	 * @param prenom
	 * @return An Object Agent whose prenom is equals to the given prenom. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.prenom = :prenom and e.isDeleted = :isDeleted")
	List<Agent> findByPrenom(@Param("prenom")String prenom, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Agent by using sexe as a search criteria.
	 * 
	 * @param sexe
	 * @return An Object Agent whose sexe is equals to the given sexe. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.sexe = :sexe and e.isDeleted = :isDeleted")
	List<Agent> findBySexe(@Param("sexe")String sexe, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Agent by using nombreHeure as a search criteria.
	 * 
	 * @param nombreHeure
	 * @return An Object Agent whose nombreHeure is equals to the given nombreHeure. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.nombreHeure = :nombreHeure and e.isDeleted = :isDeleted")
	List<Agent> findByNombreHeure(@Param("nombreHeure")String nombreHeure, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Agent by using telephone as a search criteria.
	 * 
	 * @param telephone
	 * @return An Object Agent whose telephone is equals to the given telephone. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.telephone = :telephone and e.isDeleted = :isDeleted")
	List<Agent> findByTelephone(@Param("telephone")String telephone, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Agent by using age as a search criteria.
	 * 
	 * @param age
	 * @return An Object Agent whose age is equals to the given age. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.age = :age and e.isDeleted = :isDeleted")
	List<Agent> findByAge(@Param("age")String age, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Agent by using username as a search criteria.
	 * 
	 * @param username
	 * @return An Object Agent whose username is equals to the given username. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.username = :username and e.isDeleted = :isDeleted")
	List<Agent> findByUsername(@Param("username")String username, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Agent by using password as a search criteria.
	 * 
	 * @param password
	 * @return An Object Agent whose password is equals to the given password. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.password = :password and e.isDeleted = :isDeleted")
	List<Agent> findByPassword(@Param("password")String password, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Agent by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Agent whose createdAt is equals to the given createdAt. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Agent> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Agent by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Agent whose updatedAt is equals to the given updatedAt. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Agent> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Agent by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Agent whose createdBy is equals to the given createdBy. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Agent> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Agent by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Agent whose updatedBy is equals to the given updatedBy. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Agent> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Agent by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Agent whose isDeleted is equals to the given isDeleted. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.isDeleted = :isDeleted")
	List<Agent> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Agent by using serviceId as a search criteria.
	 * 
	 * @param serviceId
	 * @return An Object Agent whose serviceId is equals to the given serviceId. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.service.id = :serviceId and e.isDeleted = :isDeleted")
	List<Agent> findByServiceId(@Param("serviceId")Integer serviceId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Agent by using siteId as a search criteria.
	 * 
	 * @param siteId
	 * @return An Object Agent whose siteId is equals to the given siteId. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.site.id = :siteId and e.isDeleted = :isDeleted")
	List<Agent> findBySiteId(@Param("siteId")Integer siteId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Agent by using directionId as a search criteria.
	 * 
	 * @param directionId
	 * @return An Object Agent whose directionId is equals to the given directionId. If
	 *         no Agent is found, this method returns null.
	 */
	@Query("select e from Agent e where e.direction.id = :directionId and e.isDeleted = :isDeleted")
	List<Agent> findByDirectionId(@Param("directionId")Integer directionId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Agent by using agentDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Agent
	 * @throws DataAccessException,ParseException
	 */
	default List<Agent> getByCriteria(Request<AgentDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Agent e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Agent> query = em.createQuery(req, Agent.class);
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
	 * Finds count of Agent by using agentDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Agent
	 * 
	 */
	default Long count(Request<AgentDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Agent e where e IS NOT NULL";
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
	default String getWhereExpression(Request<AgentDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		AgentDto dto = request.getData() != null ? request.getData() : new AgentDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (AgentDto elt : request.getDatas()) {
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
	default String generateCriteria(AgentDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getNom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nom", dto.getNom(), "e.nom", "String", dto.getNomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPrenom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("prenom", dto.getPrenom(), "e.prenom", "String", dto.getPrenomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSexe())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("sexe", dto.getSexe(), "e.sexe", "String", dto.getSexeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getNombreHeure())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nombreHeure", dto.getNombreHeure(), "e.nombreHeure", "String", dto.getNombreHeureParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTelephone())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("telephone", dto.getTelephone(), "e.telephone", "String", dto.getTelephoneParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getAge())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("age", dto.getAge(), "e.age", "String", dto.getAgeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUsername())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("username", dto.getUsername(), "e.username", "String", dto.getUsernameParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPassword())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("password", dto.getPassword(), "e.password", "String", dto.getPasswordParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
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
			if (dto.getServiceId()!= null && dto.getServiceId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("serviceId", dto.getServiceId(), "e.service.id", "Integer", dto.getServiceIdParam(), param, index, locale));
			}
			if (dto.getSiteId()!= null && dto.getSiteId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("siteId", dto.getSiteId(), "e.site.id", "Integer", dto.getSiteIdParam(), param, index, locale));
			}
			if (dto.getDirectionId()!= null && dto.getDirectionId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("directionId", dto.getDirectionId(), "e.direction.id", "Integer", dto.getDirectionIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getServiceNom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("serviceNom", dto.getServiceNom(), "e.service.nom", "String", dto.getServiceNomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSiteNom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("siteNom", dto.getSiteNom(), "e.site.nom", "String", dto.getSiteNomParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
