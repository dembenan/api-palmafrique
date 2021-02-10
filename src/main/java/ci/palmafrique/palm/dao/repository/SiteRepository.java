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
import ci.palmafrique.palm.dao.repository.customize._SiteRepository;

/**
 * Repository : Site.
 */
@Repository
public interface SiteRepository extends JpaRepository<Site, Integer>, _SiteRepository {
	/**
	 * Finds Site by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Site whose id is equals to the given id. If
	 *         no Site is found, this method returns null.
	 */
	@Query("select e from Site e where e.id = :id and e.isDeleted = :isDeleted")
	Site findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Site by using nom as a search criteria.
	 * 
	 * @param nom
	 * @return An Object Site whose nom is equals to the given nom. If
	 *         no Site is found, this method returns null.
	 */
	@Query("select e from Site e where e.nom = :nom and e.isDeleted = :isDeleted")
	List<Site> findByNom(@Param("nom")String nom, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Site by using superviseur as a search criteria.
	 * 
	 * @param superviseur
	 * @return An Object Site whose superviseur is equals to the given superviseur. If
	 *         no Site is found, this method returns null.
	 */
	@Query("select e from Site e where e.superviseur = :superviseur and e.isDeleted = :isDeleted")
	List<Site> findBySuperviseur(@Param("superviseur")String superviseur, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Site by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Site whose createdAt is equals to the given createdAt. If
	 *         no Site is found, this method returns null.
	 */
	@Query("select e from Site e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Site> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Site by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Site whose updatedAt is equals to the given updatedAt. If
	 *         no Site is found, this method returns null.
	 */
	@Query("select e from Site e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Site> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Site by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Site whose createdBy is equals to the given createdBy. If
	 *         no Site is found, this method returns null.
	 */
	@Query("select e from Site e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Site> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Site by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Site whose updatedBy is equals to the given updatedBy. If
	 *         no Site is found, this method returns null.
	 */
	@Query("select e from Site e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Site> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Site by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Site whose isDeleted is equals to the given isDeleted. If
	 *         no Site is found, this method returns null.
	 */
	@Query("select e from Site e where e.isDeleted = :isDeleted")
	List<Site> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Site by using siteDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Site
	 * @throws DataAccessException,ParseException
	 */
	default List<Site> getByCriteria(Request<SiteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Site e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Site> query = em.createQuery(req, Site.class);
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
	 * Finds count of Site by using siteDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Site
	 * 
	 */
	default Long count(Request<SiteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Site e where e IS NOT NULL";
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
	default String getWhereExpression(Request<SiteDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		SiteDto dto = request.getData() != null ? request.getData() : new SiteDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (SiteDto elt : request.getDatas()) {
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
	default String generateCriteria(SiteDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getNom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nom", dto.getNom(), "e.nom", "String", dto.getNomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSuperviseur())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("superviseur", dto.getSuperviseur(), "e.superviseur", "String", dto.getSuperviseurParam(), param, index, locale));
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
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}