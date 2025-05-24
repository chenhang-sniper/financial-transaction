<template>
  <div class="stats-table">
    <el-table :data="statsTableData" style="width: 100%">
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="value" label="值"></el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      statsTableData: [
        { name: '今日交易额', value: '0.00' },
        { name: '今日交易笔数', value: '0' },
        { name: '账户总余额', value: '0.00' },
        { name: '总收入', value: '0.00' },
        { name: '总支出', value: '0.00' },
      ],
    };
  },
  props: {
    statsData: {
      type: Object,
      default: () => {},
    },
  },
  watch: {
    statsData: {
      handler() {
        this.updateStatsTableData();
      },
      deep: true,
    },
  },
  methods: {
    updateStatsTableData() {
      this.statsTableData = [
        { name: '今日交易额', value: this.statsData.dailyAmount.toFixed(2) },
        { name: '今日交易笔数', value: this.statsData.dailyCount.toString() },
        { name: '账户总余额', value: this.statsData.totalBalance.toFixed(2) },
        { name: '总收入', value: this.statsData.totalIncome.toFixed(2) },
        { name: '总支出', value: this.statsData.totalExpense.toFixed(2) },
      ];
    },
  },
};
</script>

<style scoped>
.stats-table {
  padding: 20px;
}
</style>