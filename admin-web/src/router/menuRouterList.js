let menuRouterList
menuRouterList = [
  {
    path: '/statistics',
    name: 'statistics',
    component: () => import(/* webpackChunkName: "about" */ '../views/Layout.vue'),
    meta: {
      title: '月数据分析',
      authNo: ''
    },
    children: [
      {
        path: 'userStatistics',
        name: 'userStatistics',
        component: () => import(/* webpackChunkName: "about" */ '../views/UserStatistics.vue'),
        meta: {
          title: '用户统计'
        }
      },
      {
        path: 'orderStatistics',
        name: 'orderStatistics',
        component: () => import(/* webpackChunkName: "about" */ '../components/Statistics/OrderStatistics.vue'),
        meta: {
          title: '订单月销量'
        }
      },
      {
        path: 'spuStatistics',
        name: 'spuStatistics',
        component: () => import(/* webpackChunkName: "about" */ '../components/Statistics/SpuStatistics.vue'),
        meta: {
          title: '商品月销量'
        }
      }
    ]
  },
  {
    path: '/admin',
    name: 'admin',
    component: () => import(/* webpackChunkName: "about" */ '../views/Layout.vue'),
    meta: {
      title: '员工管理',
      authNo: ''
    },
    children: [
      {
        path: 'couponHistory',
        name: 'couponHistory',
        component: () => import(/* webpackChunkName: "about" */ '../components/admin/Employee.vue'),
        meta: {
          title: '员工列表'
        },
        hidden: true
      },
      {
        path: 'role',
        name: 'role',
        component: () => import(/* webpackChunkName: "about" */ '../components/admin/Role.vue'),
        meta: {
          title: '角色菜单'
        },
        hidden: true
      },
    ]
  },
  {
    path: '/commodity',
    name: 'commodity',
    component: () => import(/* webpackChunkName: "about" */ '../views/Layout.vue'),
    meta: {
      title: '商品管理',
      authNo: ''
    },
    children: [
      {
        path: 'category',
        name: 'productCategory',
        component: () => import(/* webpackChunkName: "about" */ '../components/commodity/product.vue'),
        meta: {
          title: '商品管理'
        }
      },
    ]
  },
  {
    path: '/order',
    name: 'order',
    component: () => import(/* webpackChunkName: "about" */ '../views/Layout.vue'),
    meta: {
      title: '订单管理',
      authNo: ''
    },
    children: [
      {
        path: 'orderList',
        name: 'orderList',
        component: () => import(/* webpackChunkName: "about" */ '../views/Order.vue'),
        meta: {
          title: '进货订单'
        }
      },
      {
        path: 'pointsOrder',
        name: 'pointsOrder',
        component: () => import(/* webpackChunkName: "about" */ '../components/order/PointsOrder.vue'),
        meta: {
          title: '售后订单'
        }
      }
    ]
  },
  {
    path: '/stock',
    name: 'stock',
    component: () => import(/* webpackChunkName: "about" */ '../views/Layout.vue'),
    meta: {
      title: '库存管理',
      authNo: ''
    },
    children: [
      {
        path: 'list',
        name: 'stockList',
        component: () => import(/* webpackChunkName: "about" */ '../views/Stock.vue'),
        meta: {
          title: '库存列表'
        }
      },
      {
        path: 'flow',
        name: 'flow',
        component: () => import(/* webpackChunkName: "about" */ '../components/stock/Flow.vue'),
        meta: {
          title: '库存流水'
        }
      },
      {
        path: 'plan',
        name: 'plan',
        component: () => import(/* webpackChunkName: "about" */ '../components/stock/Plan.vue'),
        meta: {
          title: '进货方案'
        }
      },
      {
        path: 'planDetail',
        name: 'planDetail',
        component: () => import(/* webpackChunkName: "about" */ '../components/stock/PlanDetail.vue'),
        meta: {
          title: '进货详情'
        }
      }
    ]
  }
]

export default menuRouterList
