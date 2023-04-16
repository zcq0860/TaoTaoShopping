package com.taotao.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

public interface ItemService {

	//根据id获取商品信息
	TbItem getItemById(long itemId);
	//展示商品列表
	EUDataGridResult getItemList(int page, int rows);
	//创建商品信息
	TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception;

	/**
	 * 更新商品
	 */
	TaotaoResult updateItem(TbItem item);
	//根据cid获商品分类信息
	TbItemCat getItemCatByCid(Long cid);
	//根据id获取商品描述信息
	TbItemDesc getItemDescById(Long id);
	//根据itemid获取商品规格信息
	TbItemParamItem getItemParamItemById(Long itemid);
	
	/**
	 * 更新商品状态
	 */
	//用于修改商品的删除、上架和下架状态
	TaotaoResult updateItemStatus(List<Long> ids, String method);
}
