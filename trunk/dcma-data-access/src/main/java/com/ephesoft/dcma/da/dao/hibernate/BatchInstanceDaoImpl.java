/********************************************************************************* 
* Ephesoft is a Intelligent Document Capture and Mailroom Automation program 
* developed by Ephesoft, Inc. Copyright (C) 2010-2011 Ephesoft Inc. 
* 
* This program is free software; you can redistribute it and/or modify it under 
* the terms of the GNU Affero General Public License version 3 as published by the 
* Free Software Foundation with the addition of the following permission added 
* to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED WORK 
* IN WHICH THE COPYRIGHT IS OWNED BY EPHESOFT, EPHESOFT DISCLAIMS THE WARRANTY 
* OF NON INFRINGEMENT OF THIRD PARTY RIGHTS. 
* 
* This program is distributed in the hope that it will be useful, but WITHOUT 
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
* FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more 
* details. 
* 
* You should have received a copy of the GNU Affero General Public License along with 
* this program; if not, see http://www.gnu.org/licenses or write to the Free 
* Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 
* 02110-1301 USA. 
* 
* You can contact Ephesoft, Inc. headquarters at 111 Academy Way, 
* Irvine, CA 92617, USA. or at email address info@ephesoft.com. 
* 
* The interactive user interfaces in modified source and object code versions 
* of this program must display Appropriate Legal Notices, as required under 
* Section 5 of the GNU Affero General Public License version 3. 
* 
* In accordance with Section 7(b) of the GNU Affero General Public License version 3, 
* these Appropriate Legal Notices must retain the display of the "Ephesoft" logo. 
* If the display of the logo is not reasonably feasible for 
* technical reasons, the Appropriate Legal Notices must display the words 
* "Powered by Ephesoft". 
********************************************************************************/ 

package com.ephesoft.dcma.da.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinFragment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ephesoft.dcma.core.common.BatchInstanceStatus;
import com.ephesoft.dcma.core.common.EphesoftUser;
import com.ephesoft.dcma.core.common.Order;
import com.ephesoft.dcma.core.dao.hibernate.HibernateDao;
import com.ephesoft.dcma.core.hibernate.EphesoftCriteria;
import com.ephesoft.dcma.da.dao.BatchClassDao;
import com.ephesoft.dcma.da.dao.BatchClassGroupsDao;
import com.ephesoft.dcma.da.dao.BatchInstanceDao;
import com.ephesoft.dcma.da.dao.BatchInstanceGroupsDao;
import com.ephesoft.dcma.da.domain.BatchClass;
import com.ephesoft.dcma.da.domain.BatchClassGroups;
import com.ephesoft.dcma.da.domain.BatchInstance;
import com.ephesoft.dcma.da.domain.ServerRegistry;
import com.ephesoft.dcma.da.property.BatchInstanceFilter;
import com.ephesoft.dcma.da.property.BatchInstanceProperty;
import com.ephesoft.dcma.da.property.BatchPriority;

/**
 * This class is responsible to fetch data of batch instance table from data base.
 * 
 * @author Ephesoft
 * @version 1.0
 */
@Repository
public class BatchInstanceDaoImpl extends HibernateDao<BatchInstance> implements BatchInstanceDao {

	private static final String BATCH_CLASS_PROCESS_NAME = "batchClass.processName";
	private static final String BATCH_CLASS_NAME = "batchClass.name";
	private static final String VALIDATION_USER_NAME = "validationUserName";
	private static final String REVIEW_USER_NAME = "reviewUserName";
	private static final String BATCH_NAME = "batchName";
	private static final String BATCH_CLASS_IDENTIFIER = "batchClass.identifier";
	private static final String CURRENT_USER = "currentUser";
	private static final String IS_REMOTE = "isRemote";
	private static final String PRIORITY = "priority";
	private static final String STATUS = "status";
	private static final String BATCH_CLASS = "batchClass";
	private static final String EXECUTING_SERVER = "executingServer";
	private static final String BATCH_INSTANCE_IDENTIFIER = "identifier";

	/**
	 * Instance of {@link BatchInstanceGroupsDao}.
	 */
	@Autowired
	BatchInstanceGroupsDao batchInstanceGroupsDao;

	/**
	 * Instance of {@link BatchClassGroups}.
	 */
	@Autowired
	BatchClassGroupsDao batchClassGroupsDao;

	/**
	 * Instance of {@link BatchClassDao}.
	 */
	@Autowired
	BatchClassDao batchClassDao;

	/**
	 * An api to fetch all batch instance by batch class.
	 * 
	 * @param batchClass BatchClass BatchClass
	 * @return List<BatchInstance>
	 */
	@Override
	public List<BatchInstance> getBatchInstByBatchClass(BatchClass batchClass) {
		DetachedCriteria criteria = criteria();
		criteria.add(Restrictions.eq(BATCH_CLASS, batchClass));
		return find(criteria);
	}

	@Override
	public List<BatchInstance> getBatchInstancesByBatchName(String batchName, Set<String> userRoles) {
		DetachedCriteria criteria = criteria();

		List<BatchInstance> batchInstances = new ArrayList<BatchInstance>();

		Set<String> batchClassIdentifiers = batchClassGroupsDao.getBatchClassIdentifierForUserRoles(userRoles);

		String batchNameLocal = batchName.replaceAll("%", "\\\\%");
		criteria.add(Restrictions.like(BATCH_NAME, "%" + batchNameLocal + "%"));
		if (batchClassIdentifiers != null && batchClassIdentifiers.size() > 0) {
			criteria.createAlias(BATCH_CLASS, BATCH_CLASS);
			criteria.add(Restrictions.in(BATCH_CLASS_IDENTIFIER, batchClassIdentifiers));
			batchInstances = find(criteria);
		}
		return batchInstances;
	}

	@Override
	public BatchInstance getBatchInstancesForIdentifier(String identifier) {

		DetachedCriteria criteria = criteria();
		criteria.add(Restrictions.eq("identifier", identifier));
		return findSingle(criteria);

	}

	/**
	 * An api to fetch all batch instance by review user name.
	 * 
	 * @param reviewUserName String
	 * @return List<BatchInstance>
	 */
	@Override
	public List<BatchInstance> getBatchInstByReviewUserName(String reviewUserName) {
		DetachedCriteria criteria = criteria();
		criteria.add(Restrictions.eq(REVIEW_USER_NAME, reviewUserName));
		return find(criteria);
	}

	/**
	 * An api to fetch all batch instance by validation user name.
	 * 
	 * @param validationUserName String
	 * @return List<BatchInstance>
	 */
	@Override
	public List<BatchInstance> getBatchInstByValidationUserName(String validationUserName) {
		DetachedCriteria criteria = criteria();
		criteria.add(Restrictions.eq(VALIDATION_USER_NAME, validationUserName));
		return find(criteria);
	}

