package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;
	
	@Override
	public String getContentList() {
		//调用服务层的服务
		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
//		System.out.println("portal-ContentServiceImpl-result" + result);
		//把字符串转换成TaotaoResult
		try {
			TaotaoResult taotaoResult = TaotaoResult.formatToList(result, TbContent.class);
//			System.out.println("portal-ContentServiceImpl-taotaoResult" + taotaoResult);
			//取内容列表
			
			List<TbContent> list = (List<TbContent>) taotaoResult.getData();
//			System.out.println("portal-ContentServiceImpl-list" + list);
			List<Map> resultList = new ArrayList<>();
 			//创建一个jsp页码要求的pojo列表
			for (TbContent tbContent : list) {
				Map map = new HashMap<>();
				map.put("src", tbContent.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", tbContent.getPic2());
				map.put("widthB", 550);
				map.put("heightB", 240);
				map.put("href", tbContent.getUrl());
				map.put("alt", tbContent.getSubTitle());
//				System.out.println("portal-ContentServiceImpl-map" + map);
				resultList.add(map);
			}
//			System.out.println("portal-ContentServiceImpl-resultList" + resultList);
//			System.out.println("portal-ContentServiceImpl-JsonUtils.objectToJson(resultList)" + JsonUtils.objectToJson(resultList));
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
