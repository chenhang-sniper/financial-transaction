import Vue from 'vue'
import Vuex from 'vuex'
import transactions from './transactions'
import statistics from './statistics'

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        transactions,
        statistics,
    },
})