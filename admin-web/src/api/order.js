import {postAndGet} from '../utils/api'
const _axios = postAndGet();

// TODO 获取所有订单
export const reqListOrders = (params) => {
  return _axios.post('/order/listOrders', params).then(res => res.data)
}

// TODO 订单发货
export const reqDeliverOrder = (params) => {
  return _axios.post('/order/deliverOrder', params).then(res => res.data)
}

// TODO 管理员取消订单
export const reqCancelOrder = (params) => {
  return _axios.post('/order/cancelOrder', params).then(res => res.data)
}

// TODO 每个订单状态的数量
export const reqCountOrdersByStatus = () => {
  return _axios.get('/order/countOrdersByStatus').then(res => res.data)
}

// TODO 按日期统计订单数量、金额
export const reqCountOrdersByDay = () => {
  return _axios.get('/order/countOrdersByDay').then(res => res.data)
}

// TODO 显示订单表的年份
export const reqListYears = () => {
  return _axios.get('/order/listYears').then(res => res.data)
}

// TODO 根据年份显示每月销量
export const reqListMonthSalesByYears = (params) => {
  return _axios.post('/order/listMonthSalesByYears', params).then(res => res.data)
}

// TODO 根据统计商品销量入参，返回商品月销量
export const reqListSpuMonthSales = (params) => {
  return _axios.post('/order/listSpuMonthSales', params).then(res => res.data)
}

