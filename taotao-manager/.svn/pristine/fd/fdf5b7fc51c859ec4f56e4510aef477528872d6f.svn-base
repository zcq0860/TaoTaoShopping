package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {

	// 根据id获取商品规格信息
	TbItemParam getItemParamById(long itemParamId);

	// 展示分类规格列表
	EUDataGridResult getItemParamList(int page, int rows);

	TaotaoResult getItemParamByCid(long cid);

	TaotaoResult insertItemParam(TbItemParam itemParam);

	// 删除商品规格模板
	public TaotaoResult deleteItemParam(List<Long> ids);
}
