import Vue from 'vue'
import VueRouter from 'vue-router'
import Transactions from '../views/Transactions.vue'
import Statistics from '../views/Statistics.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/transactions',
    },
    {
        path: '/transactions',
        name: 'transactions',
        component: Transactions,
    },
    {
        path: '/statistics',
        name: 'statistics',
        component: Statistics,
    },
    {
        path: '*',
        name: 'NotFound',
        component: () => import('../views/NotFound.vue'),
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
})

export default router