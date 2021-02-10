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
import ci.palmafrique.palm.dao.repository.customize._AdminRepository;

/**
 * Repository : Admin.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>, _AdminRepository {
	/**
	 * Finds Admin by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Admin whose id is equals to the given id. If
	 *         no Admin is found, this method returns null.
	 */
	@Query("select e from Admin e where e.id = :id and e.isDeleted = :isDeleted")
	Admin findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Admin by using typeUser as a search criteria.
	 * 
	 * @param typeUser
	 * @return An Object Admin whose typeUser is equals to the given typeUser. If
	 *         no Admin is found, this method returns null.
	 */
	@Query("select e from Admin e where e.typeUser = :typeUser and e.isDeleted = :isDeleted")
	List<Admin> findByTypeUser(@Param("typeUser")String typeUser, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Admin by using username as a search criteria.
	 * 
	 * @param username
	 * @return An Object Admin whose username is equals to the given username. If
	 *         no Admin is found, this method returns null.
	 */
	@Query("select e from Admin e where e.username = :username and e.isDeleted = :isDeleted")
	List<Admin> findByUsername(@Param("username")String username, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Admin by using password as a search criteria.
	 * 
	 * @param password
	 * @return An Object Admin whose password is equals to the given password. If
	 *         no Admin is found, this method returns null.
	 */
	@Query("select e from Admin e where e.password = :password and e.isDeleted = :isDeleted")
	List<Admin> findByPassword(@Param("password")String password, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Admin by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Admin whose createdAt is equals to the given createdAt. If
	 *         no Admin is found, this method returns null.
	 */
	@Query("select e from Admin e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Admin> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Admin by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Admin whose updatedAt is equals to the given updatedAt. If
	 *         no Admin is found, this method returns null.
	 */
	@Query("select e from Admin e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Admin> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Admin by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Admin whose createdBy is equals to the given createdBy. If
	 *         no Admin is found, this method returns null.
	 */
	@Query("select e from Admin e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Admin> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Admin by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Admin whose updatedBy is equals to the given updatedBy. If
	 *         no Admin is found, this method returns null.
	 */
	@Query("select e from Admin e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Admin> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Admin by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Admin whose isDeleted is equals to the given isDeleted. If
	 *         no Admin is found, this method returns null.
	 */
	@Query("select e from Admin e where e.isDeleted = :isDeleted")
	List<Admin> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Admin by using adminDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Admin
	 * @throws DataAccessException,ParseException
	 */
	default List<Admin> getByCriteria(Request<AdminDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Admin e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Admin> query = em.createQuery(req, Admin.class);
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
	 * Finds count of Admin by using adminDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Admin
	 * 
	 */
	default Long count(Request<AdminDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Admin e where e IS NOT NULL";
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
	default String getWhereExpression(Request<AdminDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		AdminDto dto = request.getData() != null ? request.getData() : new AdminDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (AdminDto elt : request.getDatas()) {
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
	default String generateCriteria(AdminDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTypeUser())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("typeUser", dto.getTypeUser(), "e.typeUser", "String", dto.getTypeUserParam(), param, index, locale));
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
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
