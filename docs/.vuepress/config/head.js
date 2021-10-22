// head
module.exports = [
  // 注入到页面<head> 中的标签，格式[tagName, { attrName: attrValue }, innerHTML?]
  ['link', { rel: 'icon', href: 'https://portrait.gitee.com/uploads/avatars/namespace/673/2020111_BTAJL_1578991426.png' }], //favicons，资源放在public文件夹
  [
    'meta',
    {
      name: 'keywords',
      content: '接口协同',
    },
  ],
  ['meta', { name: 'theme-color', content: '#42b983' }], // 移动浏览器主题颜色
]