	/**
	 * An api to fetch all batch instance by BatchInstanceStatus.
	 * 
	 * @param batchInstanceStatus BatchInstanceStatus
	 * @return List<BatchInstance>
	 */
	@Override
	public List<BatchInstance> getBatchInstByStatus(BatchInstanceStatus batchInstanceStatus) {
		DetachedCriteria criteria = criteria();
		criteria.add(Restrictions.eq(STATUS, batchInstanceStatus));
		criteria.addOrder(org.hibernate.criterion.Order.asc(PRIORITY));
		return find(criteria);
	}

	/**
	 * An api to fetch count of the batch instance table for batch instance status and batch priority. API will return those batch
	 * instance having access by the user roles on the basis of ephesoft user.
	 * 
	 * @param batchName {@link String}
	 * @param batchInstanceStatus {@link BatchInstanceStatus}
	 * @param userName {@link String}
	 * @param priority {@link BatchPriority}
	 * @param userRoles Set<{@link String}>
	 * @param ephesoftUser {@link EphesoftUser}
	 * @return int, count of the batch instance present for the batch instance status.
	 */
	@Override
	public int getCount(String batchName, BatchInstanceStatus batchInstanceStatus, String userName, BatchPriority batchPriority,
			Set<String> userRoles, EphesoftUser ephesoftUser) {
		int count = 0;
		DetachedCriteria criteria = criteria();
		if (batchName != null && !batchName.isEmpty()) {
			String batchNameLocal = batchName.replaceAll("%", "\\\\%");
			criteria.add(Restrictions.like(BATCH_NAME, "%" + batchNameLocal + "%"));
		} else if (null != batchPriority) {
			Disjunction disjunction = Restrictions.disjunction();
			Integer lowValue = batchPriority.getLowerLimit();
			Integer upperValue = batchPriority.getUpperLimit();
			disjunction.add(Restrictions.between(PRIORITY, lowValue, upperValue));
			criteria.add(disjunction);
		}
		Set<String> batchClassIdentifiers = batchClassGroupsDao.getBatchClassIdentifierForUserRoles(userRoles);

		switch (ephesoftUser) {
			case NORMAL_USER:
				Set<String> batchInstanceIdentifiers = batchInstanceGroupsDao.getBatchInstanceIdentifierForUserRoles(userRoles);

				if ((null != batchClassIdentifiers && batchClassIdentifiers.size() > 0)
						|| (null != batchInstanceIdentifiers && batchInstanceIdentifiers.size() > 0)) {
					criteria.add(Restrictions.eq(STATUS, batchInstanceStatus));
					criteria.add(Restrictions.eq(IS_REMOTE, false));
					criteria.add(Restrictions.or(Restrictions.isNull(CURRENT_USER), Restrictions.eq(CURRENT_USER, userName)));
					final Disjunction disjunction = Restrictions.disjunction();
					if (null != batchClassIdentifiers && batchClassIdentifiers.size() > 0) {
						criteria.createAlias(BATCH_CLASS, BATCH_CLASS);
						disjunction.add(Restrictions.in(BATCH_CLASS_IDENTIFIER, batchClassIdentifiers));
					}
					if (null != batchInstanceIdentifiers && batchInstanceIdentifiers.size() > 0) {
						disjunction.add(Restrictions.in(BATCH_INSTANCE_IDENTIFIER, batchInstanceIdentifiers));
					}
					criteria.add(disjunction);
					count = count(criteria);
				}
				break;
			case SUPER_ADMIN:
				List<String> allBatchClassIdentifiers = batchClassDao.getAllBatchClassIdentifiers();
				if (null != allBatchClassIdentifiers && allBatchClassIdentifiers.size() > 0) {
					criteria.add(Restrictions.eq(STATUS, batchInstanceStatus));
					criteria.add(Restrictions.eq(IS_REMOTE, false));
					criteria.add(Restrictions.or(Restrictions.isNull(CURRENT_USER), Restrictions.eq(CURRENT_USER, userName)));
					count = count(criteria);
				}
				break;
			default:
				if (null != batchClassIdentifiers && batchClassIdentifiers.size() > 0) {
					criteria.add(Restrictions.eq(STATUS, batchInstanceStatus));
					criteria.add(Restrictions.eq(IS_REMOTE, false));
					criteria.add(Restrictions.or(Restrictions.isNull(CURRENT_USER), Restrictions.eq(CURRENT_USER, userName)));
					criteria.createAlias(BATCH_CLASS, BATCH_CLASS);
					criteria.add(Restrictions.in(BATCH_CLASS_IDENTIFIER, batchClassIdentifiers));
					count = count(criteria);
				}
				break;
		}
		return count;

	}

	/**
	 * An api to fetch all the batch instances by status list. Parameter firstResult set a limit upon the number of objects to be
	 * retrieved. Parameter maxResults set the first result to be retrieved. Parameter orderList set the sort property and order of
	 * that property. If orderList parameter is null or empty then this parameter is avoided.
	 * 
	 * @param statusList List<BatchInstanceStatus> status list of batch instance status.
	 * @param firstResult the first result to retrieve, numbered from <tt>0</tt>
	 * @param maxResults maxResults the maximum number of results
	 * @param orderList List<Order> orderList set the sort property and order of that property. If orderList parameter is null or empty
	 *            then this parameter is avoided.
	 * @param filterClauseList List<BatchInstanceFilter> this will add the where clause to the criteria query based on the property
	 *            name and value. If filterClauseList parameter is null or empty then this parameter is avoided.
	 * @param batchPriorities List<BatchPriority> this will add the where clause to the criteria query based on the priority list
	 *            selected. If batchPriorities parameter is null or empty then this parameter is avoided.
	 *@param userName Current user name.
	 *@param currentUserRoles
	 * @return List<BatchInstance> return the batch instance list.
	 */
	@Override
	public List<BatchInstance> getBatchInstances(List<BatchInstanceStatus> statusList, final int firstResult, final int maxResults,
			final List<Order> orderList, final List<BatchInstanceFilter> filterClauseList, final List<BatchPriority> batchPriorities,
			String userName, final Set<String> userRoles, EphesoftUser ephesoftUser) {
		return getBatchInstances(statusList, firstResult, maxResults, orderList, filterClauseList, batchPriorities, userName,
				userRoles, ephesoftUser, null);

	}

