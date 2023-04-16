package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;

/**
 * 商品规格管理
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public TbItemParam getItemParamById(long itemParamId) {

		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemParamId);
		List<TbItemParam> list = itemParamMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItemParam itemParam = list.get(0);
			return itemParam;
		}
		return null;
	}

	@Override
	public EUDataGridResult getItemParamList(int page, int rows) {

		PageHelper.startPage(page, rows);
		TbItemParamExample example = new TbItemParamExample();
		
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		
		EUDataGridResult result = new EUDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		
		return result;
	}

	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		//补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}
	
	//删除已经存在的商品规格模板
	@Override
	public TaotaoResult deleteItemParam(List<Long> ids) {
		/*
		* 按照主键进行商品删除 --- 不可行 存在多项删除
		* 由于Mybatis逆向工程生成的SQL为单表简单查询,这里我们去构造一个多项删除的SQL语句
		*/
		int result = itemParamMapper.deleteBatch(ids);
		//System.out.println(result);
		if(result > 0){
			return  TaotaoResult.ok(ids);
		}
		return TaotaoResult.ok();
	}
}
