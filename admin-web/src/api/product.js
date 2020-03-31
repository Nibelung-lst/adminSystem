import {postAndGet} from '../utils/api'
const _axios = postAndGet();

//---------------------品牌----------------------------------------
//获取所有品牌
export const brandSend = (params) => {
  return _axios.get('/api/getAllBrand',{
    params:{
      'pageNumber':params.pageNumber,
      'pageSize':params.pageSize
    }
  }).then(res => res.data)
}
//品牌分页
export const brandHandleSizeChange = (params) => {
  return _axios.get('/api/getAllBrand',{
    params:{
      'pageSize':params.pageSize,
      'pageNumber':params.pageNumber
    }
  }).then(res => res.data)
}
//品牌分页
export const brandHandleCurrentChange = (params) => {
  return _axios.get('/api/getAllBrand',{
    params:{
      'pageNumber':params.pageNumber,
      'pageSize':params.pageSize
    }
  }).then(res => res.data)
}
// 品牌修改状态
export const handleUpdateStatus = (params) => {
  return _axios.post('/api/updateBrandStatus',params).then(res => res.data)
}
//添加品牌
export const handleAddSuccess = (params) => {
  return _axios.post('/api/addCategory',params).then(res => res.data)
}
//添加品牌
export const brandAdd = (params) => {
  return _axios.post('/api/addBrand',params).then(res => res.data)
}
//------------------------------------------------------------------------------
//-------------------------------Top分类---------------------------------------------
//分类编号检查
export const categoryCodeCheck = (params) => {
  return _axios.get('/api/checkExist',{
    params:{
      'categoryCode':params
    }
  }).then(res => res.data)
}
//分类查询
export const categoryTopSend = (params) => {
  return _axios.get('/api/getCategoryNoParent',{
    params:{
      'pageSize':params.pageSize,
      'pageNumber':params.pageNumber
    }
  }).then(res => res.data)
}
//分类分页
export const categoryTopHandleSizeChange = (params) => {
  return _axios.get('/api/getCategoryNoParent',{
    params:{
      'pageSize':params.pageSize,
      'pageNumber':params.pageNumber
    }
  }).then(res => res.data)
}
//分类分页
export const categoryTopHandleCurrentChange = (params) => {
  return _axios.get('/api/getCategoryNoParent',{
    params:{
      'pageNumber':params.pageNumber,
      'pageSize':params.pageSize
    }
  }).then(res => res.data)
}
//修改分类
export const categoryTopUpdate = (params) => {
  return _axios.post('/api/updateCategory',params).then(res => res.data)
}
//添加分类
export const categoryTopAdd = (params) => {
  return _axios.post('/api/addCategory',params).then(res => res.data)
}
//-------------------------------children分类-------------------------------------
//查询分类的子节点
export const queryChildrenCategory = (params) => {
  return _axios.get('/api/getCategoryChildren',{
    params:{
      'parentCode':params.parentCode,
      'pageNumber':params.pageNumber,
      'pageSize':params.pageSize
    }
  }).then(res => res.data)
}
//分页
export const queryChildrenCategoryByPageSize = (params) => {
  return _axios.get('/api/getCategoryChildren',{
    params:{
      'parentCode':params.parentCode,
      'pageNumber':params.pageNumber,
      'pageSize':params.pageSize
    }
  }).then(res => res.data)
}
//分页
export const queryChildrenCategoryByPageNumber = (params) => {
  return _axios.get('/api/getCategoryChildren',{
    params:{
      'parentCode':params.parentCode,
      'pageNumber':params.pageNumber,
      'pageSize':params.pageSize
    }
  }).then(res => res.data)
}
//--------------------------------spu-----------------------------------
//spu编号检查
export const spuCodeCheck = (params) => {
  return _axios.get('/api/checkSpuCode',{
    params:{
      'spuCode':params
    }
  }).then(res => res.data)
}
//查找spu
export const querySpu = (params) => {
  return _axios.get('/api/getSpuByCategoryCode',{
    params:{
      'categoryCode':params.categoryCode,
      'pageNumber':params.pageNumber,
      'pageSize':params.pageSize
    }
  }).then(res => res.data)
}
//分页
export const querySpuByPage = (params) => {
  return _axios.get('/api/getSpuByCategoryCode',{
    params:{
      'categoryCode':params.categoryCode,
      'pageNumber':params.pageNumber,
      'pageSize':params.pageSize
    }
  }).then(res => res.data)
}
//修改分类
export const spuUpdate = (params) => {
  return _axios.post('/api/updateSpu',params).then(res => res.data)
}
//修改分类状态
export const spuUpdateStatue = (params) => {
  return _axios.post('/api/updateSpuStatue',params).then(res => res.data)
}
//根据分类得到品牌
export const getBrandByCategoryCode = (params) => {
  return _axios.get('/api/getBrandByCategoryCode',{
    params:{
      'categoryCode':params
    }
  }).then(res => res.data)
}
//添加spu
export const spuAdd = (params) => {
  return _axios.post('/api/addProductSpu',params).then(res => res.data)
}
//--------------------------SKU--------------------------------------------------
//添加value
export const skuValueAdd = (params) => {
  return _axios.post('/api/addSpecsValue',params).then(res => res.data)
}
//添加sku键
export const skuKeyAdd = (params) => {
  return _axios.post('/api/addSpecsKey',params).then(res => res.data)
}
//得到sku键
export const getSkuKey = (params) => {
  return _axios.get('/api/getSpecsKey',{
    params:{
      'spuCode':params
    }
  }).then(res => res.data)
}
//更新skuKey
export const updateSkuKey = (params) => {
  return _axios.post('/api/updateSpecsKey',params).then(res => res.data)
}
export const updateSkuValue = (params) => {
  return _axios.post('/api/updateSpecsValue',params).then(res => res.data)
}
//得到sku值
export const getSkuValue = (params) => {
  return _axios.get('/api/getSpecsValue',{
    params:{
      'keyId':params
    }
  }).then(res => res.data)
}
//得到sku
export const getSku = (params) => {
  return _axios.get('/api/getSkuBySpuCode',{
    params:{
      'spuCode':params.spuCode,
      'pageNumber':params.pageNumber,
      'pageSize':params.pageSize
    }
  }).then(res => res.data)
}
//分页
export const getSkuByPage = (params) => {
  return _axios.get('/api/getSkuBySpuCode',{
    params:{
      'spuCode':params.spuCode,
      'pageNumber':params.pageNumber,
      'pageSize':params.pageSize
    }
  }).then(res => res.data)
}
//修改sku状态
export const skuUpdateStatue = (params) => {
  return _axios.post('/api/updateSkuStatue',params).then(res => res.data)
}
//一键创建sku
export const createSku = (params) => {
  return _axios.get('/api/createSku',{
    params:{
      'spuCode':params
    }
  }).then(res => res.data)
}
//修改sku
export const skuUpdate = (params) => {
  return _axios.post('/api/updateSku',params).then(res => res.data)
}
export const getSpuImage = (params) => {
  return _axios.get('/api/getSpuImage',{
    params:{
      'spuCode':params
    }
  }).then(res => res.data)
}
export const getSpuDetail = (params) => {
  return _axios.get('/api/getSpuImageDetail',{
    params:{
      'spuCode':params
    }
  }).then(res => res.data)
}
//得到Sku图片
export const getSkuImage = (params) => {
  return _axios.get('/api/getSkuImage',{
    params:{
      'skuCode':params
    }
  }).then(res => res.data)
}
//得到分类图片
export const getCategoryImage = (params) => {
  return _axios.get('/api/getCategoryImage',{
    params:{
      'categoryCode':params
    }
  }).then(res => res.data)
}
//得到全部分类树
export const getAllCategoryTree = () => {
  return _axios.post('/api/getAllCategoryTree').then(res => res.data)
}
//得到分类编号List
export const getSkuCodeListBySpuCode = (params) => {
  return _axios.get('/api/getSkuCodeListBySpuCode',{
    params:{
      'spuCode':params

    }
  }).then(res => res.data)
}
//添加库存
export const addStock = (params) => {
  return _axios.post('/api/addStock',params).then(res => res.data)
}
//--------------------------搜索商品--------------------------------------------------
//搜索商品
//得到分类编号List
export const getProductByKeyWord = (params) => {
  return _axios.post('/api/getProductByKeyWord?'+ params).then(res => res.data)
}
export const deleteSpuImage = (params) => {
  return _axios.get('api/deleteSpuImage',{
    params:{
      'imageUrl':params
    }
  }).then(res => res.data)
}
export const getCategoryIconImage = (params) => {
  return _axios.get('api/getIconImage',{
    params:{
      'categoryCode':params
    }
  }).then(res => res.data)
}