	/**
	 * An api to fetch all the batch instances excluding remotely executing batches by status list. Parameter firstResult set a limit
	 * upon the number of objects to be retrieved. Parameter maxResults set the first result to be retrieved. Parameter orderList set
	 * the sort property and order of that property. If orderList parameter is null or empty then this parameter is avoided. This will
	 * return only those batch instance which having access by the user roles on the basis of the ephesoft user.
	 * 
	 * @param batchNameToBeSearched {@link String}
	 * @param statusList List<{@link BatchInstanceStatus}> status list of batch instance status.
	 * @param firstResult the first result to retrieve, numbered from <tt>0</tt>
	 * @param maxResults maxResults the maximum number of results
	 * @param orderList List<{@link Order}> orderList set the sort property and order of that property. If orderList parameter is null
	 *            or empty then this parameter is avoided.
	 * @param filterClauseList List<{@link BatchInstanceFilter}> this will add the where clause to the criteria query based on the
	 *            property name and value. If filterClauseList parameter is null or empty then this parameter is avoided.
	 * @param batchPriorities List<{@link BatchPriority}> this will add the where clause to the criteria query based on the priority
	 *            list selected. If batchPriorities parameter is null or empty then this parameter is avoided.
	 * @param currentUser {@link String}
	 * @param userRoles Set<{@link String}>
	 * @param ephesoftUser {@link EphesoftUser}
	 * @return List<{@link BatchInstance}> return the batch instance list.
	 */
	@Override
	public List<BatchInstance> getBatchInstancesExcludedRemoteBatch(final String batchNameToBeSearched,
			List<BatchInstanceStatus> statusList, final int firstResult, final int maxResults, final List<Order> orderList,
			final List<BatchInstanceFilter> filterClauseList, final List<BatchPriority> batchPriorities, String userName,
			final Set<String> userRoles, EphesoftUser ephesoftUser) {
		EphesoftCriteria criteria = criteria();

		if (batchNameToBeSearched != null && !batchNameToBeSearched.isEmpty()) {
			String batchNameLocal = batchNameToBeSearched.replaceAll("%", "\\\\%");
			criteria.add(Restrictions.like(BATCH_NAME, "%" + batchNameLocal + "%"));
		}

		if (null != statusList) {
			criteria.add(Restrictions.in(STATUS, statusList));
			criteria.add(Restrictions.or(Restrictions.isNull(CURRENT_USER), Restrictions.eq(CURRENT_USER, userName)));
			criteria.add(Restrictions.eq(IS_REMOTE, false));
		}

		if (null != batchPriorities && !(batchPriorities.isEmpty())) {
			Disjunction disjunction = Restrictions.disjunction();
			for (BatchPriority batchPriority : batchPriorities) {
				if (null != batchPriority) {
					Integer lowValue = batchPriority.getLowerLimit();
					Integer upperValue = batchPriority.getUpperLimit();
					disjunction.add(Restrictions.between(PRIORITY, lowValue, upperValue));
				} else {
					disjunction = Restrictions.disjunction();
					break;
				}
			}
			criteria.add(disjunction);

		}
		List<BatchInstance> batchInstanceList = new ArrayList<BatchInstance>();

		Set<String> batchClassIdentifiers = batchClassGroupsDao.getBatchClassIdentifierForUserRoles(userRoles);

		switch (ephesoftUser) {
			case NORMAL_USER:
				Set<String> batchInstanceIdentifiers = batchInstanceGroupsDao.getBatchInstanceIdentifierForUserRoles(userRoles);
				if ((null != batchClassIdentifiers && batchClassIdentifiers.size() > 0)
						|| (null != batchInstanceIdentifiers && batchInstanceIdentifiers.size() > 0)) {
					BatchInstanceFilter[] filters = null;
					if (filterClauseList != null) {
						filters = filterClauseList.toArray(new BatchInstanceFilter[filterClauseList.size()]);
					}
					Order[] orders = null;
					if (orderList != null) {
						orders = orderList.toArray(new Order[orderList.size()]);
					}
					criteria.createAlias(BATCH_CLASS, BATCH_CLASS);
					final Disjunction disjunction = Restrictions.disjunction();
					if (null != batchClassIdentifiers && batchClassIdentifiers.size() > 0) {
						disjunction.add(Restrictions.in(BATCH_CLASS_IDENTIFIER, batchClassIdentifiers));
					}
					if (null != batchInstanceIdentifiers && batchInstanceIdentifiers.size() > 0) {
						disjunction.add(Restrictions.in(BATCH_INSTANCE_IDENTIFIER, batchInstanceIdentifiers));
					}
					criteria.add(disjunction);
					batchInstanceList = find(criteria, firstResult, maxResults, filters, orders);
				}
				break;
			case SUPER_ADMIN:
				List<String> allBatchClassIdentifiers = batchClassDao.getAllBatchClassIdentifiers();
				if (null != allBatchClassIdentifiers && allBatchClassIdentifiers.size() > 0) {
					BatchInstanceFilter[] filters = null;
					if (filterClauseList != null) {
						filters = filterClauseList.toArray(new BatchInstanceFilter[filterClauseList.size()]);
					}
					Order[] orders = null;
					if (orderList != null) {
						orders = orderList.toArray(new Order[orderList.size()]);
					}
					batchInstanceList = find(criteria, firstResult, maxResults, filters, orders);
				}
				break;

			default:
				if (null != batchClassIdentifiers && batchClassIdentifiers.size() > 0) {
					BatchInstanceFilter[] filters = null;
					if (filterClauseList != null) {
						filters = filterClauseList.toArray(new BatchInstanceFilter[filterClauseList.size()]);
					}
					Order[] orders = null;
					if (orderList != null) {
						orders = orderList.toArray(new Order[orderList.size()]);
					}

					criteria.createAlias(BATCH_CLASS, BATCH_CLASS);
					criteria.add(Restrictions.in(BATCH_CLASS_IDENTIFIER, batchClassIdentifiers));
					batchInstanceList = find(criteria, firstResult, maxResults, filters, orders);
				}
				break;
		}
		return batchInstanceList;
	}

