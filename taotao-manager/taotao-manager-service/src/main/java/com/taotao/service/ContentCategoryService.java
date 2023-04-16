package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContentCategory;

public interface ContentCategoryService {

	 List<EUTreeNode> getCategoryList(long parentId);
	 TaotaoResult insertContentCategory(long parentId, String name);
	 
	 //重命名节点
	 TaotaoResult updateCategory(Long id, String name);
	 
	 /**
	  * 删除节点
	  */
	 //供外部调用的接口
	 TaotaoResult deleteCategory(Long id);
	 //获得所有该节点下的孩子节点
	 List<TbContentCategory> getChildNodeList(Long id);
	 //递归删除
	 void deleteCategoryAndChildNode(Long id);
	 
}
