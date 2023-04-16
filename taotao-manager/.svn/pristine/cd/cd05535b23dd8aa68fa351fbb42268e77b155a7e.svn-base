package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;


@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	
	//显示商品信息
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	//显示商品列表
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	//修改商品信息
	@RequestMapping(value="/rest/item/update",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updateItem(TbItem item) {
		TaotaoResult result = itemService.updateItem(item);
		return result;
	}
	
	//更新商品状态
	@RequestMapping("/rest/item/{method}")
	@ResponseBody
	public TaotaoResult updateItemStatus(@RequestParam(value = "ids") List<Long> ids, @PathVariable String method) {
		TaotaoResult result = itemService.updateItemStatus(ids, method);
		return result;
	}
	
	//回显商品描述信息
	@RequestMapping("/rest/item/query/item/desc/{id}")
	@ResponseBody
	public TaotaoResult getDesc(@PathVariable long id) {
		return TaotaoResult.ok(itemService.getItemDescById(id));
	}

	//回显商品规格信息
	@RequestMapping("/rest/item/param/item/query/{itemid}")
	@ResponseBody
	public TaotaoResult getItemParamItem(@PathVariable long itemid) {
		return TaotaoResult.ok(itemService.getItemParamItemById(itemid));
	}
	
	//保存商品信息
	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	@ResponseBody
	private TaotaoResult createItem(TbItem item, String desc ,String itemParams) throws Exception {
		TaotaoResult result = itemService.createItem(item, desc,itemParams);
		return result;
	}
}