	/**
	 * An api to fetch all the batch instance for status list input. API will return those batch instance having access by the user
	 * roles on the basis of ephesoft user.
	 * 
	 * @param statusList List<BatchInstanceStatus>
	 * @param firstResult int
	 * @param maxResults int
	 * @param ephesoftUser EphesoftUser
	 * @return List<BatchInstance>
	 */
	@Override
	public List<BatchInstance> getBatchInstances(List<BatchInstanceStatus> statusList, final int firstResult, final int maxResults,
			final Set<String> userRoles, EphesoftUser ephesoftUser) {
		List<BatchInstance> batchInstances = new ArrayList<BatchInstance>();

		Set<String> batchClassIdentifiers = batchClassGroupsDao.getBatchClassIdentifierForUserRoles(userRoles);

		EphesoftCriteria criteria = criteria();
		criteria.add(Restrictions.in(STATUS, statusList));
		criteria.add(Restrictions.isNull(CURRENT_USER));
		criteria.add(Restrictions.eq(IS_REMOTE, false));

		switch (ephesoftUser) {
			case NORMAL_USER:
				Set<String> batchInstanceIdentifiers = batchInstanceGroupsDao.getBatchInstanceIdentifierForUserRoles(userRoles);
				if ((null != batchClassIdentifiers && batchClassIdentifiers.size() > 0)
						|| (null != batchInstanceIdentifiers && batchInstanceIdentifiers.size() > 0)) {
					criteria.createAlias(BATCH_CLASS, BATCH_CLASS);
					final Disjunction disjunction = Restrictions.disjunction();
					if (null != batchClassIdentifiers && batchClassIdentifiers.size() > 0) {
						disjunction.add(Restrictions.in(BATCH_CLASS_IDENTIFIER, batchClassIdentifiers));
					}
					if (null != batchInstanceIdentifiers && batchInstanceIdentifiers.size() > 0) {
						disjunction.add(Restrictions.in(BATCH_INSTANCE_IDENTIFIER, batchInstanceIdentifiers));
					}
					criteria.add(disjunction);
					List<Order> orderList = new ArrayList<Order>();
					Order orderForHighestBatchPriority = new Order(BatchInstanceProperty.PRIORITY, true);
					Order orderForLastModified = new Order(BatchInstanceProperty.LASTMODIFIED, false);
					orderList.add(orderForLastModified);
					orderList.add(orderForHighestBatchPriority);
					batchInstances = find(criteria, firstResult, maxResults, orderList.toArray(new Order[orderList.size()]));
				}
				break;
			case SUPER_ADMIN:
				List<String> allBatchClassIdentifiers = batchClassDao.getAllBatchClassIdentifiers();
				if (null != allBatchClassIdentifiers && allBatchClassIdentifiers.size() > 0) {
					List<Order> orderList = new ArrayList<Order>();
					Order orderForHighestBatchPriority = new Order(BatchInstanceProperty.PRIORITY, true);
					Order orderForLastModified = new Order(BatchInstanceProperty.LASTMODIFIED, false);
					orderList.add(orderForLastModified);
					orderList.add(orderForHighestBatchPriority);
					batchInstances = find(criteria, firstResult, maxResults, orderList.toArray(new Order[orderList.size()]));
				}
				break;
			default:
				if (null != batchClassIdentifiers && batchClassIdentifiers.size() > 0) {
					criteria.createAlias(BATCH_CLASS, BATCH_CLASS);
					criteria.add(Restrictions.in(BATCH_CLASS_IDENTIFIER, batchClassIdentifiers));
					List<Order> orderList = new ArrayList<Order>();
					Order orderForHighestBatchPriority = new Order(BatchInstanceProperty.PRIORITY, true);
					Order orderForLastModified = new Order(BatchInstanceProperty.LASTMODIFIED, false);
					orderList.add(orderForLastModified);
					orderList.add(orderForHighestBatchPriority);
					batchInstances = find(criteria, firstResult, maxResults, orderList.toArray(new Order[orderList.size()]));
				}
				break;
		}
		return batchInstances;
	}

	/**
	 * This API fetches batch instance on the basis of user name and the roles defined for that user name in the batch class.
	 * 
	 * @param userRoles
	 * @param batchInstanceIdentifier
	 * @param currentUserName
	 * @param ephesoftUser
	 * @return
	 */
	@Override
	public BatchInstance getBatchInstanceByUserRole(final Set<String> userRoles, String batchInstanceIdentifier,
			String currentUserName, EphesoftUser ephesoftUser) {

		BatchInstance batchInstances = null;

		Set<String> batchClassIdentifiers = null;

		switch (ephesoftUser) {
			case NORMAL_USER:
				batchClassIdentifiers = batchClassGroupsDao.getBatchClassIdentifierForUserRoles(userRoles);
				Set<String> batchInstanceIdentifiers = batchInstanceGroupsDao.getBatchInstanceIdentifierForUserRoles(userRoles);

				if ((null != batchClassIdentifiers && batchClassIdentifiers.size() > 0)
						|| (null != batchInstanceIdentifiers && batchInstanceIdentifiers.size() > 0)) {
					EphesoftCriteria criteria = criteria();
					criteria.add(Restrictions.or(Restrictions.isNull(CURRENT_USER), Restrictions.eq(CURRENT_USER, currentUserName)));
					criteria.add(Restrictions.eq(IS_REMOTE, false));
					criteria.add(Restrictions.eq(BATCH_INSTANCE_IDENTIFIER, batchInstanceIdentifier));
					final Disjunction disjunction = Restrictions.disjunction();
					if (null != batchClassIdentifiers && batchClassIdentifiers.size() > 0) {
						criteria.createAlias(BATCH_CLASS, BATCH_CLASS);
						disjunction.add(Restrictions.in(BATCH_CLASS_IDENTIFIER, batchClassIdentifiers));
					}
					if (null != batchInstanceIdentifiers && batchInstanceIdentifiers.size() > 0) {
						disjunction.add(Restrictions.in(BATCH_INSTANCE_IDENTIFIER, batchInstanceIdentifiers));
					}
					criteria.add(disjunction);
					batchInstances = findSingle(criteria);
				}
				break;
			case SUPER_ADMIN:
				List<String> allBatchClassIdentifiers = batchClassDao.getAllBatchClassIdentifiers();
				if (null != allBatchClassIdentifiers && allBatchClassIdentifiers.size() > 0) {
					EphesoftCriteria criteria = criteria();
					criteria.add(Restrictions.or(Restrictions.isNull(CURRENT_USER), Restrictions.eq(CURRENT_USER, currentUserName)));
					criteria.add(Restrictions.eq(IS_REMOTE, false));
					criteria.add(Restrictions.eq(BATCH_INSTANCE_IDENTIFIER, batchInstanceIdentifier));
					final Disjunction disjunction = Restrictions.disjunction();
					criteria.add(disjunction);
					batchInstances = findSingle(criteria);
				}
				break;
			default:
				batchClassIdentifiers = batchClassGroupsDao.getBatchClassIdentifierForUserRoles(userRoles);
				if (null != batchClassIdentifiers && batchClassIdentifiers.size() > 0) {
					EphesoftCriteria criteria = criteria();
					criteria.add(Restrictions.or(Restrictions.isNull(CURRENT_USER), Restrictions.eq(CURRENT_USER, currentUserName)));
					criteria.add(Restrictions.eq(IS_REMOTE, false));
					criteria.add(Restrictions.eq(BATCH_INSTANCE_IDENTIFIER, batchInstanceIdentifier));
					criteria.createAlias(BATCH_CLASS, BATCH_CLASS);
					criteria.add(Restrictions.in(BATCH_CLASS_IDENTIFIER, batchClassIdentifiers));

					batchInstances = findSingle(criteria);
				}
				break;
		}
		return batchInstances;
	}

