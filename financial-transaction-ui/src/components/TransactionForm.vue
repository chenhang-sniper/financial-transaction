<template>
  <div class="transaction-form">
    <el-form ref="form" :model="transaction" label-width="120px">
      <el-form-item label="账户ID">
        <el-input v-model="transaction.accountId"></el-input>
      </el-form-item>
      <el-form-item label="交易类型">
        <el-select v-model="transaction.transactionType">
          <el-option label="股票" value="STOCK"></el-option>
          <el-option label="债券" value="BOND"></el-option>
          <el-option label="基金" value="FUND"></el-option>
          <el-option label="期货" value="FUTURES"></el-option>
          <el-option label="期权" value="OPTIONS"></el-option>
          <el-option label="外汇" value="FOREX"></el-option>
          <el-option label="其他" value="OTHERS"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="交易方式">
        <el-select v-model="transaction.transactionMethod">
          <el-option label="买入" value="BUY"></el-option>
          <el-option label="卖出" value="SELL"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="交易金额">
        <el-input v-model.number="transaction.amount"></el-input>
      </el-form-item>
      <el-form-item label="交易状态">
        <el-select v-model="transaction.transactionStatus">
          <el-option label="交易中" value="PENDING"></el-option>
          <el-option label="已成功" value="SUCCESS"></el-option>
          <el-option label="已撤销" value="CANCELLED"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="交易时间">
        <el-date-picker
            v-model="transaction.transactionTime"
            type="datetime"
            placeholder="选择交易时间"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  props: {
    transaction: {
      type: Object,
      default: () => ({
        transactionTime: new Date(),
        id: null, // 默认没有 id，表示新增
      }),
    },
  },
  methods: {
    submitForm() {
      // 如果交易时间为null或未设置，则设置为当前时间
      if (!this.transaction.transactionTime) {
        this.transaction.transactionTime = new Date();
      }
      this.$emit('submit', {...this.transaction});
    },
  },
};
</script>

<style scoped>
.transaction-form {
  padding: 20px;
}
.el-input  {
  width: 300px;
}
.el-select  {
  width: 300px;
}
.el-date-picker {
  width: 300px;
}
</style>