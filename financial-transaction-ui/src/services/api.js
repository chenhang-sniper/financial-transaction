import axios from 'axios'

//
// // 什么一个数组用于存储每个请求的取消函数和标识
// let pending = [];
// let cancelConfig = null;
// let CancelToken = axios.CancelToken;
// let removePending = (config) => {
//     for (let p = 0; p < pending.length; p++) {
//         const params = JSON.stringify(config.params);
//         // 如果存在则执行取消操作
//         if (pending[p].u === config.url + '&' + config.method + '&' + params) {
//             // pending[p].f();// 执行取消操作
//             pending.splice(p, 1);// 移除记录
//         }
//     }
// };
//
// let cutReq = (config) => {
//     for (let p = 0; p < pending.length; p++) {
//         const params = JSON.stringify(config.params);
//         if (pending[p].u === config.url + '&' + config.method + '&' + params) {
//             return true;
//         }
//     }
// };

const instance = axios.create({
    baseURL: ``,
    timeout: 600000,
    withCredentials: true,
    headers: { 'Content-Type': 'application/json;charset=UTF-8' },
});
//
// instance.interceptors.request.use((config) => {
//     // 增加国际化参数
//     config.headers['Content-language'] = 'zh-CN';
//     let flag = cutReq(config);
//     // 当上一次相同请求未完成时，无法进行第二次相同请求
//     if (flag === true) {
//         removePending(config);
//         return config;
//     } else {
//         const params = JSON.stringify(config.params);
//         // 用于正常请求出现错误时移除
//         cancelConfig = config;
//         config.cancelToken = new CancelToken((c) => {
//             // 添加标识和取消函数
//             pending.push({
//                 u: config.url + '&' + config.method + '&' + params,
//                 f: c,
//             });
//         });
//         return config;
//     }
// }, (error) => {
//     Promise.reject(error);
// });
//
// instance.interceptors.response.use((response) => {
//     response.config.metadata.endTime = Date.now()
//     const duration = response.config.metadata.endTime - response.config.metadata.startTime
//     if (window.$Wa && duration > 2000) window.$Wa.log(`接口耗时 ${duration}: ${response.config.url}`);
//     // 在一个ajax响应成功后再执行取消操作，把已完成的请求从pending中移除
//     removePending(response.config);
//     return response;
// }, (error) => {
//     error.config.metadata.endTime = Date.now()
//     const duration = error.config.metadata.endTime - error.config.metadata.sartTime
//     if (window.$Wa && duration > 2000) window.$Wa.log(`接口耗时 ${duration}: ${error.config.url}`);
//     // 出现接口异常或者超时时的判断
//     if ((error.message && error.message.indexOf('timeout') >= 0) || (error.request && error.request.status !== 200)) {
//         for (let p in pending) {
//             if (pending[p].u === cancelConfig.url + '&' + cancelConfig.method + '&' + JSON.stringify(cancelConfig.params)) {
//                 pending.splice(p, 1);// 移除记录
//             }
//         }
//         // 优先返回后台返回的错误信息，其次是接口返回
//         return error.response || error;
//     } else if (axios.Cancel) {
//         // 如果是pengding状态，弹出提示！
//         return {
//             // data: { message: '接口请求中！请稍后……' },
//         };
//     } else {
//         return error;
//     }
// });
// let showLoginTips = false
const api = {
    instance: instance,
    error: {
        '-1': function (res) {
            console.log(res);
            alert("请稍后重试！");
        },
    },
    constructionOfResponse: {
        codePath: 'status',
        successCode: '0',
        messagePath: 'message',
        resultPath: 'data',
    },
    fetch(endpoint, data, method = 'get') {
        return axios({
            url: endpoint,
            method: method,
            data: method.toLowerCase() === 'post' || method.toLowerCase() === 'put' ? data : null,
            params: method.toLowerCase() === 'get' || method.toLowerCase() === 'delete' ? data : null,
        });
    },
};

export default api;
