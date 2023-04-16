package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;
import com.taotao.service.ItemService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemCatService itemCatService;
	
	//获取商品类目信息
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getItemCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
		List<EUTreeNode> list = itemCatService.getItemCatList(parentId);
		return list;
	}
	
	//回显商品类目信息
	@RequestMapping("/class")
	@ResponseBody
	public TaotaoResult getCatNameByCid(@RequestParam(value = "cid",defaultValue = "0") Long cid) {
		TbItemCat tbItemCat = itemService.getItemCatByCid(cid);
		return TaotaoResult.ok(tbItemCat);
	}
}
