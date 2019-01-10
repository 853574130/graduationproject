package org.iauhsoaix.manager;


import org.iauhsoaix.dal.entity.AbstractEntity;
import org.iauhsoaix.utils.BasicMapper;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public abstract class BaseManager<E extends AbstractEntity>{

	@Resource
	protected abstract BasicMapper<E> getMapper();

	public E getEntityById(Long id) {
		return getMapper().selectByPrimaryKey(id);
	}
	
	public E getByExample(Example example) {
		return getMapper().selectOneByExample(example);
	}

	public long getCountBy(E entity) {
		return getMapper().selectCount(entity);
	}

	public List<E> getListBy(int pageNum, int pageSize, E entity) {
//		PageMethod.startPage(pageNum, pageSize);//应该是要分页插件
		return getMapper().select(entity);
	}

	public List<E> getListBy(E entity) {
		return getMapper().select(entity);
	}

	public List<E> getByCondition(int pageNum, int pageSize, Condition condition) {
//		PageMethod.startPage(pageNum, pageSize);
		return getMapper().selectByCondition(condition);
	}

	public List<E> getByCondition(Condition condition) {
		return getMapper().selectByCondition(condition);
	}

	public long getCountByCondition(Condition condition) {
		return getMapper().selectCountByCondition(condition);
	}

	public long getTotal(E entity) {
		return getMapper().selectCount(entity);
	}

	public long getTotal(Condition condition) {
		return getMapper().selectCountByCondition(condition);
	}

	public E insertBy(E entity) {
		getMapper().insertSelective(entity);
		return entity;
	}

	public E updateByPrimaryKeySelective(E entity) {
		entity.setCreated(null);
		entity.setUpdated(new Date());
		getMapper().updateByPrimaryKeySelective(entity);
		return entity;
	}

	public int updateByConditionSelective(E entity, Condition condition) {
		return getMapper().updateByConditionSelective(entity, condition);
	}

	public int deleteByPrimaryKey(Integer id) {
		return getMapper().deleteByPrimaryKey(id);
	}
}
