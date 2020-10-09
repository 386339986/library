import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import { View, Text } from '@tarojs/components'
import { AtButton } from 'taro-ui'

import "taro-ui/dist/style/components/button.scss" // 按需引入
import './index1.less'

export default class Index extends Component {

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='index'>
        <Text>Hello world!</Text>
        <AtButton type='primary'>I need Taro UI</AtButton>
        <Text>首页</Text>
        <AtButton type='secondary' circle onClick={() => (Taro.switchTab({ url: '/pages/select/select' }))}>跳转</AtButton>
        <Text>注册页面</Text>
        <AtButton type='secondary' circle onClick={() => (Taro.navigateTo({ url: '/pages/register/register' }))}>点击跳转</AtButton>
      </View>
    )
  }
}
