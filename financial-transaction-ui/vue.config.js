module.exports = {
    // 基本路径
    publicPath: './',
    // 输出文件目录
    outputDir: 'dist',
    // 静态资源目录
    assetsDir: 'assets',
    // 生产环境是否生成 source map
    productionSourceMap: false,
    // 开发服务器配置
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8080', // 后端服务的地址
                changeOrigin: true
            },
        },
    },
};