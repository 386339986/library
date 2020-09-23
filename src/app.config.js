export default {
  pages: [
    'pages/index/index',
    'pages/register/register',
    'pages/select/select',
    'pages/rule/rule',
    'pages/center/center'
  ],
  window: {
    backgroundTextStyle: 'light',
    navigationBarBackgroundColor: '#fff',
    navigationBarTitleText: 'WeChat',
    navigationBarTextStyle: 'black'
  },
  tabBar: {
    color: 'rgba(68, 68, 68, 1)',
    selectedColor: 'rgba(68, 68, 68, 1)',
    backgroundColor: 'white',
    list: [
      {
        pagePath: 'pages/select/select',
        text: '首页',
        iconPath: './assets/img/ic_home.png',
        selectedIconPath: './assets/img/ic_home_pressed.png'
      },
      {
        pagePath: 'pages/rule/rule',
        text: '使用规则',
        iconPath: './assets/img/ic_rule.png',
        selectedIconPath: './assets/img/ic_rule_pressed.png'
      },
      {
        pagePath: 'pages/center/center',
        text: '个人中心',
        iconPath: './assets/img/ic_user.png',
        selectedIconPath: './assets/img/ic_user_pressed.png'
      }]
  }
}
