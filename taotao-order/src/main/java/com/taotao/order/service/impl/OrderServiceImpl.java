package com.taotao.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.pojo.NewOrder;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderExample;
import com.taotao.pojo.TbOrderExample.Criteria;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderItemExample;
import com.taotao.pojo.TbOrderShipping;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	@Autowired
	private JedisClient jedisClient;

	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;
	@Value("${ORDER_INIT_ID}")
	private String ORDER_INIT_ID;
	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;

	//创建订单
	@Override
	public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
		// 向订单表中插入记录
		// 获得订单号
		String string = jedisClient.get(ORDER_GEN_KEY);
		if (StringUtils.isBlank(string)) {
			jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		long orderId = jedisClient.incr(ORDER_GEN_KEY);
		// 补全pojo的属性
		order.setOrderId(orderId + "");
		// 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		order.setStatus(1);
		Date date = new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		// 0：未评价 1：已评价
		order.setBuyerRate(0);
		// 向订单表插入数据
		orderMapper.insert(order);
		// 插入订单明细
		for (TbOrderItem tbOrderItem : itemList) {
			// 补全订单明细
			// 取订单明细id
			long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			tbOrderItem.setId(orderDetailId + "");
			tbOrderItem.setOrderId(orderId + "");
			// 向订单明细插入记录
			orderItemMapper.insert(tbOrderItem);
		}
		// 插入物流表
		// 补全物流表的属性
		orderShipping.setOrderId(orderId + "");
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingMapper.insert(orderShipping);

		return TaotaoResult.ok(orderId);
	}

	//根据订单号查询订单信息
	@Override
	public TaotaoResult queryOrderById(String orderId) {
		
		TbOrder tbOrder = orderMapper.selectByPrimaryKey(orderId);
		NewOrder newOrder = tbOrderToOrder(tbOrder);
		
		TbOrderItemExample example = new TbOrderItemExample();
		com.taotao.pojo.TbOrderItemExample.Criteria criteria = example.createCriteria();
		List<TbOrderItem> orderItem = orderItemMapper.selectByExample(example);
		
		TbOrderShipping orderShipping = orderShippingMapper.selectByPrimaryKey(orderId);
		
		newOrder.setOrderItems(orderItem);
		newOrder.setOrderShipping(orderShipping);
		
		return TaotaoResult.ok(newOrder);
	}
	
	//根据用户昵称查询订单
	@Override
	public TaotaoResult queryOrderByNick(String buyer_nick) {
		
		TbOrderExample example = new TbOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andBuyerNickEqualTo(buyer_nick);
		List<TbOrder> orders = orderMapper.selectByExample(example);
		
		return TaotaoResult.ok(orders);
	}
	
	@Override
	public TaotaoResult changeStatus(TbOrder order) {
		
		TbOrderExample example = new TbOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(order.getOrderId());
		
		TbOrder order2 = new TbOrder();
		order2.setStatus(order.getStatus());
		order2.setPaymentTime(order.getPaymentTime());
		orderMapper.updateByExampleSelective(order2, example);
		return TaotaoResult.ok();
	}
	
	private NewOrder tbOrderToOrder(TbOrder tbOrder) {
		
		NewOrder newOrder = new NewOrder();
		newOrder.setBuyerMessage(tbOrder.getBuyerMessage());
		newOrder.setBuyerNick(tbOrder.getBuyerNick());
		newOrder.setCreateTime(tbOrder.getCreateTime());
		newOrder.setPayment(tbOrder.getPayment());
		newOrder.setPaymentType(tbOrder.getPaymentType());
		newOrder.setPostFee(tbOrder.getPostFee());
		newOrder.setStatus(tbOrder.getStatus());
		newOrder.setUserId(tbOrder.getUserId());
		
		return newOrder;
	}

}
