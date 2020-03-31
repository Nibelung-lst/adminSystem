import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'
import Layout from '../views/Layout'

Vue.use(VueRouter)

const routes = [
  {
    path: '/layout',
    name: 'layout',
    component: Layout,
    children: [
      {
        path: 'children',
        name: 'children',
        component: () => import(/* webpackChunkName: "about" */ '../components/product/CategoryChildren.vue'),
        meta: {
          title: '子节点管理'
        }
      },
      {
        path: 'Sku',
        name: 'Sku',
        component: () => import(/* webpackChunkName: "about" */ '../components/product/ProductSku.vue'),
        meta: {
          title: 'Sku管理'
        }
      },
      {
        path: 'Spu',
        name: 'Spu',
        component: () => import(/* webpackChunkName: "about" */ '../components/product/ProductSpu.vue'),
        meta: {
          title: 'Spu管理'
        }
      },
      {
        path: 'image',
        name: 'productImage',
        component: () => import(/* webpackChunkName: "about" */ '../components/product/ProductImage.vue'),
        meta: {
          title: '商品图片管理',
          hidden:true
        }
      },
      {
        path: 'orderDetail',
        name: 'orderDetail',
        component: () => import(/* webpackChunkName: "about" */ '../components/order/OrderDetail.vue'),
        meta: {
          title: '订单详情',
          hidden:true
        }
      }
    ],
    redirect: '/statistics/userStatistics'
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/',
    name: 'login',
    component: Login
  }
]

const createRouter = () => new VueRouter({
  mode: 'history',
  routes: routes
})

const router = createRouter()
// 更新路由配置
export function resetRouter () {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher
}

export default router