	/**
	 * An api to fetch count of the batch instance table for batch instance filters.
	 * 
	 * @param filterClauseList List<BatchInstanceFilter>
	 * @return count of the batch instance present for the batch instance filters.
	 */
	@Override
	public int getCount(final List<BatchInstanceFilter> filterClauseList) {
		int count = -1;
		DetachedCriteria criteria = criteria();
		if (null != filterClauseList) {
			for (BatchInstanceFilter batchInstanceFilter : filterClauseList) {
				criteria.add(Restrictions.eq(batchInstanceFilter.getNameProperty().getProperty(), batchInstanceFilter
						.getValueProperty()));
			}
			count = count(criteria);
		}
		return count;
	}

	/**
	 * An api to fetch count of the batch instance table for batch instance status list, batch priority list on the basis of the user
	 * roles. API will return the count for the batch instance having access by the user roles and current user name on the basis of
	 * the ephesoft user.
	 * 
	 * @param batchInstStatusList List<{@link BatchInstanceStatus}>
	 * @param batchPriorities List<{@link BatchPriority}>
	 * @param userRoles Set<{@link String}>
	 * @param currentUserName {@link String} current logged in user name.
	 * @param ephesoftUser Enum for ephesoft user.
	 * @return int,count of the batch instance present for the batch instance status list and batch priority list.
	 */
	@Override
	public int getCount(final List<BatchInstanceStatus> batchInstStatusList, final List<BatchPriority> batchPriorities,
			final Set<String> userRoles, final String currentUserName, EphesoftUser ephesoftUser) {
		return getCount(batchInstStatusList, batchPriorities, false, userRoles, currentUserName, ephesoftUser);
	}

	/**
	 * An api to return total count of batches from the batch instance table having access by the user roles on the basis of ephesoft
	 * user.
	 * 
	 * @param currentUser {@link String}
	 * @param userRoles Set<{@link String}>
	 * @param ephesoftUser {@link EphesoftUser}
	 * @return int, total count
	 */
	@Override
	public int getAllCount(final String currentUser, final Set<String> userRoles, EphesoftUser ephesoftUser) {
		return getCount(null, null, true, userRoles, currentUser, ephesoftUser);
	}

	/**
	 * An api to fetch count of the batch instances for a given status list and batch priority and isCurrUsrNotReq is used for adding
	 * the batch instance access by the current user. This API will return the batch instance having access by the user roles on the
	 * basis of ephesoft user.
	 * 
	 * @param batchInstStatusList List<{@link BatchInstanceStatus}>
	 * @param batchPriorities the priority list of the batches
	 * @param isCurrUsrNotReq true if the current user can be anyone. False if current user cannot be null.
	 * @param currentUser {@link String}
	 * @param userRoles Set<{@link String}>
	 * @param ephesoftUser {@link EphesoftUser}
	 * @return int, the count satisfying the above requirements
	 */
	@Override
	public int getCount(final List<BatchInstanceStatus> batchInstStatusList, final List<BatchPriority> batchPriorities,
			final boolean isNotCurrentUserCheckReq, final Set<String> userRoles, final String currentUserName,
			EphesoftUser ephesoftUser) {
		DetachedCriteria criteria = criteria();

		if (null != batchInstStatusList) {
			criteria.add(Restrictions.in(STATUS, batchInstStatusList));
		}

		if (null != batchPriorities && !(batchPriorities.isEmpty())) {
			Disjunction disjunction = Restrictions.disjunction();
			for (BatchPriority batchPriority : batchPriorities) {
				if (null != batchPriority) {
					Integer lowValue = batchPriority.getLowerLimit();
					Integer upperValue = batchPriority.getUpperLimit();
					disjunction.add(Restrictions.between(PRIORITY, lowValue, upperValue));
				} else {
					disjunction = Restrictions.disjunction();
					break;
				}
			}
			criteria.add(disjunction);
		}
		int count = 0;

		Set<String> batchClassIdentifiers = null;
		switch (ephesoftUser) {
			case ADMIN_USER:
				batchClassIdentifiers = batchClassGroupsDao.getBatchClassIdentifierForUserRoles(userRoles);
				if (null != batchClassIdentifiers && batchClassIdentifiers.size() > 0) {
					final Disjunction disjunction = Restrictions.disjunction();
					if (null != batchClassIdentifiers && batchClassIdentifiers.size() > 0) {
						criteria.createAlias(BATCH_CLASS, BATCH_CLASS);
						disjunction.add(Restrictions.in(BATCH_CLASS_IDENTIFIER, batchClassIdentifiers));
					}
					// Add check for null current users only.
					// Now we will count only for those current users those are null.
					if (!isNotCurrentUserCheckReq && null != currentUserName) {
						criteria.add(Restrictions
								.or(Restrictions.isNull(CURRENT_USER), Restrictions.eq(CURRENT_USER, currentUserName)));
					}
					criteria.add(disjunction);
					count = count(criteria);
				}
				break;
			case SUPER_ADMIN:
				List<String> allBatchClassIdentifiers = batchClassDao.getAllBatchClassIdentifiers();
				if (null != allBatchClassIdentifiers && allBatchClassIdentifiers.size() > 0) {
					// Add check for null current users only.
					// Now we will count only for those current users those are null.
					if (!isNotCurrentUserCheckReq && null != currentUserName) {
						criteria.add(Restrictions
								.or(Restrictions.isNull(CURRENT_USER), Restrictions.eq(CURRENT_USER, currentUserName)));
					}
					count = count(criteria);
				}
				break;
			default:
				batchClassIdentifiers = batchClassGroupsDao.getBatchClassIdentifierForUserRoles(userRoles);
				Set<String> batchInstanceIdentifiers = batchInstanceGroupsDao.getBatchInstanceIdentifierForUserRoles(userRoles);

				if ((null != batchClassIdentifiers && batchClassIdentifiers.size() > 0)
						|| (null != batchInstanceIdentifiers && batchInstanceIdentifiers.size() > 0)) {
					final Disjunction disjunction = Restrictions.disjunction();
					if (null != batchClassIdentifiers && batchClassIdentifiers.size() > 0) {
						criteria.createAlias(BATCH_CLASS, BATCH_CLASS);
						disjunction.add(Restrictions.in(BATCH_CLASS_IDENTIFIER, batchClassIdentifiers));
					}
					if (null != batchInstanceIdentifiers && batchInstanceIdentifiers.size() > 0) {
						disjunction.add(Restrictions.in(BATCH_INSTANCE_IDENTIFIER, batchInstanceIdentifiers));
					}
					// Add check for null current users only.
					// Now we will count only for those current users those are null.
					if (!isNotCurrentUserCheckReq && null != currentUserName) {
						criteria.add(Restrictions
								.or(Restrictions.isNull(CURRENT_USER), Restrictions.eq(CURRENT_USER, currentUserName)));
					}
					criteria.add(disjunction);
					count = count(criteria);
				}
				break;
		}

		return count;
	}

