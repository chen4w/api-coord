// nav
module.exports = [
    {text: '首页', link: '/'},
    {
        text: '开始',
        link: '/guide/',
        items: [
            {
                text: '引言',
                link: '/pages/e84ef5/',
            },
            {
                text: '准备工作',
                link: '/pages/d07be6/',
            },
            {
                text: '代码结构',
                link: '/pages/5536f8/',
            },
        ]
    },
    {
        text: '接口声明',
        link: '/deployer/', //目录页链接，此处link是vdoing主题新增的配置项，有二级导航时，可以点击一级导航跳到目录页
        items: [
            // 说明：以下所有link的值只是在相应md文件定义的永久链接（不是什么特殊生成的编码）。另外，注意结尾是有斜杠的
            {
                text: '服务定义',
                link: '/pages/a190e6/',
            }, {
                text: '服务登记',
                link: '/pages/99c155/',
            }, {
                text: '发布公告',
                link: '/pages/5c0128/',
            },
        ],
    },
    {
        text: '接口存证',
        link: '/java/', //目录页链接，此处link是vdoing主题新增的配置项，有二级导航时，可以点击一级导航跳到目录页
        items: [
            // 说明：以下所有link的值只是在相应md文件定义的永久链接（不是什么特殊生成的编码）。另外，注意结尾是有斜杠的
            {
                text: '同步请求',
                link: '/pages/b017cf/',
            }, {
                text: '异步请求',
                link: '/pages/ca1baf/',
            }, {
                text: '查看存证',
                link: '/pages/ca1baf/',
            }
        ],
    },
    {
        text: '代码示例',
        link: 'https://gitee.com/BTAJL/api-coord/tree/http-lhc/'
    }
]
