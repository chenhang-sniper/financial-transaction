import axios from 'axios'

export default {
    // namespaced: true,
    // state: {
    //     statistics: {
    //         dailyAmount: 0,
    //         dailyCount: 0,
    //         totalBalance: 0,
    //         totalIncome: 0,
    //         totalExpense: 0,
    //         chartData: [],
    //     },
    // },
    // mutations: {
    //     SET_STATISTICS(state, stats) {
    //         state.statistics = stats
    //     },
    // },
    // actions: {
    //     async fetchStatistics({ commit, state }, date = null) {
    //         try {
    //             const query = date ? `?date=${date}` : ''
    //             const response = await axios.get(`/api/statistics${query}`)
    //             commit('SET_STATISTICS', response.data)
    //         } catch (error) {
    //             console.error('Failed to fetch statistics:', error)
    //         }
    //     },
    // },
    // getters: {
    //     getStatistics: (state) => state.statistics,
    // },
}