	/**
	 * An api to fetch all the batch instance by BatchPriority.
	 * 
	 * @param batchPriority BatchPriority this will add the where clause to the criteria query based on the priority column.
	 * @return List<BatchInstance> return the batch instance list.
	 */
	@Override
	public List<BatchInstance> getBatchInstance(BatchPriority batchPriority) {
		DetachedCriteria criteria = criteria();
		Integer lowValue = batchPriority.getLowerLimit();
		Integer upperValue = batchPriority.getUpperLimit();
		criteria.add(Restrictions.between(PRIORITY, lowValue, upperValue));
		return find(criteria);
	}

	/**
	 * An api to fetch all batch instance by batch Class Name.
	 * 
	 * @param batchClassName String
	 * @return List<BatchInstance>
	 */
	@Override
	public List<BatchInstance> getBatchInstByBatchClassName(String batchClassName) {
		DetachedCriteria criteria = criteria();
		criteria.createAlias(BATCH_CLASS, BATCH_CLASS, JoinFragment.INNER_JOIN);
		criteria.add(Restrictions.eq(BATCH_CLASS_NAME, batchClassName));
		return find(criteria);
	}

	/**
	 * An api to fetch all batch instance by batch Class Process Name.
	 * 
	 * @param batchClassProcessName String
	 * @return List<BatchInstance>
	 */
	@Override
	public List<BatchInstance> getBatchInstByBatchClassProcessName(String batchClassProcessName) {
		DetachedCriteria criteria = criteria();
		criteria.createAlias(BATCH_CLASS, BATCH_CLASS, JoinFragment.INNER_JOIN);
		criteria.add(Restrictions.eq(BATCH_CLASS_PROCESS_NAME, batchClassProcessName));
		return find(criteria);
	}

	/**
	 * This method will create the batch instance for input batch class, unc sub folder path and local folder path.
	 * 
	 * @param batchClass BatchClass
	 * @param uncSubFolder String
	 * @param localFolder String
	 * @param priority int
	 * @return BatchInstance
	 */
	@Override
	public BatchInstance createBatchInstance(BatchClass batchClass, String uncSubfolder, String localFolder, int priority) {
		BatchInstance batchInstance = new BatchInstance();

		batchInstance.setBatchClass(batchClass);
		batchInstance.setUncSubfolder(uncSubfolder);
		batchInstance.setLocalFolder(localFolder);
		batchInstance.setPriority(priority);
		batchInstance.setStatus(BatchInstanceStatus.NEW);

		String batchName = null;
		if (null != uncSubfolder) {
			int index = uncSubfolder.lastIndexOf('\\');
			if (index != -1) {
				batchName = uncSubfolder.substring(index + 1);
			} else {
				index = uncSubfolder.lastIndexOf('/');
				if (index != -1) {
					batchName = uncSubfolder.substring(index + 1);
				}
			}
		}

		batchInstance.setBatchName(batchName);

		this.create(batchInstance);
		return batchInstance;
	}

	/**
	 * This method will update the status for batch instance.
	 * 
	 * @param batchInstance BatchInstance
	 * @param status BatchInstanceStatus
	 */
	@Override
	public void updateBatchInstanceStatus(BatchInstance batchInstance, BatchInstanceStatus status) {
		batchInstance.setStatus(status);
		this.saveOrUpdate(batchInstance);
	}

	/**
	 * This method will create a new batch instance.
	 * 
	 * @param batchInstance BatchInstance
	 */
	@Override
	public void createBatchInstance(BatchInstance batchInstance) {
		create(batchInstance);
	}

	/**
	 * This method will update the existing batch instance.
	 * 
	 * @param batchInstance BatchInstance
	 */
	@Override
	public void updateBatchInstance(BatchInstance batchInstance) {
		saveOrUpdate(batchInstance);
	}

	/**
	 * This method will remove the existing batch instance.
	 * 
	 * @param batchInstance BatchInstance
	 */
	@Override
	public void removeBatchInstance(BatchInstance batchInstance) {
		remove(batchInstance);
	}

	/**
	 * An api to fetch all batch instance by batch Class Process and batch instance id's list.
	 * 
	 * @param batchClassName String
	 * @return List<BatchInstance>
	 */
	@Override
	public List<BatchInstance> getBatchInstanceList(String batchClassName, List<String> batchInstanceIDList) {
		DetachedCriteria criteria = criteria();
		criteria.createAlias(BATCH_CLASS, BATCH_CLASS, JoinFragment.INNER_JOIN);
		criteria.add(Restrictions.eq(BATCH_CLASS_NAME, batchClassName));
		criteria.add(Restrictions.in("identifier", batchInstanceIDList));
		return find(criteria);
	}

	@Override
	public List<BatchInstance> getAllBatchInstancesForCurrentUser(String currentUser) {
		DetachedCriteria criteria = criteria();
		criteria.add(Restrictions.eq(CURRENT_USER, currentUser));
		return find(criteria);
	}

	@Override
	public BatchInstance getBatch(long batchId, LockMode lockMode) {
		return get(batchId, lockMode);
	}

	@Override
	public List<BatchInstance> getRunningBatchInstancesFor(ServerRegistry lockOwner) {
		DetachedCriteria criteria = criteria();
		criteria.add(Restrictions.ne(STATUS, BatchInstanceStatus.NEW));
		criteria.add(Restrictions.ne(STATUS, BatchInstanceStatus.ERROR));
		criteria.add(Restrictions.ne(STATUS, BatchInstanceStatus.FINISHED));
		criteria.add(Restrictions.ne(STATUS, BatchInstanceStatus.READY_FOR_REVIEW));
		criteria.add(Restrictions.ne(STATUS, BatchInstanceStatus.READY_FOR_VALIDATION));
		criteria.add(Restrictions.eq("lockOwner", lockOwner));
		return find(criteria);
	}

	@Override
	public List<BatchInstance> getAllBatchInstances(List<Order> orders) {
		EphesoftCriteria criteria = criteria();
		return find(criteria, orders.toArray(new Order[orders.size()]));
	}

