module.exports = {
  publicPath: process.env.NODE_ENV === "production" ? "./" : "/",
  outputDir: "dist",
  lintOnSave: true,
  runtimeCompiler: true, //关键点在这
  chainWebpack: () => {},
  configureWebpack: () => {},
  devServer: {
    host: "0.0.0.0",
    port: 8085,
    https: false,
    hotOnly: false,
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          "^/api": "",
        },
      },
    },
    before: (app) => {},
  },
};
