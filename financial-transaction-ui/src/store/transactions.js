import axios from 'axios';
import api from '../services/api';

const actions = {
    fetchTransactions({
                          pageNo = 1,
                          pageSize = 10,
                          accountId,
                          transactionType,
                          transactionMethod,
                          transactionStatus,
                          startTime,
                          endTime,
                      }) {
        const searchParams = {
            pageNo,
            pageSize,
            accountId,
            transactionType,
            transactionMethod,
            transactionStatus,
            startTime,
            endTime,
        };
        return api.fetch('/api/v1/transactions/list', searchParams, 'post');
    },
    deleteTransaction(id) {
        return api.fetch(`/api/v1/transactions/${id}`,{}, 'delete');
    },
    saveTransaction(transaction) {
        // 如果没有提供交易时间，默认使用当前时间
        if (!transaction.transactionTime) {
            transaction.transactionTime = new Date();
        }
        // 调用后端API保存交易记录
        return api.fetch('/api/v1/transactions', transaction, 'post');
    },
    updateTransaction(transaction) {
        // 调用后端API更新交易记录
        return api.fetch(`/api/v1/transactions/${transaction.id}`, transaction, 'put');
    },
};

export default actions;