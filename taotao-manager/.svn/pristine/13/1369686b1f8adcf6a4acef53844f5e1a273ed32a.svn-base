package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	//显示商品规格信息
	@RequestMapping("/{itemParamId}")
	@ResponseBody
	public TbItemParam getItemParamById(@PathVariable Long itemParamId) {
		TbItemParam tbParamItem = itemParamService.getItemParamById(itemParamId);
		return tbParamItem;
	}
	
	//显示商品规格列表
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemParamList(Integer page, Integer rows) {
		EUDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}
	
	//获取商品规格信息
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId) {
		TaotaoResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	
	//保存商品规格信息
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
		//创建pojo对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		TaotaoResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}
	
    //商品规格参数模板的删除
    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteItemParam(@RequestParam("ids") List<Long> ids){
//        测试下传输过来的参数是否正确
//        for (int i = 0; i < ids.size(); i++) {
//            System.out.println(ids.get(i));
//        }
//        System.out.println(ids.size());
        
        return itemParamService.deleteItemParam(ids);
    }
}
