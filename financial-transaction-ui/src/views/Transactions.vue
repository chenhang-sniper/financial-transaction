<template>
  <div class="transactions">
    <!-- 搜索功能 -->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="账户ID">
        <el-input v-model="searchParams.accountId" placeholder="输入账户ID"></el-input>
      </el-form-item>
      <el-form-item label="交易类型">
        <el-select v-model="searchParams.transactionType" placeholder="选择交易类型">
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
        <el-select v-model="searchParams.transactionMethod" placeholder="选择交易方式">
          <el-option label="买入" value="BUY"></el-option>
          <el-option label="卖出" value="SELL"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="交易状态">
        <el-select v-model="searchParams.transactionStatus" placeholder="选择交易状态">
          <el-option label="交易中" value="PENDING"></el-option>
          <el-option label="已成功" value="SUCCESS"></el-option>
          <el-option label="已撤销" value="CANCELLED"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="交易开始日期">
        <el-date-picker
            v-model="searchParams.startTime"
            type="date"
            placeholder="选择开始日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="交易结束日期">
        <el-date-picker
            v-model="searchParams.endTime"
            type="date"
            placeholder="选择结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">搜索</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作按钮 -->
    <el-button type="primary" @click="openAddTransactionDialog">新增交易</el-button>

    <!-- 交易列表 -->
    <el-table :data="transactions" style="width: 100%">
      <el-table-column prop="id" label="交易流水ID" width="100"></el-table-column>
      <el-table-column prop="accountId" label="账户ID" width="120"></el-table-column>
      <el-table-column label="交易类型" width="120">
        <template slot-scope="scope">
          {{ getTypeDesc(scope.row.transactionType) }}
        </template>
      </el-table-column>
      <el-table-column label="交易方式" width="120">
        <template slot-scope="scope">
          {{ getMethodDesc(scope.row.transactionMethod) }}
        </template>
      </el-table-column>
      <el-table-column prop="amount" label="交易金额" width="120"></el-table-column>
      <el-table-column prop="transactionTime" label="交易时间" width="180"></el-table-column>
      <el-table-column label="交易状态" width="120">
        <template slot-scope="scope">
          {{ getStatusDesc(scope.row.transactionStatus) }}
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="180"></el-table-column>
      <el-table-column prop="updateTime" label="更新时间" width="180"></el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button @click="openEditTransactionDialog(scope.row)">编辑</el-button>
          <el-button type="danger"  @click="deleteConfirm(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
        @current-change="handlePageChange"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="totalTransactions"
        layout="prev, pager, next"
    ></el-pagination>

    <!-- 交易信息对话框 -->
    <el-dialog title="交易信息" :visible.sync="dialogVisible" width="500px">
      <TransactionForm :transaction="selectedTransaction" @submit="handleFormSubmit"></TransactionForm>
    </el-dialog>
  </div>
</template>

<script>
import TransactionForm from '../components/TransactionForm.vue';
import actions from '../store/transactions'; // 引入actions模块

export default {
  components: {
    TransactionForm,
  },
  data() {
    return {
      searchParams: {
        accountId: null,
        transactionType: null,
        transactionMethod: null,
        transactionStatus: null,
        startTime: null,
        endTime: null,
      },
      currentPage: 1,
      pageSize: 10,
      dialogVisible: false,
      selectedTransaction: null,
      transactions: [], // 交易记录数据
      totalTransactions: 0, // 总记录数
    };
  },
  methods: {
    handlePageChange(page) {
      this.currentPage = page;
      this.search();
    },
    openAddTransactionDialog() {
      this.selectedTransaction = {};
      this.dialogVisible = true;
    },
    openEditTransactionDialog(transaction) {
      this.selectedTransaction = {...transaction};
      this.dialogVisible = true;
    },
    handleFormSubmit(transaction) {
      this.dialogVisible = false;
      if (transaction.id) {
        // 更新交易
        actions.updateTransaction(transaction)
            .then((response) => {
              if (response.data.code === 0) {
                this.$message({
                  type: 'info',
                  message: '保存成功'
                });
                this.search();
              } else {
                this.$message({
                  type: 'error',
                  message: 'Failed to update transactions:'  + response.data.msg
                });
              }
            })
            .catch((error) => {
              this.$message({
                type: 'error',
                message: 'Failed to update transactions:' + error
              });
            });
      } else {
        // 新增交易
        actions.saveTransaction(transaction)
            .then((response) => {
              if (response.data.code === 0) {
                this.$message({
                  type: 'info',
                  message: '保存成功'
                });
                this.search();
              } else {
                this.$message({
                  type: 'error',
                  message: 'Failed to save transactions:'  + response.data.msg
                });
              }
            })
            .catch((error) => {
              this.$message({
                type: 'error',
                message: 'Failed to save transactions:' + error
              });
            });
      }
    },
    search() {
      const params = {
        pageNo: this.currentPage,
        pageSize: this.pageSize,
        ...this.searchParams,
      };
      actions.fetchTransactions(params)
          .then((response) => {
            if (response.data.code === 0) {
              this.transactions = response.data.data.totalList || [];
              this.totalTransactions = response.data.data.total || 0;
            } else {
              this.$message({
                type: 'error',
                message: 'Failed to fetch transactions:' + response.data.msg
              });
            }
          })
          .catch((error) => {
            this.$message({
              type: 'error',
              message: 'Failed to fetch transactions:' + error
            });
          });
    },
    deleteConfirm(id){
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
          this.deleteTransaction(id);
      });
    },
    deleteTransaction(id) {
      actions.deleteTransaction(id)
          .then((response) => {
            if (response.data.code === 0) {
              this.$message({
                type: 'info',
                message: '删除成功'
              });
              this.search();
            } else {
              this.$message({
                type: 'error',
                message: 'Failed to delete transactions:' + response.data.msg
              });
            }
          })
          .catch((error) => {
            this.$message({
              type: 'error',
              message: 'Failed to delete transactions:' + error
            });
          });
    },
    getTypeDesc(code) {
      const typeMap = {
        STOCK: '股票',
        BOND: '债券',
        FUND: '基金',
        FUTURES: '期货',
        OPTIONS: '期权',
        FOREX: '外汇',
        OTHERS: '其他',
      };
      return typeMap[code] || '未知';
    },
    getMethodDesc(code) {
      const methodMap = {
        BUY: '买入',
        SELL: '卖出',
      };
      return methodMap[code] || '未知';
    },
    getStatusDesc(code) {
      const statusMap = {
        PENDING: '交易中',
        SUCCESS: '已成功',
        CANCELLED: '已撤销',
      };
      return statusMap[code] || '未知';
    },
  },
  created() {
    this.search();
  },
};
</script>

<style scoped>
.transactions {
  padding: 20px;
}

.demo-form-inline {
  margin-bottom: 20px;
}
</style>