	@Override
	public List<BatchInstance> getAllUnFinishedBatchInstances(String uncFolder) {
		DetachedCriteria criteria = criteria();
		criteria.createAlias(BATCH_CLASS, BATCH_CLASS, JoinFragment.INNER_JOIN);
		criteria.add(Restrictions.eq("batchClass.uncFolder", uncFolder));

		List<BatchInstanceStatus> statusList = new ArrayList<BatchInstanceStatus>();
		statusList.add(BatchInstanceStatus.NEW);
		statusList.add(BatchInstanceStatus.ERROR);
		statusList.add(BatchInstanceStatus.READY_FOR_REVIEW);
		statusList.add(BatchInstanceStatus.READY_FOR_VALIDATION);
		statusList.add(BatchInstanceStatus.RUNNING);
		statusList.add(BatchInstanceStatus.READY);
		statusList.add(BatchInstanceStatus.RESTART_IN_PROGRESS);
		statusList.add(BatchInstanceStatus.LOCKED);
		criteria.add(Restrictions.in(STATUS, statusList));

		return find(criteria);
	}

	@Override
	public void updateBatchInstanceLocalFolder(BatchInstance batchInsctance, String localFolder) {
		batchInsctance.setLocalFolder(localFolder);
		saveOrUpdate(batchInsctance);
	}

	@Override
	public void updateBatchInstanceUncFolder(BatchInstance batchInsctance, String uncFolder) {
		batchInsctance.setUncSubfolder(uncFolder);
		saveOrUpdate(batchInsctance);
	}

	/**
	 * An API return all unfinished remotely executing batch instances.
	 */
	@Override
	public List<BatchInstance> getAllUnfinshedRemotelyExecutedBatchInstance() {
		DetachedCriteria criteria = criteria();
		criteria.add(Restrictions.eq(IS_REMOTE, true));
		List<BatchInstanceStatus> statusList = new ArrayList<BatchInstanceStatus>();
		statusList.add(BatchInstanceStatus.READY_FOR_REVIEW);
		statusList.add(BatchInstanceStatus.READY_FOR_VALIDATION);
		statusList.add(BatchInstanceStatus.RUNNING);
		statusList.add(BatchInstanceStatus.TRANSFERRED);
		statusList.add(BatchInstanceStatus.READY);
		criteria.add(Restrictions.in(STATUS, statusList));
		return find(criteria);

	}

	/**
	 * An api to fetch all the batch instances only remotely executing batches by status list. Parameter firstResult set a limit upon
	 * the number of objects to be retrieved. Parameter maxResults set the first result to be retrieved. Parameter orderList set the
	 * sort property and order of that property. If orderList parameter is null or empty then this parameter is avoided.
	 * 
	 * @param statusList List<BatchInstanceStatus> status list of batch instance status.
	 * @param firstResult the first result to retrieve, numbered from <tt>0</tt>
	 * @param maxResults maxResults the maximum number of results
	 * @param orderList List<Order> orderList set the sort property and order of that property. If orderList parameter is null or empty
	 *            then this parameter is avoided.
	 * @param filterClauseList List<BatchInstanceFilter> this will add the where clause to the criteria query based on the property
	 *            name and value. If filterClauseList parameter is null or empty then this parameter is avoided.
	 * @param batchPriorities List<BatchPriority> this will add the where clause to the criteria query based on the priority list
	 *            selected. If batchPriorities parameter is null or empty then this parameter is avoided.
	 *@param userName Current user name.
	 *@param currentUserRoles
	 * @return List<BatchInstance> return the batch instance list.
	 */
	@Override
	public List<BatchInstance> getRemoteBatchInstances(List<BatchInstanceStatus> statusList, final int firstResult,
			final int maxResults, final List<Order> orderList, final List<BatchInstanceFilter> filterClauseList,
			final List<BatchPriority> batchPriorities, String userName, final Set<String> userRoles) {
		EphesoftCriteria criteria = criteria();

		if (null != statusList) {
			criteria.add(Restrictions.in(STATUS, statusList));
			criteria.add(Restrictions.or(Restrictions.isNull(CURRENT_USER), Restrictions.eq(CURRENT_USER, userName)));
			criteria.add(Restrictions.eq(IS_REMOTE, true));
		}

		if (null != batchPriorities && !(batchPriorities.isEmpty())) {
			Disjunction disjunction = Restrictions.disjunction();
			for (BatchPriority batchPriority : batchPriorities) {
				if (null != batchPriority) {
					Integer lowValue = batchPriority.getLowerLimit();
					Integer upperValue = batchPriority.getUpperLimit();
					disjunction.add(Restrictions.between(PRIORITY, lowValue, upperValue));
				} else {
					disjunction = Restrictions.disjunction();
					break;
				}
			}
			criteria.add(disjunction);

		}

		List<BatchInstance> batchInstances = new ArrayList<BatchInstance>();

		Set<String> batchClassIdentifiers = batchClassGroupsDao.getBatchClassIdentifierForUserRoles(userRoles);

		if (null != batchClassIdentifiers && batchClassIdentifiers.size() > 0) {
			BatchInstanceFilter[] filters = null;
			if (filterClauseList != null) {
				filters = filterClauseList.toArray(new BatchInstanceFilter[filterClauseList.size()]);
			}
			Order[] orders = null;
			if (orderList != null) {
				orders = orderList.toArray(new Order[orderList.size()]);
			}

			criteria.createAlias(BATCH_CLASS, BATCH_CLASS);
			criteria.add(Restrictions.in(BATCH_CLASS_IDENTIFIER, batchClassIdentifiers));
			batchInstances = find(criteria, firstResult, maxResults, filters, orders);
		}

		return batchInstances;
	}

	@Override
	public List<BatchInstance> getExecutingJobByServerIP(String serverIP) {
		EphesoftCriteria criteria = criteria();
		if (null != serverIP) {
			criteria.add(Restrictions.eq(EXECUTING_SERVER, serverIP));
			criteria.add(Restrictions.eq(STATUS, BatchInstanceStatus.RUNNING));
		}
		return find(criteria);
	}

	@Override
	public List<BatchInstance> getBatchInstanceByExecutingServerIPAndBatchStatus(String executingServerIP,
			BatchInstanceStatus batchInstanceStatus) {
		EphesoftCriteria criteria = criteria();
		if (null != executingServerIP) {
			criteria.add(Restrictions.eq(EXECUTING_SERVER, executingServerIP));
			criteria.add(Restrictions.eq(STATUS, BatchInstanceStatus.LOCKED));
		}
		return find(criteria);
	}

