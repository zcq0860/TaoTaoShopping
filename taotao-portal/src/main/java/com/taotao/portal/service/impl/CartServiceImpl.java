package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	

	/**
	 * 添加购物车商品
	 */
	@Override
	public TaotaoResult addCartItem(long itemId, int num, 
			HttpServletRequest request, HttpServletResponse response) {
		//取商品信息
		CartItem cartItem = null;
		//取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		//判断购物车商品列表中是否存在此商品
		for (CartItem cItem : itemList) {
			//如果存在此商品
			if (cItem.getId() == itemId) {
				//增加商品数量
				cItem.setNum(cItem.getNum() + num);
				cartItem = cItem;
				break;
			}
		}
		if (cartItem == null) {
			cartItem = new CartItem();
			//根据商品id查询商品基本信息。
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId); 
			//把json转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItem.class);
			if (taotaoResult.getStatus() == 200) {
				TbItem item = (TbItem) taotaoResult.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage() == null ? "":item.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
			}
			//添加到购物车列表
			itemList.add(cartItem);
		}
		//把购物车列表写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		
		return TaotaoResult.ok();
	}
	
	/**
	 * 从cookie中取商品列表
	 */
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		//从cookie中取商品列表
		String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
		if (cartJson == null) {
			return new ArrayList<>();
		}
		//把json转换成商品列表
		try {
			List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	/*
	 * 从cookie取出商品信息用于展示
	 */
	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> itemList = getCartItemList(request);
		return itemList;
	}
	
	/**
	 * 删除购物车商品
	 */
	@Override
	public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
		//从cookie中取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		//从列表中找到此商品
		for (CartItem cartItem : itemList) {
			if (cartItem.getId() == itemId) {
				itemList.remove(cartItem);
				break;
			}
				 
		}
		//把购物车列表重新写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		
		return TaotaoResult.ok();
	}
	
	/*
	 *直接 修改购物车商品数量
	 */
	@Override
	public TaotaoResult updateCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		//取商品信息
				CartItem cartItem = null;
				//取购物车商品列表
				List<CartItem> itemList = getCartItemList(request);
				//判断购物车商品列表中是否存在此商品
				for (CartItem cItem : itemList) {
					//如果存在此商品
					if (cItem.getId() == itemId) {
						//增加商品数量
						cItem.setNum(num);
						cartItem = cItem;
						break;
					}
				}
				if (cartItem == null) {
					cartItem = new CartItem();
					//根据商品id查询商品基本信息。
					String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId); 
					//把json转换成java对象
					TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItem.class);
					if (taotaoResult.getStatus() == 200) {
						TbItem item = (TbItem) taotaoResult.getData();
						cartItem.setId(item.getId());
						cartItem.setTitle(item.getTitle());
						cartItem.setImage(item.getImage() == null ? "":item.getImage().split(",")[0]);
						cartItem.setNum(num);
						cartItem.setPrice(item.getPrice());
					}
					//添加到购物车列表
					itemList.add(cartItem);
				}
				//把购物车列表写入cookie
				CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
				
				return TaotaoResult.ok();
	}
	
	/*
	 *+ - 修改购物车商品数量
	 */
	@Override
	public TaotaoResult updateCartItem_two(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
	
//		// 取购物车商品列表
//	    List<CartItem> cartList = getCartItemList(request);
//	    // 遍历商品列表找到商品
//	    for (CartItem cart : cartList) {
//	        if (cart.getId() == itemId) {
//	            // 更新商品数量
//	        	cart.setNum(num);
//	        	cartList.add(cart);
//	            break;
//	        }
//	    }
//	    // 写入cookie
//	    CookieUtils.setCookie(request, response, "TT_CART", 
//	            JsonUtils.objectToJson(cartList), true);
//	    // 返回结果
//	    
//	    return TaotaoResult.ok();
	    
	    
	    
	    CartItem cartItem = null;
		//取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		//判断购物车商品列表中是否存在此商品
		for (CartItem cItem : itemList) {
			//如果存在此商品
			if (cItem.getId() == itemId) {
				//增加商品数量
				cItem.setNum(num);
				cartItem = cItem;
				break;
			}
		}
		if (cartItem == null) {
			cartItem = new CartItem();
			//根据商品id查询商品基本信息。
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId); 
			//把json转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItem.class);
			if (taotaoResult.getStatus() == 200) {
				TbItem item = (TbItem) taotaoResult.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage() == null ? "":item.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
			}
			//添加到购物车列表
			itemList.add(cartItem);
		}
		//把购物车列表写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		
		return TaotaoResult.ok();
	}

}
