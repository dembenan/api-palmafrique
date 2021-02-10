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
import ci.palmafrique.palm.dao.repository.customize._MedecinRepository;

/**
 * Repository : Medecin.
 */
@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Integer>, _MedecinRepository {
	/**
	 * Finds Medecin by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Medecin whose id is equals to the given id. If
	 *         no Medecin is found, this method returns null.
	 */
	@Query("select e from Medecin e where e.id = :id and e.isDeleted = :isDeleted")
	Medecin findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Medecin by using nom as a search criteria.
	 * 
	 * @param nom
	 * @return An Object Medecin whose nom is equals to the given nom. If
	 *         no Medecin is found, this method returns null.
	 */
	@Query("select e from Medecin e where e.nom = :nom and e.isDeleted = :isDeleted")
	List<Medecin> findByNom(@Param("nom")String nom, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Medecin by using prenom as a search criteria.
	 * 
	 * @param prenom
	 * @return An Object Medecin whose prenom is equals to the given prenom. If
	 *         no Medecin is found, this method returns null.
	 */
	@Query("select e from Medecin e where e.prenom = :prenom and e.isDeleted = :isDeleted")
	List<Medecin> findByPrenom(@Param("prenom")String prenom, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Medecin by using telephone as a search criteria.
	 * 
	 * @param telephone
	 * @return An Object Medecin whose telephone is equals to the given telephone. If
	 *         no Medecin is found, this method returns null.
	 */
	@Query("select e from Medecin e where e.telephone = :telephone and e.isDeleted = :isDeleted")
	List<Medecin> findByTelephone(@Param("telephone")String telephone, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Medecin by using ville as a search criteria.
	 * 
	 * @param ville
	 * @return An Object Medecin whose ville is equals to the given ville. If
	 *         no Medecin is found, this method returns null.
	 */
	@Query("select e from Medecin e where e.ville = :ville and e.isDeleted = :isDeleted")
	List<Medecin> findByVille(@Param("ville")String ville, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Medecin by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Medecin whose createdAt is equals to the given createdAt. If
	 *         no Medecin is found, this method returns null.
	 */
	@Query("select e from Medecin e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Medecin> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Medecin by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Medecin whose updatedAt is equals to the given updatedAt. If
	 *         no Medecin is found, this method returns null.
	 */
	@Query("select e from Medecin e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Medecin> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Medecin by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Medecin whose createdBy is equals to the given createdBy. If
	 *         no Medecin is found, this method returns null.
	 */
	@Query("select e from Medecin e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Medecin> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Medecin by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Medecin whose updatedBy is equals to the given updatedBy. If
	 *         no Medecin is found, this method returns null.
	 */
	@Query("select e from Medecin e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Medecin> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Medecin by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Medecin whose isDeleted is equals to the given isDeleted. If
	 *         no Medecin is found, this method returns null.
	 */
	@Query("select e from Medecin e where e.isDeleted = :isDeleted")
	List<Medecin> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Medecin by using medecinDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Medecin
	 * @throws DataAccessException,ParseException
	 */
	default List<Medecin> getByCriteria(Request<MedecinDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Medecin e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Medecin> query = em.createQuery(req, Medecin.class);
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
	 * Finds count of Medecin by using medecinDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Medecin
	 * 
	 */
	default Long count(Request<MedecinDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Medecin e where e IS NOT NULL";
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
	default String getWhereExpression(Request<MedecinDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		MedecinDto dto = request.getData() != null ? request.getData() : new MedecinDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (MedecinDto elt : request.getDatas()) {
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
	default String generateCriteria(MedecinDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
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
			if (Utilities.notBlank(dto.getTelephone())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("telephone", dto.getTelephone(), "e.telephone", "String", dto.getTelephoneParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getVille())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ville", dto.getVille(), "e.ville", "String", dto.getVilleParam(), param, index, locale));
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
