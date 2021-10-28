const head = require('./config/head.js');
const plugins = require('./config/plugins.js');
const themeConfig = require('./config/themeConfig.js');

module.exports = {
  // theme: 'vdoing', // 使用依赖包主题
  theme: require.resolve('../../theme-vdoing'), // 使用本地主题
  title: "接口协同",
  description: '基于RepChain区块链的接口协同组件',
  base: '/api-coord/', // 格式：'/<仓库名>/'， 默认'/'
  markdown: {
    lineNumbers: true, // 代码行号
  },
  head,
  plugins,
  themeConfig,
}