	@Override
	public List<BatchInstance> getBatchInstanceListByBatchNameAndStatus(String batchName, BatchInstanceStatus batchStatus,
			String userName, Set<String> userRoles) {
		EphesoftCriteria criteria = criteria();
		List<BatchInstance> batchInstanceList = null;
		String batchNameLocal = batchName.replaceAll("%", "\\\\%");
		if (batchStatus != null) {
			criteria.add(Restrictions.like(BATCH_NAME, "%" + batchNameLocal + "%"));
			criteria.add(Restrictions.eq(STATUS, batchStatus));
			criteria.add(Restrictions.or(Restrictions.isNull(CURRENT_USER), Restrictions.eq(CURRENT_USER, userName)));

			Set<String> batchClassIdentifiers = batchClassGroupsDao.getBatchClassIdentifierForUserRoles(userRoles);

			if (null != batchClassIdentifiers && batchClassIdentifiers.size() > 0) {
				criteria.createAlias(BATCH_CLASS, BATCH_CLASS);
				criteria.add(Restrictions.in(BATCH_CLASS_IDENTIFIER, batchClassIdentifiers));
				batchInstanceList = find(criteria);
			}
		}
		return batchInstanceList;
	}

	/**
	 * This API fetches all the batch instances on the basis of batch status list passed.
	 * 
	 * @param batchStatusList List<{@link BatchInstanceStatus}>
	 * @return List<{@link BatchInstance}>
	 */
	@Override
	public List<BatchInstance> getBatchInstanceByStatusList(List<BatchInstanceStatus> batchStatusList) {
		DetachedCriteria criteria = criteria();
		if (null != batchStatusList && !batchStatusList.isEmpty()) {
			criteria.add(Restrictions.in(STATUS, batchStatusList));
		}
		return find(criteria);
	}

	@Override
	public void clearCurrentUser(String batchInstanceIdentifier) {
		BatchInstance batchInstance = getBatchInstancesForIdentifier(batchInstanceIdentifier);
		batchInstance.setCurrentUser(null);
		updateBatchInstance(batchInstance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ephesoft.dcma.da.dao.BatchInstanceDao#getBatchInstancesForStatusPriority(java.util.List, java.util.List, java.util.Set)
	 */
	@Override
	public List<BatchInstance> getBatchInstancesForStatusPriority(List<BatchInstanceStatus> statusList,
			final List<BatchPriority> batchPriorities, final Set<String> userRoles) {
		EphesoftCriteria criteria = criteria();
		criteria.add(Restrictions.isNull(CURRENT_USER));
		return getBatchInstances(statusList, -1, -1, null, null, batchPriorities, null, userRoles, EphesoftUser.ADMIN_USER, criteria);
	}

	/**
	 * An api to fetch all the batch instances by status list. Parameter firstResult set a limit upon the number of objects to be
	 * retrieved. Parameter maxResults set the first result to be retrieved. Parameter orderList set the sort property and order of
	 * that property. If orderList parameter is null or empty then this parameter is avoided.
	 * 
	 * @param statusList List<BatchInstanceStatus> status list of batch instance status.
	 * @param firstResult the first result to retrieve, numbered from <tt>0</tt>
	 * @param maxResults maxResults the maximum number of results
	 * @param orderList List<Order> orderList set the sort property and order of that property. If orderList parameter is null or empty
	 *            then this parameter is avoided.
	 * @param filterClauseList List<BatchInstanceFilter> this will add the where clause to the criteria query based on the property
	 *            name and value. If filterClauseList parameter is null or empty then this parameter is avoided.
	 * @param batchPriorities List<BatchPriority> this will add the where clause to the criteria query based on the priority list
	 *            selected. If batchPriorities parameter is null or empty then this parameter is avoided.
	 *@param userName Current user name.
	 *@param userRoles currentUserRoles
	 *@param EphesoftUser current ephesoft-user
	 *@return List<BatchInstance> return the batch instance list.
	 */
	public List<BatchInstance> getBatchInstances(List<BatchInstanceStatus> statusList, final int firstResult, final int maxResults,
			final List<Order> orderList, final List<BatchInstanceFilter> filterClauseList, final List<BatchPriority> batchPriorities,
			String userName, final Set<String> userRoles, EphesoftUser ephesoftUser, final EphesoftCriteria criteria) {
		EphesoftCriteria criteriaLocal = null;
		if (criteria == null) {
			criteriaLocal = criteria();
		} else {
			criteriaLocal = criteria;
		}

		if (null != statusList) {
			criteriaLocal.add(Restrictions.in(STATUS, statusList));
			// criteria.add(Restrictions.or(Restrictions.isNull(CURRENT_USER), Restrictions.eq(CURRENT_USER, userName)));
		}

		if (null != batchPriorities && !(batchPriorities.isEmpty())) {
			Disjunction disjunction = Restrictions.disjunction();
			for (BatchPriority batchPriority : batchPriorities) {
				if (null != batchPriority) {
					Integer lowValue = batchPriority.getLowerLimit();
					Integer upperValue = batchPriority.getUpperLimit();
					disjunction.add(Restrictions.between(PRIORITY, lowValue, upperValue));
				} else {
					disjunction = Restrictions.disjunction();
					break;
				}
			}
			criteriaLocal.add(disjunction);

		}

		List<BatchInstance> batchInstaceList = new ArrayList<BatchInstance>();
		BatchInstanceFilter[] filters = null;
		Order[] orders = null;
		switch (ephesoftUser) {
			case SUPER_ADMIN:
				if (filterClauseList != null) {
					filters = filterClauseList.toArray(new BatchInstanceFilter[filterClauseList.size()]);
				}
				if (orderList != null) {
					orders = orderList.toArray(new Order[orderList.size()]);
				}
				batchInstaceList = find(criteriaLocal, firstResult, maxResults, filters, orders);
				break;
			default:
				Set<String> batchClassIndentifiers = batchClassGroupsDao.getBatchClassIdentifierForUserRoles(userRoles);
				if (null != batchClassIndentifiers && batchClassIndentifiers.size() > 0) {

					if (filterClauseList != null) {
						filters = filterClauseList.toArray(new BatchInstanceFilter[filterClauseList.size()]);
					}
					if (orderList != null) {
						orders = orderList.toArray(new Order[orderList.size()]);
					}
					criteriaLocal.createAlias(BATCH_CLASS, BATCH_CLASS);
					criteriaLocal.add(Restrictions.in(BATCH_CLASS_IDENTIFIER, batchClassIndentifiers));
					batchInstaceList = find(criteriaLocal, firstResult, maxResults, filters, orders);
				}
				break;
		}
		return batchInstaceList;
	}

}
