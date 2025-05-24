<template>
  <div id="app">
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="transactions">交易管理</el-menu-item>
      <el-menu-item index="statistics">交易统计</el-menu-item>
    </el-menu>
    <router-view />
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: 'transactions', // 默认选中 Transactions 菜单
    };
  },
  methods: {
    handleSelect(index) {
      // 根据菜单项的 index 跳转到对应的路由
      this.$router.push({ name: index });
    },
  },
};
</script>

<style scoped>
#app {
  padding: 10px;
}
</style>