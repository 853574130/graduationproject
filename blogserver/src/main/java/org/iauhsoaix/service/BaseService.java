package org.iauhsoaix.service;


import org.iauhsoaix.bean.BaseInfo;
import org.iauhsoaix.dal.entity.AbstractEntity;
import org.iauhsoaix.exceptions.CommonBusinessException;
import org.iauhsoaix.filter.LoginFilter;
import org.iauhsoaix.manager.BaseManager;
import org.iauhsoaix.utils.ExchangeUtils;
import org.iauhsoaix.utils.Pager;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public abstract class BaseService<E extends AbstractEntity, I extends BaseInfo> {
	//implements LogCapability
//	protected Logger logger = getLogger();

	protected abstract BaseManager<E> getManager();
	protected Class<E> eClass = (Class<E>) (((ParameterizedType) this.getClass().getGenericSuperclass())
			.getActualTypeArguments()[0]);
	protected Class<I> iClass = (Class<I>) (((ParameterizedType) this.getClass().getGenericSuperclass())
			.getActualTypeArguments()[1]);


//	private ThreadLocal<Condition> conditionThreadLocal = new ThreadLocal<Condition>();
//
	public I getModel(Long id) {
		E entity = getManager().getEntityById(id);
		if (null == entity) {
			throw new CommonBusinessException("记录不存在");
		}
		return ExchangeUtils.exchangeObject(entity, iClass);
	}
//
	public E getEntity(Long id) {
		E entity = getManager().getEntityById(id);
		if (null == entity) {
			throw new CommonBusinessException("记录不存在");
		}
		return entity;
	}

	public List<I> getListBy(I info) {
		E entity = ExchangeUtils.exchangeObject(info, eClass);
		List<E> list = getManager().getListBy(entity);
		return ExchangeUtils.exchangeList(list, iClass);
	}
	//
	public E insertBy(I info) {
		E entity = ExchangeUtils.exchangeObject(info, eClass);
		entity = insert(entity);
		doAfterUpdate(entity);
		return entity;
	}


	public E insert(E entity) {
//		if (entity instanceof BaseCompanyAndStatusEntity) {
//			((BaseCompanyAndStatusEntity) entity).setCompanyId(LoginFilter.getCurrentUser().getCompanyId());
//		}
		return insertDirectly(entity);
	}

	public E insertDirectly(E entity) {
		return getManager().insertBy(entity);
	}

	public void updateByPrimaryKeySelective(I info) {
		E entity = ExchangeUtils.exchangeObject(info, eClass);
		getManager().updateByPrimaryKeySelective(entity);
		doAfterUpdate(entity);
	}



	protected void doAfterUpdate(E entity) {
	}

	public int deleteBy(Integer id) {
		return getManager().deleteByPrimaryKey(id);
	}

	public I saveOrUpdate(I info) {
		E entity = ExchangeUtils.exchangeObject(info, eClass);
		if (getManager().getEntityById(entity.getId()) != null) {
			entity = getManager().updateByPrimaryKeySelective(entity);
			doAfterUpdate(entity);
		} else {
			entity = insertBy(info);
		}
		return ExchangeUtils.exchangeObject(entity, iClass);
	}

	public E getEntityAfterSaveOrUpdate(I info) {
		E entity = ExchangeUtils.exchangeObject(info, eClass);
		if (entity.getId() != null && getManager().getEntityById(entity.getId()) != null) {
			entity = getManager().updateByPrimaryKeySelective(entity);
			doAfterUpdate(entity);
		} else {
			entity = insertBy(info);
		}
		return entity;
	}



	/**
	 * 精确查询
	 * @param pageNum
	 * @param pageSize
	 * @param info
	 * @return
	 */
	public Pager<I> getModelList(int pageNum, int pageSize, I info) {
		E entity = ExchangeUtils.exchangeObject(info, eClass);
//		if (entity instanceof BaseCompanyAndStatusEntity) {
//			((BaseCompanyAndStatusEntity) entity).setCompanyId(LoginFilter.getCurrentUser().getCompanyId());
//		}
		List<E> list = getManager().getListBy(pageNum, pageSize, entity);
		long total = getManager().getTotal(entity);
		List<I> modelList = ExchangeUtils.exchangeList(list, iClass);
		setCustomProperties(modelList);
		Pager<I> pager = new Pager<I>(pageNum, pageSize, total, modelList);
		return pager;
	}
//
//	/**
//	 * 模糊查询(字符串参数)
//	 * @param pageNum
//	 * @param pageSize
//	 * @param info
//	 * @return
//	 */
//	public Pager<I> getModelListByLike(int pageNum, int pageSize, I info, boolean ignoreStatus) {
//		Condition condition = new Condition(eClass);
//		Example.Criteria criteria = condition.createCriteria();
//		if (info instanceof BaseCompanyAndStatusInfo) {
//			if (!ignoreStatus) {
//				criteria.andEqualTo("status", STATUS_DEFAULT);
//			}
//			criteria.andEqualTo("companyId", LoginFilter.getCurrentUser().getCompanyId());
//		}
//		try {
//			List<Field> fields = new ArrayList<>();
//			getAllField(fields, info.getClass());
//			for (Field field : fields) {
//				field.setAccessible(true);
//				Object value = field.get(info);
//				if (value != null) {
//					if (value instanceof String) {
//						if (StringUtils.isNotBlank((String)value)) {
//							criteria.andLike(field.getName(), "%" + (String)value + "%");
//						}
//					} else {
//						criteria.andEqualTo(field.getName(), value);
//					}
//				}
//			}
//		} catch (Exception e) {
//			throw new CommonBusinessException(e.getMessage(), e);
//		}
//		long total = getManager().getTotal(condition);
//		List<E> list = getManager().getByCondition(pageNum, pageSize, condition);
//		List<I> modelList = ExchangeUtils.exchangeList(list, iClass);
//		setCustomProperties(modelList);
//		Pager<I> pager = new Pager<I>(pageNum, pageSize, total, modelList);
//		return pager;
//	}
//
//	private void getAllField(List<Field> fields, Class clazz) {
//		if (clazz == null || !clazz.toString().startsWith("class com.ecms.")) {
//			return;
//		}
//		Field[] fieldArray = clazz.getDeclaredFields();
//		if (fieldArray != null) {
//			fields.addAll(Arrays.asList(fieldArray));
//		}
//		getAllField(fields, clazz.getSuperclass());
//	}
//
	protected void setCustomProperties(List<I> list) {
	}
//
//	public List<I> getValidList() {
//		List<E> list = getValidEntityList();
//		List<I> modelList = ExchangeUtils.exchangeList(list, iClass);
//		setCustomProperties(modelList);
//		return modelList;
//	}
//

//	public E updateStatus(int id) {
//		E entity;
//		try {
//			entity = getEntity(id);
//			if (entity instanceof BaseCompanyAndStatusEntity) {
//				int newStatus = ((BaseCompanyAndStatusEntity) entity).getStatus() == STATUS_DEFAULT ? STATUS_INVALID : STATUS_DEFAULT;
//				((BaseCompanyAndStatusEntity) entity).setStatus(newStatus);
//				getManager().updateByPrimaryKeySelective(entity);
//				doAfterUpdate(entity);
//			}
//		} catch (Exception e) {
//		    logger.error(e.getMessage(), e);
//			throw new CommonBusinessException("更新状态失败");
//		}
//		return entity;
//	}
//	public List<E> getValidEntityList() {
//		Condition condition = new Condition(eClass);
//		Example.Criteria criteria = condition.createCriteria();
//		criteria.andEqualTo("status", STATUS_DEFAULT);
//		criteria.andEqualTo("companyId", LoginFilter.getCurrentUser().getCompanyId());
//		List<E> list = getManager().getByCondition(condition);
//		return list;
//	}
//
//	public List<I> getValidCompanyList() {
//		List<E> list = getValidCompanyEntityList();
//		List<I> modelList = ExchangeUtils.exchangeList(list, iClass);
//		setCustomProperties(modelList);
//		return modelList;
//	}
//
//	public List<E> getValidCompanyEntityList() {
//		Condition condition = new Condition(eClass);
//		Example.Criteria criteria = condition.createCriteria();
//		criteria.andEqualTo("companyId", LoginFilter.getCurrentUser().getCompanyId());
//		List<E> list = getManager().getByCondition(condition);
//		return list;
//	}
//
//	public List<E> getValidStatusList() {
//		Condition condition = new Condition(eClass);
//		Example.Criteria criteria = condition.createCriteria();
//		criteria.andEqualTo("status", STATUS_DEFAULT);
//		return getManager().getByCondition(condition);
//	}
//

//	/**
//	 * 注意：一个请求内只能获取一次，多次请求会覆盖前面的condition
//	 *
//	 * @return
//	 */
//	protected Example.Criteria getDefaultCriteria() {
//		Condition condition = new Condition(eClass);
//		Example.Criteria criteria = condition.createCriteria();
//		criteria.andEqualTo("status", STATUS_DEFAULT);
//		criteria.andEqualTo("companyId", LoginFilter.getCurrentUser().getCompanyId());
//		conditionThreadLocal.set(condition);
//		return criteria;
//	}
//
//	/**
//	 * 注意：一个请求内只能获取一次
//	 *
//	 * @return
//	 */
//	protected List<I> getByCondition() {
//		Condition condition = conditionThreadLocal.get();
//		if (condition == null) {
//			throw new CommonBusinessException("本地线程中未找到condition");
//		}
//		List<E> list = getManager().getByCondition(condition);
//
//		conditionThreadLocal.remove();
//		return ExchangeUtils.exchangeList(list, iClass);
//	}
//
//	public Pager<I> getByCondition(int pageNum, int pageSize) {
//		Condition condition = conditionThreadLocal.get();
//		if (condition == null) {
//			throw new CommonBusinessException("本地线程中未找到condition");
//		}
//		List<E> list = getManager().getByCondition(pageNum, pageSize, condition);
//		long total = getManager().getTotal(condition);
//		List<I> modelList = ExchangeUtils.exchangeList(list, iClass);
//		setCustomProperties(modelList);
//		Pager<I> pager = new Pager<I>(pageNum, pageSize, total, modelList);
//		return pager;
//	}
//
//	protected List<E> getEntityListByCondition() {
//		Condition condition = conditionThreadLocal.get();
//		if (condition == null) {
//			throw new CommonBusinessException("本地线程中未找到condition");
//		}
//		List<E> list = getManager().getByCondition(condition);
//		conditionThreadLocal.remove();
//		return list;
//	}
//
//	protected long getCountByCondition() {
//		Condition condition = conditionThreadLocal.get();
//		if (condition == null) {
//			throw new CommonBusinessException("本地线程中未找到condition");
//		}
//		long count = getManager().getCountByCondition(condition);
//		conditionThreadLocal.remove();
//		return count;
//	}



	/**
	 * 格式化当前时间
	 * @param format 格式 yyyy-MM-dd HH:mm:ss或yyyy-MM-dd
	 * @return
	 */
	public String getDateFormat(String format){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}
}
