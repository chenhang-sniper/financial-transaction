<template>
  <div class="statistics">
    <h1>报表统计</h1>
    <el-date-picker
        v-model="searchDate"
        type="date"
        placeholder="选择日期"
        @change="fetchStatistics"
    ></el-date-picker>
    <div class="chart-container">
      <StatsChart :stats-data="statsData" />
    </div>
    <el-table :data="statsTableData" style="width: 100%">
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="value" label="值"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import StatsChart from '../components/StatsChart.vue'

export default {
  components: {
    StatsChart,
  },
  data() {
    return {
      searchDate: '',
      statsData: [],
      statsTableData: [
        { name: '今日交易额', value: '0.00' },
        { name: '今日交易笔数', value: '0' },
        { name: '账户总余额', value: '0.00' },
        { name: '总收入', value: '0.00' },
        { name: '总支出', value: '0.00' },
      ],
    }
  },
  computed: {
    ...mapState('statistics', ['statistics']),
  },
  methods: {
    ...mapActions('statistics', ['fetchStatistics']),
    updateStatsTableData() {
      this.statsTableData = [
        { name: '今日交易额', value: this.statistics.dailyAmount.toFixed(2) },
        { name: '今日交易笔数', value: this.statistics.dailyCount.toString() },
        { name: '账户总余额', value: this.statistics.totalBalance.toFixed(2) },
        { name: '总收入', value: this.statistics.totalIncome.toFixed(2) },
        { name: '总支出', value: this.statistics.totalExpense.toFixed(2) },
      ]
      this.statsData = this.statistics.chartData
    },
  },
  watch: {
    statistics: {
      handler() {
        this.updateStatsTableData()
      },
      deep: true,
    },
  },
  created() {
    this.fetchStatistics(this.searchDate)
  },
}
</script>

<style scoped>
.statistics {
  padding: 20px;
}
.chart-container {
  margin: 20px 0;
  height: 400px;
}
</style>