<template>
  <div ref="chart" class="chart"></div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  props: {
    statsData: {
      type: Array,
      default: () => [],
    },
  },
  watch: {
    statsData: {
      handler() {
        this.initChart()
      },
      deep: true,
    },
  },
  mounted() {
    this.initChart()
  },
  methods: {
    initChart() {
      const chartDom = this.$refs.chart
      const chart = echarts.init(chartDom)
      const option = {
        title: {
          text: '交易统计',
        },
        tooltip: {
          trigger: 'axis',
        },
        xAxis: {
          type: 'category',
          data: this.statsData.map(item => item.name),
        },
        yAxis: {
          type: 'value',
        },
        series: [
          {
            data: this.statsData.map(item => item.value),
            type: 'bar',
          },
        ],
      }
      chart.setOption(option)
    },
  },
}
</script>

<style scoped>
.chart {
  width: 100%;
  height: 400px;
}
</style>